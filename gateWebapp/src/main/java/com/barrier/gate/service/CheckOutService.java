package com.barrier.gate.service;

import com.barrier.gate.model.CardDetails;
import com.barrier.gate.repository.CardDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckOutService {

    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    public CardDetails getCardById(Long barcode){
        CardDetails cardDetails  = cardDetailsRepository.findByBarcode(barcode);
        return cardDetails;
    }
}
