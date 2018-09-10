package com.cristinaverdi.streamspractice;

import Java8PracticeTest.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.partitioningBy;

public class Transform {
    public List<String> toUpperCase(List<String> elements) {
        return elements.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public List<String> filterElementsWithLessThanFourCharacters(List<String> elements) {
        return elements.stream()
                .filter(element -> element.length() < 4)
                .collect(Collectors.toList());
    }

    public List<String> flattenMatrix(List<List<String>> matrix) {
        return matrix.stream()
                .flatMap(vector -> vector.stream())
                .collect(Collectors.toList());
    }

    public Person getOldestPerson(List<Person> people) {
        return people.stream()
                .max(Comparator.comparing(Person::getAge))
                .get();
    }

    public int total(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, (total, number) -> total + number);
    }

    public List<String> findPeopleOver18(List<Person> people) {
        return people.stream()
                .filter(person -> person.getAge() > 18)
                .map(Person::getName)
                .collect(Collectors.toList());
    }

    public Map<Boolean, List<Person>> separateAdultsFromKids(List<Person> people) {
        return people.stream()
                .collect(
                    partitioningBy(person -> person.age >= 18));
    }

    public Map<String, List<Person>> groupByName(List<Person> people) {
        return  people.stream().collect(groupingBy(Person::getName));
    }

    public String getPeopleNames(List<Person> people) {
        return people.stream()
                .map(Person::getName)
                .collect(joining(", ", "Names: ", "."));
    }
}
