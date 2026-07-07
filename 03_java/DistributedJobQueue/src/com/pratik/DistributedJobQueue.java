package com.pratik;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DistributedJobQueue {

    enum JobStatus {
        PENDING,
        RUNNING,
        SUCCESS,
        FAILED
    }

    static class Job implements Comparable<Job> {

        private final String jobId;
        private final String jobName;
        private final int priority;
        private final int maxRetries;

        private int retryCount;
        private JobStatus status;

        public Job(String jobName, int priority, int maxRetries) {

            this.jobId = UUID.randomUUID().toString();
            this.jobName = jobName;
            this.priority = priority;
            this.maxRetries = maxRetries;
            this.retryCount = 0;
            this.status = JobStatus.PENDING;
        }

        public boolean execute() {

            status = JobStatus.RUNNING;

            boolean success =
                    new Random().nextInt(100) >= 25;

            if (success) {
                status = JobStatus.SUCCESS;
                return true;
            }

            retryCount++;

            if (retryCount > maxRetries) {
                status = JobStatus.FAILED;
            } else {
                status = JobStatus.PENDING;
            }

            return false;
        }

        public boolean shouldRetry() {
            return retryCount <= maxRetries;
        }

        @Override
        public int compareTo(Job other) {
            return Integer.compare(
                    other.priority,
                    this.priority
            );
        }

        @Override
        public String toString() {
            return jobName +
                    " | " +
                    status +
                    " | Retry=" +
                    retryCount;
        }
    }

    static class JobScheduler {

        private final PriorityBlockingQueue<Job> queue =
                new PriorityBlockingQueue<>();

        private final ExecutorService workers;

        private final AtomicInteger completed =
                new AtomicInteger();

        public JobScheduler(int workerCount) {

            workers =
                    Executors.newFixedThreadPool(workerCount);
        }

        public void submit(Job job) {

            queue.offer(job);
        }

        public void start() {

            while (!queue.isEmpty()) {

                Job job = queue.poll();

                workers.submit(() -> {

                    boolean success = job.execute();

                    if (!success && job.shouldRetry()) {

                        queue.offer(job);

                    } else {

                        completed.incrementAndGet();

                        System.out.println(job);
                    }
                });
            }
        }

        public void shutdown() throws Exception {

            workers.shutdown();

            workers.awaitTermination(
                    1,
                    TimeUnit.MINUTES);

            System.out.println(
                    "\nCompleted Jobs : "
                            + completed.get());
        }
    }

    public static void main(String[] args)
            throws Exception {

        JobScheduler scheduler =
                new JobScheduler(3);

        scheduler.submit(
                new Job("Image Processing", 5, 2));

        scheduler.submit(
                new Job("Email Sending", 2, 1));

        scheduler.submit(
                new Job("Invoice Generation", 4, 3));

        scheduler.submit(
                new Job("Backup Database", 1, 2));

        scheduler.start();

        scheduler.shutdown();
    }
}