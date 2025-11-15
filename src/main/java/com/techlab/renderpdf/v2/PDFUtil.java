package com.techlab.renderpdf.v2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

import fr.opensagres.poi.xwpf.converter.core.XWPFConverterException;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PDFUtil {
    private PDFUtil() {
    }

    public static void convertToPDF(XWPFDocument document, String userPassword,
            String fontPath, File tempFile) throws XWPFConverterException, IOException {

        try (FileOutputStream tempOutputStream = new FileOutputStream(tempFile)) {
            // Convert directly to temp file
            PdfOptions options = PdfOptions.create();

            if (Objects.nonNull(fontPath)) {
                options.fontProvider((familyName, encoding, size, style, color) -> {
                    try {
                        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                        return new Font(baseFont, size, style, color);
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Font error: " + e);
                    }
                });
            }

            PdfConverter.getInstance().convert(document, tempOutputStream, options);
        }

        File protectedTempFile = File.createTempFile("protected_", ".pdf");

        try {
            // Load the original PDF
            try (PDDocument pdfDocument = Loader.loadPDF(tempFile)) {
                AccessPermission accessPermission = new AccessPermission();
                accessPermission.setCanModify(false);

                StandardProtectionPolicy protection = new StandardProtectionPolicy(
                        null, // No owner password
                        userPassword, // User password
                        accessPermission);

                // Apply protection
                pdfDocument.protect(protection);

                // Save to the protected temp file
                try (FileOutputStream protectedOutputStream = new FileOutputStream(protectedTempFile)) {
                    pdfDocument.save(protectedOutputStream);
                }
            }

            // Now replace the original file with the protected one
            Files.move(protectedTempFile.toPath(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            log.warn("Convert from word to pdf failed!", e);
        } finally {
            protectedTempFile.delete();
        }
    }
}
