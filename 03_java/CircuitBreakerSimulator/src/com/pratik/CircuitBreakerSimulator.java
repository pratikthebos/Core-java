package com.pratik;

import java.util.*;

public class CircuitBreakerSimulator {

    enum State {
        CLOSED,
        OPEN,
        HALF_OPEN
    }

    static class CircuitBreaker {

        private State state;
        private int failureCount;
        private final int failureThreshold;
        private final long retryTimeout;
        private long lastFailureTime;

        public CircuitBreaker(
                int failureThreshold,
                long retryTimeout) {

            this.state = State.CLOSED;
            this.failureCount = 0;
            this.failureThreshold = failureThreshold;
            this.retryTimeout = retryTimeout;
        }

        public boolean allowRequest() {

            if (state == State.OPEN) {

                long currentTime =
                        System.currentTimeMillis();

                if (currentTime - lastFailureTime
                        > retryTimeout) {

                    state = State.HALF_OPEN;
                    return true;
                }

                return false;
            }

            return true;
        }

        public void recordSuccess() {
            failureCount = 0;
            state = State.CLOSED;
        }

        public void recordFailure() {

            failureCount++;
            lastFailureTime =
                    System.currentTimeMillis();

            if (failureCount >= failureThreshold) {
                state = State.OPEN;
            }
        }

        public State getState() {
            return state;
        }
    }

    static class ExternalService {

        private Random random =
                new Random();

        public boolean call() {
            return random.nextInt(100) >= 50;
        }
    }

    public static void main(String[] args)
            throws Exception {

        CircuitBreaker breaker =
                new CircuitBreaker(3, 5000);

        ExternalService service =
                new ExternalService();

        for (int i = 1; i <= 10; i++) {

            System.out.println(
                    "\nRequest " + i);

            if (!breaker.allowRequest()) {

                System.out.println(
                        "Blocked by Circuit Breaker | State: "
                                + breaker.getState());

                Thread.sleep(1000);
                continue;
            }

            boolean success = service.call();

            if (success) {

                breaker.recordSuccess();

                System.out.println(
                        "Service Success | State: "
                                + breaker.getState());

            } else {

                breaker.recordFailure();

                System.out.println(
                        "Service Failed | State: "
                                + breaker.getState());
            }

            Thread.sleep(1000);
        }
    }
}