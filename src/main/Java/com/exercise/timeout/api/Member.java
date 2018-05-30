package com.exercise.timeout.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * team preferences, uses Jackson to map to JSON
 */

public class Member {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @JsonProperty("name")
    private String name;

    @JsonProperty("wont_eat")
    private List<String> wontEat;

    @JsonProperty("drinks")
    private List<String> drinks;

    public Member() {
        // Jackson deserialization
    }

    @JsonCreator
    public Member(@JsonProperty("name") String name,
                  @JsonProperty("wont_eat") List<String> wontEat,
                  @JsonProperty("drinks") List<String> drinks) {
        this.name = name;
        this.wontEat = wontEat;
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

    public List<String> getWontEat() {
        return wontEat;
    }

    public List<String> getDrinks() {
        return drinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;

        Member that = (Member) o;

        if (!getName().equals(that.getName())) return false;
        if (!getWontEat().equals(that.getWontEat())) return false;
        return getDrinks().equals(that.getDrinks());
    }
}
