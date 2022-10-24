package com.asra.developer.repository.basic;

import com.asra.developer.models.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepositoryBasic extends JpaRepository<Account, Long> {
    Optional<Account> findByUserName(String userName);

    Optional<Account> findByEmail(String email);

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);

}
