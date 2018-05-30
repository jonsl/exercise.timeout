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
import java.util.List;
import java.util.stream.Collectors;

public class JsonFeeds {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String USERS_FILE_NAME = "users.json";
    private static final String VENUES_FILE_NAME = "venues.json";

    private List<Member> memberList;
    private List<Venue> venueList;

    public JsonFeeds() throws Exception {

        ObjectMapper objectMapper = MAPPER.configure(JsonParser.Feature.ALLOW_TRAILING_COMMA, true);

        String usersResourceContent = readResourceFileContents("users.json");
        try {
            memberList = objectMapper.readValue(usersResourceContent, new TypeReference<List<Member>>() {
            });
        } catch (JsonMappingException e) {
            System.err.println("JsonMappingException: " + e.getMessage());
        }

        String venuesResourceContent = readResourceFileContents("venues.json");
        try {
            venueList = objectMapper.readValue(venuesResourceContent, new TypeReference<List<Venue>>() {
            });
        } catch (JsonParseException e) {
            System.err.println("JsonParseException: " + e.getMessage());
        }
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public List<Venue> getVenueList() {
        return venueList;
    }

    private String readResourceFileContents(String fileName) throws Exception {
        // read test file
        URI url = this.getClass().getResource("/" + fileName).toURI();
        Path path = Paths.get(url);
        return java.nio.file.Files.lines(path).collect(Collectors.joining());
    }
}
