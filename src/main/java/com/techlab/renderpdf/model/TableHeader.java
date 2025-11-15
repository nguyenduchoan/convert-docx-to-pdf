package com.techlab.renderpdf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model for table header with name (display) and key (for data mapping)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableHeader {
    /**
     * Display name (e.g., "Sản phẩm", "Số lượng")
     */
    private String name;
    
    /**
     * Key for data mapping (e.g., "product", "quantity")
     */
    private String key;
    
    /**
     * Constructor for backward compatibility (string header)
     */
    public TableHeader(String header) {
        this.name = header;
        this.key = header; // Use header as key for backward compatibility
    }
    
    /**
     * Check if this is a simple string header (backward compatibility)
     */
    public boolean isSimpleHeader() {
        return name != null && name.equals(key);
    }
}

