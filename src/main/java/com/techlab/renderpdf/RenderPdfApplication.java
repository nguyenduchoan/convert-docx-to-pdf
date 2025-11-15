package com.techlab.renderpdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RenderPdfApplication {

    public static void main(String[] args) {
        System.setProperty("docx4j.PhysicalFonts.discoverPhysicalFonts", "false");
        SpringApplication.run(RenderPdfApplication.class, args);
    }
}

