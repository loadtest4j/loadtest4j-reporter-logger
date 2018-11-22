package org.loadtest4j.reporters.logger.utils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TestResource {
    private static String streamToString(InputStream is) {
        // From https://stackoverflow.com/a/5445161
        final var s = new Scanner(is, StandardCharsets.UTF_8.name()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static String read(String name) {
        TestResource.class.getClassLoader().getResource("abc");
        return streamToString(TestResource.class.getClassLoader().getResourceAsStream(name));
    }
}
