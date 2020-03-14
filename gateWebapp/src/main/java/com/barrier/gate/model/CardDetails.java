package com.barrier.gate.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "card")
public class CardDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer barcodeId;
    private Long barcode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date issueDate;
    private String status;
    private String cardType;
    private String cardPolicy;

    @OneToMany(mappedBy = "cardDetailsEntity",cascade = CascadeType.ALL)
    private Set<CardOperation> cardOperationsEntity;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = true)
    private Organization cardDetailsOrganizationEntity;

    public Integer getBarcodeId() {
        return barcodeId;
    }

    public void setBarcodeId(Integer barcodeId) {
        this.barcodeId = barcodeId;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardPolicy() {
        return cardPolicy;
    }

    public void setCardPolicy(String cardPolicy) {
        this.cardPolicy = cardPolicy;
    }

    public Set<CardOperation> getCardOperationsEntity() {
        return cardOperationsEntity;
    }

    public void setCardOperationsEntity(Set<CardOperation> cardOperationsEntity) {
        this.cardOperationsEntity = cardOperationsEntity;
    }

    public Organization getCardDetailsOrganizationEntity() {
        return cardDetailsOrganizationEntity;
    }

    public void setCardDetailsOrganizationEntity(Organization cardDetailsOrganizationEntity) {
        this.cardDetailsOrganizationEntity = cardDetailsOrganizationEntity;
    }
}
