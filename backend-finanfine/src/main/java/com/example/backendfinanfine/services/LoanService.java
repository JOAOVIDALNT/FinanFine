package com.example.backendfinanfine.services;

import com.example.backendfinanfine.dtos.CustomerUpdateDTO;
import com.example.backendfinanfine.dtos.LoanDTO;
import com.example.backendfinanfine.dtos.LoanFindDTO;
import com.example.backendfinanfine.exceptions.customerExceptions.CustomerNotFoundException;
import com.example.backendfinanfine.exceptions.loanExceptions.LoanNotAllowedException;
import com.example.backendfinanfine.exceptions.loanExceptions.LoanNotFoundException;
import com.example.backendfinanfine.mapper.ModelMap;
import com.example.backendfinanfine.models.Customer;
import com.example.backendfinanfine.models.Loan;
import com.example.backendfinanfine.repositories.CustomerRepository;
import com.example.backendfinanfine.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

@Service
public class LoanService {

    private Logger logger = Logger.getLogger(LoanService.class.getName());

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    public LoanDTO create(String cpf, LoanDTO loanDTO) {
        logger.info("Creating one loan");
        // VERIFICA SE O CPF É CADASTRADO
        Customer customer = customerRepository.findByCpf(cpf);
        if(customer == null) {
            throw new CustomerNotFoundException();
        }
        BigDecimal customerMonthlyIncome = customer.getMonthlyIncome();
        BigDecimal loanLimit = customerMonthlyIncome.multiply(new BigDecimal(10));

        // VERIFICA SE O USUÁRIO TEM LIMITE DE EMPRÉSTIMO DISPONÍVEL
        BigDecimal totalLoans = customer.calcLoans();
        if(totalLoans.compareTo(loanLimit) == 1 || totalLoans.compareTo(loanLimit) == 0) {
            throw new LoanNotAllowedException();
        }
        int loans = customer.getLoans().size();
        Loan loan = ModelMap.parseObject(loanDTO, Loan.class);
        // CALCULA O JUROS DO EMPRÉSTIMO
        BigDecimal result = loan.loanCalc(loanDTO.getInitialAmount(), loans);
        BigDecimal finalAmount = result;

        Loan entity = new Loan(
                customer,
                loanDTO.getInitialAmount(),
                finalAmount,
                loanDTO.getRelationship(),
                loanDTO.getInitialDate(),
                loanDTO.getFinalDate()
        );
        customer.addLoan(entity);

        LoanDTO dto = ModelMap.parseObject(loanRepository.save(entity), LoanDTO.class);
        return dto;
    }

    public List<LoanFindDTO> findAllByCpf(String cpf) {
        logger.info("Finding all loans");

        Customer customer = customerRepository.findByCpf(cpf);
        if(customer == null) {
            throw new CustomerNotFoundException();
        }

        return ModelMap.parseListObjects(loanRepository.findAllByCustomerCpf(cpf), LoanFindDTO.class);
    }

    public LoanFindDTO findOneByCpfAndId(String cpf, Long id) {
        logger.info("Finding loan by id");

        Customer customer = customerRepository.findByCpf(cpf);
        if(customer == null) {
            throw new CustomerNotFoundException();
        }

        return ModelMap.parseObject(loanRepository.findByCustomerCpfAndId(cpf, id), LoanFindDTO.class);
    }

    public void delete(String cpf, Long id) {
        logger.info("Deleting one loan");
        Customer customer = customerRepository.findByCpf(cpf);

        if(customer == null) {
            throw new CustomerNotFoundException();
        }

        Loan loan = loanRepository.findById(id).orElseThrow(() -> new LoanNotFoundException());
        customer.removeLoan(loan);
        CustomerUpdateDTO customerUpdateDTO = ModelMap.parseObject(customer, CustomerUpdateDTO.class);

        customerService.update(cpf, customerUpdateDTO);
        loanRepository.delete(loan);
    }
}
