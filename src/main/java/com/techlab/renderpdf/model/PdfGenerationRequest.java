package com.techlab.renderpdf.model;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * Request model for PDF generation
 */
@Data
public class PdfGenerationRequest {
    @NotBlank(message = "Template name is required")
    private String templateName;
    
    private Map<String, Object> variables;
    private List<TableData> tables;
    
    // Optional: specify output filename
    private String outputFilename;
}

