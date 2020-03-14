package com.barrier.gate.Controller;

import com.barrier.gate.model.CardDetails;
import com.barrier.gate.model.wrapper.CardDetailsWrapper;
import com.barrier.gate.service.CardDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/gate")
public class CardDetailsController {
    private final Logger logger = LoggerFactory.getLogger(CheckingController.class);

    @Autowired
    private CardDetailService cardDetailService;

    @GetMapping(value = "/cardDetails")
    public ResponseEntity<List<CardDetails>> getCardDetails(@RequestBody CardDetailsWrapper cardDetails){
        try {
            List<CardDetails> cardDetailsList = cardDetailService.getCardDetails(cardDetails);
            logger.info("success get card details");
            return new ResponseEntity<>(cardDetailsList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("print stack get card details"+ e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(value = "/cardDetailsByBarcode")
    @ResponseBody
    public ResponseEntity<CardDetailsWrapper> getCardDetailsByBarcode(@RequestParam Long barcode){
        try {
            CardDetails cardDetails = cardDetailService.getCardDetailsByBarcode(barcode);
            if(cardDetails == null){
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                CardDetailsWrapper cardDetailsWrapper = new CardDetailsWrapper();
                cardDetailsWrapper.setBarcodeId(cardDetails.getBarcodeId());
                cardDetailsWrapper.setBarcode(cardDetails.getBarcode());
                cardDetailsWrapper.setStatus(cardDetails.getStatus());
                cardDetailsWrapper.setCardType(cardDetails.getCardType());
                cardDetailsWrapper.setCardPolicy(cardDetails.getCardPolicy());
                cardDetailsWrapper.setIssueDate(cardDetails.getIssueDate());
                logger.info("success get card details");
                return new ResponseEntity<>(cardDetailsWrapper, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("print stack get card details"+ e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value = "/cardDetails")
    @ResponseBody
    public ResponseEntity<String> postCardDetails(@RequestBody CardDetailsWrapper cardDetails){
        try {
            String cardDetailSave = cardDetailService.saveUpdateCardDetails(cardDetails, "save");
            logger.info("post card details " + cardDetailSave);
             return new ResponseEntity<>(cardDetailSave, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("print stack post card details"+ e);
            return new ResponseEntity<>("failed", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping(value = "/cardDetails")
    public ResponseEntity<String> putCardDetails(@RequestBody CardDetailsWrapper cardDetails){
        try {
            String cardDetailsUpdate = cardDetailService.saveUpdateCardDetails(cardDetails, "update");
            logger.info("post card details " + cardDetailsUpdate);
            return new ResponseEntity<>(cardDetailsUpdate, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("print stack put card details"+ e);
            return new ResponseEntity<>("failed", HttpStatus.EXPECTATION_FAILED);
        }
    }


}
