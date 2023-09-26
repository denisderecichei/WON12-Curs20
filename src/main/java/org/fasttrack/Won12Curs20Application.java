package org.fasttrack;

import org.fasttrack.model.Transaction;
import org.fasttrack.model.TransactionType;
import org.fasttrack.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Won12Curs20Application {

	public static void main(String[] args) {
		SpringApplication.run(Won12Curs20Application.class, args);
	}

	@Bean
	CommandLineRunner atStartup(TransactionRepository repository) {
		return args -> {
			Transaction t1 = new Transaction(1, "cola", TransactionType.BUY, 10.2);
			Transaction t2 = new Transaction(2, "pepsi", TransactionType.SELL, 8);
			Transaction t3 = new Transaction(3, "fanta", TransactionType.BUY, 4);
			repository.save(t1);
			repository.save(t2);
			repository.save(t3);
//			repository.saveAll(List.of(t1, t2, t3));
		};
	}

}
