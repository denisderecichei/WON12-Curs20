package org.fasttrack.service;

import org.fasttrack.exception.EntityNotFoundException;
import org.fasttrack.model.Transaction;
import org.fasttrack.model.TransactionType;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private List<Transaction> transactions;

    TransactionService() {
        this.transactions = generateTransactions();
    }

    public List<Transaction> getAllTransactions(String product, TransactionType type, Double minAmount, Double maxAmount) {
        return transactions.stream()
                .filter(t -> product == null || t.getProduct().equals(product))
                .filter(t -> type == null || t.getType().equals(type))
                .filter(t -> minAmount == null || t.getAmount() > minAmount)
                .filter(t -> maxAmount == null || t.getAmount() < maxAmount)
                .collect(Collectors.toList());
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
        return transactions.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new EntityNotFoundException("Nu am putut gasi tranzactie cu idul " + id, id));
    }

    public Transaction addTransaction(Transaction transaction) {
        transactions.add(transaction);
        return transaction;
    }

    public Transaction putTransaction(Transaction transaction) {
        for (Transaction currentTrans : transactions) {
            if (currentTrans.getId() == transaction.getId()) {
                transactions.remove(currentTrans);
                break;
            }
        }
        transactions.add(transaction);
        return transaction;
    }

    public String deleteTransaction(int id) {
        boolean wasDeleted = false;
        for (Transaction currentTrans : transactions) {
            if (currentTrans.getId() == id) {
                wasDeleted = transactions.remove(currentTrans);
                break;
            }
        }
        if (wasDeleted) {
            return String.format("Am sters transactia %d cu success", id);
        } else {
            return String.format("Nu am putut sterge transactia %d cu success", id);
        }
    }

    public Map<TransactionType, List<Transaction>> getReportsByType() {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getType));
    }

    public Map<String, List<Transaction>> getReportsByProduct() {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getProduct));
    }
}
