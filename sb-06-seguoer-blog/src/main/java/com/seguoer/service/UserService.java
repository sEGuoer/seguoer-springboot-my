package com.seguoer.service;

import com.seguoer.po.Blog;
import com.seguoer.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<Blog> selectAllBlog();
    List<Blog> selectBlogByPage(@Param("offset") int offset, @Param("rowCount") int rowCount);
    List<User> selectUsersByEmail(String email);
    List<Blog> selectBlogs(String id);
    List<User> selectAllUser();
    List<User> selectUsers();
    int changeUsersByID(String id,String account);
    int changeBlogByID(String id,String content);
    int addNewUser(User user);
    int addNewBlog(Blog blog);
    int addNewBlog(String email,String content,String title);
    int deleteUsersByID(String id);
    int deleteBlogByID(String id);
    int updateBlog(Map map);
    int deleteBlogByTitle(String title);
    String selectBlogContent(String title);
    String userLogin(String email,String password);
}
