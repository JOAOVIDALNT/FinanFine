package com.example.backendfinanfine.services;

import com.example.backendfinanfine.dtos.CustomerDTO;
import com.example.backendfinanfine.dtos.CustomerUpdateDTO;
import com.example.backendfinanfine.exceptions.customerExceptions.*;
import com.example.backendfinanfine.mapper.ModelMap;
import com.example.backendfinanfine.models.Customer;
import com.example.backendfinanfine.repositories.CustomerRepository;
import com.example.backendfinanfine.validation.CepValidation;
import com.example.backendfinanfine.validation.CpfValidation;
import com.example.backendfinanfine.validation.PhoneValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CustomerService {
    private Logger logger = Logger.getLogger(CustomerService.class.getName());

    @Autowired
    private CustomerRepository repository;

    public List<CustomerDTO> findAll() {
        logger.info("Finding all customers");
        return ModelMap.parseListObjects(repository.findAll(), CustomerDTO.class);
    }

    public CustomerDTO findByCpf(String cpf) {
        logger.info("Finding one customer");
        Customer entity = repository.findByCpf(cpf);
        if(entity == null) {
            throw new CustomerNotFoundException();
        }
        return ModelMap.parseObject(entity, CustomerDTO.class);
    }

    public CustomerDTO create(@Valid CustomerDTO customerDTO) {
        logger.info("Inserting one customer");
        // VERIFICA SE O CPF JÁ ESTÁ CADASTRADO E SE JÁ ESTIVER RETORNA UMA EXCEÇÃO PERSONALIZADA
        Customer isCpfPersistedAlready = repository.findByCpf(customerDTO.getCpf());
        if(isCpfPersistedAlready != null) {
            throw new CpfAlreadyRegistered();
        }
        // VALIDA O CPF E SE NÃO FOR UM CPF VÁLIDO RETORNA UMA EXCEÇÃO PERSONALIZADA
        CpfValidation cpfValidation = new CpfValidation();
        boolean isCpfValid = cpfValidation.valida(customerDTO.getCpf());
        if(!isCpfValid) {
            throw new InvalidCpfException();
        }
        // VALIDA O CEP E SE NÃO FOR UM CEP VÁLIDO RETORNA UMA EXCEÇÃO PERSONALIZADA
        CepValidation cepValidation = new CepValidation();
        cepValidation.cepValidator(customerDTO.getCep());

        // VALIDA O TELEFONE E SE NÃO FOR UM TELEFONE VÁLIDO RETORNA UMA EXCEÇÃO PERSONALZADA
        PhoneValidation phoneValidation = new PhoneValidation();
        phoneValidation.phoneValidator(customerDTO.getPhone());
        // VERIFICA SE NOME, ENDEREÇO E NÚMERO SÃO VÁLIDOS
        if (customerDTO.getName().length() < 3) {
            throw new InvalidNameException();
        } else if (customerDTO.getAddress().length() < 5) {
            throw new InvalidAddressException();
        } else if (customerDTO.getAddressNumber().length() < 1 || customerDTO.getAddressNumber().length() > 5) {
            throw new InvalidAddressException();
        }


        Customer entity = ModelMap.parseObject(customerDTO, Customer.class);
        CustomerDTO dto = ModelMap.parseObject(repository.save(entity), CustomerDTO.class);
        return dto;
    }

    public CustomerUpdateDTO update(String cpf, CustomerUpdateDTO customerDTO) {
        logger.info("Updating one customer");

        Customer entity = repository.findByCpf(cpf);
        if(entity == null) {
            throw new CustomerNotFoundException();
        }
        entity.setName(customerDTO.getName());
        entity.setPhone(customerDTO.getPhone());
        entity.setAddress(customerDTO.getAddress());
        entity.setAddressNumber(customerDTO.getAddressNumber());
        entity.setMonthlyIncome(customerDTO.getMonthlyIncome());

        // VALIDA O CEP E SE NÃO FOR UM CEP VÁLIDO RETORNA UMA EXCEÇÃO PERSONALIZADA
        CepValidation cepValidation = new CepValidation();
        cepValidation.cepValidator(customerDTO.getCep());

        // VALIDA O TELEFONE E SE NÃO FOR UM TELEFONE VÁLIDO RETORNA UMA EXCEÇÃO PERSONALZADA
        PhoneValidation phoneValidation = new PhoneValidation();
        phoneValidation.phoneValidator(customerDTO.getPhone());
        // VERIFICA SE NOME, ENDEREÇO E NÚMERO SÃO VÁLIDOS
        if (customerDTO.getName().length() < 3) {
            throw new InvalidNameException();
        } else if (customerDTO.getAddress().length() < 5) {
            throw new InvalidAddressException();
        } else if (customerDTO.getAddressNumber().length() < 1 || customerDTO.getAddressNumber().length() > 5) {
            throw new InvalidAddressException();
        }

        CustomerUpdateDTO dto = ModelMap.parseObject(repository.save(entity), CustomerUpdateDTO.class);
        return dto;
    }

    public void delete(String cpf) {
        logger.info("Deleting one customer");
        Customer entity = repository.findByCpf(cpf);
        if(entity == null) {
            throw new CustomerNotFoundException();
        }
        repository.delete(entity);
    }

}
