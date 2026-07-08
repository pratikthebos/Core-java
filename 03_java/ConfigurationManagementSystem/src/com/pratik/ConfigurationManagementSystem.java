package com.pratik;

import java.util.*;

public class ConfigurationManagementSystem {

    static class Configuration {

        private final String application;
        private final String environment;
        private final TreeMap<Integer, Map<String, String>> versions;
        private int currentVersion;

        public Configuration(String application,
                             String environment) {

            this.application = application;
            this.environment = environment;
            this.versions = new TreeMap<>();
            this.currentVersion = 1;
            this.versions.put(currentVersion, new HashMap<>());
        }

        public void put(String key, String value) {

            Map<String, String> next =
                    new HashMap<>(versions.get(currentVersion));

            next.put(key, value);

            currentVersion++;

            versions.put(currentVersion, next);
        }

        public String get(String key) {

            return versions
                    .get(currentVersion)
                    .get(key);
        }

        public void rollback(int version) {

            if (versions.containsKey(version)) {
                currentVersion = version;
            }
        }

        public void printCurrentConfiguration() {

            System.out.println(
                    "\nApplication : " + application);

            System.out.println(
                    "Environment : " + environment);

            System.out.println(
                    "Version : " + currentVersion);

            System.out.println(
                    versions.get(currentVersion));
        }
    }

    static class ConfigurationRegistry {

        private final Map<String, Configuration> registry =
                new HashMap<>();

        public void register(Configuration configuration) {

            String key =
                    configuration.application +
                    "-" +
                    configuration.environment;

            registry.put(key, configuration);
        }

        public Configuration get(String app,
                                 String env) {

            return registry.get(app + "-" + env);
        }
    }

    public static void main(String[] args) {

        ConfigurationRegistry registry =
                new ConfigurationRegistry();

        Configuration config =
                new Configuration(
                        "Order-Service",
                        "PROD");

        registry.register(config);

        config.put(
                "database.url",
                "jdbc:mysql://prod-db");

        config.put(
                "cache.enabled",
                "true");

        config.put(
                "api.timeout",
                "30");

        config.printCurrentConfiguration();

        config.rollback(2);

        System.out.println(
                "\nAfter Rollback:");

        config.printCurrentConfiguration();
    }
}