package org.fasttrack.repository;

import org.fasttrack.model.Country;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Repository("file")
public class CountryReader {
    @Value("${file.location}")
    private String fileLocation;

    public List<Country> getAllCountries() {
        System.out.println(fileLocation);
        List<Country> allCountries = new ArrayList<>();
        FileReader reader;
        try {
            reader = new FileReader(fileLocation);
        } catch (FileNotFoundException e) {
            System.out.println("couldn't open file");
            return new ArrayList<>();
        }
        Scanner fileScanner = new Scanner(reader);

        while (fileScanner.hasNext()) {
            String currentLine = fileScanner.nextLine();
            String[] partsOfCountries = currentLine.split("\\|");
            if (partsOfCountries.length < 5) {
            } else {
                String name = partsOfCountries[0];
                String capital = partsOfCountries[1];
                int population = Integer.parseInt(partsOfCountries[2]);
                int area = Integer.parseInt(partsOfCountries[3]);
                String continent = partsOfCountries[4];
                List<String> neighbours = new ArrayList<>();
                if (partsOfCountries.length == 6) {
                    String allNeighboursAsString = partsOfCountries[5];
                    neighbours.addAll(List.of(allNeighboursAsString.split("~")));
                }
                Country currentCountry = new Country(name, capital, population, area, continent, neighbours);
                allCountries.add(currentCountry);
            }
        }
        return allCountries;
    }

    public static List<Country> getAllCountries(String filePath) {
        System.out.println(filePath);
        List<Country> allCountries = new ArrayList<>();
        FileReader reader;
        try {
            reader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("couldn't open file");
            return new ArrayList<>();
        }
        Scanner fileScanner = new Scanner(reader);

        while (fileScanner.hasNext()) {
            String currentLine = fileScanner.nextLine();
            String[] partsOfCountries = currentLine.split("\\|");
            if (partsOfCountries.length < 5) {
            } else {
                String name = partsOfCountries[0];
                String capital = partsOfCountries[1];
                int population = Integer.parseInt(partsOfCountries[2]);
                int area = Integer.parseInt(partsOfCountries[3]);
                String continent = partsOfCountries[4];
                List<String> neighbours = new ArrayList<>();
                if (partsOfCountries.length == 6) {
                    String allNeighboursAsString = partsOfCountries[5];
                    neighbours.addAll(List.of(allNeighboursAsString.split("~")));
                }
                Country currentCountry = new Country(name, capital, population, area, continent, neighbours);
                allCountries.add(currentCountry);
            }
        }
        return allCountries;
    }
}
