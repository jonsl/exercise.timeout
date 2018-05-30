package com.exercise.timeout;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

class TestUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static String readTestResoucesFileContents(Object object, String fileName) throws Exception {
        // read test file
        URI url = object.getClass().getResource("/" + fileName).toURI();
        Path path = Paths.get(url);
        String testFileContents = java.nio.file.Files.lines(path).collect(Collectors.joining());
        return MAPPER.readValue(testFileContents, JsonNode.class).toString();
    }
}
