package com.bankIndia.account.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankIndia.account.entity.Customers;
@Repository
public interface CustomerRepo extends JpaRepository<Customers, Long> {
   Optional<Customers> findByMobileNumber(String mobileNumber);
}
