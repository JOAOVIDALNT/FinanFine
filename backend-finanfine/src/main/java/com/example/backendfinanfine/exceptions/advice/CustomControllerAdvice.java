package com.example.backendfinanfine.exceptions.advice;

import com.example.backendfinanfine.exceptions.customerExceptions.*;
import com.example.backendfinanfine.exceptions.handler.MessageExceptionHandler;
import com.example.backendfinanfine.exceptions.loanExceptions.LoanNotAllowedException;
import com.example.backendfinanfine.exceptions.loanExceptions.LoanNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice(basePackages = "com.example.backendfinanfine.controllers")
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> customerNotFound(CustomerNotFoundException customerNotFoundException) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "Cliente não encontrado");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ResponseBody
    @ExceptionHandler(InvalidCpfException.class)
    public ResponseEntity<MessageExceptionHandler> cpfInvalid(InvalidCpfException invalidCpfException) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), "Cpf inválido");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(CpfAlreadyRegistered.class)
    public ResponseEntity<MessageExceptionHandler> cpfAlreadyRegistered(CpfAlreadyRegistered cpfAlreadyRegistered) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), "O cpf informado já está cadastrado");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(InvalidCepException.class)
    public ResponseEntity<MessageExceptionHandler> cepInvalid(InvalidCepException invalidCepException) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), "Cep inválido");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(InvalidPhoneException.class)
    public ResponseEntity<MessageExceptionHandler> phoneInvalid(InvalidPhoneException invalidPhoneException) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), "Telefone inválido");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<MessageExceptionHandler> invalidName(InvalidNameException invalidNameException) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), "Nome inválido");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(InvalidAddressException.class)
    public ResponseEntity<MessageExceptionHandler> invalidAddress(InvalidAddressException invalidAddressException) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), "Endereço inválido");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(LoanNotAllowedException.class)
    public ResponseEntity<MessageExceptionHandler> loanNotAllowed(LoanNotAllowedException loanNotAllowedException) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.FORBIDDEN.value(), "Limite para empréstimo excedido");
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ResponseBody
    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> loanNotFound(LoanNotFoundException loanNotFoundException) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "Empréstimo não encontrado");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
