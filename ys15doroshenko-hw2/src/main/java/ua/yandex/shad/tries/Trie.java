package ua.yandex.shad.tries;

import ua.yandex.shad.collections.Tuple;

public interface Trie {

    public void add(Tuple word);

    public boolean contains(String word);

    public boolean delete(String word);

    public Iterable<String> words();

    public Iterable<String> wordsWithPrefix(String pref);
    
    public int size();
}
