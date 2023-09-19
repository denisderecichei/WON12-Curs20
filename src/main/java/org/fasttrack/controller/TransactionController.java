package org.fasttrack.controller;

import org.fasttrack.model.Transaction;
import org.fasttrack.model.TransactionType;
import org.fasttrack.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    private TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Transaction> getAllTransactionFiltered(@RequestParam(required = false) String product, @RequestParam(required = false) TransactionType type, @RequestParam(required = false) Double minAmount, @RequestParam(required = false) Double maxAmount) {
        return service.getAllTransactions(product, type, minAmount, maxAmount);
    }

    @GetMapping("{id}")
    public Transaction getTransactionById(@PathVariable int id) {
        return service.getTransactionById(id);
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return service.addTransaction(transaction);
    }

    @PutMapping
    public Transaction putTransaction(@RequestBody Transaction transaction) {
        return service.putTransaction(transaction);
    }

    @DeleteMapping("{id}")
    public String deleteTransaction(@PathVariable int id) {
        return service.deleteTransaction(id);
    }

    @GetMapping("reports/type")
    public Map<TransactionType, List<Transaction>> reportsByType() {
        return service.getReportsByType();
    }

    @GetMapping("reports/product")
    public Map<String, List<Transaction>> reportsByProduct() {
        return service.getReportsByProduct();
    }
}
