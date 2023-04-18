package com.example.backendfinanfine.models;

import com.example.backendfinanfine.enums.Relationship;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Customer customer;
    @NotNull(message = "Required: Initial Amount")
    private BigDecimal initialAmount;
    @NotNull(message = "Required: final Amount")
    private BigDecimal finalAmount;
    private Relationship relationship;
    private Date initialDate;
    private Date finalDate;

    public Loan() {
    }

    public Loan(Customer customer, BigDecimal initialAmount, BigDecimal finalAmount, Relationship relationship, Date initialDate, Date finalDate) {
        this.customer = customer;
        this.initialAmount = initialAmount;
        this.finalAmount = finalAmount;
        this.relationship = relationship;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(BigDecimal initialAmount) {
        this.initialAmount = initialAmount;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
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

    public BigDecimal loanCalc(BigDecimal initialAmount, int loans) {
        return this.relationship.loanCalc(initialAmount, loans);
    }
}
