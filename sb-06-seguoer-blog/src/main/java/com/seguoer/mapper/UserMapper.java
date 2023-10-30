package com.seguoer.mapper;

import com.seguoer.dao.UserDao;
import com.seguoer.po.Blog;
import com.seguoer.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface UserMapper extends UserDao {
    @Override
    List<Blog> selectBlogByPage(@Param("offset") int offset, @Param("rowCount") int rowCount);

    @Override
    List<User> selectUsers();

    @Override
    int changeBlogByID(@Param("id") String id, @Param("content") String content);

    @Override
    int addNewBlog(Blog blog);

    @Override
    int updateBlog(Map map);

    @Override
    List<User> selectUsersByName(@Param("email") String email);

    @Override
    List<Blog> selectAllBlog();

    @Override
    int deleteBlogByTitle(String title);

    @Override
    List<Blog> selectBlogs(String id);

    @Override
    int changeUsersByID(@Param("id") String id, @Param("account") String account);

    @Override
    int addNewUser(User user);

    @Override
    int deleteUsersByID(String id);

    @Override
    int deleteBlogByID(String id);

    @Override
    List<User> selectAllUser();

    @Override
    String selectBlogContent(@Param("title") String title);

}
