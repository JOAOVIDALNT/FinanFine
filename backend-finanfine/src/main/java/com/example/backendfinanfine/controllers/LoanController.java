package com.example.backendfinanfine.controllers;

import com.example.backendfinanfine.dtos.LoanDTO;
import com.example.backendfinanfine.dtos.LoanFindDTO;
import com.example.backendfinanfine.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/clientes/{cpf}/emprestimos")
    public ResponseEntity<LoanDTO> create(@PathVariable String cpf, @RequestBody @Valid LoanDTO loanDTO) {
        return new ResponseEntity<>(loanService.create(cpf, loanDTO), HttpStatus.CREATED);
    }

    @GetMapping("/clientes/{cpf}/emprestimos")
    public ResponseEntity<List<LoanFindDTO>> findAllByCpf(@PathVariable String cpf) {
        return new ResponseEntity<>(loanService.findAllByCpf(cpf), HttpStatus.OK);
    }

    @GetMapping("/clientes/{cpf}/emprestimos/{id}")
    public ResponseEntity<LoanFindDTO> findOneByCpfAndId(@PathVariable String cpf, @PathVariable Long id) {
        return new ResponseEntity<>(loanService.findOneByCpfAndId(cpf, id), HttpStatus.OK);
    }

    @DeleteMapping("/clientes/{cpf}/emprestimos/{id}")
    public ResponseEntity<?> delete(@PathVariable String cpf, @PathVariable Long id) {
        loanService.delete(cpf, id);
        return ResponseEntity.noContent().build();
    }
}
