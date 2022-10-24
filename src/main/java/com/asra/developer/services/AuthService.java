package com.asra.developer.services;

import com.asra.developer.models.payload.request.SignInRequest;
import com.asra.developer.models.payload.request.SignUpRequest;
import com.asra.developer.models.payload.response.SignInResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    SignInResponse signIn(SignInRequest signInRequest);

    void signUp( SignUpRequest signUpRequest);

    void logout(HttpServletRequest request, HttpServletResponse response);

}
