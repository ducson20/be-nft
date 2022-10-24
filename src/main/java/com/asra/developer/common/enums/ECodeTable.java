package com.asra.developer.common.enums;

import org.webjars.NotFoundException;

import java.util.Arrays;

public enum ECodeTable {


    USER("users"),
    ROLE("roles");

    private String value;

    ECodeTable(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ECodeTable get(final String value) throws Exception{
        return Arrays.stream(ECodeTable.values())
                .filter((codeTable -> codeTable.value.equals(value)))
                .findFirst().orElseThrow(() -> new NotFoundException("codeTable"));
    }
}
