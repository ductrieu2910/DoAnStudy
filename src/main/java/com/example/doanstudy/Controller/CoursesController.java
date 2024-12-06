package com.example.doanstudy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Course")
public class CoursesController {
    @GetMapping
    public String index(){
        return "Pages/Courses/CoursesList";
    }

}
