package com.example.backendfinanfine.dtos;

import com.example.backendfinanfine.enums.Relationship;

import java.math.BigDecimal;
import java.util.Date;

public class LoanDTO {

    private BigDecimal initialAmount;
    private Relationship relationship;
    private Date initialDate;
    private Date finalDate;


    public BigDecimal getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(BigDecimal initialAmount) {
        this.initialAmount = initialAmount;
    }


    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
}
