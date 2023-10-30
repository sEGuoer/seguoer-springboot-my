package com.seguoer.controller;

import com.seguoer.method.R;
import com.seguoer.po.Blog;
import com.seguoer.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
@Tag(name = "blog_controller", description = "博客管理")
public class BlogController {
    @Autowired
    UserService userService;

    @GetMapping
    @Operation(summary = "博客列表", description = "支持分页的文章列表接口，默认显示第一页(page=1), 每页显示2条(perPage=2)")
    R index(@Parameter(description = "当前页码")@RequestParam(defaultValue = "1")int page,@Parameter(description = "每页显示数量") @RequestParam(defaultValue = "2")int perpage) {
        if (page == 0 && perpage == 0){
            return R.ok(userService.selectAllBlog());
        }else {
            return R.ok(userService.selectBlogByPage(page,perpage));
        }
    }

    @PostMapping
    R store(Blog blog) {
        return R.ok(userService.addNewBlog(blog));
    }


    @DeleteMapping("/{id}")
    R destory(@PathVariable String id) {
        return R.ok(userService.deleteBlogByID(id));
    }

    @GetMapping("/{id}")
    R show(@PathVariable String id) {
        if (userService.selectBlogs(id) != null){
            return R.ok(userService.selectBlogs(id).get(0));
        }else {
            return null;
        }

    }
}
