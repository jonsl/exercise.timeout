package com.exercise.timeout.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * describes Members and their preferences
 * uses Jackson to map to and from JSON
 */

public class Venue {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @JsonProperty("name")
    private String name;

    @JsonProperty("food")
    private List<String> food;

    @JsonProperty("drinks")
    private List<String> drinks;

    public Venue() {
        // Jackson deserialization
    }

    @JsonCreator
    public Venue(@JsonProperty("name") String name,
                 @JsonProperty("food") List<String> food,
                 @JsonProperty("drinks") List<String> drinks) {
        this.name = name;
        this.food = food;
        this.drinks = drinks;
    }

    public static String toJson(Member member) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(MAPPER.writeValueAsString(member));
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            System.err.println("JsonProcessingException: " + e.getMessage());
        }
        return sb.toString();
    }

    public static Member fromJson(String json) {
        try {
            return MAPPER.readValue(json, Member.class);
        } catch (java.io.IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public List<String> getFood() {
        return food;
    }

    public List<String> getDrinks() {
        return drinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venue)) return false;

        Venue that = (Venue) o;

        if (!getName().equals(that.getName())) return false;
        if (!getFood().equals(that.getFood())) return false;
        return getDrinks().equals(that.getDrinks());
    }
}
