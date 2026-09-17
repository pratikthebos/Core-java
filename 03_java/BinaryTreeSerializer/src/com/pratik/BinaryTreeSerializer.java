package com.pratik;

import java.util.*;

public class BinaryTreeSerializer {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static String serialize(TreeNode root) {
        if (root == null) return "null";

        return root.val + "," +
               serialize(root.left) + "," +
               serialize(root.right);
    }

    static TreeNode deserialize(String data) {
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return build(q);
    }

    static TreeNode build(Queue<String> q) {
        String value = q.poll();

        if (value.equals("null")) return null;

        TreeNode node = new TreeNode(Integer.parseInt(value));
        node.left = build(q);
        node.right = build(q);

        return node;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String data = serialize(root);
        System.out.println("Serialized: " + data);

        TreeNode newRoot = deserialize(data);
        System.out.println("Deserialized successfully: " + newRoot.val);
    }
}