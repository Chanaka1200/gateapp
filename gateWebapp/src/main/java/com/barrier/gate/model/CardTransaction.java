package com.barrier.gate.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "card_transaction")
public class CardTransaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    private Double debit;
    private Double credit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "operation_id", nullable = true)
    private CardOperation cardOperationEntity;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = true)
    private Organization cardTransactionOrganizationEntity;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CardOperation getCardOperationEntity() {
        return cardOperationEntity;
    }

    public void setCardOperationEntity(CardOperation cardOperationEntity) {
        this.cardOperationEntity = cardOperationEntity;
    }

    public Organization getCardTransactionOrganizationEntity() {
        return cardTransactionOrganizationEntity;
    }

    public void setCardTransactionOrganizationEntity(Organization cardTransactionOrganizationEntity) {
        this.cardTransactionOrganizationEntity = cardTransactionOrganizationEntity;
    }
}
