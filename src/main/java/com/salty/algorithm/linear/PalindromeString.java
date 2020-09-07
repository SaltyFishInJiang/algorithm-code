package com.salty.algorithm.linear;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author ryan
 * @since 2020/09/04
 */
@AllArgsConstructor
@Data
public class PalindromeString {

    @AllArgsConstructor
    @Getter
    static class Node {

        char c;

        Node next;
    }

    public static void main(String[] args) {
        Assert.isTrue(_chargeIsPalindromeStringFirst(_generateString("112211")));
        Assert.isTrue(_chargeIsPalindromeStringFirst(_generateString("11211")));
        Assert.isTrue(_chargeIsPalindromeStringSecond(_generateString("112211")));
        Assert.isTrue(_chargeIsPalindromeStringSecond(_generateString("11211")));


        Assert.isTrue(!_chargeIsPalindromeStringFirst(_generateString("11122211")));
        Assert.isTrue(!_chargeIsPalindromeStringSecond(_generateString("112231211")));
    }

    private static boolean _chargeIsPalindromeStringFirst(Node head) {
        Node newNode = _reverseAndGenerate(head);
        return _charge(head, newNode);
    }

    private static boolean _charge(Node node, Node newNode) {
        while (node != null) {
            if (node.c != newNode.c) {
                return false;
            }
            node = node.next;
            newNode = newNode.next;
        }
        return true;
    }

    private static Node _reverseAndGenerate(Node head) {
        Node prev = null;
        Node node = head;
        while (node != null) {
            prev = new Node(node.c, prev);
            node = node.next;
        }
        return prev;
    }

    private static Node _reverse(Node head) {
        Node prev = null;
        Node node = head;
        while (node != null) {
            Node next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }

    private static boolean _chargeIsPalindromeStringSecond(Node head) {
        Node f = head;
        Node s = head;
        Node prev = null;
        while (s != null && s.next != null) {
            prev = f;
            f = f.next;
            s = s.next.next;
        }
        if (prev != null) {
            prev.next = null;
        }
        Node next = s == null ? f : f.next;
        Node newNode = _reverse(head);

        return _charge(next, newNode);
    }

    private static Node _generateString(String s) {
        Objects.requireNonNull(s);
        s = s.trim();
        Node[] nodes = new Node[s.length()];
        for (int i = 0; i < s.toCharArray().length; i++) {
            nodes[i] = new Node(s.charAt(i), null);
            if (i > 0) {
                nodes[i - 1].next = nodes[i];
            }
        }
        return nodes[0];
    }
}