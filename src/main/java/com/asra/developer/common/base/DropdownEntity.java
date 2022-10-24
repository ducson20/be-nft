package com.asra.developer.common.base;

public class DropdownEntity {

    private String label;
    private long code;

    public DropdownEntity() {

    }

    public DropdownEntity(String label, long code) {
        this.label = label;
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }
}
