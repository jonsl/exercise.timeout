package com.exercise.timeout.model;

import com.exercise.timeout.api.Member;
import com.exercise.timeout.api.Venue;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class SocialManager {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Fixture fixture;

    private String userJson;
    private String venuesJson;

    private List<Member> memberList;
    private Map<String, Member> memberMap = new HashMap<String, Member>();
    private List<Venue> venueList;

    public SocialManager(String userJson, String venuesJson) throws Exception {

        ObjectMapper objectMapper = MAPPER.configure(JsonParser.Feature.ALLOW_TRAILING_COMMA, true);

        String usersResourceContent = readResourceFileContents(userJson);
        try {
            memberList = objectMapper.readValue(usersResourceContent, new TypeReference<List<Member>>() {
            });

            // create memberMap on name
            for (Member member : memberList) {
                memberMap.put(member.getName(), member);
            }

        } catch (JsonMappingException e) {
            System.err.println("JsonMappingException: " + e.getMessage());
        }

        String venuesResourceContent = readResourceFileContents(venuesJson);
        try {
            venueList = objectMapper.readValue(venuesResourceContent, new TypeReference<List<Venue>>() {
            });
        } catch (JsonParseException e) {
            System.err.println("JsonParseException: " + e.getMessage());
        }

        this.userJson = userJson;
        this.venuesJson = venuesJson;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public Member getMember(String name) {
        if (memberMap.containsKey(name)) {
            return memberMap.get(name);
        }
        return null;
    }

    public List<Venue> getVenueList() {
        return venueList;
    }

    public Fixture getFixtures(String[] names) {

        fixture = new Fixture(getVenueList());

        for (String name : names) {

            Member member = getMember(name);

            if (member != null) {

                for (Venue venue : getVenueList()) {

                    // check if can eat here

                    checkAddAvoidEatAtVenue(member, venue);

                    // check if can drink here

                    checkAddAvoidDrinkAtVenue(member, venue);
                }
            }
        }

        return fixture;
    }

    private void checkAddAvoidEatAtVenue(Member member, Venue venue) {

        for (String wontEatItem : member.getWontEat()) {
            for (String food : venue.getFood()) {
                if (!food.toUpperCase().equals(wontEatItem.toUpperCase())) {
                    return;
                }
            }
        }

        // we cant eat here
        Set<String> values = fixture.getAvoidEatMap().computeIfAbsent(venue.getName(), k -> new HashSet<String>());
        values.add(member.getName());
    }

    private void checkAddAvoidDrinkAtVenue(Member member, Venue venue) {
        for (String willDrinkItem : member.getDrinks()) {
            for (String drink : venue.getDrinks()) {
                if (drink.toUpperCase().equals(willDrinkItem.toUpperCase())) {
                    return;
                }
            }
        }

        // we cant drink here
        Set<String> values = fixture.getAvoidDrinkMap().computeIfAbsent(venue.getName(), k -> new HashSet<String>());
        values.add(member.getName());
    }

    private String readResourceFileContents(String fileName) throws Exception {
        // read test file
        URI url = this.getClass().getResource("/" + fileName).toURI();
        Path path = Paths.get(url);
        return java.nio.file.Files.lines(path).collect(Collectors.joining());
    }
}
