package com.example.doanstudy.Controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/Cart")
public class CartController {

    @GetMapping("/T1")
    public String getCartT1Academy(){
        return "/Pages/Cart/Cart";
    }

    @GetMapping("/Checkout")
    public String getCheckoutT1Academy(){
        return "/Pages/OrderCompleted";
    }


}
