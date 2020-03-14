package com.barrier.gate.service;

import com.barrier.gate.Controller.CheckingController;
import com.barrier.gate.model.CardDetails;
import com.barrier.gate.model.CardOperation;
import com.barrier.gate.model.CardTransaction;
import com.barrier.gate.model.wrapper.CardOperationWrapper;
import com.barrier.gate.repository.CardDetailsRepository;
import com.barrier.gate.repository.CardOperationRepository;
import com.barrier.gate.repository.CardTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class CardOperationDetailService {
    private final Logger logger = LoggerFactory.getLogger(CheckingController.class);

    @Autowired
    private CardDetailsRepository cardDetailsRepository;
    @Autowired
    private CardOperationRepository cardOperationRepository;
    @Autowired
    private CardTransactionRepository cardTransactionRepository;
    @Autowired
    ServletContext servletContext;

    public List<CardOperation> getCardOperationDetails(CardOperationWrapper  cardOperationWrapper){
        List<CardOperation> cardDetails1 = cardOperationRepository.findAll();
        return  cardDetails1;
    }

    public CardOperationWrapper getCardOperationDetailsByBarcode(Long barcode){
        CardOperation cardDetails1 = cardOperationRepository.findTopByCardDetailsEntity_BarcodeAndCardDetailsEntity_StatusOrderByOperationIdDesc(barcode, "active");
        CardOperationWrapper cardOperationWrapper = new CardOperationWrapper();
        if(cardDetails1 != null){
            cardOperationWrapper.setOperationId(cardDetails1.getOperationId());
            cardOperationWrapper.setCheckIn(cardDetails1.getCheckIn());
            cardOperationWrapper.setCheckOut(cardDetails1.getCheckOut());
            cardOperationWrapper.setStatus(cardDetails1.getStatus());
            cardOperationWrapper.setPrice(cardDetails1.getPrice());
            cardOperationWrapper.setPaid(cardDetails1.getPaid());
            cardOperationWrapper.setBarcode(cardDetails1.getCardDetailsEntity().getBarcode());
            String frontImage = getImagePath(cardDetails1.getFrontImage());
            cardOperationWrapper.setFrontImage(frontImage);
            String backImage = getImagePath(cardDetails1.getBackImage());
            cardOperationWrapper.setBackImage(backImage);
            String frontOutImage = getImagePath(cardDetails1.getFrontOutImage());
            cardOperationWrapper.setFrontOutImage(frontOutImage);
            String backOutImage = getImagePath(cardDetails1.getBackOutImage());
            cardOperationWrapper.setBackOutImage(backOutImage);

            cardOperationWrapper.setFrontImageFilePath(cardDetails1.getFrontImage());
            cardOperationWrapper.setBackImageFilePath(cardDetails1.getBackImage());
            cardOperationWrapper.setFrontOutImageFilePath(cardDetails1.getFrontOutImage());
            cardOperationWrapper.setBackOutImageFilePath(cardDetails1.getBackOutImage());
            return  cardOperationWrapper;
        }
        return  cardOperationWrapper;
    }

    @Transactional
    public String saveUpdateCardDetails(CardOperationWrapper cardOperationWrapper, String type){
        String status = null;
        CardOperation existsCardOperation = cardOperationRepository.findTopByCardDetailsEntity_BarcodeAndCardDetailsEntity_StatusOrderByOperationIdDesc(cardOperationWrapper.getBarcode(), "active");
        if(existsCardOperation != null && type.equals("save") && existsCardOperation.getPaid() == false){
            return "this operation data not paid!. Please update it first";
        }
        if(existsCardOperation != null && type.equals("update") && existsCardOperation.getPaid() == true){
            return "this operation data is already paid!.";
        }
        CardDetails cardDetails = cardDetailsRepository.findByBarcode(cardOperationWrapper.getBarcode());
        if(cardDetails == null && type.equals("save")){
            return "barcode not registered!. Please check this barcode is registered barcode";
        }
        if(cardDetails == null && type.equals("update")){
            return "barcode not registered!. Please check this barcode is registered barcode";
        }
        CardOperation cardDetails1 = new CardOperation();
        cardDetails1.setOperationId(cardOperationWrapper.getOperationId());
        if(type.equals("save")){
            cardDetails1.setCheckIn(new Date());
        } else {
            cardDetails1.setCheckIn(cardOperationWrapper.getCheckIn());
        }
        if(type.equals("update")){
            cardDetails1.setCheckOut(new Date());
        } else {
            cardDetails1.setCheckOut(cardOperationWrapper.getCheckOut());
        }
        cardDetails1.setStatus(cardOperationWrapper.getStatus());
        cardDetails1.setPrice(cardOperationWrapper.getPrice());
        cardDetails1.setPaid(cardOperationWrapper.getPaid());
        cardDetails1.setCardDetailsEntity(cardDetails);
        if(cardOperationWrapper.getFrontImageFilePath().equals("")){
            String frontFile = setImagePath(cardOperationWrapper.getFrontImage());
            cardDetails1.setFrontImage(frontFile);
        } else {
            cardDetails1.setFrontImage(cardOperationWrapper.getFrontImageFilePath());
        }
        if(cardOperationWrapper.getBackImageFilePath().equals("")){
            String backFile = setImagePath(cardOperationWrapper.getBackImage());
            cardDetails1.setBackImage(backFile);
        } else {
            cardDetails1.setBackImage(cardOperationWrapper.getBackImageFilePath());
        }
        if(cardOperationWrapper.getFrontOutImageFilePath().equals("")){
            String frontOutFile = setImagePath(cardOperationWrapper.getFrontOutImage());
            cardDetails1.setFrontOutImage(frontOutFile);
        } else {
            cardDetails1.setFrontOutImage(cardOperationWrapper.getFrontOutImageFilePath());
        }
        if(cardOperationWrapper.getBackOutImageFilePath().equals("")){
            String backOutFile = setImagePath(cardOperationWrapper.getBackOutImage());
            cardDetails1.setBackOutImage(backOutFile);
        } else {
            cardDetails1.setBackOutImage(cardOperationWrapper.getBackOutImageFilePath());
        }
        CardOperation cardOperation = cardOperationRepository.save(cardDetails1);
        if(cardOperation != null){
            status = "save success operation data";
        } else {
            status = "failed to save operation data";
        }

        CardTransaction cardTransactionSave = null;
        if(cardOperationWrapper.getPrice() != 0.00){
            CardTransaction cardTransaction = new CardTransaction();
            cardTransaction.setTransactionId(0);
            cardTransaction.setCredit(cardOperationWrapper.getPrice());
            cardTransaction.setDebit(0.00);
            cardTransaction.setDate(new Date());
            CardOperation cardOperation1 = cardOperationRepository.findById(cardOperationWrapper.getOperationId()).get();
            cardTransaction.setCardOperationEntity(cardOperation1);
            cardTransactionSave = cardTransactionRepository.save(cardTransaction);
            if(cardOperation != null && cardTransactionSave != null){
                status = "payment success for operation data";
            } else {
                status = "payment failed for this operation data";
            }
        }
        return  status;
    }

    private String setImagePath(String base64File){
        byte[] data = Base64.getDecoder().decode(base64File);
        String webInfPath = servletContext.getRealPath("WEB-INF" );
        String downloadPath = webInfPath + "/attachments/";
        int fileRandomName = 10000 + new Random().nextInt(90000);
        String fileLocation = downloadPath + fileRandomName + ".jpg";
        try {
            OutputStream outputStream = new FileOutputStream(fileLocation);
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/attachments/" + fileRandomName + ".jpg";
    }

    private String getImagePath(String filePath){
        String webInfPath = servletContext.getRealPath("WEB-INF" );
        String fileLocation = webInfPath + filePath;
        String encodedFile = null;
        File f =  new File(fileLocation);
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(f);
            byte[] bytes = new byte[(int)f.length()];
            fileInputStreamReader.read(bytes);
            encodedFile = Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  encodedFile;
    }
}
