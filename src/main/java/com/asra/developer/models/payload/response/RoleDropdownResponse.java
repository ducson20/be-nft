package com.asra.developer.models.payload.response;

import com.asra.developer.common.base.DropdownEntity;

public class RoleDropdownResponse extends DropdownEntity {

    public RoleDropdownResponse() {
    }

    public RoleDropdownResponse(String label, long value) {
        super(label, value);
    }
}
