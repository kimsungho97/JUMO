package JUMO.project.Controller;

import JUMO.project.Entity.User;
import JUMO.project.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    private final UserServiceImpl user_service;

    @GetMapping(value="/")
    public String main(){
        return "index";
    }

    @GetMapping(value="/login")
    public String loginPages(){
        return "login";
    }

    @PostMapping(value="/signup")
    public String signup(User user){
        user_service.Signup(user);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signupPage(){
        return "signup";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

    @GetMapping("/userinfo")
    public String userInfo(Model model){
        User user=(User)user_service.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        DecimalFormat format = new DecimalFormat("###,###");
        String balance=format.format(user.getBalance());
        model.addAttribute("user",user);
        model.addAttribute("balance",balance);
        return "userinfo";
    }


}
