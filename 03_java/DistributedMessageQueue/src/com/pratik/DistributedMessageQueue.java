package com.pratik;

import java.util.*;

public class DistributedMessageQueue {

    static class Message {
        private final String messageId;
        private final String payload;
        private final long timestamp;

        public Message(String payload) {
            this.messageId = UUID.randomUUID().toString();
            this.payload = payload;
            this.timestamp = System.currentTimeMillis();
        }

        public String getPayload() {
            return payload;
        }

        @Override
        public String toString() {
            return "Message{id='" + messageId +
                    "', payload='" + payload +
                    "', timestamp=" + timestamp + "}";
        }
    }

    static class Topic {
        private final String name;
        private final List<Message> messages;

        public Topic(String name) {
            this.name = name;
            this.messages = new ArrayList<>();
        }

        public void publish(Message message) {
            messages.add(message);
        }

        public Message getMessage(int offset) {
            if (offset >= messages.size()) {
                return null;
            }
            return messages.get(offset);
        }

        public int size() {
            return messages.size();
        }
    }

    static class MessageQueueService {
        private final Map<String, Topic> topics = new HashMap<>();
        private final Map<String, Integer> consumerOffsets = new HashMap<>();

        public void createTopic(String topicName) {
            topics.put(topicName, new Topic(topicName));
            System.out.println("Topic Created: " + topicName);
        }

        public void publish(String topicName, String payload) {
            Topic topic = topics.get(topicName);

            if (topic == null) {
                System.out.println("Topic not found");
                return;
            }

            topic.publish(new Message(payload));
            System.out.println("Message Published to " + topicName);
        }

        public void consume(String topicName, String consumerId) {
            Topic topic = topics.get(topicName);

            if (topic == null) {
                System.out.println("Topic not found");
                return;
            }

            String key = consumerId + "_" + topicName;
            int offset = consumerOffsets.getOrDefault(key, 0);

            Message message = topic.getMessage(offset);

            if (message == null) {
                System.out.println("No new messages");
                return;
            }

            System.out.println(
                    consumerId + " consumed: " + message.getPayload()
            );

            consumerOffsets.put(key, offset + 1);
        }
    }

    public static void main(String[] args) {

        MessageQueueService mq = new MessageQueueService();

        mq.createTopic("orders");

        mq.publish("orders", "Order #1001 Created");
        mq.publish("orders", "Order #1002 Created");

        mq.consume("orders", "consumer1");
        mq.consume("orders", "consumer1");
        mq.consume("orders", "consumer1");
    }
}