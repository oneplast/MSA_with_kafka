package com.river.demo.domain.accounts.repository;

import com.river.demo.domain.accounts.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);
    Optional<Account> findByCode(String code);

}
