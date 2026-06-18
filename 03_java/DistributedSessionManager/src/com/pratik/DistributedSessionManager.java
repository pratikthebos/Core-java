package com.pratik;

import java.util.*;

public class DistributedSessionManager {

    static class Session {

        private String sessionId;
        private String userId;
        private long expiryTime;

        public Session(
                String sessionId,
                String userId,
                long ttlMillis) {

            this.sessionId = sessionId;
            this.userId = userId;
            this.expiryTime =
                    System.currentTimeMillis()
                            + ttlMillis;
        }

        public boolean isExpired() {
            return System.currentTimeMillis()
                    > expiryTime;
        }

        public String getSessionId() {
            return sessionId;
        }

        @Override
        public String toString() {
            return "Session ID: " + sessionId +
                    ", User: " + userId;
        }
    }

    static class SessionService {

        private Map<String, Session> sessions =
                new HashMap<>();

        public String createSession(
                String userId,
                long ttlMillis) {

            String sessionId =
                    UUID.randomUUID().toString();

            Session session =
                    new Session(
                            sessionId,
                            userId,
                            ttlMillis);

            sessions.put(sessionId, session);

            return sessionId;
        }

        public boolean validateSession(
                String sessionId) {

            Session session =
                    sessions.get(sessionId);

            if (session == null ||
                    session.isExpired()) {

                sessions.remove(sessionId);
                return false;
            }

            return true;
        }

        public void logout(
                String sessionId) {

            sessions.remove(sessionId);
        }

        public void showSessions() {

            System.out.println(
                    "\nActive Sessions");

            for (Session session
                    : sessions.values()) {

                if (!session.isExpired()) {
                    System.out.println(session);
                }
            }
        }
    }

    public static void main(String[] args)
            throws Exception {

        SessionService service =
                new SessionService();

        String sessionId =
                service.createSession(
                        "USER_101",
                        5000);

        System.out.println(
                "Session Created: "
                        + sessionId);

        System.out.println(
                service.validateSession(
                        sessionId));

        Thread.sleep(6000);

        System.out.println(
                service.validateSession(
                        sessionId));

        service.showSessions();
    }
}