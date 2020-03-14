package com.barrier.gate.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "card_operation")
public class CardOperation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer operationId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkIn;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOut;
    private String status;
    private Double price;
    private Boolean paid;
    private String frontImage;
    private String backImage;
    private String frontOutImage;
    private String backOutImage;

    @ManyToOne
    @JoinColumn(name = "barcode_id", nullable = true)
    private CardDetails cardDetailsEntity;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = true)
    private Organization cardOperationOrganizationEntity;

    @OneToMany(mappedBy = "cardOperationEntity",cascade = CascadeType.ALL)
    private Set<CardTransaction> cardTransactionsEntity;

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public CardDetails getCardDetailsEntity() {
        return cardDetailsEntity;
    }

    public void setCardDetailsEntity(CardDetails cardDetailsEntity) {
        this.cardDetailsEntity = cardDetailsEntity;
    }

    public Set<CardTransaction> getCardTransactionsEntity() {
        return cardTransactionsEntity;
    }

    public void setCardTransactionsEntity(Set<CardTransaction> cardTransactionsEntity) {
        this.cardTransactionsEntity = cardTransactionsEntity;
    }

    public String getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public Organization getCardOperationOrganizationEntity() {
        return cardOperationOrganizationEntity;
    }

    public void setCardOperationOrganizationEntity(Organization cardOperationOrganizationEntity) {
        this.cardOperationOrganizationEntity = cardOperationOrganizationEntity;
    }

    public String getFrontOutImage() {
        return frontOutImage;
    }

    public void setFrontOutImage(String frontOutImage) {
        this.frontOutImage = frontOutImage;
    }

    public String getBackOutImage() {
        return backOutImage;
    }

    public void setBackOutImage(String backOutImage) {
        this.backOutImage = backOutImage;
    }
}
