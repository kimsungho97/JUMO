package JUMO.project.Controller;

import JUMO.project.Entity.Price;
import JUMO.project.Service.Price_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@RequiredArgsConstructor
@Controller
public class Price_Controller {
    private final Price_Service price_service;

    @GetMapping("/price")
    public String price(Model model){
        ArrayList<Price> price= (ArrayList<Price>) price_service.findAll();
        model.addAttribute("price",price);
        return "price";
    }
}
