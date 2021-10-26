package JUMO.project.Controller;

import JUMO.project.Service.HoldingService;
import JUMO.project.springsecurity.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class investingController {

    private final JwtTokenProvider jwtTokenProvider;
    private final HoldingService holdingService;

    @GetMapping("/myinvestment")
    public Map<String, Object> investInfo(HttpServletRequest request){
        Long uid = jwtTokenProvider.getUserUid(jwtTokenProvider.resolveToken(request));
        List<HoldingInvestmentDTO> userAllHoldingInfo = holdingService.findUserAllHoldingInfo(uid);

        Map<String, Object> model = new HashMap<>();
        model.put("data", userAllHoldingInfo);
        return model;
    }

}
