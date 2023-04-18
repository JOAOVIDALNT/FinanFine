package com.example.backendfinanfine.repositories;

import com.example.backendfinanfine.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCpf(String cpf);
    void deleteByCpf(String cpf);
}
