package com.bankIndia.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankIndia.entity.Loans;

public interface LoanRepo extends JpaRepository<Loans, Long>{

	Optional<Loans> findByMobileNumber(String mobileNumber);

}
