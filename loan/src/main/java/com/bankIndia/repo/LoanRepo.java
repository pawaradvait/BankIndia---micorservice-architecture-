package com.bankIndia.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankIndia.entity.Loans;
@Repository
public interface LoanRepo extends JpaRepository<Loans, Long>{

	Optional<Loans> findByMobileNumber(String mobileNumber);

}
