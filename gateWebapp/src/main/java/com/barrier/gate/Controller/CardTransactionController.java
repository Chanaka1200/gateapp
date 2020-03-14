package com.barrier.gate.Controller;

import com.barrier.gate.model.CardTransaction;
import com.barrier.gate.model.wrapper.CardTransactionWrapper;
import com.barrier.gate.service.CardTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/gate")
public class CardTransactionController {
    private final Logger logger = LoggerFactory.getLogger(CheckingController.class);

    @Autowired
    private CardTransactionService cardTransactionService;
    @GetMapping(value = "/getAllDataByDateAndTypeAndPageable")
    private ResponseEntity<List<CardTransactionWrapper>> getAll(@RequestParam String startDate, @RequestParam String endDateParse,@RequestParam Integer pageNo,@RequestParam Integer pageSize,@RequestParam String sortBy,@RequestParam String sortOrder){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date startDateParse = formatter.parse(startDate);
            Date endDateParses = formatter.parse(endDateParse);
            List<CardTransactionWrapper> cardTransactionWrapperList = cardTransactionService.getCardOperationDetailsByDateAndTypeAndPageable(startDateParse, endDateParses, pageNo, pageSize,sortBy, sortOrder);
            return new ResponseEntity<>(cardTransactionWrapperList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   // @GetMapping(value = "/getAllDataByDateAndTypeAndPageable")
    public ResponseEntity<List<CardTransactionWrapper>> getAllClientByDateAndTypeAndPagable(@RequestParam String startDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            Date startDateParse = formatter.parse(startDate);
          //  Date endDateParse = formatter.parse(endDate);
            /*List<CardTransactionWrapper> cardTransactionWrapperList = cardTransactionService.getCardOperationDetailsByDateAndTypeAndPageable(startDateParse, endDateParse, pageNo, pageSize,sortBy, sortOrder);*/
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
