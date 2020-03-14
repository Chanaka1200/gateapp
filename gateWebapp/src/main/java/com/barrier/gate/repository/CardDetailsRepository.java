package com.barrier.gate.repository;

import com.barrier.gate.model.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDetailsRepository extends JpaRepository<CardDetails, Long> {
    CardDetails findByBarcode(Long barcode);
}
