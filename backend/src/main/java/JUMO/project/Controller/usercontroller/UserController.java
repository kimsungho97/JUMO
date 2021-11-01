package JUMO.project.Controller.usercontroller;

import JUMO.project.Controller.HoldingInvestmentDTO;
import JUMO.project.Controller.usercontroller.LoginResultDTO;
import JUMO.project.Controller.usercontroller.SignupResultDTO;
import JUMO.project.Entity.User;
import JUMO.project.Repository.UserRepository;
import JUMO.project.Service.HoldingService;
import JUMO.project.Service.OrderService;
import JUMO.project.Service.UserServiceImpl;
import JUMO.project.springsecurity.JwtTokenProvider;
import JUMO.project.springsecurity.LoginStatusManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    // jwt token 조회, 발급
    private final JwtTokenProvider jwtTokenProvider;
    // 로그인 상태 관리
    private final LoginStatusManager loginStatusManager;
    private final HoldingService holdingService;

    @PostMapping("/login")
    public LoginResultDTO login(@RequestBody Map<String, String> user) {

        Optional<User> findUser = userService.login(user.get("id"), user.get("password"));
        if (findUser.isPresent()){
            User member = findUser.get();
            String token = jwtTokenProvider.createJwtAuthToken(member.getId(), member).get();
            loginStatusManager.putLoginStatus(token);
            return new LoginResultDTO(
                true, member.getId(), member.getBalance(),
                null, token);
        }
        return new LoginResultDTO(false, null, null, "failed login", null);
    }

    @PostMapping("/logout")
    public Map<String, Object> logout(@RequestBody Map<String, String> model, HttpServletRequest request){
        Map<String, Object> resModel = new HashMap<>();

        String token = jwtTokenProvider.resolveToken(request);
        log.info("user Logout [{}]", model.get("id"));

        try {
            loginStatusManager.removeLoginStatus(token);
        } catch (Exception e){
            log.error(e.getMessage());
            resModel.put("result", false);
            return resModel;
        }
        resModel.put("result", true);
        return resModel;
    }
    // 회원가입
    @PostMapping("/signup")
    public SignupResultDTO join(@RequestBody Map<String, String> user) {

        Optional<User> newUser = userService.Signup(user.get("id"), user.get("password"));
        if (newUser.isEmpty()){
            return new SignupResultDTO(false, "id has been already used");
        }

        return new SignupResultDTO(true, null);
    }

    @GetMapping("/userinfo")
    public UserInfoDTO userInfo(HttpServletRequest request, HttpServletResponse response){
        String token = jwtTokenProvider.resolveToken(request);
        System.out.println(jwtTokenProvider.getUserUid(token));
        String userId = jwtTokenProvider.getUserId(token);

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            User foundUser = userOptional.get();
            return new UserInfoDTO(true, foundUser.getId(), foundUser.getBalance(), null);
        }
        else{
            log.error("can't find userInfo");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new UserInfoDTO(false, null, null, "can't find userInfo");
        }
    }

    /**
     * reset user's investment and money
     */
    @PostMapping("/resetuser")
    public void resetUser(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request);
        Long userUid = jwtTokenProvider.getUserUid(token);
        userService.reset(userUid);
    }

    @GetMapping("/totalasset")
    public TotalAssetDTO totalAsset(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request);
        Long userUid = jwtTokenProvider.getUserUid(token);
        String userId = jwtTokenProvider.getUserId(token);
        List<HoldingInvestmentDTO> investmentDTOList = holdingService.findUserAllHoldingInfo(userUid);
        long valuationLoss = 0L;
        long totalAsset =  0L;
        long totalPurchaseValue = 0L;
        for (HoldingInvestmentDTO holdingInvestmentDTO : investmentDTOList){
            valuationLoss += holdingInvestmentDTO.getValuationLoss().longValue();
            totalAsset += holdingInvestmentDTO.getCurrentPrice() * holdingInvestmentDTO.getRemainingAmount();
            totalPurchaseValue += holdingInvestmentDTO.getAveragePurchasePrice() * holdingInvestmentDTO.getRemainingAmount();
        }
        Double valuationLossRate = Double.parseDouble(Long.toString(valuationLoss)) / Double.parseDouble(Long.toString(totalPurchaseValue)) * 100;

        return new TotalAssetDTO(userId, valuationLoss, valuationLossRate, totalAsset);
    }
}
