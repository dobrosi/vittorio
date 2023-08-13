package com.example.vittorio.controller;

import com.example.vittorio.dto.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class WebshopController {
    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public String sendOrderWithMultipartForm(@RequestPart("data") Data data) {
        return getFirstname(data);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public String sendOrderWithJsonBody(@RequestBody Data data) {
        return getFirstname(data);
    }

    private String getFirstname(final Data data) {
        return data.orders.order.get(0).firstname;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        return "Hello " + name + "!";
    }
}
