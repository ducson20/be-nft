package com.asra.developer.common.enums;

public enum EMessageCode {

    E001("E001"),
    E002("E002"),
    E003("E003"),
    E004("E004"),
    E005("E005"),
    E006("E006"),

    I001("I001"),
    I002("I002");

    private final String message;

    /**
     * @param message
     */
    EMessageCode(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
