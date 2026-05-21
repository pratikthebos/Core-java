package com.pratik;

import java.util.*;

public class AutoCompleteSearchEngine {

    static class TrieNode {

        Map<Character, TrieNode> children;
        boolean isWord;

        TrieNode() {
            children = new HashMap<>();
            isWord = false;
        }
    }

    static class Trie {

        private final TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        // Insert word
        public void insert(String word) {

            TrieNode current = root;

            for (char ch : word.toCharArray()) {

                current.children.putIfAbsent(ch,
                        new TrieNode());

                current = current.children.get(ch);
            }

            current.isWord = true;
        }

        // Get suggestions
        public List<String> searchPrefix(String prefix) {

            List<String> result = new ArrayList<>();
            TrieNode current = root;

            // Reach prefix node
            for (char ch : prefix.toCharArray()) {

                if (!current.children.containsKey(ch)) {
                    return result;
                }

                current = current.children.get(ch);
            }

            dfs(current, prefix, result);

            return result;
        }

        private void dfs(TrieNode node,
                         String word,
                         List<String> result) {

            if (node.isWord) {
                result.add(word);
            }

            for (char ch : node.children.keySet()) {

                dfs(node.children.get(ch),
                        word + ch,
                        result);
            }
        }
    }

    public static void main(String[] args) {

        Trie trie = new Trie();

        trie.insert("apple");
        trie.insert("application");
        trie.insert("apply");
        trie.insert("banana");

        List<String> suggestions =
                trie.searchPrefix("ap");

        System.out.println("Suggestions: "
                + suggestions);
    }
}