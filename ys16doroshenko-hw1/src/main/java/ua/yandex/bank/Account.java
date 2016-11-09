package ua.yandex.bank;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dmitry Doroshenko.
 *         Yandex SHAD.
 */
public class Account {
    public final AtomicInteger amount;

    public Account() {
        this.amount = new AtomicInteger(0);
    }

    public boolean withdraw(int count) {
        while (true) {
            int tmp = amount.get();

            if (count > tmp) {
                return false;
            }

            if (amount.compareAndSet(tmp, tmp - count)) {
                return true;
            }
        }
    }

    public boolean deposit(int count) throws Exception {
        if (count < 0) {
            throw new Exception("You try to deposite less than 0.");
        }

        while (true) {
            int tmp = amount.get();
            if (amount.compareAndSet(tmp, tmp + count)) {
                return true;
            }
        }
    }

    public int getAmount() {
        return amount.get();
    }
}
