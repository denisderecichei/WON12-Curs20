package org.fasttrack;

import org.fasttrack.model.City;
import org.fasttrack.model.Country;
import org.fasttrack.model.Transaction;
import org.fasttrack.model.TransactionType;
import org.fasttrack.repository.CityRepository;
import org.fasttrack.repository.CountryReader;
import org.fasttrack.repository.CountryRepository;
import org.fasttrack.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Won12Curs20Application {
	@Value("${file.location}")
	private String fileLocation;

	public static void main(String[] args) {
		SpringApplication.run(Won12Curs20Application.class, args);
	}

	@Bean
	CommandLineRunner atStartup(TransactionRepository repository, CountryRepository countryRepo, CityRepository cityRepo) {
		return args -> {
			Transaction t1 = new Transaction(1, "cola", TransactionType.BUY, 10.2);
			Transaction t2 = new Transaction(2, "pepsi", TransactionType.SELL, 8);
			Transaction t3 = new Transaction(3, "fanta", TransactionType.BUY, 4);
			repository.save(t1);
			repository.save(t2);
			repository.save(t3);
//			repository.saveAll(List.of(t1, t2, t3));
			List<Country> allCountries = CountryReader.getAllCountries(fileLocation);
			Country firstCountry = allCountries.get(0);
			City firstCapitalCity = new City("Prima capitala", 9999, 1984);
			City secondCity = new City("oras normal", 1000, 1000);
			firstCountry.setCapitalCity(firstCapitalCity);
			firstCountry.addCity(firstCapitalCity);
			firstCountry.addCity(secondCity);

			firstCapitalCity.setBelongingCountry(firstCountry);
			firstCapitalCity.setCapitalOfCountry(firstCountry);

			secondCity.setBelongingCountry(firstCountry);
			countryRepo.saveAll(allCountries);
//			countryRepo.save(firstCountry);
		};
	}

}
