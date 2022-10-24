package com.asra.developer.models.payload.response;

public class AccountDropdownResponse  {

    private String label;

    private long code;

    private String highestRole;

    public AccountDropdownResponse() {

    }

    public AccountDropdownResponse(String label, long code, String highestRole) {
        this.label = label;
        this.code = code;
        this.highestRole = highestRole;
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

    public String getHighestRole() {
        return highestRole;
    }

    public void setHighestRole(String highestRole) {
        this.highestRole = highestRole;
    }
}
