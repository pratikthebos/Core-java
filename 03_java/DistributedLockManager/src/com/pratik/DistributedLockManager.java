package com.pratik;

import java.util.*;

public class DistributedLockManager {

    static class Lock {

        private String resourceId;
        private String ownerId;
        private long expiryTime;

        public Lock(
                String resourceId,
                String ownerId,
                long ttlMillis) {

            this.resourceId = resourceId;
            this.ownerId = ownerId;
            this.expiryTime =
                    System.currentTimeMillis()
                            + ttlMillis;
        }

        public boolean isExpired() {
            return System.currentTimeMillis()
                    > expiryTime;
        }

        public String getOwnerId() {
            return ownerId;
        }

        @Override
        public String toString() {
            return "Resource: " + resourceId +
                    ", Owner: " + ownerId;
        }
    }

    static class LockService {

        private Map<String, Lock> lockMap =
                new HashMap<>();

        public synchronized boolean acquireLock(
                String resourceId,
                String ownerId,
                long ttlMillis) {

            Lock existingLock =
                    lockMap.get(resourceId);

            if (existingLock != null &&
                    !existingLock.isExpired()) {
                return false;
            }

            lockMap.put(
                    resourceId,
                    new Lock(
                            resourceId,
                            ownerId,
                            ttlMillis));

            return true;
        }

        public synchronized boolean releaseLock(
                String resourceId,
                String ownerId) {

            Lock lock =
                    lockMap.get(resourceId);

            if (lock == null) {
                return false;
            }

            if (!lock.getOwnerId()
                    .equals(ownerId)) {
                return false;
            }

            lockMap.remove(resourceId);

            return true;
        }

        public void showLocks() {

            System.out.println(
                    "\nActive Locks");

            for (Lock lock :
                    lockMap.values()) {

                if (!lock.isExpired()) {
                    System.out.println(lock);
                }
            }
        }
    }

    public static void main(String[] args)
            throws Exception {

        LockService service =
                new LockService();

        boolean acquired =
                service.acquireLock(
                        "RESOURCE_1",
                        "USER_A",
                        5000);

        System.out.println(
                "Lock Acquired: "
                        + acquired);

        service.showLocks();

        boolean released =
                service.releaseLock(
                        "RESOURCE_1",
                        "USER_A");

        System.out.println(
                "Lock Released: "
                        + released);
    }
}