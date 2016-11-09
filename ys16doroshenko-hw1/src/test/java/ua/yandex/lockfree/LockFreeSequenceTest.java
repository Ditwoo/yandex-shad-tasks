package ua.yandex.lockfree;

import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author Dmitry Doroshenko.
 *         Yandex SHAD.
 */
public class LockFreeSequenceTest {

    private static class Sequencer implements Runnable {
        BigInteger ans;

        @Override
        public void run() {
            ans = LockFreeSequence.next();
        }

        public BigInteger getAns() {
            return ans;
        }
    }

    @Test
    public void testNext() {
        int countOfThreads = 50;
        Thread[] threads = new Thread[countOfThreads];

        Sequencer[] sequencer = new Sequencer[countOfThreads];

        for (int i = 0; i < countOfThreads; i++) {
            sequencer[i] = new Sequencer();
            threads[i] = new Thread(sequencer[i]);
        }

        ArrayList<BigInteger> act = new ArrayList<>();

        for (int i = 0; i < countOfThreads; i++) {
            threads[i].run();
        }

        for (int i = 0; i < countOfThreads; i++) {
            act.add(sequencer[i].getAns());
        }

        int exp = countOfThreads;
        assertEquals(exp, act.size());
    }
}
