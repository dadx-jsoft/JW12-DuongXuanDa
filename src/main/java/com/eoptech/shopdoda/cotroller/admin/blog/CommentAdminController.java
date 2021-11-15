package com.eoptech.shopdoda.cotroller.admin.blog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.eoptech.shopdoda.dto.AjaxResponse;
import com.eoptech.shopdoda.dto.ApprovedObj;
import com.eoptech.shopdoda.dto.search.ReviewOrCommentSearch;
import com.eoptech.shopdoda.entities.blog.BlogComment;
import com.eoptech.shopdoda.repositories.blog.BlogCommentRepo;
import com.eoptech.shopdoda.services.blog.BlogCommentService;

@Controller
public class CommentAdminController {

    @Autowired
    private BlogCommentRepo commentRepo;

    @Autowired
    private BlogCommentService commentService;

    @GetMapping(value = { "/admin/comments/{id}" })
    public String index(final ModelMap model, final HttpServletRequest request, @PathVariable("id") int blogId) {
        ReviewOrCommentSearch commentSearch = new ReviewOrCommentSearch();
        commentSearch.setIdProductOrBlog(blogId);
        commentSearch.buildPaging(request);

        List<BlogComment> comments = commentService.search(commentSearch);
        model.addAttribute("comments", comments);
        model.addAttribute("commentSearch", commentSearch);

        return "back-end/user/comment";
    }

    // C3: Dùng Ajax
    @PostMapping(value = { "/admin/comments/approve" })
    public ResponseEntity<AjaxResponse> approveComment(final ModelMap model, final HttpServletRequest request,
            final HttpServletResponse response, @RequestBody ApprovedObj approvedObj) {
        BlogComment comment = commentRepo.findById(approvedObj.getId()).get();
        comment.setStatus(true);
        commentRepo.save(comment);

        return ResponseEntity.ok(new AjaxResponse(200, "Duyệt comment thành công!"));
    }
}
