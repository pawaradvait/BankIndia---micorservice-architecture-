package com.bankIndia.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.bankIndia.account.entity.Account;

import jakarta.transaction.Transactional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    Account findByCustomerId(Long customerId);
 
    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);

}
