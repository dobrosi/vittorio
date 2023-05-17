package com.example.vittorio.controller;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockPart;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebshopControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Value("classpath:orders.json")
    Resource json;

    @Test
    void sendOrderWithMultipartForm() throws Exception {
        mockMvc.perform(multipart("/").part(new MockPart("data",
                                                         getJson())).contentType(MULTIPART_FORM_DATA)).andExpect(status().isOk()).andExpect(
                content().string("Epres"));
    }

    @Test
    void sendOrderWithJsonBody() throws Exception {
        mockMvc.perform(post("/").contentType(APPLICATION_JSON).content(getJson())).andExpect(status().isOk()).andExpect(
                content().string("Epres"));
    }

    private byte[] getJson() throws IOException {
        return json.getInputStream().readAllBytes();
    }
}