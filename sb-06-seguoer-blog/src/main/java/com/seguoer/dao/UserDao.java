package com.seguoer.dao;

import com.seguoer.po.Blog;
import com.seguoer.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<Blog> selectAllBlog();
    List<Blog> selectBlogByPage(@Param("offset") int offset, @Param("rowCount") int rowCount);
    List<User> selectUsersByName(String name);
    List<Blog> selectBlogs(String id);
    List<User> selectAllUser();
    List<User> selectUsers();
    int changeUsersByID(String id,String account);
    int changeBlogByID(String id,String content);
    int addNewUser(User user);
    int addNewBlog(Blog blog);
    int deleteUsersByID(String id);
    int deleteBlogByID(String id);
    int deleteBlogByTitle(String title);
    int updateBlog(Map map);
    String selectBlogContent(String title);
}
