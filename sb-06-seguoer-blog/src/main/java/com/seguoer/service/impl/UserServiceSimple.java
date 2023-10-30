package com.seguoer.service.impl;

import com.seguoer.mapper.UserMapper;
import com.seguoer.po.Blog;
import com.seguoer.po.User;
import com.seguoer.service.UserService;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceSimple implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int deleteBlogByTitle(String title) {
        return  userMapper.deleteBlogByTitle(title);
    }

    @Override
    public List<Blog> selectAllBlog() {
        return userMapper.selectAllBlog();
    }

    @Override
    public List<Blog> selectBlogByPage(int offset, int rowCount) {
        return userMapper.selectBlogByPage(offset - 1, rowCount);
    }

    @Override
    public List<User> selectUsersByEmail(String email) {
        return userMapper.selectUsersByName(email);
    }

    @Override
    public List<Blog> selectBlogs(String id) {
        return userMapper.selectBlogs(id);
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public List<User> selectUsers() {
        return userMapper.selectUsers();
    }

    @Override
    public int changeUsersByID(String id, String account) {
        return userMapper.changeUsersByID(id, account);
    }

    @Override
    public int changeBlogByID(String id, String content) {
        return userMapper.changeBlogByID(id, content);
    }

    @Override
    public int addNewUser(User user) {
        return userMapper.addNewUser(user);
    }

    @Override
    public int addNewBlog(Blog blog) {
        return userMapper.addNewBlog(blog);
    }

    @Override
    public int addNewBlog(String email, String content ,String title) {
        List<User> userList = selectUsersByEmail(email);
        if (userList != null){
            User user = userList.get(0);
            Blog blog = new Blog(title,user.getUsername(),"xx","xx",new Date(),new Date(),user.getId(),content,"md",0);
            userMapper.addNewBlog(blog);
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int deleteUsersByID(String id) {
        return userMapper.deleteUsersByID(id);
    }

    @Override
    public int deleteBlogByID(String id) {
        return userMapper.deleteBlogByID(id);
    }

    @Override
    public int updateBlog(Map map) {
        System.out.println("UserServiceSimple.updateBlog");
        return userMapper.updateBlog(map);
    }

    @Override
    public String selectBlogContent(String title) {
        String mySQL = userMapper.selectBlogContent(title);
        if (mySQL != null) {
            System.out.println(mySQL);
            Parser parser = Parser.builder().build();
            Node document = parser.parse(mySQL);
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            String Content = renderer.render(document);
            System.out.println(Content);
            return Content;
        } else {
            return "Please input correctly title";
        }
    }

    @Override
    public String userLogin(String email, String password) {
        List<User> userList = userMapper.selectUsersByName(email);
        if (userList.size() == 1) {
            User user = userList.get(0);
            String sqlPassword = user.getPassword();
            if (sqlPassword.equals(password)) {
                if (user.getUsername().equals("person")) {
                    return "用户登录";
                } else if (user.getUsername().equals("admin")) {
                    return "管理员登陆";
                } else {
                    return "?";
                }
            }else {
                    return "密码错误";
                }
            } else if (userList.size() == 0) {
                return "没有找到该用户";
            } else {
                return "请输入正确的账号";
            }
        }
    }
