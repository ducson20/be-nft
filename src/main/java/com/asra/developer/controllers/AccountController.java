package com.asra.developer.controllers;

import com.asra.developer.common.enums.EMessageCode;
import com.asra.developer.common.utils.MessageUtils;
import com.asra.developer.models.payload.request.ChangePasswordRequest;
import com.asra.developer.models.payload.response.AccountInfoResponse;
import com.asra.developer.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
public class AccountController {

    @Autowired
    private AccountService accountServices;

    @GetMapping("/info")
    public ResponseEntity<?> getInformation() {

        AccountInfoResponse accountInfoResponse = accountServices.getUserInfo();

        return new ResponseEntity<>(accountInfoResponse, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest inputRequest) {
        Map<String, String> signUpResponse = new HashMap<>();

        signUpResponse.put("message", MessageUtils.getMessageFromCode(EMessageCode.I002.getMessage()));

        return new ResponseEntity<>(signUpResponse, HttpStatus.OK);
    }
}
