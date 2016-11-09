package ua.yandex.shad.autocomplete;

import static org.junit.Assert.*;

import org.junit.Test;
import ua.yandex.shad.collections.Queue;

public class PrefixMatchesTest {

    @Test
    public void loadTetOnEasyStrings() {

        String[] temp = new String[] {
                "some",
                "string",
                "writed",
                "here"};

        PrefixMatches pref = new PrefixMatches();
        pref.load(temp);

        boolean expRes = true;
        boolean actRes = pref.contains("writed");

        assertEquals(expRes, actRes);
    }

    @Test
    public void loadTestOnLongString() {

        String temp = "some text are writed here";

        PrefixMatches pref = new PrefixMatches();

        int expResult = 5;
        int actResult =  pref.load(temp);

        assertEquals(expResult, actResult);
    }

    @Test
    public void loadTetOnEasyStringsWithNotExistedElement() {

        String[] temp = new String[] {
                "some",
                "string",
                "writed",
                "here"};

        PrefixMatches pref = new PrefixMatches();
        pref.load(temp);

        boolean expRes = false;
        boolean actRes = pref.contains("blabla");

        assertEquals(expRes, actRes);
    }

    @Test
    public void containsTestOnEasyStrings() {

        String[] temp = new String[] {
                "some",
                "string",
                "writed",
                "here",
                "and",
                "something"};

        PrefixMatches pref = new PrefixMatches();
        pref.load(temp);

        boolean expRes = true;
        boolean actRes = pref.contains("here");

        assertEquals(expRes, actRes);
    }

    @Test
    public void containsTestOnEasyStringsWithNotExistedElement() {

        String[] temp = new String[] {
                "some",
                "string",
                "writed",
                "here",
                "ornot"};

        PrefixMatches pref = new PrefixMatches();
        pref.load(temp);

        boolean expRes = false;
        boolean actRes = pref.contains("anotherstring");

        assertEquals(expRes, actRes);
    }

    @Test
    public void deleteTestOnEasyStrings() {

        String[] temp = new String[] {
                "some",
                "string",
                "writed",
                "here",
                "and",
                "something"};

        PrefixMatches pref = new PrefixMatches();
        pref.load(temp);

        boolean expRes = true;
        boolean actRes = pref.delete("writed");

        assertEquals(expRes, actRes);
    }

    @Test
    public void deleteTestOnEasyStringsWithNotExistedElement() {

        String[] temp = new String[] {
                "some",
                "string",
                "writed",
                "here",
                "and",
                "something"};

        PrefixMatches pref = new PrefixMatches();
        pref.load(temp);

        boolean expRes = false;
        boolean actRes = pref.contains("blabla");

        assertEquals(expRes, actRes);
    }

    @Test
    public void wordsWithPrefixTestOnEasyStrings() {

        String[] strings = new String[] {
                "blabla",
                "blablaolololo",
                "blablabla",
                "anotherstring"};

        PrefixMatches pref = new PrefixMatches();
        pref.load(strings);

        String expOne = "blabla";
        String expTwo = "blablabla";
        String expThree = "blablaolololo";

        Queue<String> temp = (Queue<String>)pref.wordsWithPrefix("bla");

        assertEquals(expOne, temp.dequeue());
        assertEquals(expTwo, temp.dequeue());
        assertEquals(expThree, temp.dequeue());
    }

    @Test
    public void wordsWithPrefixTestOnExistedElements() {

        String[] temp = new String[] {};

        PrefixMatches pref = new PrefixMatches();
        pref.load(temp);

        int expRes = 0;
        Queue<String> t = (Queue<String>)pref.wordsWithPrefix("some");
        int actRes = t.size();

        assertEquals(expRes, actRes);
    }

    @Test
    public void wordsWithPrefixTestOnSmallStrings() {

        PrefixMatches pref = new PrefixMatches();
        pref.load("so", "me", "s", "ma", "ll");

        int expRes = 0;
        int actRes = pref.size();

        assertEquals(expRes, actRes);
    }

    @Test
    public void wordsWithPrefixTestWitHIntOnEasyStrings() {

        PrefixMatches pref = new PrefixMatches();
        pref.load("abc", "abcd", "abce", "abcdef", "abcde");

        String expResult = "abc";
        Queue<String> temp = (Queue<String>)pref.wordsWithPrefix("abc", 3);

        assertEquals(expResult, temp.dequeue());
    }

    @Test
    public void wordsWithPrefixTestWithIntOnSmallPrefix() {

        PrefixMatches pref = new PrefixMatches();
        pref.load("abc", "abcd", "abce", "abcdef", "abcde");

        Queue<String> expRes = null;
        Queue<String> t = (Queue<String>)pref.wordsWithPrefix("so", 1);

        assertEquals(expRes, t);
    }

    @Test
    public void wordsWithPrefixTestWitHIntOnExistedPrefix() {

        PrefixMatches pref = new PrefixMatches();
        pref.load("abc", "abcd", "abce", "abcdef", "abcde");

        Queue<String> expResult = null;
        Queue<String> temp = (Queue<String>)pref.wordsWithPrefix("prefix", 6);

        assertEquals(expResult, temp);
    }

    @Test
    public void wordsWithPrefixTestWitHIntOnExistedElelements() {

        PrefixMatches pref = new PrefixMatches();
        pref.load("");

        Queue<String> expResult = null;
        Queue<String> temp = (Queue<String>)pref.wordsWithPrefix("some", 4);

        assertEquals(expResult, temp);
    }

    @Test
    public void sizeTestOnEasyStrings() {

        String[] temp = new String[] {
                "some",
                "string",
                "writed",
                "here",
                "and",
                "something"};

        PrefixMatches pref = new PrefixMatches();
        pref.load(temp);

        int expRes = 6;
        int actRes = pref.size();

        assertEquals(expRes, actRes);
    }



    @Test
    public void sizeTestOnEmptyStrings() {

        String[] temp = new String[] {};

        PrefixMatches pref = new PrefixMatches();
        pref.load(temp);

        int expRes = 0;
        int actRes = pref.size();

        assertEquals(expRes, actRes);
    }

}