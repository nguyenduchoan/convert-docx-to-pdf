package com.techlab.renderpdf.controller;

import com.techlab.renderpdf.model.PdfGenerationRequest;
import com.techlab.renderpdf.service.PdfGenerationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for PDF generation
 * Uses virtual threads for concurrent processing
 */
@Slf4j
@RestController
@RequestMapping("/api/pdf")
@RequiredArgsConstructor
public class PdfController {

    private final PdfGenerationService pdfGenerationService;

    /**
     * Generate PDF from DOCX template using  (PdfConverter)
     * Process variables/tables in DOCX first, then convert to PDF using PdfConverter
     * Better performance than LibreOffice with good format preservation
     */
    @PostMapping("/generate")
    public ResponseEntity<byte[]> generatePdf(@Valid @RequestBody PdfGenerationRequest request) {
        try {
            log.info("Generating PDF using  (PdfConverter) for template: {}", request.getTemplateName());
            long startTime = System.currentTimeMillis();
            
            byte[] pdfBytes = pdfGenerationService.generatePdfFromDocxTemplate(request);
            
            long duration = System.currentTimeMillis() - startTime;
            log.info("PDF generated using  (PdfConverter) in {} ms", duration);

            String filename = request.getOutputFilename() != null 
                    ? request.getOutputFilename() 
                    : "generated__" + System.currentTimeMillis() + ".pdf";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", filename);
            headers.setContentLength(pdfBytes.length);

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            
        } catch (Exception e) {
            log.error("Error generating PDF using  (PdfConverter)", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(("Error: " + e.getMessage()).getBytes());
        }
    }

    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("PDF Generation Service is running");
    }
}

