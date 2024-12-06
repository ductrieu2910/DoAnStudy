package com.example.doanstudy.AdminController;

import com.example.doanstudy.model.entity.CourseEntity;
import com.example.doanstudy.model.entity.CoursePartEntity;
import com.example.doanstudy.service.CoursePartService;
import com.example.doanstudy.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/coursepart")
public class CoursePartController {
    private CoursePartService coursePartService;
    private CourseService courseService;

    @GetMapping("/pa")
    public String getCoursePartManager(@RequestParam(value = "page", required = false, defaultValue = "1")Integer pageNumber,
                                       @RequestParam(value = "s", required = false)String search, Model model){
        model.addAttribute("title","Quản lý học phần");
        Page<CoursePartEntity> page = coursePartService.findAll(search,pageNumber);
        List<CourseEntity> courseEntityList = courseService.findAll();
        model.addAttribute("courseEntityList",courseEntityList);
        model.addAttribute("newcoursepart",new CoursePartEntity());
        model.addAttribute("coursepart",page.getContent());
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("s",search);
        return "Admin/CoursePartManager";
    }

    @GetMapping("/edit/{id}")
    public String saveOrUpdate(@PathVariable("id")String id, Model model){
        try {
            List<CourseEntity> courseEntityList = courseService.findAll();
            model.addAttribute("courseEntityList",courseEntityList);
            model.addAttribute("newcoursepart",coursePartService.findById(id));
            return "/Admin/CoursePartAdmin/EditCoursePart";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());

        }
        return "redirect:/admin/course/pa";
    }


    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("coursepart") CoursePartEntity coursePartEntity,
                               Model model){
        try {
            // Lưu học phần
            coursePartService.SaveorUpdate(coursePartEntity);
            return "redirect:/admin/coursepart/pa";
        } catch (Exception e) {
            model.addAttribute("error", "Có lỗi xảy ra khi lưu học phần: " + e.getMessage());
            model.addAttribute("title", "Quản lý học phần");

            // Lấy lại danh sách khóa học và học phần để hiển thị lại trên trang
            List<CourseEntity> courseEntityList = courseService.findAll();
            model.addAttribute("courseEntityList", courseEntityList);

            return "Admin/CoursePartManager";
        }

    }

    // Xóa khóa học
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        CoursePartEntity coursePartEntity = coursePartService.findById(id);
        if (coursePartEntity == null) {
            return "redirect:/admin/coursepart/pa";
        }

        try {
            coursePartService.remove(coursePartEntity);
            return "redirect:/admin/coursepart/pa";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "Admin/CoursePartManager";
        }
    }
}
