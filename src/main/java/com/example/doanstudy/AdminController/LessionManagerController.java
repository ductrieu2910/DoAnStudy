package com.example.doanstudy.AdminController;

import com.example.doanstudy.model.entity.CategoriesEntity;
import com.example.doanstudy.model.entity.CourseEntity;
import com.example.doanstudy.model.entity.CoursePartEntity;
import com.example.doanstudy.model.entity.LessonEntity;

import com.example.doanstudy.service.CloudinaryService;
import com.example.doanstudy.service.CoursePartService;
import com.example.doanstudy.service.CourseService;
import com.example.doanstudy.service.LessionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/lession")
public class LessionManagerController {
    private final LessionService lessionService;
    private final CoursePartService coursePartService;
    private final CloudinaryService cloudinaryService;
    private CourseService courseService;
    @GetMapping("/le")
    public String getCourseManager(@RequestParam(value = "page", required = false, defaultValue = "1")Integer pageNumber,
                                   @RequestParam(value = "s", required = false)String search, Model model) {
        model.addAttribute("title" ,"Quản lý bài học");
        Page<LessonEntity> page = lessionService.findAll(search, pageNumber);
        model.addAttribute("lession", page.getContent());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("s", search);
        return "Admin/LessionManager";
    }

    @GetMapping("/save1")
    public String saveOrUpdate(Model model){
        try {
            List<CourseEntity> courseEntityList = courseService.findAll();
            List<CoursePartEntity>  coursePartEntityList = coursePartService.findAll();
            model.addAttribute("courseEntityList",courseEntityList);
            model.addAttribute("coursePartEntityList", coursePartEntityList);
            model.addAttribute("newlession", new LessonEntity());
            return "/Admin/LessionAdmin/CreateLession";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());

        }
        return "redirect:/admin/course/co";
    }

    @GetMapping("/edit/{id}")
    public String saveOrUpdate(@PathVariable("id")String id, Model model){
        try{
            List<CourseEntity> courseEntityList = courseService.findAll();
            List<CoursePartEntity>  coursePartEntityList = coursePartService.findAll();
            model.addAttribute("courseEntityList",courseEntityList);
            model.addAttribute("coursePartEntityList", coursePartEntityList);
            model.addAttribute("newlession", lessionService.findById(id));
            return "/Admin/LessionAdmin/Editlession";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/admin/lession/le";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("coursepart") LessonEntity lessonEntity,
                               @RequestParam(value = "video", required = false) MultipartFile videoFile,
                               @RequestParam(value = "file", required = false) MultipartFile imageFile, // Thêm tham số cho hình ảnh
                               Model model) {
        try {
            if(lessonEntity.getId() != null){
                LessonEntity existingLession = lessionService.findById(lessonEntity.getId());
                if(existingLession != null){
                    lessonEntity.setPath(existingLession.getPath());
                }
            }

            lessionService.saveOrUpdate(lessonEntity);

            if(videoFile != null && !videoFile.isEmpty()){
                cloudinaryService.uploadFileAsync(videoFile).thenAccept(videoUrl -> {
                    if(videoUrl != null){
                        lessonEntity.setPath(videoUrl);
                        lessionService.saveOrUpdate(lessonEntity);
                    }
                });
            }
            // Upload video nếu có
//            if (videoFile != null && !videoFile.isEmpty()) {
//                String videoUrl = cloudinaryService.uploadFileAsync(videoFile).join(); // Upload video và lấy URL
//                lessonEntity.setPath(videoUrl);  // Lưu URL video vào entity
//            }
//
//            // Upload ảnh nếu có
//            if (imageFile != null && !imageFile.isEmpty()) {
//                String imageUrl = cloudinaryService.uploadFileAsync(imageFile).join(); // Upload ảnh và lấy URL
//                lessonEntity.setPath(imageUrl);  // Lưu URL ảnh vào entity
//            }
//
//            // Lưu bài học vào cơ sở dữ liệu
//            lessionService.SaveorUpdate(lessonEntity);
            return "redirect:/admin/lession/le";
        } catch (Exception e) {
            model.addAttribute("error", "Có lỗi xảy ra khi lưu bài học: " + e.getMessage());
            model.addAttribute("title", "Quản lý bài học");
            List<LessonEntity> lessonEntityList = lessionService.findAll();
            model.addAttribute("lessonEntityList", lessonEntityList);
            return "Admin/LessionManager";
        }
    }



    // Xóa khóa học
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        LessonEntity lessonEntity = lessionService.findById(id);
        if (lessonEntity == null) {
            return "redirect:/admin/lession/le";
        }

        try {
            lessionService.remove(lessonEntity);
            return "redirect:/admin/lession/le";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "Admin/LessionManager";
        }
    }
}
