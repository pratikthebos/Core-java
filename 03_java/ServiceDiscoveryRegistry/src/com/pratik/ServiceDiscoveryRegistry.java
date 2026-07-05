package com.pratik;

import java.util.*;

public class ServiceDiscoveryRegistry {

    static class ServiceInstance {

        private final String serviceName;
        private final String instanceId;
        private final String host;
        private final int port;
        private long lastHeartbeat;

        public ServiceInstance(
                String serviceName,
                String instanceId,
                String host,
                int port) {

            this.serviceName = serviceName;
            this.instanceId = instanceId;
            this.host = host;
            this.port = port;
            this.lastHeartbeat = System.currentTimeMillis();
        }

        public void heartbeat() {
            lastHeartbeat = System.currentTimeMillis();
        }

        public boolean isAlive(long timeoutMillis) {
            return System.currentTimeMillis() - lastHeartbeat
                    <= timeoutMillis;
        }

        public String getServiceName() {
            return serviceName;
        }

        public String getInstanceId() {
            return instanceId;
        }

        @Override
        public String toString() {
            return instanceId +
                    " -> " +
                    host +
                    ":" +
                    port;
        }
    }

    static class Registry {

        private final Map<String, List<ServiceInstance>>
                services = new HashMap<>();

        private static final long HEARTBEAT_TIMEOUT = 5000;

        public void register(ServiceInstance instance) {

            services
                    .computeIfAbsent(
                            instance.getServiceName(),
                            key -> new ArrayList<>())
                    .add(instance);
        }

        public void heartbeat(
                String serviceName,
                String instanceId) {

            List<ServiceInstance> list =
                    services.get(serviceName);

            if (list == null) {
                return;
            }

            for (ServiceInstance instance : list) {

                if (instance.getInstanceId()
                        .equals(instanceId)) {

                    instance.heartbeat();
                }
            }
        }

        public List<ServiceInstance> discover(
                String serviceName) {

            cleanup();

            return services.getOrDefault(
                    serviceName,
                    Collections.emptyList());
        }

        private void cleanup() {

            for (List<ServiceInstance> list
                    : services.values()) {

                list.removeIf(instance ->
                        !instance.isAlive(
                                HEARTBEAT_TIMEOUT));
            }
        }
    }

    public static void main(String[] args)
            throws Exception {

        Registry registry = new Registry();

        ServiceInstance order =
                new ServiceInstance(
                        "Order-Service",
                        "ORDER-1",
                        "10.0.0.5",
                        8080);

        ServiceInstance payment =
                new ServiceInstance(
                        "Payment-Service",
                        "PAY-1",
                        "10.0.0.8",
                        9090);

        registry.register(order);
        registry.register(payment);

        System.out.println(
                "Order Service:");

        System.out.println(
                registry.discover(
                        "Order-Service"));

        Thread.sleep(6000);

        System.out.println(
                "\nAfter Timeout:");

        System.out.println(
                registry.discover(
                        "Order-Service"));
    }
}