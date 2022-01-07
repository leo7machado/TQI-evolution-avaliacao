package com.tqievolutionavaliacao.loanbackend.loan.repository;

import com.tqievolutionavaliacao.loanbackend.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByUsername(String username);

}
