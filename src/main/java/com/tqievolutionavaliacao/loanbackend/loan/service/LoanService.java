package com.tqievolutionavaliacao.loanbackend.loan.service;

import com.tqievolutionavaliacao.loanbackend.loan.repository.LoanRepository;
import com.tqievolutionavaliacao.loanbackend.loan.model.Loan;
import com.tqievolutionavaliacao.loanbackend.loan.model.LoanDTO;
import com.tqievolutionavaliacao.loanbackend.loan.model.LoanStatus;
import com.tqievolutionavaliacao.loanbackend.user.model.User;
import com.tqievolutionavaliacao.loanbackend.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    public Loan registerLoanRequest(LoanDTO loanDTO, Principal principal) {
        // Utilizar Principal para adiquirir o nome do usuário
        String userName = principal.getName();
        Optional<User> byEmail = userRepository.findByEmail(userName);
        LocalDate firstPaymentDate = loanDTO.getFirstPaymentDate();

        // Limitar prazo da primeira parcela
        if (!firstPaymentDate.isBefore(LocalDate.now().plusMonths(3)) && firstPaymentDate.isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First payment can't be over 3 months");
        }

        // Limitar quantidade máxima de parcelas em 60
        Integer term = loanDTO.getTerm();
        if (term > 60 ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Maximum months is 60");
        }

        Loan loan = new Loan(
                loanDTO.getAmount(),
                loanDTO.getTerm()
        );
        loan.setUsername(userName);
        loan.setFirstPaymentDate(loanDTO.getFirstPaymentDate());
        loan.setCreatedAt(LocalDateTime.now());
        loan.setDueDate(loan.getFirstPaymentDate().plusMonths(loanDTO.getTerm()));
        loan.setLoanStatus(LoanStatus.ANALYSIS);
        loan.setIncome(byEmail.get().getIncome());

        // Rejeitar se a parcela for maior que a renda do usuário
        if (loan.getAmount()/loan.getTerm() > loan.getIncome()) {
            loan.setLoanStatus(LoanStatus.REJECTED);
        }


        return loanRepository.save(loan);
    }

    public List<LoanDTO> findAll(Principal principal) {
        String userName = principal.getName();
        List<LoanDTO> listByEmail = loanRepository.findByUsername(userName).stream().map(loan -> mapToDTO(loan, new LoanDTO())).collect(Collectors.toList());
        return listByEmail;
    }

    public Optional<Loan> findById(Long id, Principal principal) {
        String userName = principal.getName();
        Optional<Loan> byId = loanRepository.findById(id);
        if (!byId.get().getUsername().equals(userName)) {
            throw new AuthorizationServiceException("Not authorized");
        }

        return byId;
    }

    private LoanDTO mapToDTO(final Loan loan, final LoanDTO loanDTO) {
        loanDTO.setId(loan.getId());
        loanDTO.setAmount(loan.getAmount());
        loanDTO.setTerm(loan.getTerm());
        loanDTO.setFirstPaymentDate(loan.getFirstPaymentDate());
        return loanDTO;
    }

}
