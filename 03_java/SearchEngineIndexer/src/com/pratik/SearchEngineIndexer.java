package com.pratik;

import java.util.*;

public class SearchEngineIndexer {

    static class Document {
        private String docId;
        private String content;

        public Document(String docId, String content) {
            this.docId = docId;
            this.content = content;
        }

        public String getDocId() {
            return docId;
        }

        public String getContent() {
            return content;
        }
    }

    static class SearchEngine {

        private Map<String, Set<String>> invertedIndex;
        private Map<String, Integer> searchAnalytics;

        public SearchEngine() {
            invertedIndex = new HashMap<>();
            searchAnalytics = new HashMap<>();
        }

        public void addDocument(Document document) {

            String[] words =
                    document.getContent()
                            .toLowerCase()
                            .split("\\s+");

            for (String word : words) {
                invertedIndex
                        .computeIfAbsent(
                                word,
                                k -> new HashSet<>())
                        .add(document.getDocId());
            }
        }

        public Set<String> search(String keyword) {

            keyword = keyword.toLowerCase();

            searchAnalytics.put(
                    keyword,
                    searchAnalytics.getOrDefault(keyword, 0) + 1
            );

            return invertedIndex.getOrDefault(
                    keyword,
                    new HashSet<>()
            );
        }

        public void showAnalytics() {
            System.out.println("\nSearch Analytics:");
            System.out.println(searchAnalytics);
        }
    }

    public static void main(String[] args) {

        SearchEngine engine = new SearchEngine();

        engine.addDocument(
                new Document(
                        "DOC1",
                        "java backend distributed systems"));

        engine.addDocument(
                new Document(
                        "DOC2",
                        "java spring boot microservices"));

        System.out.println(
                "Search Result: " +
                        engine.search("java"));

        System.out.println(
                "Search Result: " +
                        engine.search("backend"));

        engine.showAnalytics();
    }
}