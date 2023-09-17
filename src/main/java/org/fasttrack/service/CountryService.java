package org.fasttrack.service;

import org.fasttrack.exception.EntityNotFoundException;
import org.fasttrack.model.Country;
import org.fasttrack.repository.CountryReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private CountryReader countryReader;
    private List<Country> countries;

    @Autowired
    public CountryService(CountryReader countryReader) {
        this.countryReader = countryReader;
        countries = countryReader.getAllCountries();
    }

    public List<Country> getAllCountries() {
        return countries;
    }

    public void load() {
    }

    public List<Country> getAllCountriesByContinent(String continentName) {
        return countries.stream().filter(country -> country.getContinent().equals(continentName)).collect(Collectors.toList());
    }

    public List<Country> getAllCountriesByContinentAndPopulation(String continentName, Integer minPopulation) {
        int finalMinPopulation = minPopulation == null ? 0 : minPopulation;
        return getAllCountriesByContinent(continentName).stream().filter(c -> c.getPopulation() >= finalMinPopulation).collect(Collectors.toList());

    }

    public Country addCountry(Country country) {
        countries.add(country);
        return country;
    }

    public Country getCountryById(int countryId) {
        return countries.stream().filter(c -> c.getId() == countryId).findFirst().orElseThrow(() -> new EntityNotFoundException("Nu a fost gasita tara cu id-ul " + countryId, countryId));
    }
}
