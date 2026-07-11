package com.pratik;

import java.time.LocalDateTime;
import java.util.*;

public class FeatureToggleAnalyticsPlatform {

    static class FeatureFlag {

        private final String featureName;
        private boolean enabled;
        private int rolloutPercentage;
        private final Set<String> targetedUsers;

        public FeatureFlag(String featureName) {
            this.featureName = featureName;
            this.rolloutPercentage = 100;
            this.enabled = false;
            this.targetedUsers = new HashSet<>();
        }

        public void enable() {
            enabled = true;
        }

        public void disable() {
            enabled = false;
        }

        public void setRolloutPercentage(int percentage) {
            rolloutPercentage = percentage;
        }

        public void addTargetUser(String userId) {
            targetedUsers.add(userId);
        }

        public boolean isAccessible(String userId) {

            if (!enabled) {
                return false;
            }

            if (targetedUsers.contains(userId)) {
                return true;
            }

            int bucket =
                    Math.abs(userId.hashCode()) % 100;

            return bucket < rolloutPercentage;
        }

        public String getFeatureName() {
            return featureName;
        }
    }

    static class AnalyticsRecord {

        private final String userId;
        private final String featureName;
        private final LocalDateTime accessTime;

        public AnalyticsRecord(
                String userId,
                String featureName) {

            this.userId = userId;
            this.featureName = featureName;
            this.accessTime = LocalDateTime.now();
        }

        @Override
        public String toString() {

            return accessTime +
                    " | " +
                    userId +
                    " | " +
                    featureName;
        }
    }

    static class FeaturePlatform {

        private final Map<String, FeatureFlag> features =
                new HashMap<>();

        private final List<AnalyticsRecord> analytics =
                new ArrayList<>();

        public void createFeature(String featureName) {

            features.put(
                    featureName,
                    new FeatureFlag(featureName));
        }

        public FeatureFlag getFeature(
                String featureName) {

            return features.get(featureName);
        }

        public void accessFeature(
                String userId,
                String featureName) {

            FeatureFlag feature =
                    features.get(featureName);

            if (feature == null) {

                System.out.println("Feature Not Found");
                return;
            }

            if (feature.isAccessible(userId)) {

                analytics.add(
                        new AnalyticsRecord(
                                userId,
                                featureName));

                System.out.println(
                        userId +
                        " accessed " +
                        featureName);

            } else {

                System.out.println(
                        "Access Denied");
            }
        }

        public void printAnalytics() {

            System.out.println(
                    "\nFeature Usage Analytics");

            Map<String, Integer> usage =
                    new HashMap<>();

            for (AnalyticsRecord record :
                    analytics) {

                usage.put(
                        record.featureName,
                        usage.getOrDefault(
                                record.featureName,
                                0) + 1);

                System.out.println(record);
            }

            System.out.println("\nSummary");

            usage.forEach(
                    (feature, count) ->
                            System.out.println(
                                    feature +
                                    " -> " +
                                    count));
        }
    }

    public static void main(String[] args) {

        FeaturePlatform platform =
                new FeaturePlatform();

        platform.createFeature(
                "CHAT_V2");

        FeatureFlag chat =
                platform.getFeature("CHAT_V2");

        chat.enable();

        chat.setRolloutPercentage(40);

        chat.addTargetUser("ADMIN");

        platform.accessFeature(
                "ADMIN",
                "CHAT_V2");

        platform.accessFeature(
                "USER100",
                "CHAT_V2");

        platform.accessFeature(
                "USER101",
                "CHAT_V2");

        platform.accessFeature(
                "USER102",
                "CHAT_V2");

        platform.printAnalytics();
    }
}