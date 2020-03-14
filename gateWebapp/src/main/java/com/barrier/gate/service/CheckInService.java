package com.barrier.gate.service;

import com.barrier.gate.model.CardDetails;
import com.barrier.gate.repository.CardDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckInService {

    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    public Boolean postCardDetails(CardDetails barCode) throws Exception{
        CardDetails cardDetails = cardDetailsRepository.save(barCode);
        if(cardDetails == null){
            return  false;
        }
        return true;
    }
}
