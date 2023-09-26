package org.fasttrack.repository;

import org.fasttrack.model.Transaction;
import org.fasttrack.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByType(TransactionType type);
    List<Transaction> findByAmountGreaterThanEqual(Double minAmount);
    List<Transaction> findByAmountLessThanEqual(Double maxAmount);
    List<Transaction> findByTypeAndAmountGreaterThanEqual(TransactionType type, Double minAmount);
    List<Transaction> findByTypeAndAmountLessThanEqual(TransactionType type, Double maxAmount);
    List<Transaction> findByAmountBetween(Double minAmount, Double maxAmount);
    List<Transaction> findByTypeAndAmountBetween(TransactionType type, Double minAmount, Double maxAmount);

}
