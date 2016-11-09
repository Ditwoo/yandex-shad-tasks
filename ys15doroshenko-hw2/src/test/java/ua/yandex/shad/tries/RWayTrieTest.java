package ua.yandex.shad.autocomplete;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import ua.yandex.shad.collections.Queue;
import ua.yandex.shad.tries.RWayTrie;
import ua.yandex.shad.collections.Tuple;


public class RWayTrieTest {

    @Mock private RWayTrie mockTrie = Mockito.mock(RWayTrie.class);

    @Test
    public void addTestOnOneElement() {

        Tuple oneTuple = new Tuple("sometext", 7);
        RWayTrie trie = new RWayTrie();
        trie.add(oneTuple);

        boolean expResult = true;
        boolean actResult = trie.contains(oneTuple.term);

        assertEquals(expResult, actResult);
    }

    @Test
    public void addTestOnNotExistedElement() {

        Tuple oneTuple = new Tuple("sometext", 7);
        RWayTrie trie = new RWayTrie();
        trie.add(oneTuple);

        boolean expResult = false;
        boolean actResult = trie.contains("some");

        assertEquals(expResult, actResult);
    }

    @Test
    public void conainsTestOneElement() {

        Tuple oneTuple = new Tuple("sometext", 7);
        RWayTrie trie = new RWayTrie();
        trie.add(oneTuple);

        boolean expResult = true;
        boolean actResult = trie.contains(oneTuple.term);

        assertEquals(expResult, actResult);
    }

    @Test
    public void containsTestWithAddingSameTwoElements() {

        Tuple oneTuple = new Tuple("sometext", 7);
        Tuple twoTuple = new Tuple("anothertext", 5);
        RWayTrie trie = new RWayTrie();
        trie.add(oneTuple);
        trie.add(twoTuple);
        trie.add(oneTuple);

        boolean expResult = true;
        boolean actResult = trie.contains(oneTuple.term);

        assertEquals(expResult, actResult);
    }

    @Test
    public void containsTestOnNotExistedElement() {

        Tuple oneTuple = new Tuple("sometext", 7);
        RWayTrie trie = new RWayTrie();
        trie.add(oneTuple);

        boolean expResult = false;
        boolean actResult = trie.contains("some");

        assertEquals(expResult, actResult);
    }

    @Test
    public void deleteTestOnOne() {

        Tuple oneTuple = new Tuple("someanothertext", 7);
        RWayTrie trie = new RWayTrie();
        trie.add(oneTuple);

        boolean expResult = true;
        boolean actResult = trie.delete("someanothertext");

        assertEquals(expResult, actResult);
    }

    @Test
    public void deleteTestOnNotExistedElement() {

        Tuple oneTuple = new Tuple("someanothertext", 7);
        RWayTrie trie = new RWayTrie();
        trie.add(oneTuple);

        boolean expResult = false;
        boolean actResult = trie.delete("text");

        assertEquals(expResult, actResult);
    }

    @Test
    public void sizeTestOnOneElement() {

        RWayTrie trie = mockTrie;
        when(trie.size()).thenReturn(1);

        int expResult = 1;
        int actResult = trie.size();

        assertEquals(expResult, actResult);
    }

    @Test
    public void sizeTestOnZeroElements() {

        RWayTrie trie = mock(RWayTrie.class);
        when(trie.size()).thenReturn(0);

        int expResult = 0;
        int actResult = trie.size();

        assertEquals(expResult, actResult);
    }

    @Test
    public void sizeTestOnDuplicatedElements() {

        Tuple oneTuple = new Tuple("someanothertext", 65);
        Tuple twoTuple = new Tuple("someanother", 3);
        Tuple threeTuple = new Tuple("blablabla", 9);
        RWayTrie trie = new RWayTrie();

        trie.add(oneTuple);
        trie.add(twoTuple);
        trie.add(threeTuple);
        trie.add(oneTuple);
        trie.add(threeTuple);

        int expResult = 3;
        int actResult = trie.size();

        assertEquals(expResult, actResult);
    }

    @Test
    public void wordsTestWithOneWord() {
        Tuple satanTuple = new Tuple("satan", 666);
        RWayTrie trie = new RWayTrie();

        trie.add(satanTuple);

        String expResult = "satan";
        Queue<String> temp = (Queue<String>)trie.words();

        assertEquals(expResult, temp.dequeue());
    }

    @Test
    public void wordsTestWithFewWords() {
        Tuple abaTuple = new Tuple("abba", 12);
        Tuple oneTuple = new Tuple("blabla", 33);
        Tuple twoTuple = new Tuple("blablabla", 20);
        RWayTrie trie = new RWayTrie();

        trie.add(abaTuple);
        trie.add(oneTuple);
        trie.add(twoTuple);

        String expResultAba = "abba";
        String expResultOne = "blabla";
        String expResultTwo = "blablabla";
        Queue<String> temp = (Queue<String>)trie.words();

        assertEquals(expResultAba, temp.dequeue());
        assertEquals(expResultOne, temp.dequeue());
        assertEquals(expResultTwo, temp.dequeue());
    }

    @Test
    public void wordsTestWithExistedElements() {
        RWayTrie trie = new RWayTrie();

        int expResult = 0;
        Queue<String> temp = (Queue<String>)trie.words();

        assertEquals(expResult, temp.size());
    }

    @Test
    public void wordsWithPrefixTestOnOneWord() {
        Tuple oneTuple = new Tuple("blabla", 33);
        RWayTrie trie = new RWayTrie();

        trie.add(oneTuple);

        String expResult = "blabla";
        Queue<String> temp = (Queue<String>)trie.wordsWithPrefix("bla");

        assertEquals(expResult, temp.dequeue());
    }

    @Test
    public void wordsWithPrefixTestWithExistedElement() {
        Tuple oneTuple = new Tuple("blabla", 33);
        Tuple satanTuple = new Tuple("satan", 666);
        Tuple twoTuple = new Tuple("someletters", 31);
        Tuple threeTuple = new Tuple("blablabla", 20);

        RWayTrie trie = new RWayTrie();

        trie.add(oneTuple);
        trie.add(satanTuple);
        trie.add(twoTuple);
        trie.add(threeTuple);

        int expResult = 0;
        Queue<String> temp = (Queue<String>)trie.wordsWithPrefix("lol");

        assertEquals(expResult, temp.size());
    }

    @Test
    public void wordsWithPrefixTestOnFewWords() {
        Tuple oneTuple = new Tuple("blabla", 33);
        Tuple twoTuple = new Tuple("blablaishere", 33);
        Tuple satanTuple = new Tuple("satan", 666);
        RWayTrie trie = new RWayTrie();

        trie.add(oneTuple);
        trie.add(twoTuple);
        trie.add(satanTuple);

        String expResultOne = "blabla";
        String expResultTwo = "blablaishere";
        Queue<String> temp = (Queue<String>)trie.wordsWithPrefix("bla");

        assertEquals(expResultOne, temp.dequeue());
        assertEquals(expResultTwo, temp.dequeue());
    }
}