package com.asra.developer.controllers;

import com.asra.developer.common.enums.ECodeTable;
import com.asra.developer.common.utils.RoleUtils;
import com.asra.developer.models.entity.Account;
import com.asra.developer.models.entity.Role;
import com.asra.developer.models.payload.response.AccountDropdownResponse;
import com.asra.developer.models.payload.response.RoleDropdownResponse;
import com.asra.developer.services.AccountService;
import com.asra.developer.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/code-table")
public class CustomController {

    @Autowired
    private AccountService accountServices;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<?> getDropdownNFTApp(@RequestParam("categoryIdList[]") List<String> category) {
        Map<String, List<?>> codeTable = new HashMap<>();
        for (String str : category) {
            if (ECodeTable.USER.getValue().equals(str)) {
                codeTable.put(ECodeTable.USER.getValue(), getAllAccounts());
            }
            if (ECodeTable.ROLE.getValue().equals(str)) {
                codeTable.put(ECodeTable.ROLE.getValue(), getAllRoles());
            }
        }


        return new ResponseEntity<>(codeTable, HttpStatus.OK);
    }

    public List<AccountDropdownResponse> getAllAccounts() {
        List<AccountDropdownResponse> accountDropdownList = new ArrayList<>();
        for (Account account : accountServices.getAllAccounts()) {
            accountDropdownList.add(
                    new AccountDropdownResponse(account.getFullName(), account.getId(), RoleUtils.getRoleHighest(account.getRoles()))
            );
        }

        return accountDropdownList;
    }

    public List<RoleDropdownResponse> getAllRoles() {
        List<RoleDropdownResponse> roleDropdownList = new ArrayList<>();
        for (Role role : roleService.getAllRoles()) {
            roleDropdownList.add(new RoleDropdownResponse(role.getName().name(), role.getId()));
        }
        return roleDropdownList;
    }
}
