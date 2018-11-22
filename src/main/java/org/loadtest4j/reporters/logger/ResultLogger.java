package org.loadtest4j.reporters.logger;

import org.loadtest4j.Result;

import java.lang.System.Logger.Level;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Report a loadtest4j {@link Result} via System.Logger.
 */
public class ResultLogger implements Consumer<Result> {

    private static final System.Logger LOG = System.getLogger(ResultLogger.class.getName());

    private final Consumer<String> logger;

    ResultLogger(Consumer<String> logger) {
        this.logger = logger;
    }

    public ResultLogger() {
        this(msg -> LOG.log(Level.INFO, msg));
    }

    @Override
    public synchronized void accept(Result result) {
        final var diagnostics = result.getDiagnostics();

        final var duration = diagnostics.getDuration().toMillis();
        final var rps = diagnostics.getRequestsPerSecond();

        final var requestCount = diagnostics.getRequestCount();
        final var ok = requestCount.getOk();
        final var ko = requestCount.getKo();
        final var total = requestCount.getTotal();

        final var responseTime = result.getResponseTime();
        final var min = responseTime.getPercentile(0).toMillis();
        final var p50 = responseTime.getPercentile(50).toMillis();
        final var p75 = responseTime.getPercentile(75).toMillis();
        final var p90 = responseTime.getPercentile(90).toMillis();
        final var p95 = responseTime.getPercentile(95).toMillis();
        final var p99 = responseTime.getPercentile(99).toMillis();
        final var max = responseTime.getPercentile(100).toMillis();

        final var b = new FormattingStringBuilder();

        b.println();
        b.println("Summary");
        b.println("================================================================================");
        b.println();
        b.println("Global Information");
        b.println("--------------------------------------------------------------------------------");
        b.println();
        b.println("    duration (ms)        %55d", duration);
        b.println("    mean requests/sec    %55f", rps);
        b.println();
        b.println("Request Count");
        b.println("--------------------------------------------------------------------------------");
        b.println();
        b.println("    ok                   %55d", ok);
        b.println("    ko                   %55d", ko);
        b.println("    total                %55d", total);
        b.println();
        b.println("Response Time");
        b.println("--------------------------------------------------------------------------------");
        b.println();
        b.println("    min (ms)             %55d", min);
        b.println("    p50 (ms)             %55d", p50);
        b.println("    p75 (ms)             %55d", p75);
        b.println("    p90 (ms)             %55d", p90);
        b.println("    p95 (ms)             %55d", p95);
        b.println("    p99 (ms)             %55d", p99);
        b.println("    max (ms)             %55d", max);
        b.println();

        logger.accept(b.get());
    }

    private static class FormattingStringBuilder implements Supplier<String> {

        private final StringBuffer sb = new StringBuffer();

        synchronized void println() {
            sb.append("\n");
        }

        synchronized void println(String format, Object... args) {
            sb.append(String.format(format, args));
            println();
        }

        @Override
        public String get() {
            return sb.toString();
        }
    }
}
