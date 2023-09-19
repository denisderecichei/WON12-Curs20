package org.fasttrack.repository;

import org.fasttrack.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    List<Country> findAllByContinent(String continent);

    List<Country> findAllByContinentAndCapital(String continent, String capital);

    @Query("SELECT c FROM Country c WHERE c.continent = :contName AND c.population > :min") //<- e mai safe cu numele parametrilor
//    @Query("SELECT c FROM Country c WHERE c.continent = ?1 AND c.population > ?2") <- pozitia parametrilor
    List<Country> findByContinentAndMinPopulation(@Param("contName") String continent, @Param("min") Integer minPopulation);

}
