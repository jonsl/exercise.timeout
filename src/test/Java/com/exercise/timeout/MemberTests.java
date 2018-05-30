package com.exercise.timeout;

import com.exercise.timeout.api.Member;
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
 * payment representation tests: {@link Member}
 */

public class MemberTests {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String TEST_MEMBER_JSON_FILE_NAME_1 = "validMember_1.json";
    private static final String TEST_MEMBER_JSON_FILE_NAME_2 = "validMember_2.json";

    // no need for @Before or @BeforeClass

    private static Member getValidMemberPrefernce_1() {
        return new Member(
                "John Davis",
                Collections.singletonList("Fish"),
                Arrays.asList("Cider", "Rum", "Soft drinks")
        );
    }

    private static Member getValidMemberPrefernce_2() {
        return new Member(
                "Gavin Coulson",
                Collections.emptyList(),
                Arrays.asList("Cider", "Beer", "Rum", "Soft drinks")
        );
    }

    @Test
    public void serializesToJson_1() throws Exception {
        String fileJson = readTestResourceJsonFile(TEST_MEMBER_JSON_FILE_NAME_1);
        assertEquals(fileJson, MAPPER.writeValueAsString(getValidMemberPrefernce_1()));
    }

    @Test
    public void deserializesFromJson_1() throws Exception {
        String fileJson = readTestResourceJsonFile(TEST_MEMBER_JSON_FILE_NAME_1);
        assertEquals(MAPPER.readValue(fileJson, Member.class), getValidMemberPrefernce_1());
    }

    @Test
    public void serializesToJson_2() throws Exception {
        String fileJson = readTestResourceJsonFile(TEST_MEMBER_JSON_FILE_NAME_2);
        assertEquals(fileJson, MAPPER.writeValueAsString(getValidMemberPrefernce_2()));
    }

    @Test
    public void deserializesFromJson_2() throws Exception {
        String fileJson = readTestResourceJsonFile(TEST_MEMBER_JSON_FILE_NAME_2);
        assertEquals(MAPPER.readValue(fileJson, Member.class), getValidMemberPrefernce_2());
    }

    private String readTestResourceJsonFile(String fileName) throws Exception {
        // read test file
        URI url = this.getClass().getResource("/" + fileName).toURI();
        Path path = Paths.get(url);
        String testFileContents = java.nio.file.Files.lines(path).collect(Collectors.joining());
        return MAPPER.readValue(testFileContents, JsonNode.class).toString();
    }
}
