package com.example.doanstudy.AdminController;

import com.example.doanstudy.model.entity.CategoriesEntity;
import com.example.doanstudy.model.entity.CourseEntity;
import com.example.doanstudy.service.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/categories")
public class CategoriesManagerController {

    private final CategoriesService categoriesService;

    // Hiển thị trang quản lý danh mục
    @GetMapping("/ca")
    public String getCourseManager(@RequestParam(value = "page", required = false, defaultValue = "1")Integer pageNumber,
                                   @RequestParam(value = "s", required = false)String search, Model model) {
        model.addAttribute("title" ,"Quản lý danh mục");
        Page<CategoriesEntity> page = categoriesService.findAll(search, pageNumber);
        List<CategoriesEntity> topLevelCategories = categoriesService.findByParentIdIsNotNull();
        model.addAttribute("categoriesList", page.getContent());
        model.addAttribute("topLevelCategories", topLevelCategories);
        model.addAttribute("categories", new CategoriesEntity()); // Đối tượng mới để thêm/sửa
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("s", search);
        return "Admin/CategoriesManager";
    }

    // Xử lý thêm mới hoặc cập nhật danh mục
    @PostMapping("/save")
    public String saveOrUpdateCategory(@ModelAttribute("categories") CategoriesEntity categoriesEntity, Model model) {
        try {
            // Kiểm tra nếu `parentId` có giá trị, thì lưu vào entity
            if (categoriesEntity.getParentId() != null && !categoriesEntity.getParentId().isEmpty()) {
                CategoriesEntity parentCategory = categoriesService.findById(categoriesEntity.getParentId());
                if (parentCategory != null) {
                    categoriesEntity.setParentId(parentCategory.getId());
                }
            }
            categoriesService.SaveorUpdate(categoriesEntity);
            return "redirect:/admin/categories/ca";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "Admin/CategoriesManager";
        }
    }

    // Xóa danh mục theo ID
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") String id, Model model) {
        CategoriesEntity category = categoriesService.findById(id);
        if (category != null) {
            categoriesService.remove(category);
        }
        return "redirect:/admin/categories/ca";
    }
}
