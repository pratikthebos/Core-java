package com.pratik;

import java.util.*;

public class FeatureFlagManagementSystem {

    static class FeatureFlag {

        private String featureName;
        private boolean enabled;

        public FeatureFlag(String featureName) {

            this.featureName = featureName;
            this.enabled = false;
        }

        public void enable() {
            enabled = true;
        }

        public void disable() {
            enabled = false;
        }

        public boolean isEnabled() {
            return enabled;
        }

        @Override
        public String toString() {

            return featureName +
                    " -> " +
                    (enabled
                            ? "ENABLED"
                            : "DISABLED");
        }
    }

    static class FeatureManager {

        private final Map<String,
                FeatureFlag> features =
                new HashMap<>();

        public void addFeature(
                String featureName) {

            features.put(
                    featureName,
                    new FeatureFlag(
                            featureName));

            System.out.println(
                    "Feature Added: "
                            + featureName);
        }

        public void enableFeature(
                String featureName) {

            FeatureFlag feature =
                    features.get(featureName);

            if (feature != null) {
                feature.enable();
            }
        }

        public void disableFeature(
                String featureName) {

            FeatureFlag feature =
                    features.get(featureName);

            if (feature != null) {
                feature.disable();
            }
        }

        public boolean isFeatureEnabled(
                String featureName) {

            FeatureFlag feature =
                    features.get(featureName);

            return feature != null
                    && feature.isEnabled();
        }

        public void showFeatures() {

            System.out.println(
                    "\nFeature Flags");

            for (FeatureFlag feature
                    : features.values()) {

                System.out.println(
                        feature);
            }
        }
    }

    public static void main(String[] args) {

        FeatureManager manager =
                new FeatureManager();

        manager.addFeature(
                "NEW_LOGIN_UI");

        manager.addFeature(
                "PAYMENT_V2");

        manager.enableFeature(
                "NEW_LOGIN_UI");

        System.out.println(
                "Login UI Enabled: "
                        + manager.isFeatureEnabled(
                        "NEW_LOGIN_UI"));

        manager.showFeatures();
    }
}