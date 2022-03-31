package cn.element.function.collecting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class CollectingIntoMaps {
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Person {
        private int id;
        private String name;
    }
    
    public static Stream<Person> people() {
        return Stream.of(
                new Person(1001, "洛必达"), 
                new Person(1002, "牛顿"),
                new Person(1003, "伯努利")
        );
    }

    public static void main(String[] args) {
        Map<Integer, String> idToName = people().collect(toMap(Person::getId, Person::getName));
        System.out.println("idToName: " + idToName);

        Map<Integer, Person> idToPerson = people().collect(toMap(Person::getId, Function.identity()));
        System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);
        
        idToPerson = people().collect(toMap(
                Person::getId, 
                Function.identity(), 
                (existed, newVal) -> { throw new IllegalStateException(); }, 
                TreeMap::new)
        );
        System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);

        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, String> languageNames =  locales.collect(toMap(
                Locale::getLanguage,
                l -> l.getDisplayLanguage(l),
                (existed, newVal) -> existed)
        );
        System.out.println("languageNames = " + languageNames);
        
        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryLanguages =  locales.collect(toMap(
                Locale::getDisplayCountry,
                l -> Set.of(l.getDisplayLanguage()),
                (a, b) -> new HashSet<>(b)
        ));
        System.out.println("countryLanguages = " + countryLanguages);
        
    }

}
