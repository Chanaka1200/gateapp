package com.barrier.gate.Controller;

import com.barrier.gate.model.CardOperation;
import com.barrier.gate.model.wrapper.CardOperationWrapper;
import com.barrier.gate.service.CardOperationDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/gate")
public class CardOperationController {
    private final Logger logger = LoggerFactory.getLogger(CheckingController.class);

    @Autowired
    private CardOperationDetailService cardOperationDetailService;

    @GetMapping(value = "/cardOperationDetails")
    public ResponseEntity<List<CardOperation>> getCardOperationDetails(@RequestBody CardOperationWrapper cardOperationWrapper){
        try {
             List<CardOperation> cardOperationDetailsList = cardOperationDetailService.getCardOperationDetails(cardOperationWrapper);
             return new ResponseEntity<>(cardOperationDetailsList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("print stack get card operation"+ e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(value = "/cardOperationDetailsByBarcode")
    public ResponseEntity<CardOperationWrapper> getCardOperationDetailsByBarcode(@RequestParam Long barcode){
        try {
            CardOperationWrapper cardOperation = cardOperationDetailService.getCardOperationDetailsByBarcode(barcode);
            return new ResponseEntity<>(cardOperation, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("print stack get card operation"+ e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value = "/cardOperationDetails")
    public ResponseEntity<String> postCardOperationDetails(@RequestBody CardOperationWrapper cardOperationWrapper){
        try {
            String cardOperationSave = cardOperationDetailService.saveUpdateCardDetails(cardOperationWrapper, "save");
            logger.info("post card operation " + cardOperationSave);
            return new ResponseEntity<>(cardOperationSave, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("print stack post card operation"+ e);
            return new ResponseEntity<>("failed", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping(value = "/cardOperationDetails")
    public ResponseEntity<String> putCardOperationDetails(@RequestBody CardOperationWrapper cardOperationWrapper){
        try {
            String cardOperationUpdate = cardOperationDetailService.saveUpdateCardDetails(cardOperationWrapper, "update");
            logger.info("post card operation " + cardOperationUpdate);
            return new ResponseEntity<>(cardOperationUpdate, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("print stack put card operation"+ e);
            return new ResponseEntity<>("failed", HttpStatus.EXPECTATION_FAILED);
        }
    }
}
