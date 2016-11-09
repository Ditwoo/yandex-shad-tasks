package ua.yandex.prodcons;

import ua.yandex.prodcons.threads.Buffer;

import java.util.Random;

/**
 * @author Dmitry Doroshenko.
 *         Yandex SHAD.
 */
public class Consumer implements Runnable {
    private static final int SLEEP_TIME = 2000;
    private static final Random random = new Random(20);

    private Buffer buffer;

    public Consumer(Buffer buffer) throws Exception {
        if (buffer == null) {
            throw new Exception("Null buffer.");
        }
        else {
            this.buffer = buffer;
        }

    }

    @Override
    public void run() {
        for (Integer i = buffer.get(); i <= 666; i = buffer.get()) {
            System.out.print(buffer.get());

            try {
                Thread.sleep(random.nextInt(SLEEP_TIME));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
