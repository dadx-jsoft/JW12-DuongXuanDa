package com.eoptech.shopdoda.cotroller.admin.blog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eoptech.shopdoda.dto.search.BlogSearch;
import com.eoptech.shopdoda.entities.blog.Blog;
import com.eoptech.shopdoda.repositories.blog.BlogRepo;
import com.eoptech.shopdoda.services.blog.BlogService;
import com.eoptech.shopdoda.services.blog.BlogTypeService;

@Controller
public class BlogAdminController {

    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogTypeService blogTypeSevice;

    @GetMapping(value = { "admin/blogs" })
    public String blogs(final ModelMap model, final HttpServletRequest request) {
        BlogSearch blogSearch = new BlogSearch();
        blogSearch.buildPaging(request);

        List<Blog> blogs = blogService.search(blogSearch);

        model.addAttribute("blogs", blogs);
        model.addAttribute("blogSearch", blogSearch);

        return "back-end/blog/blogs";
    }

    @GetMapping(value = { "admin/blogs/add" })
    public String addBlog(final ModelMap model) {

        model.addAttribute("blog", new Blog());
        model.addAttribute("blogTypes", blogTypeSevice.getBlogTypes());

        return "back-end/blog/save_blog";
    }

    @PostMapping(value = { "admin/blogs/add" })
    public String saveBlog(final ModelMap model, @ModelAttribute("blog") Blog blog,
            @RequestParam("avatar_file") MultipartFile avatar) throws Exception {

        blogService.saveOrUpdate(blog, avatar);

        return "redirect:/admin/blogs";
    }

    @GetMapping(value = { "admin/blogs/edit/{id}" })
    public String editProduct(final ModelMap model, @PathVariable("id") int blogId) {

        model.addAttribute("blog", blogRepo.findById(blogId).get());
        model.addAttribute("blogTypes", blogTypeSevice.getBlogTypes());

        return "back-end/blog/save_blog";
    }

    @GetMapping(value = { "admin/blogs/delete/{id}" })
    public String deleteProduct(final ModelMap model, @PathVariable("id") int blogId) {
        Blog deletedBlog = blogRepo.findById(blogId).get();
        deletedBlog.setStatus(false);
        blogRepo.save(deletedBlog);

        return "redirect:/admin/blogs";
    }

    @GetMapping(value = { "admin/blogs/search-all" })
    public String searchAll(final ModelMap model, final HttpServletRequest request) {

        BlogSearch blogSearch = new BlogSearch();
        blogSearch.buildPaging(request);

        blogSearch.setSearchText(request.getParameter("searchText"));
        List<Blog> blogs = blogService.search(blogSearch);

        model.addAttribute("blogs", blogs);
        model.addAttribute("blogSearch", blogSearch);

        return "back-end/blog/blogs";
    }
}
