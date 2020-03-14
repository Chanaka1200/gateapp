package com.barrier.gate.model.wrapper;

import java.io.Serializable;
import java.util.Date;

public class CardDetailsWrapper implements Serializable {
    private Integer barcodeId;
    private Long barcode;
    private Date issueDate;
    private String status;
    private String cardType;
    private String cardPolicy;

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
}
