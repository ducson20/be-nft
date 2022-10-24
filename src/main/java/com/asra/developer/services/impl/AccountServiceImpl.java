package com.asra.developer.services.impl;

import com.asra.developer.common.enums.EMessageCode;
import com.asra.developer.common.error.BusinessException;
import com.asra.developer.common.utils.MessageUtils;
import com.asra.developer.common.utils.RoleUtils;
import com.asra.developer.common.utils.StringUtils;
import com.asra.developer.models.entity.Account;
import com.asra.developer.models.entity.CustomAccountDetail;
import com.asra.developer.models.entity.Role;
import com.asra.developer.models.payload.request.ChangePasswordRequest;
import com.asra.developer.models.payload.response.AccountInfoResponse;
import com.asra.developer.repository.AccountRepository;
import com.asra.developer.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private PasswordEncoder encoder;

    @Override
    public AccountInfoResponse getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        AccountInfoResponse accountInfoResponse = new AccountInfoResponse();

        if (authentication != null && authentication.getPrincipal() != null) {
            String userName = ((UserDetails) authentication.getPrincipal()).getUsername();

            Optional<Account> userOp = accountRepository.findByUserName(userName);

            if (userOp.isPresent()) {

                Account user = userOp.get();

                accountInfoResponse.setUserID(user.getId());

                accountInfoResponse.setUsername(user.getUserName());

                accountInfoResponse.setFullName(StringUtils.nullToEmpty(user.getFullName()));

                accountInfoResponse.setEmail(StringUtils.nullToEmpty(user.getEmail()));

                accountInfoResponse.setAge(user.getAge());

                accountInfoResponse.setGender(user.getGender() == null ? "-1" : user.getGender());

                List<String> roles = new ArrayList<>();

                for(Role role : user.getRoles()){
                    roles.add(role.getName().name());
                }

                accountInfoResponse.setRoles(roles);

                CustomAccountDetail userDetails = (CustomAccountDetail) authentication.getPrincipal();

                Set<Role> roleSet = new HashSet<>();

                for(Role role : userDetails.getRoles()){
                    roleSet.add(role);
                }

                accountInfoResponse.setRoleHighest(RoleUtils.getRoleHighest(roleSet));
            } else {
                throw new BusinessException(MessageUtils.getMessageFromCode(EMessageCode.E001.getMessage()));
            }
        } else {
            throw new BusinessException(MessageUtils.getMessageFromCode(EMessageCode.E005.getMessage()));
        }
        return accountInfoResponse;
    }

    @Override
    public void changePassword(ChangePasswordRequest inputRequest) {

        Map<String, Object> changePasswordResponse = new HashMap<>();

        String currentUserName = getCurrentAccountUsername();

        Optional<Account> accountOp = accountRepository.findByUserName(currentUserName);

        if (accountOp.isPresent()) {
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(currentUserName, inputRequest.getOldPassword()));

                Account account = accountOp.get();

                account.setPassword(encoder.encode(inputRequest.getNewPassword()));

                accountRepository.save(account);
            } catch (AuthenticationException e) {
                throw new BusinessException(MessageUtils.getMessageFromCode(EMessageCode.E001.getMessage()));
            }

        } else {
            throw new BusinessException(MessageUtils.getMessageFromCode(EMessageCode.E001.getMessage()));
        }
    }

    @Override
    public String getCurrentAccountUsername() {
        String userName = "SYSTEM";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {

            Object principal = authentication.getPrincipal();

            if (principal != null && principal instanceof UserDetails) {
                userName = ((UserDetails) authentication.getPrincipal()).getUsername();
            }
        }
        return userName;
    }

    public Account getCurrentAccount() {

        Optional<Account> accountOp = accountRepository.findByUserName(getCurrentAccountUsername());

        if (!accountOp.isPresent()) {
            throw new BusinessException(MessageUtils.getMessageFromCode(EMessageCode.E001.getMessage()));
        }

        return accountOp.get();
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.getById(accountId);
    }


    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }
}
