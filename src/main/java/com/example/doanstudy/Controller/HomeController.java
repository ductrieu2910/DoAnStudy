package com.example.doanstudy.Controller;

import com.example.doanstudy.Config.Constants;
import com.example.doanstudy.model.dto.CategoriesDTO;
import com.example.doanstudy.model.entity.CategoriesEntity;
import com.example.doanstudy.model.entity.CourseEntity;
import com.example.doanstudy.repository.CategoriesRepository;
import com.example.doanstudy.service.CategoriesService;
import com.example.doanstudy.service.CourseService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/Home")
public class HomeController {
    private final CourseService courseService;
    private final CategoriesService categoriesService;
    @GetMapping("/T1")
    public String GetHomeT1(Model model){
        Pageable pageable = PageRequest.of(0, 5);
        Page<CourseEntity> courseSale = courseService.findBy(null, Constants.Status.AVAILABLE, pageable);
        model.addAttribute("courseSale", courseSale.getContent());

        List<CategoriesEntity> parentCategories = categoriesService.findAllByParentIdIsNull();
        model.addAttribute("parentCategories", parentCategories);
        List<CategoriesDTO> categoriesDTOList = categoriesService.categoriesDTOList();
        model.addAttribute("categoriesDTOList",categoriesDTOList);
        return "Pages/Home";
    }

    @GetMapping("/category/{categoryIds}")
    public String GetCourseList(@PathVariable String categoryIds, Model model){
        // Lấy danh sách tên khóa học từ chuỗi categoryIds
        List<CourseEntity> courseNames = courseService.getCourseNamesByCategories(categoryIds);
        // Truyền dữ liệu vào model
        model.addAttribute("courseNames", courseNames);
        return "/Pages/Course/CoursesList";
    }

    @GetMapping("/Course-Details/{id}")
    public String coursedetail(@PathVariable("id") String id, Model model){
        CourseEntity course = courseService.findById(id);
        model.addAttribute("course",course);
        return "Pages/Course/CourseDetails";
    }

    @GetMapping("/loginT1")
    public  String GetLogin(Model model){
        List<CategoriesEntity> parentCategories = categoriesService.findAllByParentIdIsNull();
        model.addAttribute("parentCategories", parentCategories);
        List<CategoriesDTO> categoriesDTOList = categoriesService.categoriesDTOList();
        model.addAttribute("categoriesDTOList",categoriesDTOList);
        return "User/LoginUsers";
    }

    @PostMapping("/loginT1")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response, Model model) {
        if ("customer".equals(username) && "12345".equals(password)) {
            Cookie cookie = new Cookie(Constants.LOGIN_CUSTOMER, username);
            cookie.setMaxAge(60 * 5);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            response.addCookie(cookie);
            return "redirect:/Home/T1";
        }
        return "redirect:/Home/loginT1";
    }

    @GetMapping("/logoutT1")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (Constants.LOGIN_CUSTOMER.equals(c.getName())) {
                c.setMaxAge(0);
                response.addCookie(c);
                break;
            }
        }
        return "redirect:/Home/loginT1";
    }
}
