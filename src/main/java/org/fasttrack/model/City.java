package org.fasttrack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int cityId;
    private String cityName;
    private int population;
    private int cityFoundingYear;

    @OneToOne
    @JoinColumn(name = "capital_of")
    @JsonIgnore //to avoid infinite loops
    private Country capitalOfCountry;

    //mapat pe mappedBy in Country
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "country_id")
    private Country belongingCountry;

    public City(int cityId, String cityName, int population, int cityFoundingYear, Country capitalOfCountry) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.population = population;
        this.cityFoundingYear = cityFoundingYear;
        this.capitalOfCountry = capitalOfCountry;
    }

    public City(String cityName, int population, int cityFoundingYear, Country capitalOfCountry, Country belongingCountry) {
        this.cityName = cityName;
        this.population = population;
        this.cityFoundingYear = cityFoundingYear;
        this.capitalOfCountry = capitalOfCountry;
        this.belongingCountry = belongingCountry;
    }

    public City(String name, int population, int cityFoundingYear) {
        this.cityName = name;
        this.population = population;
        this.cityFoundingYear = cityFoundingYear;
    }

    public City(String cityName, int population, int cityFoundingYear, Country capitalOfCountry) {
        this.cityName = cityName;
        this.population = population;
        this.cityFoundingYear = cityFoundingYear;
        this.capitalOfCountry = capitalOfCountry;
    }

    public City() {
    }


    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getCityFoundingYear() {
        return cityFoundingYear;
    }

    public void setCityFoundingYear(int cityFoundingYear) {
        this.cityFoundingYear = cityFoundingYear;
    }

    public Country getCapitalOfCountry() {
        return capitalOfCountry;
    }

    public void setCapitalOfCountry(Country capitalOfCountry) {
        this.capitalOfCountry = capitalOfCountry;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Country getBelongingCountry() {
        return belongingCountry;
    }

    public void setBelongingCountry(Country belongingCountry) {
        this.belongingCountry = belongingCountry;
    }
}
