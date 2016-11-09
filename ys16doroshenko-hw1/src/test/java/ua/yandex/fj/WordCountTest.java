package ua.yandex.fj;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Dmitry Doroshenko.
 *         Yandex SHAD.
 */
public class WordCountTest {

    @Test
    public void testComputeSimple() {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        String[] tmp = {"one", "two", "three", "two", "three", "one"};
        Map<String, Integer> exp = new HashMap<>();
        exp.put("one", 2);
        exp.put("two", 2);
        exp.put("three", 2);

        Map<String, Integer> act = forkJoinPool.invoke(new WordCount.WordCountTask(0, 5, tmp));

        assertTrue(exp.equals(act));
    }

    @Test
    public void testComputeSimpleWithFail() {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        String[] tmp = {"one", "two", "three", "two", "three", "one", "four"};
        Map<String, Integer> exp = new HashMap<>();
        exp.put("one", 1);
        exp.put("two", 2);
        exp.put("three", 3);
        exp.put("four", 5);

        Map<String, Integer> act = forkJoinPool.invoke(new WordCount.WordCountTask(0, 5, tmp));

        assertFalse(exp.equals(act));
    }
}
