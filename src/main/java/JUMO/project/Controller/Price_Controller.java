package JUMO.project.Controller;

import JUMO.project.Entity.Price;
import JUMO.project.Entity.User;
import JUMO.project.Service.PredictService;
import JUMO.project.Service.Price_Service;
import JUMO.project.Service.User_Service;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.time.ZoneOffset.UTC;

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
@RequiredArgsConstructor
public class Price_Controller {
    private final Price_Service price_service;
    private final User_Service user_service;
    private final PredictService predictService;

//    @Autowired
//    public Price_Controller(Price_Service price_service, User_Service user_service){
//        this.price_service=price_service;
//        this.user_service=user_service;
//    }

    @GetMapping("/price")
    public String price(Model model){
        ArrayList<Price> price= (ArrayList<Price>) price_service.findAll();
        //User user=(User) user_service.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        //model.addAttribute("user",user);
        model.addAttribute("price",price);
        return "price";
    }

    @GetMapping("/chart")
    public String chart(@RequestParam String stock, ModelMap model){
        ArrayList<Price> prices= (ArrayList<Price>) price_service.findByname(stock);
        Collections.sort(prices);
        long data[][]= new long[prices.size()][8];
        int index=0;
        String stock_name=prices.get(0).getName();
        for(Price p: prices){
            data[index][0]= p.getTime().getYear();
            data[index][1]= p.getTime().getMonthValue();
            data[index][2]= p.getTime().getDayOfMonth();
            data[index][3]= p.getOpen();
            data[index][4]= p.getHigh();
            data[index][5]= p.getLow();
            data[index][6]= p.getClose();
            data[index][7]=p.getVolume();
            index++;
        }
        ArrayList<String> recommend = predictService.getRecommend(stock);
        System.out.println("recommendShort = " + recommend.get(1));
        System.out.println("recommendLong = " + recommend.get(0));
        model.addAttribute("stock_name", stock_name);
        model.addAttribute("stock_code",prices.get(0).getCode());
        model.addAttribute("prices",data);
        model.addAttribute("recommend_Long",recommend.get(0));
        model.addAttribute("recommend_Short",recommend.get(1));
        return "chart";
    }


    @GetMapping("/stock_list")
    public String stock_list(@RequestParam @Nullable String name, @RequestParam @Nullable String code, ModelMap model){
        HashMap<String, String> stock_name=null;

        if(name==null || (name.equals("") && code.equals(""))){
            stock_name= price_service.allStock_name("");
        }
        else if(name.equals("")){
            stock_name= price_service.allStock_code(code);
        }
        else if(code.equals("")){
            stock_name= price_service.allStock_name(name);
        }
        System.out.println("name: "+name+", code: "+code);

        model.addAttribute("stocks",stock_name);
        return "stock_list";
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
