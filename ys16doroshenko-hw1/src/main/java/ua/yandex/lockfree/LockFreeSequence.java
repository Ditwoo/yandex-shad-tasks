package ua.yandex.lockfree;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Dmitry Doroshenko.
 *         Yandex SHAD.
 */
public class LockFreeSequence {

    private static AtomicReference<BigInteger> number = new AtomicReference<>(
            BigInteger.ONE);

    public static BigInteger next() {
        boolean ready = false;
        BigInteger answer = null;

        while (!ready) {
            answer = number.get();
            ready = number.compareAndSet(answer,
                    answer.multiply(new BigInteger("2")));
        }

        return answer;
    }
}
