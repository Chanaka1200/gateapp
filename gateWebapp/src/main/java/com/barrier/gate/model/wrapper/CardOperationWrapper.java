package com.barrier.gate.model.wrapper;

import java.util.Date;

public class CardOperationWrapper {
    private Integer operationId;
    private Date checkIn;
    private Date checkOut;
    private String status;
    private Double price;
    private Boolean paid;
    private Long barcode;
    private String frontImage;
    private String backImage;
    private String frontImageFilePath;
    private String backImageFilePath;
    private String frontOutImage;
    private String frontOutImageFilePath;
    private String backOutImage;
    private String backOutImageFilePath;

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

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
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

    public String getFrontImageFilePath() {
        return frontImageFilePath;
    }

    public void setFrontImageFilePath(String frontImageFilePath) {
        this.frontImageFilePath = frontImageFilePath;
    }

    public String getBackImageFilePath() {
        return backImageFilePath;
    }

    public void setBackImageFilePath(String backImageFilePath) {
        this.backImageFilePath = backImageFilePath;
    }

    public String getFrontOutImage() {
        return frontOutImage;
    }

    public void setFrontOutImage(String frontOutImage) {
        this.frontOutImage = frontOutImage;
    }

    public String getFrontOutImageFilePath() {
        return frontOutImageFilePath;
    }

    public void setFrontOutImageFilePath(String frontOutImageFilePath) {
        this.frontOutImageFilePath = frontOutImageFilePath;
    }

    public String getBackOutImage() {
        return backOutImage;
    }

    public void setBackOutImage(String backOutImage) {
        this.backOutImage = backOutImage;
    }

    public String getBackOutImageFilePath() {
        return backOutImageFilePath;
    }

    public void setBackOutImageFilePath(String backOutImageFilePath) {
        this.backOutImageFilePath = backOutImageFilePath;
    }
}
