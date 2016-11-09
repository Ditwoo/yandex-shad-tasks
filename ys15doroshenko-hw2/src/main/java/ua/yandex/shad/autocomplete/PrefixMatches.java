package ua.yandex.shad.autocomplete;

import ua.yandex.shad.collections.Queue;
import ua.yandex.shad.tries.Trie;
import ua.yandex.shad.collections.Tuple;
import ua.yandex.shad.tries.RWayTrie;

import java.util.Iterator;

public class PrefixMatches {

    private Trie trie = new RWayTrie();

    public int load(String... strings) {

        if (strings.length == 0) {
            return 0;
        }

        for (String str : strings) {

            int wBefore = 0;
            int len = str.length();

            for (int i = 0; i < len; i++) {

                if (str.charAt(i) == ' ') {
                    if (i - wBefore > 2) {
                        trie.add(new Tuple(str.substring(wBefore, i),
                                i - wBefore));
                    }
                    wBefore = i + 1;
                }
            }

            if (len - wBefore > 2) {
                trie.add(new Tuple(str.substring(wBefore), len - wBefore));
            }

        }

        return trie.size();
    }

    public boolean contains(String word) {

        return trie.contains(word);
    }

    public boolean delete(String word) {

        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {

        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {

        if (pref.length() <= 2) {
            return null;
        }

        Iterable<String> all = trie.wordsWithPrefix(pref);
        Iterator<String> it  = all.iterator();
        Queue<String> ans = new Queue();

        if (!it.hasNext()) {
            return null;
        }

        String word = it.next();
        int len = word.length();
        int lastLen = word.length();
        int i = 0;

        while (it.hasNext() && i < k) {

            ans.enqueue(word);
            word = it.next();
            len = word.length();

            if (len != lastLen) {
                i++;
                lastLen = len;
            }
        }

        return ans;
    }

    public int size() {

        return trie.size();
    }
}
