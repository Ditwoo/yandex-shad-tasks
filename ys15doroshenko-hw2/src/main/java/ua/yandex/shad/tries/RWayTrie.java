package ua.yandex.shad.tries;

import ua.yandex.shad.collections.Queue;
import ua.yandex.shad.collections.Tuple;

public class RWayTrie implements Trie {

    //size of alphabet
    private static final int R = 26;
    private static final String EMPTY_STRING = "";

    public class Node {

        public int weight;
        public Node[] nextNode = new Node[R];

    }

    private Node root = new Node();

    private Node add(Node x, String key, int value, int d) {

        if (x == null) {
            x = new Node();
        }

        if (d == key.length()) {
            x.weight = value;
            return x;
        }

        int c = key.charAt(d) - 'a';
        x.nextNode[c] = add(x.nextNode[c], key, value, d + 1);
        return x;
    }

    @Override
    public void add(Tuple t) {

        if (!contains(t.term)) {
            root = add(root, t.term, t.weight, 0);
        }
    }

    private Node getNode(Node x, String key, int d) {

        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }

        int c = key.charAt(d) - 'a';
        return getNode(x.nextNode[c], key, d + 1);
    }

    private int getValue(String s) {

        Node node = getNode(root, s, 0);
        if (node == null) {
            return 0;
        }
        return node.weight;
    }

    @Override
    public boolean contains(String word) {

        return getValue(word) != 0;
    }

    private boolean delete(Node x, String key, int d) {

        if (x == null) {
            return false;
        }
        if (d == key.length()) {
            x.weight = 0;
            return true;
        }

        int chAtDist = key.charAt(d) - 'a';
        return delete(x.nextNode[chAtDist], key, d + 1);
    }

    @Override
    public boolean delete(String word) {

        if (contains(word)) {
            return delete(root, word, 0);
        }
        return false;
    }

    @Override
    public Iterable<String> words() {

        return wordsWithPrefix(EMPTY_STRING);
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {

        Queue<String> q = new Queue();
        Queue<String> words = new Queue();
        Queue<Node[]> path = new Queue();

        Node nodeWithLastLetter = getNode(root, s, 0);

        if (nodeWithLastLetter == null) {
            return new Queue<String>();
        }
        if (nodeWithLastLetter.weight != 0) {
            q.enqueue(s);
        }

        path.enqueue(nodeWithLastLetter.nextNode);
        words.enqueue(s);

        while (!path.isEmpty()) {

            Node[] lastNode = path.dequeue();
            String lastWord = words.dequeue();

            for (int i = 0; i < R; i++) {

                if (lastNode[i] != null) {

                    if (lastNode[i].weight != 0) {
                        q.enqueue(lastWord + (char) (i + 'a'));
                    }
                    path.enqueue(lastNode[i].nextNode);
                    words.enqueue(lastWord + (char) (i + 'a'));
                }
            }
        }

        return q;
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }

        int countOfWords = 0;
        if (x.weight != 0) {
            countOfWords++;
        }

        for (int i = 0; i < R; i++) {
            countOfWords += size(x.nextNode[i]);
        }

        return countOfWords;
    }

    @Override
    public int size() {

        return size(root);
    }
}
