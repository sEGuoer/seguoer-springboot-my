package com.seguoer;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class D20231025SbApplicationTests {

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("重定向之后显示对应页面")
    void swaggerUI() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/swagger-ui.html"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/swagger-ui/index.html"))
        ;
    }

    @Test
    @DisplayName("直接定位到跳转网址 ")
    void swaggerUIActual() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/swagger-ui/index.html"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("<div id=\"swagger-ui\">")))
        ;
    }
    @Test
    @DisplayName("""
            @Tag(name = "blog_controller", description = "博客管理")
            查看是否生成@Tag对应内容
            """)
    void swaggerUIWithTagAnnotation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/v3/api-docs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("tags.*.name", hasItem("blog_controller")))
                .andExpect(MockMvcResultMatchers.jsonPath("tags.*.description", hasItem("博客管理")))
        ;
    }
    @Test
    @DisplayName("""
               @Operation(summary = "博客列表", description = "支持分页的文章列表接口，默认显示第一页(page=1), 每页显示2条(perPage=2)") 
               查看  /v3/api-docs中生成的对应内容                                                                                        
            """)
    void swaggerUIWithOperationAnnotation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/v3/api-docs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("paths['/blog'].get.summary", is("博客列表")))
                .andExpect(MockMvcResultMatchers.jsonPath("paths['/blog'].get.description", is("支持分页的文章列表接口，默认显示第一页(page=1), 每页显示2条(perPage=2)")))
        ;
    }
    @Test
    @DisplayName("""
            @Parameter(description = "当前页码")
            @Parameter(description = "每页显示数量")
            """)
    void swaggerUIWithParameterAnnotation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/v3/api-docs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("paths['/blog'].get.parameters.*.description", hasItems("当前页码", "每页显示数量")))
        ;
    }
    @Test
    @DisplayName("""
            @Schema(description = "博客")
            @Schema(description = "统一返回结果")
            @Schema(description = "状态码")
            @Schema(description = "描述消息")
            ...
            """)
    void swaggerUIWithSchemaAnnotation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/v3/api-docs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("components.schemas.Blog.description", is("博客")))
                .andExpect(MockMvcResultMatchers.jsonPath("components.schemas.R.description", is("统一返回结果")))
                .andExpect(MockMvcResultMatchers.jsonPath("components.schemas.R.properties.code.description", is("状态码")))
                .andExpect(MockMvcResultMatchers.jsonPath("components.schemas.R.properties.msg.description", is("状态消息")))
                .andExpect(MockMvcResultMatchers.jsonPath("components.schemas.R.properties.data.description", is("具体数据")))
        ;
    }
}
