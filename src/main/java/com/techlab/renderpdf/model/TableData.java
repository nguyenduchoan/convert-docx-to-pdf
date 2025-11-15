package com.techlab.renderpdf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Model for dynamic table data
 * Supports both simple string headers and header objects with name and key
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableData {
    private String tableName;
    
    /**
     * Headers can be either:
     * 1. List<String> - simple headers (backward compatibility)
     * 2. List<TableHeader> - headers with name and key (new approach)
     */
    private Object headers; // Can be List<String> or List<TableHeader>
    
    private List<Map<String, Object>> rows;
    
    public TableData(String tableName) {
        this.tableName = tableName;
    }
    
    /**
     * Get headers as List<TableHeader>
     */
    @SuppressWarnings("unchecked")
    public List<TableHeader> getHeadersAsObjects() {
        if (headers == null) {
            return new ArrayList<>();
        }
        
        if (headers instanceof List) {
            List<?> headerList = (List<?>) headers;
            if (headerList.isEmpty()) {
                return new ArrayList<>();
            }
            
            // Check if first element is TableHeader
            if (headerList.get(0) instanceof TableHeader) {
                return (List<TableHeader>) headers;
            }
            
            // Convert List<String> to List<TableHeader>
            if (headerList.get(0) instanceof String) {
                return ((List<String>) headers).stream()
                    .map(TableHeader::new)
                    .collect(Collectors.toList());
            }
        }
        
        return new ArrayList<>();
    }
    
    /**
     * Get header keys for data mapping
     */
    public List<String> getHeaderKeys() {
        return getHeadersAsObjects().stream()
            .map(TableHeader::getKey)
            .collect(Collectors.toList());
    }
    
    /**
     * Get header names for display
     */
    public List<String> getHeaderNames() {
        return getHeadersAsObjects().stream()
            .map(TableHeader::getName)
            .collect(Collectors.toList());
    }
    
    /**
     * Check if headers are simple strings (backward compatibility)
     */
    public boolean isSimpleHeaders() {
        if (headers == null) {
            return true;
        }
        
        if (headers instanceof List) {
            List<?> headerList = (List<?>) headers;
            if (headerList.isEmpty()) {
                return true;
            }
            return headerList.get(0) instanceof String;
        }
        
        return false;
    }
}

