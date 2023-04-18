package com.example.backendfinanfine.validation;

import com.example.backendfinanfine.exceptions.customerExceptions.InvalidPhoneException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidation {

    public void phoneValidator(String phone) {
        String regex = "(^[0-9]{2})?(\\s|-)?(9?[0-9]{4})-?([0-9]{4}$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        boolean matchFound = matcher.find();
        if(!matchFound) {
            throw new InvalidPhoneException();
        }
    }
}
