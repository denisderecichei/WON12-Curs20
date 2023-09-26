package org.fasttrack.service;

import org.fasttrack.exception.EntityNotFoundException;
import org.fasttrack.model.Transaction;
import org.fasttrack.model.TransactionType;
import org.fasttrack.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;

    @Autowired
    TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions(TransactionType type, Double minAmount, Double maxAmount) {
        //A B C A=type B=min C=max
        //[], A, B, C, AB, AC, BC, ABC
        if (type != null) {
            if (minAmount != null) {
                if (maxAmount != null) {
                    return transactionRepository.findByTypeAndAmountBetween(type, minAmount, maxAmount);
                } else {
                    return transactionRepository.findByTypeAndAmountGreaterThanEqual(type, minAmount);
                }
            } else {
                if (maxAmount != null) {
                    return transactionRepository.findByTypeAndAmountLessThanEqual(type, maxAmount);
                } else {
                    return transactionRepository.findByType(type);
                }
            }
        } else {
            if (minAmount != null) {
                if (maxAmount != null) {
                    return transactionRepository.findByAmountBetween(minAmount, maxAmount);
                } else {
                    return transactionRepository.findByAmountGreaterThanEqual(minAmount);
                }
            } else {
                if (maxAmount != null) {
                    return transactionRepository.findByAmountLessThanEqual(maxAmount);
                } else {
                    return transactionRepository.findAll();
                }
            }
        }
    }

    private static List<Transaction> generateTransactions() {
        Transaction t1 = new Transaction(1, "cola", TransactionType.BUY, 10.2);
        Transaction t2 = new Transaction(2, "pepsi", TransactionType.SELL, 8);
        Transaction t3 = new Transaction(3, "fanta", TransactionType.BUY, 4);
        List<Transaction> myTrans = new ArrayList<>();
        myTrans.add(t1);
        myTrans.add(t2);
        myTrans.add(t3);
        return myTrans;
    }

    public Transaction getTransactionById(int id) {
        Optional<Transaction> foundTransaction = transactionRepository.findById(id);
        if (foundTransaction.isPresent()) {
            return foundTransaction.get();
        } else {
            throw new EntityNotFoundException("", id);
        }
    }

    public Transaction addTransaction(Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        return savedTransaction;
    }

    public Transaction putTransaction(Transaction fromAPICall) {
        Transaction endResult;
        Optional<Transaction> foundTransaction = transactionRepository.findById(fromAPICall.getId());
        if (foundTransaction.isPresent()) {
            Transaction fromDb = foundTransaction.get();
            if (fromAPICall.getProduct() != null) {
                fromDb.setProduct(fromAPICall.getProduct());
            }
            if (fromAPICall.getType() != null) {
                fromDb.setType(fromAPICall.getType());
            }
            if (fromAPICall.getAmount() != 0) {
                fromDb.setAmount(fromAPICall.getAmount());
            }
            endResult = transactionRepository.save(fromDb);
        } else {
            endResult = transactionRepository.save(fromAPICall);
        }
        return endResult;
    }

    public String deleteTransaction(int id) {
        transactionRepository.deleteById(id);
        return String.format("Am sters transactia %d cu success", id);
    }

    public Map<TransactionType, List<Transaction>> getReportsByType() {
        return transactionRepository.findAll().stream()
                .collect(Collectors.groupingBy(Transaction::getType));
    }

    public Map<String, List<Transaction>> getReportsByProduct() {
        return transactionRepository.findAll().stream()
                .collect(Collectors.groupingBy(Transaction::getProduct));
    }
}
