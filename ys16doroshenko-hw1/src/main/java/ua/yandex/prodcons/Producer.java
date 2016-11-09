package ua.yandex.prodcons;

import ua.yandex.prodcons.threads.Buffer;

/**
 * @author Dmitry Doroshenko.
 *         Yandex SHAD.
 */
public class Producer implements Runnable {
    private static final int SLEEP_TIME = 2000;

    private Buffer buffer;

    public Producer(Buffer buffer) throws Exception {
        if (buffer == null) {
            throw new Exception("Null buffer.");
        }
        else {
            this.buffer = buffer;
        }
    }

    @Override
    public void run() {
        Integer[] tmp = {663, 664, 662, 660, 662, 665, 666};
        for (int i = 0; i < tmp.length; i++) {
            buffer.add(tmp[i]);

            try {
                Thread.sleep(SLEEP_TIME);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
