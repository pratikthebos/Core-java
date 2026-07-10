package com.pratik;

import java.time.LocalDateTime;
import java.util.*;

public class RealTimeLogAggregator {

    enum LogLevel {
        INFO,
        WARN,
        ERROR
    }

    static class LogEntry {

        private final LocalDateTime timestamp;
        private final String serviceName;
        private final LogLevel level;
        private final String message;

        public LogEntry(
                String serviceName,
                LogLevel level,
                String message) {

            this.timestamp = LocalDateTime.now();
            this.serviceName = serviceName;
            this.level = level;
            this.message = message;
        }

        public String getServiceName() {
            return serviceName;
        }

        public LogLevel getLevel() {
            return level;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return timestamp +
                    " | " +
                    serviceName +
                    " | " +
                    level +
                    " | " +
                    message;
        }
    }

    static class LogAggregator {

        private final List<LogEntry> logs =
                new ArrayList<>();

        public void addLog(
                String service,
                LogLevel level,
                String message) {

            logs.add(
                    new LogEntry(
                            service,
                            level,
                            message));
        }

        public void searchByKeyword(
                String keyword) {

            System.out.println("\nKeyword Search:");

            for (LogEntry log : logs) {

                if (log.getMessage()
                        .toLowerCase()
                        .contains(
                                keyword.toLowerCase())) {

                    System.out.println(log);
                }
            }
        }

        public void filterByLevel(
                LogLevel level) {

            System.out.println(
                    "\nLogs : " + level);

            for (LogEntry log : logs) {

                if (log.getLevel() == level) {

                    System.out.println(log);
                }
            }
        }

        public void analytics() {

            Map<LogLevel, Integer> count =
                    new EnumMap<>(LogLevel.class);

            for (LogLevel level :
                    LogLevel.values()) {

                count.put(level, 0);
            }

            for (LogEntry log : logs) {

                count.put(
                        log.getLevel(),
                        count.get(log.getLevel()) + 1);
            }

            System.out.println("\nAnalytics");

            for (LogLevel level :
                    count.keySet()) {

                System.out.println(
                        level +
                        " : " +
                        count.get(level));
            }
        }
    }

    public static void main(String[] args) {

        LogAggregator aggregator =
                new LogAggregator();

        aggregator.addLog(
                "Order-Service",
                LogLevel.INFO,
                "Order created");

        aggregator.addLog(
                "Payment-Service",
                LogLevel.ERROR,
                "Payment failed");

        aggregator.addLog(
                "User-Service",
                LogLevel.WARN,
                "Password expires soon");

        aggregator.addLog(
                "Order-Service",
                LogLevel.INFO,
                "Order delivered");

        aggregator.searchByKeyword("order");

        aggregator.filterByLevel(
                LogLevel.ERROR);

        aggregator.analytics();
    }
}