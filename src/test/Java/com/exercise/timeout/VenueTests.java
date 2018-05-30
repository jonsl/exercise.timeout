package com.exercise.timeout;

import com.exercise.timeout.api.Venue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * tests for venue json validity: {@link Venue}
 */

public class VenueTests {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String TEST_VENUE_JSON_FILE_NAME_1 = "venues/valid_venue_1.json";
    private static final String TEST_VENUE_JSON_FILE_NAME_2 = "venues/valid_venue_2.json";
    private static final String TEST_INVALID_VENUE_JSON_FILE_NAME_1 = "venues/invalid_venue_1.json";

    // no need for @Before or @BeforeClass

    private static Venue getValidVenue_1() {
        return new Venue(
                "El Cantina",
                Collections.singletonList("Mexican"),
                Arrays.asList("Soft drinks", "Tequila", "Beer")
        );
    }

    private static Venue getValidVenue_2() {
        return new Venue(
                "The Cambridge",
                Arrays.asList("Eggs", "Meat", "Fish", "Pasta", "Dairy"),
                Arrays.asList("Vokda", "Gin", "Cider", "Beer", "Soft drinks")
        );
    }

    private static Venue getInvalidVenue_1() {
        return new Venue(
                "Fabrique",
                Arrays.asList("Bread", "Cheese", "Deli"),
                Arrays.asList("Soft Drinks", "Tea", "Coffee")
        );
    }

    @Test
    public void serializesToJson_1() throws Exception {
        String fileJson = TestUtils.readTestResoucesFileContents(MAPPER, this, TEST_VENUE_JSON_FILE_NAME_1);
        assertEquals(fileJson, MAPPER.writeValueAsString(getValidVenue_1()));
    }

    @Test
    public void deserializesFromJson_1() throws Exception {
        String fileJson = TestUtils.readTestResoucesFileContents(MAPPER, this, TEST_VENUE_JSON_FILE_NAME_1);
        assertEquals(MAPPER.readValue(fileJson, Venue.class), getValidVenue_1());
    }

    @Test
    public void serializesToJson_2() throws Exception {
        String fileJson = TestUtils.readTestResoucesFileContents(MAPPER, this, TEST_VENUE_JSON_FILE_NAME_2);
        assertEquals(fileJson, MAPPER.writeValueAsString(getValidVenue_2()));
    }

    @Test
    public void deserializesFromJson_2() throws Exception {
        String fileJson = TestUtils.readTestResoucesFileContents(MAPPER, this, TEST_VENUE_JSON_FILE_NAME_2);
        assertEquals(MAPPER.readValue(fileJson, Venue.class), getValidVenue_2());
    }

    @Test
    public void deserializesOkFromInvalidJson_1() throws Exception {
        final ObjectMapper objectMapper = MAPPER.configure(JsonParser.Feature.ALLOW_TRAILING_COMMA, true);
        String fileJson = TestUtils.readTestResoucesFileContents(objectMapper, this, TEST_INVALID_VENUE_JSON_FILE_NAME_1);
        assertEquals(MAPPER.readValue(fileJson, Venue.class), getInvalidVenue_1());
    }
}
