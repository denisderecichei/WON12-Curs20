package org.fasttrack.model;

import java.util.List;

public class Country {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String capital;
    private int population;
    private int area;
    private String continent;
    private List<String> neighbours;

    public Country(String name, String capital, int population, int area, String continent, List<String> neighbours) {
        this.id = idCounter++;
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.continent = continent;
        this.neighbours = neighbours;
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

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Country.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
