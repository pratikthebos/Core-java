package com.pratik;

import java.util.*;

public class FeatureFlagManager {

    static class FeatureFlag {
        private final String name;
        private boolean enabled;
        private int rolloutPercentage;
        private final Set<String> allowedUsers;

        public FeatureFlag(String name) {
            this.name = name;
            this.enabled = false;
            this.rolloutPercentage = 0;
            this.allowedUsers = new HashSet<>();
        }

        public void enable() {
            enabled = true;
        }

        public void disable() {
            enabled = false;
        }

        public void setRolloutPercentage(int percentage) {
            this.rolloutPercentage = percentage;
        }

        public void allowUser(String userId) {
            allowedUsers.add(userId);
        }

        public boolean isEnabledFor(String userId) {

            if (!enabled) {
                return false;
            }

            if (allowedUsers.contains(userId)) {
                return true;
            }

            int bucket =
                    Math.abs(userId.hashCode()) % 100;

            return bucket < rolloutPercentage;
        }

        @Override
        public String toString() {
            return "FeatureFlag{" +
                    "name='" + name + '\'' +
                    ", enabled=" + enabled +
                    ", rollout=" + rolloutPercentage +
                    "%}";
        }
    }

    static class FeatureFlagService {

        private final Map<String, FeatureFlag> flags =
                new HashMap<>();

        public void createFeature(String name) {
            flags.put(name, new FeatureFlag(name));
        }

        public FeatureFlag getFeature(String name) {
            return flags.get(name);
        }

        public void printFeatures() {

            System.out.println("\nRegistered Features:");

            for (FeatureFlag flag : flags.values()) {
                System.out.println(flag);
            }
        }
    }

    public static void main(String[] args) {

        FeatureFlagService service =
                new FeatureFlagService();

        service.createFeature("NEW_DASHBOARD");

        FeatureFlag dashboard =
                service.getFeature("NEW_DASHBOARD");

        dashboard.enable();
        dashboard.setRolloutPercentage(40);

        dashboard.allowUser("ADMIN_001");

        String[] users = {
                "ADMIN_001",
                "USER_101",
                "USER_102",
                "USER_103"
        };

        for (String user : users) {

            System.out.println(
                    user + " -> " +
                    dashboard.isEnabledFor(user));
        }

        service.printFeatures();
    }
}