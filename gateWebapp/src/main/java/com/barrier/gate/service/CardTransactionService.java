package com.barrier.gate.service;

import com.barrier.gate.Controller.CheckingController;
import com.barrier.gate.model.CardTransaction;
import com.barrier.gate.model.wrapper.CardOperationWrapper;
import com.barrier.gate.model.wrapper.CardTransactionWrapper;
import com.barrier.gate.repository.CardTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CardTransactionService {
    private final Logger logger = LoggerFactory.getLogger(CheckingController.class);

    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    public List<CardTransactionWrapper> getCardOperationDetailsByDateAndTypeAndPageable(Date startDate, Date endDate, Integer pageNo, Integer pageSize, String sortBy, String sortOrder){
        Pageable paging = null;
        if(sortOrder.equals("asc")){
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        } else {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        }
        List<CardTransactionWrapper> cardTransactionWrapperList = null;
        Page<CardTransaction> getTransactionData = cardTransactionRepository.findAllByDateBetween(startDate, endDate, paging);
        CardTransactionWrapper cardTransactionWrapper = new CardTransactionWrapper();
        for(CardTransaction cardTransaction: getTransactionData ){
            cardTransactionWrapper.setTransactionId(cardTransaction.getTransactionId());
            cardTransactionWrapper.setBarcode(cardTransaction.getCardOperationEntity().getCardDetailsEntity().getBarcode());
            cardTransactionWrapper.setCheckIn(cardTransaction.getCardOperationEntity().getCheckIn());
            cardTransactionWrapper.setCheckOut(cardTransaction.getCardOperationEntity().getCheckOut());
            cardTransactionWrapper.setCredit(cardTransaction.getCredit());
            cardTransactionWrapper.setDebit(cardTransaction.getDebit());
            cardTransactionWrapper.setDate(cardTransaction.getDate());
            cardTransactionWrapperList.add(cardTransactionWrapper);
        }
        return  cardTransactionWrapperList;
    }
}
