package com.tqievolutionavaliacao.loanbackend.loan.controller;

import com.tqievolutionavaliacao.loanbackend.loan.service.LoanService;
import com.tqievolutionavaliacao.loanbackend.loan.model.Loan;
import com.tqievolutionavaliacao.loanbackend.loan.model.LoanDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/loan")
@AllArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<Loan> requestLoan(@RequestBody @Valid LoanDTO loanDTO, Principal principal) {
        return new ResponseEntity<>(loanService.registerLoanRequest(loanDTO, principal), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAllLoans(Principal principal) {
        return new ResponseEntity<>(loanService.findAll(principal), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Loan>> getLoanById(@PathVariable Long id, Principal principal) {
        return new ResponseEntity<>(loanService.findById(id, principal), HttpStatus.OK);
    }

}
