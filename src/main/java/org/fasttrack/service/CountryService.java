package org.fasttrack.service;

import org.fasttrack.exception.EntityNotFoundException;
import org.fasttrack.model.Country;
import org.fasttrack.repository.CountryReader;
import org.fasttrack.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private CountryReader countryReader;
    private CountryRepository repository;

    @Autowired
    public CountryService(CountryReader countryReader, CountryRepository repository) {
        this.repository = repository;
        this.countryReader = countryReader;
        repository.saveAll(countryReader.getAllCountries());
    }

    public List<Country> getAllCountries() {
        return repository.findAll();
    }

    public List<Country> getAllCountriesByContinent(String continentName) {
        return repository.findAllByContinent(continentName);
//        return getAllCountries().stream().filter(country -> country.getContinent().equals(continentName)).collect(Collectors.toList());
    }

    public List<Country> getAllCountriesByContinentAndPopulation(String continentName, Integer minPopulation) {
        minPopulation = minPopulation == null ? 0 : minPopulation;
        return repository.findByContinentAndMinPopulation(continentName, minPopulation);
    }

    public Country addCountry(Country country) {
        return repository.save(country);
    }

    public Country getCountryById(int countryId) {
        Optional<Country> foundCountry = repository.findById(countryId);
        if (foundCountry.isPresent()) {
            return foundCountry.get();
        } else {
            throw new EntityNotFoundException("Nu a fost gasita tara cu id-ul " + countryId, countryId);
        }
    }

    public String removeCountry(int countryId) {
        repository.deleteById(countryId);
        return "Sters cu succes";
    }
}
