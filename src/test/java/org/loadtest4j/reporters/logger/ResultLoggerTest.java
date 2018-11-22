package org.loadtest4j.reporters.logger;

import org.junit.Test;
import org.loadtest4j.reporters.logger.utils.ResultFactory;
import org.loadtest4j.reporters.logger.utils.TestResource;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultLoggerTest {

    @Test
    public void shouldReport() {
        // Given
        final var logger = new ArrayList<String>();
        final var sut = new ResultLogger(logger::add);

        // When
        sut.accept(ResultFactory.fakeResult());

        // Then
        assertThat(logger)
                .hasSize(1)
                .containsExactly("\n" + TestResource.read("example-report.md") + "\n");
    }
}
