package com.example.backendfinanfine.validation;

import br.com.caelum.stella.validation.CPFValidator;

public class CpfValidation {

    public boolean valida(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        try {
            cpfValidator.assertValid(cpf);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
