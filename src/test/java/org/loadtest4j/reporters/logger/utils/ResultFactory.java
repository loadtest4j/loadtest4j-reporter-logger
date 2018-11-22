package org.loadtest4j.reporters.logger.utils;

import org.loadtest4j.Diagnostics;
import org.loadtest4j.RequestCount;
import org.loadtest4j.ResponseTime;
import org.loadtest4j.Result;
import org.loadtest4j.driver.DriverResponseTime;

import java.time.Duration;

public class ResultFactory {
    public static Result fakeResult() {
        final var duration = Duration.ofMillis(Long.MAX_VALUE);
        final var ko = Long.MAX_VALUE / 2;
        final var ok = Long.MAX_VALUE / 2;
        final DriverResponseTime driverResponseTime = (percentile) -> Duration.ofMillis(Long.MAX_VALUE);

        final var requestCount = new RequestCount(ok, ko, ok + ko);
        final var diagnostics = new Diagnostics(duration, requestCount, 1000);
        final var responseTime = new ResponseTime(driverResponseTime);
        return new Result(diagnostics, 100, responseTime);
    }
}
