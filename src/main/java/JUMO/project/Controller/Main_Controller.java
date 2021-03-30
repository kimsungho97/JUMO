package JUMO.project.Controller;

import JUMO.project.Service.User_Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Main_Controller {

    private final User_Service user_service;


    public Main_Controller(User_Service user_service) {
        this.user_service = user_service;
    }

    @GetMapping("/")
    public String main(){
        return "";
    }
}
