package ua.yandex.prodcons.threads;

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

    public Buffer(int bufferSize) {
        this.array = new Integer[bufferSize];
        this.size = bufferSize;

        this.addPosition = 0;
        this.getPosition = 0;
        this.capacity = 0;
    }

    public int getSize() {
        return this.size;
    }

    private int modSize(int value) {
        return (value + 1) % size;
    }

    public synchronized void add(Integer value) {
        while (capacity == size) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        capacity++;
        array[addPosition] = value;

        //modSize
        addPosition = modSize(addPosition);
        notifyAll();
    }

    public synchronized Integer get() {
        while (capacity == 0) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Integer ans = array[getPosition];
        getPosition = modSize(getPosition);

        capacity--;
        notifyAll();

        return ans;
    }
}
