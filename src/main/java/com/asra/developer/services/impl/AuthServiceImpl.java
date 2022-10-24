package com.asra.developer.services.impl;

import com.asra.developer.common.enums.EMessageCode;
import com.asra.developer.common.enums.ERole;
import com.asra.developer.common.error.BusinessException;
import com.asra.developer.common.utils.MessageUtils;
import com.asra.developer.common.utils.RoleUtils;
import com.asra.developer.models.entity.Account;
import com.asra.developer.models.entity.CustomAccountDetail;
import com.asra.developer.models.entity.Role;
import com.asra.developer.models.payload.request.SignInRequest;
import com.asra.developer.models.payload.request.SignUpRequest;
import com.asra.developer.models.payload.response.SignInResponse;
import com.asra.developer.models.payload.response.UserInfo;
import com.asra.developer.repository.AccountRepository;
import com.asra.developer.repository.RoleRepository;
import com.asra.developer.security.jwt.JwtUtils;
import com.asra.developer.services.AccountService;
import com.asra.developer.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountRepository userRepository;

    @Autowired
    private AccountService accountServices;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BusinessException(MessageUtils.getMessageFromCode(EMessageCode.E002.getMessage()));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomAccountDetail userDetails = (CustomAccountDetail) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        Set<Role> roleSet = new HashSet<>();

        for(Role role : userDetails.getRoles()){
            roleSet.add(role);
        }

        Account currentAccount = accountServices.getAccountById(userDetails.getId());
        return new SignInResponse(jwt, new UserInfo(userDetails.getId(), userDetails.getUsername(), currentAccount.getEmail(),
                currentAccount.getFullName(), currentAccount.getAge(), currentAccount.getGender(), roles, RoleUtils.getRoleHighest(roleSet)));
    }

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUsername())) {
            throw new BusinessException(MessageUtils.getMessageFromCode(EMessageCode.E003.getMessage(), signUpRequest.getUsername()));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BusinessException(MessageUtils.getMessageFromCode(EMessageCode.E004.getMessage(), signUpRequest.getEmail()));
        }

        Account account = new Account(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new BusinessException(MessageUtils.getMessageFromCode(EMessageCode.E001.getMessage())));

        roles.add(userRole);

        account.setRoles(roles);

        account.setFullName(signUpRequest.getFullName());

        account.setEmail(signUpRequest.getEmail());
        userRepository.save(account);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }

}
