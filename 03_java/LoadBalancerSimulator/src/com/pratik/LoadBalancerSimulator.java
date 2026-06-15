package com.pratik;

import java.util.*;

public class LoadBalancerSimulator {

    static class Server {

        private String serverId;
        private boolean active;
        private int requestCount;

        public Server(String serverId) {
            this.serverId = serverId;
            this.active = true;
            this.requestCount = 0;
        }

        public boolean isActive() {
            return active;
        }

        public void disable() {
            active = false;
        }

        public void enable() {
            active = true;
        }

        public void handleRequest() {
            requestCount++;
        }

        @Override
        public String toString() {
            return "Server: " + serverId +
                    ", Active: " + active +
                    ", Requests: " + requestCount;
        }
    }

    static class LoadBalancer {

        private List<Server> servers;
        private int currentIndex;

        public LoadBalancer() {
            servers = new ArrayList<>();
            currentIndex = 0;
        }

        public void addServer(Server server) {
            servers.add(server);
        }

        public void routeRequest() {

            if (servers.isEmpty()) {
                System.out.println("No servers available");
                return;
            }

            int checked = 0;

            while (checked < servers.size()) {

                Server server =
                        servers.get(currentIndex);

                currentIndex =
                        (currentIndex + 1)
                                % servers.size();

                checked++;

                if (server.isActive()) {
                    server.handleRequest();
                    System.out.println(
                            "Request routed to "
                                    + server.serverId);
                    return;
                }
            }

            System.out.println(
                    "No active server found");
        }

        public void showStatus() {

            System.out.println(
                    "\nLoad Balancer Status");

            for (Server server : servers) {
                System.out.println(server);
            }
        }
    }

    public static void main(String[] args) {

        LoadBalancer balancer =
                new LoadBalancer();

        balancer.addServer(
                new Server("S1"));

        balancer.addServer(
                new Server("S2"));

        balancer.addServer(
                new Server("S3"));

        balancer.routeRequest();
        balancer.routeRequest();
        balancer.routeRequest();
        balancer.routeRequest();

        balancer.showStatus();
    }
}