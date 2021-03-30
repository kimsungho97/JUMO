package JUMO.project.Controller;

import JUMO.project.Entity.User;
import JUMO.project.Service.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class Main_Controller {

    private final User_Service user_service;

    @Autowired
    public Main_Controller(User_Service user_service) {
        this.user_service = user_service;
    }


    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/login")
    public String login_pages(HttpSession httpSession){
        return "login";
    }

    @PostMapping("/login")
    public String login(User user){
        user_service.Signup(user);
        return "main";
    }

    @GetMapping("/signup")
    public String signup_page(HttpSession httpSession){
        return "main";
    }
}
