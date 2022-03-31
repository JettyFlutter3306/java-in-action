package cn.element.function.collecting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class DownstreamCollectors {
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class City {
        private String name;
        private String state;
        private int population;
    }
    
    public static Stream<City> readCities(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName))
                    .map(l -> l.split(", "))
                    .map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }

    public static void main(String[] args) throws IOException {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<Locale>> countryToLocaleSet = locales.collect(groupingBy(Locale::getCountry, toSet()));
        System.out.println("countryToLocaleSet = " + countryToLocaleSet);
        
        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Long> countryToLocalCounts = locales.collect(groupingBy(Locale::getCountry, counting()));
        System.out.println("countryToLocalCounts = " + countryToLocalCounts);

        Stream<City> cities = readCities("cities.txt");
        Map<String, Integer> stateToCityPopulation = cities.collect(groupingBy(City::getState, summingInt(City::getPopulation)));
        System.out.println("stateToCityPopulation = " + stateToCityPopulation);

        cities = readCities("cities.txt");
        Map<String, Optional<String>> stateToLongestCityName = cities.collect(groupingBy(
                City::getState,
                mapping(City::getName, maxBy(Comparator.comparing(String::length))))
        );
        System.out.println("stateToLongestCityName = " + stateToLongestCityName);
        
        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryToLanguages = locales.collect(groupingBy(
                Locale::getDisplayCountry,
                mapping(Locale::getDisplayLanguage, toSet()))
        );
        System.out.println("countryToLanguages = " + countryToLanguages);

        cities = readCities("cities.txt");
        Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities.collect(groupingBy(
                City::getState, 
                summarizingInt(City::getPopulation))
        );
        System.out.println(stateToCityPopulationSummary.get("NY"));

        cities = readCities("cities.txt");
        Map<String, String> stateToCityNames1 = cities.collect(groupingBy(
                City::getState,
                reducing("", City::getName, (s, t) -> s.length() == 0 ? t : s + ", " + t))
        );
        System.out.println("stateToCityNames1 = " + stateToCityNames1);

        cities = readCities("cities.txt");
        Map<String, String> stateToCityNames2 = cities.collect(groupingBy(
                City::getState, 
                mapping(City::getName, joining(", ")))
        );
        System.out.println("stateToCityNames2 = " + stateToCityNames2);
    }

}
