package com.example.backendfinanfine.validation;

import com.example.backendfinanfine.exceptions.customerExceptions.InvalidCepException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CepValidation {

    public void cepValidator(String cep) {
        String regex = "(^[0-9]{5})-?([0-9]{3}$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cep);
        boolean matchFound = matcher.find();
        if(!matchFound) {
            throw new InvalidCepException();
        }
    }
}
