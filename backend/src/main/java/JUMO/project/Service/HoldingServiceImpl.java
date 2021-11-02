package JUMO.project.Service;

import JUMO.project.Controller.HoldingInvestmentDTO;
import JUMO.project.Entity.Holding;
import JUMO.project.Repository.HoldingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HoldingServiceImpl implements HoldingService{

    private final HoldingRepository holdingRepository;
    private final OrderService orderService;

    @Override
    public List<HoldingInvestmentDTO> findUserAllHoldingInfo(Long uid) {
        List<HoldingInvestmentDTO> investmentDTOList = new ArrayList<>();

        List<Holding> holdings = holdingRepository.findByUserId(uid);
        for (var holding : holdings){
            Long curPrice = orderService.getStockPrice(holding.getStockId());

            Long valuationLoss =  (curPrice - holding.getAveragePrice()) * holding.getCount();

            Double yield = ((Double.longBitsToDouble(curPrice))
                    / Double.longBitsToDouble(holding.getAveragePrice()) * 100) - 100;

            Long valuationAmount = holding.getAveragePrice() * holding.getCount();

            investmentDTOList.add(new HoldingInvestmentDTO(
                    holding.getStockName(),
                    valuationLoss,
                    yield,
                    holding.getCount(),
                    valuationAmount,
                    holding.getAveragePrice(),
                    curPrice
                    )
            );
        }

        return investmentDTOList;
    }
}
