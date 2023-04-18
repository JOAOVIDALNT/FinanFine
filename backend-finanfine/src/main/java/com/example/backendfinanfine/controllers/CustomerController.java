package com.example.backendfinanfine.controllers;

import com.example.backendfinanfine.dtos.CustomerDTO;
import com.example.backendfinanfine.dtos.CustomerUpdateDTO;
import com.example.backendfinanfine.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping("/clientes")
    public ResponseEntity<List<CustomerDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/clientes/{cpf}")
    public ResponseEntity<CustomerDTO> findByCpf(@PathVariable String cpf) {
        return new ResponseEntity<>(service.findByCpf(cpf), HttpStatus.OK);
    }

    @PostMapping("/clientes")
    public ResponseEntity<CustomerDTO> create(@RequestBody @Valid CustomerDTO customerDTO) {
        return new ResponseEntity<>(service.create(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/clientes/{cpf}")
    public ResponseEntity<CustomerUpdateDTO> update(@PathVariable String cpf, @RequestBody CustomerUpdateDTO customerDTO) {
        return new ResponseEntity<>(service.update(cpf, customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/clientes/{cpf}")
    public ResponseEntity<?> delete(@PathVariable String cpf) {
        service.delete(cpf);
        return ResponseEntity.noContent().build();
    }


}
