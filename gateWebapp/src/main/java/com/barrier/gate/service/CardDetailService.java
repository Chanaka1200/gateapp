package com.barrier.gate.service;

import com.barrier.gate.Controller.CheckingController;
import com.barrier.gate.model.CardDetails;
import com.barrier.gate.model.wrapper.CardDetailsWrapper;
import com.barrier.gate.repository.CardDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CardDetailService {
    private final Logger logger = LoggerFactory.getLogger(CheckingController.class);

    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    public List<CardDetails> getCardDetails(CardDetailsWrapper cardDetailsWrapper){
        List<CardDetails> cardDetails1 = cardDetailsRepository.findAll();
        return  cardDetails1;
    }

    public CardDetails getCardDetailsByBarcode(Long barcode){
        CardDetails cardDetails1 = cardDetailsRepository.findByBarcode(barcode);
        return  cardDetails1;
    }

    public String saveUpdateCardDetails(CardDetailsWrapper carDetailsWrapper, String type){
        CardDetails existsCardDetails = cardDetailsRepository.findByBarcode(carDetailsWrapper.getBarcode());
        if(existsCardDetails != null && type.equals("save")){
            return "barcode number is exists";
        }
        if(existsCardDetails == null && type.equals("update")){
            return "barcode not exists";
        }
        CardDetails cardDetails = new CardDetails();
        cardDetails.setBarcodeId(carDetailsWrapper.getBarcodeId());
        cardDetails.setBarcode(carDetailsWrapper.getBarcode());
        cardDetails.setIssueDate(new Date());
        cardDetails.setStatus(carDetailsWrapper.getStatus());
        cardDetails.setCardType(carDetailsWrapper.getCardType());
        cardDetails.setCardPolicy(carDetailsWrapper.getCardPolicy());
        CardDetails cardDetails1 = cardDetailsRepository.save(cardDetails);
        if(cardDetails1 == null){
            return  "failed to save card details";
        }
        return "save success card details";
    }

}
