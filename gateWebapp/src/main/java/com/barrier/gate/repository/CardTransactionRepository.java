package com.barrier.gate.repository;

import com.barrier.gate.model.CardTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface CardTransactionRepository extends JpaRepository<CardTransaction, Long> {
    Page<CardTransaction> findAllByDateBetween(Date startDate, Date endDate, Pageable pageable);
}
