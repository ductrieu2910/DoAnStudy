package com.example.doanstudy.AdminController;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class DashBoardController {

    @GetMapping("/dashboard")
    public String GetDashBoard(){
        return "Admin/Dashboard";
    }
}
