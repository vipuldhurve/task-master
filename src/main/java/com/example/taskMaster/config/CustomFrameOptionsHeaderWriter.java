package com.example.taskMaster.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.header.HeaderWriter;

public class CustomFrameOptionsHeaderWriter implements HeaderWriter {
    @Override
    public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
        // Allow frames from the same origin
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
    }
}
