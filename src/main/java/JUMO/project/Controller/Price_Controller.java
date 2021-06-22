package JUMO.project.Controller;

import JUMO.project.Entity.Price;
import JUMO.project.Entity.User;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

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
        //User user=(User) user_service.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        //model.addAttribute("user",user);
        model.addAttribute("price",price);
        return "price";
    }

    @GetMapping("/chart")
    public String chart(@RequestParam String stock, ModelMap model){
        ArrayList<Price> prices= (ArrayList<Price>) price_service.findByname(stock);
        String time[]=new String[10];
        int data[][]= new int[10][5];
        int index=0;
        for(Price p: prices){
            time[index]=p.getTime().toString();
            data[index][0]= (int) p.getLow();
            data[index][1]= (int) p.getOpen();
            data[index][2]= (int) ((p.getOpen()+p.getClose())/2);
            data[index][3]= (int) p.getClose();
            data[index][4]= (int) p.getHigh();
            index++;
            if(index>=10) break;
        }
        model.addAttribute("category",time);
        model.addAttribute("prices",data);
        return "chart";
    }

    @PostMapping("/chart")
    public String chart_search(Price_Search price_search, ModelMap model){
        System.out.println("name: "+price_search.getName()+", code:"+price_search.getCode());
        if(price_search.getCode().length()==0 && price_search.getName().length()==0){
            return "chart";
        }
        if(price_search.getCode().length()==0){
            //Price price[]= price_service.findByname(price_search.getName()).toArray();
            //model.addAttribute("price",price);
            return "chart";
        }

        else if(price_search.getName().length()==0){
            Price price[]= (Price[])  price_service.findBycode(price_search.getCode()).toArray();
            model.addAttribute("price",price);
            return "chart";
        }
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
