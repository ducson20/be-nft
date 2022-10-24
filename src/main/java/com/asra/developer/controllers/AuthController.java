package com.asra.developer.controllers;

import com.asra.developer.common.enums.EMessageCode;
import com.asra.developer.common.utils.MessageUtils;
import com.asra.developer.models.payload.request.SignInRequest;
import com.asra.developer.models.payload.request.SignUpRequest;
import com.asra.developer.models.payload.response.SignInResponse;
import com.asra.developer.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Transactional
	@PostMapping("/sign-in")
	public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest signInRequest) {

		SignInResponse signInResponse = authService.signIn(signInRequest);

		return new ResponseEntity<>(signInResponse, HttpStatus.OK);
	}

	@Transactional
	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
		Map<String, String> signUpResponse = new HashMap<>();

		authService.signUp(signUpRequest);

		signUpResponse.put("message", MessageUtils.getMessageFromCode(EMessageCode.I001.getMessage()));
		return new ResponseEntity<>(signUpResponse, HttpStatus.OK);
	}

	@GetMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response){
		authService.logout(request, response);
	}


}
