package com.bankIndia.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankIndia.account.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

}
