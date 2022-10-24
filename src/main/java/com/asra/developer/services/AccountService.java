package com.asra.developer.services;

import com.asra.developer.models.entity.Account;
import com.asra.developer.models.payload.request.ChangePasswordRequest;
import com.asra.developer.models.payload.response.AccountInfoResponse;

import java.util.List;

public interface AccountService {

    AccountInfoResponse getUserInfo();

    void changePassword(ChangePasswordRequest inputRequest);

    Account getAccountById(Long accountId);

    String getCurrentAccountUsername();

    Account getCurrentAccount();

    List<Account> getAllAccounts();

}
