package com.example.backendfinanfine.repositories;

import com.example.backendfinanfine.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findAllByCustomerCpf(String cpf);
    Loan findByCustomerCpfAndId(String cpf, Long id);
}
