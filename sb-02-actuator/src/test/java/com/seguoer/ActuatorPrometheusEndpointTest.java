package com.seguoer;

import org.hamcrest.Matchers;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(properties = {"management.prometheus.metrics.export.enabled=true", "management.endpoints.web.exposure.include=prometheus"})
@AutoConfigureMockMvc
public class ActuatorPrometheusEndpointTest {
    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("测试 /actuator 页面包含 `prometheus`")
    void actuatorPrometheus() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator"))
                .andExpect(MockMvcResultMatchers.jsonPath("_links.prometheus").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("_links.prometheus.href", Matchers.endsWith("/actuator/prometheus")))
        ;
    }

    @Test
    @DisplayName("测试 /actuator/prometheus 页面内容")
    void actuatorPrometheusDetail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator/prometheus"))
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("jvm_memory_used")))
        ;
    }
}
