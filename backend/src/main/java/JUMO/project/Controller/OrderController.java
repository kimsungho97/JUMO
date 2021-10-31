package JUMO.project.Controller;

import JUMO.project.Entity.Order;
import JUMO.project.Repository.OrderRepository;
import JUMO.project.Service.OrderService;
import JUMO.project.exception.NoHoldingException;
import JUMO.project.exception.NotEnoughHoldingException;
import JUMO.project.exception.NotEnoughMoneyException;
import JUMO.project.springsecurity.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final JwtTokenProvider jwtTokenProvider;
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @PostMapping("/trade")
    public Map<String, Object> buyingOrder(@RequestBody Map<String, Object> model, HttpServletRequest request, HttpServletResponse response){
        Long userId = jwtTokenProvider.getUserUid(jwtTokenProvider.resolveToken(request));
        String type = (String) model.get("type");
        Map<String, Object> responseModel = new HashMap<>();

        try{
            String strCode = (String) model.get("stockCode");
            strCode = strCode.substring(0,strCode.length()-3);
            String stockName = (String) model.get("stockName");
            Integer amount = (Integer) model.get("amount");

            if (type.equals("buy")){
                log.info("buying request");
                orderService.buyingOrder(userId, strCode, stockName, amount);
            } else if (type.equals("sell")){
                log.info("selling request");
                orderService.sellingOrder(userId, strCode, stockName, amount);
            } else{
                throw new RuntimeException();
            }
            responseModel.put("result", true);
            responseModel.put("msg", "");
            return responseModel;
        } catch (NotEnoughMoneyException|NotEnoughHoldingException|NoHoldingException e){
            log.error(e.getMessage());
            responseModel.put("result", false);
            responseModel.put("msg", e.getMessage());
            return responseModel;
        } catch (ResourceAccessException e){
            log.error(e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseModel.put("result", false);
            responseModel.put("msg", "server Something wrong");
            return responseModel;
        }
        catch (Exception e){
            log.warn("wrong request msg");
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseModel.put("result", false);
            responseModel.put("msg", "wrong request msg");
            return responseModel;
        }
    }

    @GetMapping("/history")
    public Map<String, Object> orderHistory(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request);
        Long userUid = jwtTokenProvider.getUserUid(token);
        List<OrderDTO> orderDTOList = new ArrayList<>();

        List<Order> historyList = orderRepository.findAllHistoryByUid(userUid);

        for(Order order : historyList){
            orderDTOList.add(new OrderDTO(order.getOrderDate().toLocalDate(),
                    order.getStockName(),
                    order.getStockId()+".KS",
                    order.getCount(),
                    order.getTradeType().toString(),
                    order.getEachPrice()*order.getCount()));
        }
        Map<String, Object> resModel = new HashMap<>();
        resModel.put("data", orderDTOList);
        return resModel;
    }
}
