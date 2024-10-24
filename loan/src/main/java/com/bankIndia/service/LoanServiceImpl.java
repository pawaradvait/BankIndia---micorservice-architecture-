package com.bankIndia.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankIndia.constant.LoanConstant;
import com.bankIndia.dto.LoanDto;
import com.bankIndia.entity.Loans;
import com.bankIndia.exception.LoanAlreadyExistExcpetion;
import com.bankIndia.exception.ResourceNotFoundException;
import com.bankIndia.mapper.LoanMapper;
import com.bankIndia.repo.LoanRepo;

@Service
public class LoanServiceImpl implements LoanService{
 
	@Autowired
	private LoanRepo loanrepo;
	
	@Override
	public void createLoan(String mobileNumber) {

		Optional<Loans> loan =loanrepo.findByMobileNumber(mobileNumber);
		if(loan.isPresent()) {
			throw new LoanAlreadyExistExcpetion("LOAN ALREADY EXIST WITH MOBILE NUMBER"+mobileNumber);
			
		}
		
		loanrepo.save(createNewLoan(mobileNumber));
		
	}
	  private Loans createNewLoan(String mobileNumber) {
	        Loans newLoan = new Loans();
	        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
	        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
	        newLoan.setMobileNumber(mobileNumber);
	        newLoan.setLoanType(LoanConstant.HOME_LOAN);
	        newLoan.setTotalLoan(LoanConstant.NEW_LOAN_LIMIT);
	        newLoan.setAmountPaid(0);
	        newLoan.setOutstandingAmount(LoanConstant.NEW_LOAN_LIMIT);
	        return newLoan;
	    }
	@Override
	public LoanDto fetchLoan(String mobileNumber) {

		Loans loan  =loanrepo.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber));
		
		
			LoanDto loanDto =LoanMapper.mapToLoansDto(loan, new LoanDto());
			
		
		
		 
		
		return loanDto;
	}
	@Override
	public boolean updateLoan(LoanDto loansDto) {
		// TODO Auto-generated method stub

		Loans loan  =loanrepo.findByMobileNumber(loansDto.getMobileNumber()).orElseThrow(()-> new ResourceNotFoundException("Loan", "Mobile Number", loansDto.getMobileNumber()));
		loan = LoanMapper.mapToLoans(loansDto, loan);
		loanrepo.save(loan);
		return true;


	}
	@Override
	public boolean deleteLoan(String mobileNumber) {
		// TODO Auto-generated method stub

 Loans loan =		loanrepo.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber));
  
 loanrepo.deleteById(loan.getLoanId());

		return true;
 
}
	
	

}
