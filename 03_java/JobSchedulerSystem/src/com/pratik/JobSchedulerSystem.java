package com.pratik;

import java.util.*;

public class JobSchedulerSystem {

    static class Job {
        private int jobId;
        private String jobName;
        private int priority;
        private String status;

        public Job(int jobId, String jobName, int priority) {
            this.jobId = jobId;
            this.jobName = jobName;
            this.priority = priority;
            this.status = "PENDING";
        }

        public int getPriority() {
            return priority;
        }

        public void markCompleted() {
            status = "COMPLETED";
        }

        @Override
        public String toString() {
            return "Job ID: " + jobId +
                    ", Name: " + jobName +
                    ", Priority: " + priority +
                    ", Status: " + status;
        }
    }

    static class JobScheduler {

        private PriorityQueue<Job> queue;

        public JobScheduler() {
            queue = new PriorityQueue<>(
                    (a, b) -> b.getPriority() - a.getPriority()
            );
        }

        public void submitJob(Job job) {
            queue.offer(job);
            System.out.println("Job Submitted");
        }

        public void executeJob() {

            if (queue.isEmpty()) {
                System.out.println("No Jobs Pending");
                return;
            }

            Job job = queue.poll();
            job.markCompleted();

            System.out.println(
                    "Executed: " + job
            );
        }

        public void showPendingJobs() {

            System.out.println("\nPending Jobs:");

            if (queue.isEmpty()) {
                System.out.println("None");
                return;
            }

            for (Job job : queue) {
                System.out.println(job);
            }
        }
    }

    public static void main(String[] args) {

        JobScheduler scheduler =
                new JobScheduler();

        scheduler.submitJob(
                new Job(1, "Data Backup", 2));

        scheduler.submitJob(
                new Job(2, "Email Service", 5));

        scheduler.submitJob(
                new Job(3, "Report Generation", 3));

        scheduler.showPendingJobs();

        scheduler.executeJob();

        scheduler.showPendingJobs();
    }
}