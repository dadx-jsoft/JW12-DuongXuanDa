package com.eoptech.shopdoda.cotroller.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.eoptech.shopdoda.entities.Category;
import com.eoptech.shopdoda.repositories.CategoriesRepo;
import com.eoptech.shopdoda.utils.UserUtils;
import com.eoptech.shopdoda.utils.Utilities;

@Controller
public class CategoryAdminController {

    @Autowired
    private CategoriesRepo categoriesRepo;

    @GetMapping(value = { "/admin/categories" })
    public String categories(final ModelMap model) {

        model.addAttribute("categoryList", categoriesRepo.findAll());

        return "back-end/category";
    }

    @GetMapping(value = { "/admin/categories/edit/{id}" })
    public String editCategory(final ModelMap model, @PathVariable("id") Integer CategoryId) throws Exception {

        Category cat = categoriesRepo.findById(CategoryId).get();

        model.addAttribute("categoryEdit", cat);

        return "back-end/save_category";
    }

    @GetMapping(value = { "/admin/categories/add" })
    public String addCategory(final ModelMap model) {
        model.addAttribute("categoryEdit", new Category());
        return "back-end/save_category";
    }

    @PostMapping(value = { "/admin/categories/save" })
    public String saveCategory(final ModelMap model, @ModelAttribute("categoryEdit") Category categoryEdit) {
        // TH chỉnh sửa
        if (categoryEdit.getId() != null && categoryEdit.getId() > 0) {
            Category categoryInDB = categoriesRepo.findById(categoryEdit.getId()).get();
            // created & updated date
            categoryEdit.setCreatedDate(categoryInDB.getCreatedDate());
            categoryEdit.setUpdatedDate(new Date());
            // created & updated by
            categoryEdit.setCreatedBy(categoryInDB.getCreatedBy());
            categoryEdit.setUpdatedBy(UserUtils.getUserId());

            categoryEdit.setSeo(Utilities.seo(categoryEdit.getName()));
        } else {
            categoryEdit.setCreatedDate(new Date());
            categoryEdit.setCreatedBy(UserUtils.getUserId());
            categoryEdit.setSeo(Utilities.seo(categoryEdit.getName()));
        }
        categoriesRepo.save(categoryEdit);

        return "redirect:/admin/categories";
    }

    @GetMapping(value = { "/admin/categories/delete/{id}" })
    public String deleteCategory(final ModelMap model, @PathVariable("id") Integer categoryId) {
        Category deletedCategory = categoriesRepo.findById(categoryId).get();
        deletedCategory.setStatus(false);
        categoriesRepo.save(deletedCategory);

        return "redirect:/admin/categories";
    }

}
