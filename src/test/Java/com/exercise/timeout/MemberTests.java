package com.exercise.timeout;

import com.exercise.timeout.api.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * tests for member json validity: {@link Member}
 */

public class MemberTests {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String TEST_MEMBER_JSON_FILE_NAME_1 = "members/valid_member_1.json";
    private static final String TEST_MEMBER_JSON_FILE_NAME_2 = "members/valid_member_2.json";

    // no need for @Before or @BeforeClass

    private static Member getValidMember_1() {
        return new Member(
                "John Davis",
                Collections.singletonList("Fish"),
                Arrays.asList("Cider", "Rum", "Soft drinks")
        );
    }

    private static Member getValidMember_2() {
        return new Member(
                "Gavin Coulson",
                Collections.emptyList(),
                Arrays.asList("Cider", "Beer", "Rum", "Soft drinks")
        );
    }

    @Test
    public void serializesToJson_1() throws Exception {
        String fileJson = TestUtils.readTestResoucesFileContents(MAPPER, this, TEST_MEMBER_JSON_FILE_NAME_1);
        assertEquals(fileJson, MAPPER.writeValueAsString(getValidMember_1()));
    }

    @Test
    public void deserializesFromJson_1() throws Exception {
        String fileJson = TestUtils.readTestResoucesFileContents(MAPPER, this, TEST_MEMBER_JSON_FILE_NAME_1);
        assertEquals(MAPPER.readValue(fileJson, Member.class), getValidMember_1());
    }

    @Test
    public void serializesToJson_2() throws Exception {
        String fileJson = TestUtils.readTestResoucesFileContents(MAPPER, this, TEST_MEMBER_JSON_FILE_NAME_2);
        assertEquals(fileJson, MAPPER.writeValueAsString(getValidMember_2()));
    }

    @Test
    public void deserializesFromJson_2() throws Exception {
        String fileJson = TestUtils.readTestResoucesFileContents(MAPPER, this, TEST_MEMBER_JSON_FILE_NAME_2);
        assertEquals(MAPPER.readValue(fileJson, Member.class), getValidMember_2());
    }
}
