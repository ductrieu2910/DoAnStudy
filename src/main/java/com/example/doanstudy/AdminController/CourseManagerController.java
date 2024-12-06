package com.example.doanstudy.AdminController;

import com.example.doanstudy.model.entity.CategoriesEntity;
import com.example.doanstudy.model.entity.CourseEntity;
import com.example.doanstudy.service.CategoriesService;
import com.example.doanstudy.service.CloudinaryService;
import com.example.doanstudy.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/course")
public class CourseManagerController {
    private final CourseService courseService;
    private final CategoriesService categoriesService;
    private final CloudinaryService cloudinaryService;

    // Hiển thị trang CourseManager với danh sách khóa học
    @GetMapping("/co")
    public String getCourseManager(@RequestParam(value = "page", required = false, defaultValue = "1")Integer pageNumber,
                                   @RequestParam(value = "s", required = false)String search, Model model) {
        model.addAttribute("title" ,"Quản lý khóa học");
        Page<CourseEntity> page = courseService.findAll(search, pageNumber);
        model.addAttribute("courses", page.getContent());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("s", search);// Đối tượng để thêm mới khóa học
        return "Admin/CourseManager"; // View Thymeleaf cho quản lý khóa học
    }

    @GetMapping("/save1")
    public String saveOrUpdate(Model model){
        try {
            List<CategoriesEntity> categoriesListCourse = categoriesService.findAll();
            model.addAttribute("categoriesListCourse", categoriesListCourse);
            model.addAttribute("newcourses", new CourseEntity());
            return "/Admin/CourseAdmin/CreatCourse";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());

        }
        return "redirect:/admin/course/co";
    }

    @GetMapping("/edit/{id}")
    public String saveOrUpdate(@PathVariable("id")String id, Model model){
        try{
            List<CategoriesEntity> categoriesListCourse = categoriesService.findAll();
            model.addAttribute("categoriesListCourse", categoriesListCourse);
            model.addAttribute("newcourses", courseService.findById(id));
            return "/Admin/CourseAdmin/EditCourse";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/admin/course/co";
    }

    @PostMapping("/save")
    public String saveOrUpdate(
            @ModelAttribute("course") CourseEntity courseEntity,
            @RequestParam(value = "file", required = false) MultipartFile file,
            Model model) {
        try {
            if (courseEntity.getId() != null) {
                CourseEntity existingCourse = courseService.findById(courseEntity.getId());
                if (existingCourse != null) {
                    courseEntity.setArt(existingCourse.getArt());
                }
            }

            // Lưu khóa học trước khi upload ảnh
            courseService.saveOrUpdate(courseEntity);

            // Tải ảnh lên bất đồng bộ nếu có ảnh mới
            if (file != null && !file.isEmpty()) {
                cloudinaryService.uploadFileAsync(file).thenAccept(imageUrl -> {
                    if (imageUrl != null) {
                        courseEntity.setArt(imageUrl);
                        courseService.saveOrUpdate(courseEntity);
                    }
                });
            }

            return "redirect:/admin/course/co";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi lưu khóa học: " + e.getMessage());
        }
        return "Admin/CourseManager";
    }


    // Xóa khóa học
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        CourseEntity courseEntity = courseService.findById(id);
        if (courseEntity == null) {
            return "redirect:/admin/course/co";
        }

        try {
            courseService.remove(courseEntity);
            return "redirect:/admin/course/co";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "Admin/CourseManager";
        }
    }
}
