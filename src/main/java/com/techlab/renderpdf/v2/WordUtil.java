package com.techlab.renderpdf.v2;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WordUtil {
    private WordUtil() {
    }

    public static XWPFDocument fillTemplate(String templatePath, Map<String, String> parameters) throws Exception {
        // Load the DOCX template
        try (InputStream fis = WordUtil.class.getClassLoader().getResourceAsStream(templatePath)) {
            XWPFDocument document = new XWPFDocument(fis);

            // Replace placeholders in paragraphs
            replaceInParagraphs(document.getParagraphs(), parameters);

            // Replace placeholders in tables
            for (XWPFTable table : document.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        replaceInParagraphs(cell.getParagraphs(), parameters);
                    }
                }
            }

            // Replace placeholders in headers, if any
            if (document.getHeaderFooterPolicy() != null &&
                    document.getHeaderFooterPolicy().getDefaultHeader() != null) {
                replaceInParagraphs(document.getHeaderFooterPolicy().getDefaultHeader().getParagraphs(), parameters);
            }

            // Replace placeholders in footers, if any
            if (document.getHeaderFooterPolicy() != null &&
                    document.getHeaderFooterPolicy().getDefaultFooter() != null) {
                replaceInParagraphs(document.getHeaderFooterPolicy().getDefaultFooter().getParagraphs(), parameters);
            }

            return document;
        } catch (Exception e) {
            log.warn("Fill param to template failed!", e);
        }

        return null;
    }

    private static void replaceInParagraphs(List<XWPFParagraph> paragraphs, Map<String, String> parameters) {
        for (XWPFParagraph paragraph : paragraphs) {
            List<XWPFRun> runs = paragraph.getRuns();
            if (runs != null) {
                for (XWPFRun run : runs) {
                    String text = run.getText(0);
                    if (text != null) {
                        for (Map.Entry<String, String> entry : parameters.entrySet()) {
                            if (text.contains(entry.getKey())) {
                                text = text.replace(entry.getKey(), entry.getValue());
                                run.setText(text, 0);
                            }
                        }
                    }
                }
            }
        }
    }
}
