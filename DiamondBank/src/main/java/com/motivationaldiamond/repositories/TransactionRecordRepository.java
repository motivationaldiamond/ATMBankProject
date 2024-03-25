package com.motivationaldiamond.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.motivationaldiamond.entities.TransactionRecord;

@Repository
public interface TransactionRecordRepository extends JpaRepository<TransactionRecord, Long> {
	
	List<TransactionRecord> findByAccount_AccountNumber(long accountNumber);
}
