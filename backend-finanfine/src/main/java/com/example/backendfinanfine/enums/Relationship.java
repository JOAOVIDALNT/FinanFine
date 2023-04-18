package com.example.backendfinanfine.enums;

import java.math.BigDecimal;

public enum Relationship {

    BRONZE {
        @Override
        public BigDecimal loanCalc(BigDecimal initialAmount, int loans) {
            return initialAmount.multiply(new BigDecimal("1.8"));
        }
    },
    SILVER {
        @Override
        public BigDecimal loanCalc(BigDecimal initialAmount, int loans) {
            if(initialAmount.compareTo(new BigDecimal("5000")) > 0) {
                return initialAmount.multiply(new BigDecimal("1.4"));
            }
                return initialAmount.multiply(new BigDecimal("1.6"));

        }
    },
    GOLD {
        @Override
        public BigDecimal loanCalc(BigDecimal initialAmount, int loans) {
           if(loans <= 1) {
               return initialAmount.multiply(new BigDecimal("1.2"));
           }

           return initialAmount.multiply(new BigDecimal("1.3"));

    }
};


    public abstract BigDecimal loanCalc(BigDecimal initialAmount, int loans);
}
