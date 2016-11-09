package ua.yandex.prodcons.utilconcurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dmitry Doroshenko.
 *         Yandex SHAD.
 */
public class Buffer {

    private Integer[] array;

    private final int size;
    private int capacity;

    private int addPosition;
    private int getPosition;

    private final Lock lock = new ReentrantLock();
    private final Condition isFull = lock.newCondition();
    private final Condition isEmpty = lock.newCondition();

    public Buffer(int bufferSize) {
        this.array = new Integer[bufferSize];
        this.size = bufferSize;

        this.addPosition = 0;
        this.getPosition = 0;
        this.capacity = 0;
    }

    private int modSize(int value) {
        return (value + 1) % size;
    }

    public void add(Integer value) {
        lock.lock();
        try {
            while (capacity == size) {
                try {
                    isFull.await();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            capacity++;
            array[addPosition] = value;

            //modSize
            addPosition = modSize(addPosition);
            isEmpty.signal();
        }
        finally {
            lock.unlock();
        }
    }

    public Integer get() {
        lock.lock();
        try {
            while (capacity == 0) {
                try {
                    isEmpty.await();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Integer ans = array[getPosition];
            getPosition = modSize(getPosition);

            capacity--;
            isFull.signal();
            return ans;
        }
        finally {
            lock.unlock();
        }
    }
}
