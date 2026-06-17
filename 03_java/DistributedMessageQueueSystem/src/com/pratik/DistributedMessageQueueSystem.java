package com.pratik;

import java.util.*;

public class DistributedMessageQueueSystem {

    static class Message {

        private String content;
        private long timestamp;

        public Message(String content) {
            this.content = content;
            this.timestamp = System.currentTimeMillis();
        }

        @Override
        public String toString() {
            return "Message: " + content +
                    ", Timestamp: " + timestamp;
        }
    }

    static class Topic {

        private String topicName;
        private Queue<Message> messages;

        public Topic(String topicName) {
            this.topicName = topicName;
            this.messages = new LinkedList<>();
        }

        public void publish(String content) {
            messages.offer(new Message(content));
        }

        public Message consume() {
            return messages.poll();
        }

        public boolean isEmpty() {
            return messages.isEmpty();
        }
    }

    static class MessageBroker {

        private Map<String, Topic> topics =
                new HashMap<>();

        public void createTopic(String topicName) {

            topics.put(
                    topicName,
                    new Topic(topicName));

            System.out.println(
                    "Topic Created: " +
                            topicName);
        }

        public void publishMessage(
                String topicName,
                String message) {

            Topic topic = topics.get(topicName);

            if (topic == null) {
                System.out.println("Topic not found");
                return;
            }

            topic.publish(message);

            System.out.println(
                    "Published to " + topicName);
        }

        public void consumeMessage(
                String topicName) {

            Topic topic = topics.get(topicName);

            if (topic == null ||
                    topic.isEmpty()) {

                System.out.println(
                        "No messages available");
                return;
            }

            Message message = topic.consume();

            System.out.println(
                    "Consumed: " + message);
        }
    }

    public static void main(String[] args) {

        MessageBroker broker =
                new MessageBroker();

        broker.createTopic("Orders");
        broker.createTopic("Payments");

        broker.publishMessage(
                "Orders",
                "Order #1001 Created");

        broker.publishMessage(
                "Payments",
                "Payment Success");

        broker.consumeMessage("Orders");
        broker.consumeMessage("Payments");
    }
}