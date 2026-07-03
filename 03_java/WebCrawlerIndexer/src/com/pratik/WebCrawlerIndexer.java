package com.pratik;

import java.util.*;

public class WebCrawlerIndexer {

    static class WebPage {
        private String url;
        private String content;

        public WebPage(String url, String content) {
            this.url = url;
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public String getContent() {
            return content;
        }
    }

    static class CrawlerService {
        private Queue<String> crawlQueue = new LinkedList<>();
        private Set<String> visitedUrls = new HashSet<>();
        private Map<String, Set<String>> searchIndex = new HashMap<>();

        public void addUrl(String url) {
            if (!visitedUrls.contains(url)) {
                crawlQueue.offer(url);
            }
        }

        public void crawl() {
            while (!crawlQueue.isEmpty()) {
                String url = crawlQueue.poll();

                if (visitedUrls.contains(url)) {
                    continue;
                }

                WebPage page = fetchPage(url);

                indexPage(page);

                visitedUrls.add(url);

                System.out.println("Crawled: " + url);
            }
        }

        private WebPage fetchPage(String url) {

            String content =
                    "java backend distributed systems crawler";

            return new WebPage(url, content);
        }

        private void indexPage(WebPage page) {

            String[] words =
                    page.getContent()
                        .toLowerCase()
                        .split("\\s+");

            for (String word : words) {
                searchIndex
                        .computeIfAbsent(
                                word,
                                k -> new HashSet<>())
                        .add(page.getUrl());
            }
        }

        public Set<String> search(String keyword) {
            return searchIndex.getOrDefault(
                    keyword.toLowerCase(),
                    new HashSet<>()
            );
        }
    }

    public static void main(String[] args) {

        CrawlerService crawler =
                new CrawlerService();

        crawler.addUrl("https://site1.com");
        crawler.addUrl("https://site2.com");

        crawler.crawl();

        System.out.println(
                "Search Result: " +
                        crawler.search("java"));
    }
}