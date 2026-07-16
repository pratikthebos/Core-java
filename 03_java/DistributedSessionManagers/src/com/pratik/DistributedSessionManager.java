package com.pratik;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DistributedSessionManager {

    static class Session {

        private final String sessionId;
        private final String userId;
        private final Instant createdAt;
        private Instant expiresAt;

        public Session(String userId, long ttlSeconds) {
            this.sessionId = UUID.randomUUID().toString();
            this.userId = userId;
            this.createdAt = Instant.now();
            this.expiresAt = createdAt.plusSeconds(ttlSeconds);
        }

        public String getSessionId() {
            return sessionId;
        }

        public boolean isExpired() {
            return Instant.now().isAfter(expiresAt);
        }

        public void refresh(long ttlSeconds) {
            expiresAt = Instant.now().plusSeconds(ttlSeconds);
        }

        @Override
        public String toString() {
            return "Session{" +
                    "user='" + userId + '\'' +
                    ", sessionId='" + sessionId + '\'' +
                    ", expiresAt=" + expiresAt +
                    '}';
        }
    }

    static class SessionService {

        private final Map<String, Session> sessions =
                new ConcurrentHashMap<>();

        private static final long SESSION_TTL = 300;

        public String login(String userId) {

            Session session =
                    new Session(userId, SESSION_TTL);

            sessions.put(
                    session.getSessionId(),
                    session);

            return session.getSessionId();
        }

        public boolean validate(String sessionId) {

            Session session =
                    sessions.get(sessionId);

            if (session == null ||
                    session.isExpired()) {

                sessions.remove(sessionId);

                return false;
            }

            return true;
        }

        public void refresh(String sessionId) {

            Session session =
                    sessions.get(sessionId);

            if (session != null &&
                    !session.isExpired()) {

                session.refresh(SESSION_TTL);
            }
        }

        public void logout(String sessionId) {

            sessions.remove(sessionId);
        }

        public void cleanupExpiredSessions() {

            sessions.entrySet()
                    .removeIf(entry ->
                            entry.getValue().isExpired());
        }

        public void printActiveSessions() {

            System.out.println("\nActive Sessions");

            sessions.values()
                    .forEach(System.out::println);
        }
    }

    public static void main(String[] args) {

        SessionService service =
                new SessionService();

        String session1 =
                service.login("USER1001");

        String session2 =
                service.login("USER1002");

        System.out.println(
                "Session Valid: " +
                        service.validate(session1));

        service.refresh(session1);

        service.logout(session2);

        service.cleanupExpiredSessions();

        service.printActiveSessions();
    }
}