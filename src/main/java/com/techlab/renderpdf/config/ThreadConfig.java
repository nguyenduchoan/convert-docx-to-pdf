package com.techlab.renderpdf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Configuration for virtual threads
 * Spring Boot 3.2+ automatically enables virtual threads when spring.threads.virtual.enabled=true
 * This configuration provides additional thread pool configuration if needed
 */
@Configuration
@EnableAsync
public class ThreadConfig {

    /**
     * Virtual thread executor for async operations
     * Virtual threads are lightweight and perfect for I/O-bound operations like PDF generation
     */
    @Bean(name = "virtualThreadExecutor")
    public Executor virtualThreadExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}

