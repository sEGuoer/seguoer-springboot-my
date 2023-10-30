package com.seguoer.po;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(description = "博客")
public class Blog {
    private int id;

    public Blog() {
    }

    public Blog(String title, String creator, String cover, String slug, Date createTime, Date updateTime, int userId, String content, String contentType, int readingTime) {
        this.title = title;
        this.creator = creator;
        this.cover = cover;
        this.slug = slug;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userId = userId;
        this.content = content;
        this.contentType = contentType;
        this.readingTime = readingTime;
    }

    public Blog(int id, String title, String creator, String cover, String slug, Date createTime, Date updateTime, int userId, String content, String contentType, int readingTime) {
        this.id = id;
        this.title = title;
        this.creator = creator;
        this.cover = cover;
        this.slug = slug;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userId = userId;
        this.content = content;
        this.contentType = contentType;
        this.readingTime = readingTime;
    }

    private String title;
    private String creator;
    private String cover;
    private String slug;
    private Date createTime;
    private Date updateTime;
    private int userId;
    private String content;
    private String contentType;
    private int readingTime;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(int readingTime) {
        this.readingTime = readingTime;
    }
}
