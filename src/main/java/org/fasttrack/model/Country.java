package org.fasttrack.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "countries")

//JPA = specificatii, doar interfete. E modul abstract de a face lucrurile
//Hibernate = o implementare de JPA
//Spring Data = Hibernate + chestii de convenienta
//Spring Data extends Hibernate implements JPA
//jakarta = java, specific JPA
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private int countryId;
    private String name;
    private String capital;
    private int population;
    private int area;
    private String continent;
    private List<String> neighbours;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "capital_id")
    private City capitalCity;

    //mappedBy = city.belongingCountry <- ca sa faca legatura orasul cu tara de care apartine
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "belongingCountry")
    private List<City> cities;



    public Country(String name, String capital, int population, int area, String continent, List<String> neighbours) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.continent = continent;
        this.neighbours = neighbours;
    }

    public Country(String name, String capital, int population, int area, String continent, List<String> neighbours, City capitalCity) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.continent = continent;
        this.neighbours = neighbours;
        this.capitalCity = capitalCity;
    }

    public Country(int id, String name, String capital, int population, int area, String continent, List<String> neighbours, City capitalCity) {
        this.countryId = id;
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.continent = continent;
        this.neighbours = neighbours;
        this.capitalCity = capitalCity;
    }

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public List<String> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<String> neighbours) {
        this.neighbours = neighbours;
    }

    public City getCapitalCity() {
        return capitalCity;
    }

    public void setCapitalCity(City capitalCity) {
        this.capitalCity = capitalCity;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void addCity(City city) {
        if (cities == null) {
            this.cities = new ArrayList<>();
        }
        this.cities.add(city);
    }

    public void removeCity(City city) {
        if (cities == null) {
            this.cities = new ArrayList<>();
        }
        this.cities.remove(city);
    }
}
