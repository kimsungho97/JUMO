package JUMO.project.Controller;

import JUMO.project.Entity.Price;
import JUMO.project.Entity.User;
import JUMO.project.Service.Price_Service;
import JUMO.project.Service.User_Service;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Getter
@Setter
class Price_Search{
    private String name;
    private String code;

    public Price_Search(String name, String code){
        this.name=name;
        this.code=code;
    }
}

//@RequiredArgsConstructor
@Controller
public class Price_Controller {
    private final Price_Service price_service;
    private final User_Service user_service;

    @Autowired
    public Price_Controller(Price_Service price_service, User_Service user_service){
        this.price_service=price_service;
        this.user_service=user_service;
    }

    @GetMapping("/price")
    public String price(Model model){
        ArrayList<Price> price= (ArrayList<Price>) price_service.findAll();
        User user=(User) user_service.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        //model.addAttribute("user",user);
        model.addAttribute("price",price);
        return "price";
    }

    @PostMapping("/search")
    public String price_search(Price_Search price_search, Model model){
        System.out.println("name: "+price_search.getName()+", code:"+price_search.getCode());
        if(price_search.getCode().length()==0 && price_search.getName().length()==0){
            ArrayList<Price> price= (ArrayList<Price>) price_service.findAll();
            model.addAttribute("price",price);
            return "price";
        }
        else if(price_search.getCode().length()==0){
            ArrayList<Price> price= (ArrayList<Price>) price_service.findByname(price_search.getName());
            model.addAttribute("price",price);
            return "price";
        }
        else if(price_search.getName().length()==0){
            ArrayList<Price> price= (ArrayList<Price>) price_service.findBycode(price_search.getCode());
            model.addAttribute("price",price);
            return "price";
        }
        return "price";
    }
}
