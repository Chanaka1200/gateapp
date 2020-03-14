package com.barrier.gate.repository;

import com.barrier.gate.model.CardOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardOperationRepository extends JpaRepository<CardOperation, Integer> {
    CardOperation findTopByCardDetailsEntity_BarcodeAndCardDetailsEntity_StatusOrderByOperationIdDesc(Long barcode, String status);
    CardOperation findFirstByCardDetailsEntity_BarcodeOrderByOperationId(Long barcode);
}
