package com.exercise.timeout;

import com.exercise.timeout.model.Fixture;
import com.exercise.timeout.model.SocialManager;
import org.junit.Test;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FixtureTests {

    private static final String USERS_FILE_NAME = "users.json";
    private static final String VENUES_FILE_NAME = "venues.json";

    @Test
    public void testFixture() throws Exception {

        SocialManager socialManager = new SocialManager(USERS_FILE_NAME, VENUES_FILE_NAME);

        String[] names = {"David Lang"};

        Fixture fixture = socialManager.getFixtures(names);

        Map<String, Set<String>> avoidEatMap = fixture.getAvoidEatMap();

        assertEquals(avoidEatMap.size(), 1);

        assertTrue(avoidEatMap.get("Twin Dynasty").contains("David Lang"));
    }

    private String readResourceFileContents(String fileName) throws Exception {
        // read test file
        URI url = this.getClass().getResource("/" + fileName).toURI();
        Path path = Paths.get(url);
        return java.nio.file.Files.lines(path).collect(Collectors.joining());
    }

}
