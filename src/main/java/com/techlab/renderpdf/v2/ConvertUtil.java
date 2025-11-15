package com.techlab.renderpdf.v2;

import java.io.File;
import java.util.*;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConvertUtil {
//    private final ObjectMapper objectMapper;
//    private final FontPathConfig fontPathConfig;
//
//    public String kebabToSnake(Object object) throws JsonProcessingException {
//        ObjectNode root = objectMapper.createObjectNode();
//
//        if (Objects.nonNull(object)) {
//            Map<String, String> map = objectMapper.convertValue(object, new TypeReference<Map<String, String>>() {
//            });
//            map.forEach((key, value) -> {
//                root.put(StringUtil.kebabToSnake(key), value);
//            });
//        }
//
//        return objectMapper.writeValueAsString(root);
//    }
//
//    public void convertWordToPDFWithPassword(String templatePath, Map<String, String> parameters,
//            String password, File tempFile) {
//        try (XWPFDocument document = WordUtil.fillTemplate(templatePath, parameters)) {
//            PDFUtil.convertToPDF(document, password, fontPathConfig.getBase() + "/arial.ttf", tempFile);
//        } catch (Exception e) {
//            log.warn("Convert from word to pdf failed!", e);
//        }
//    }
}
