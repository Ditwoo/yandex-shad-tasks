package ua.yandex.bank;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Dmitry Doroshenko.
 *         Yandex SHAD.
 */
public class Bank {
    private final CopyOnWriteArrayList<Account> accounts;

    public Bank() {
        accounts = new CopyOnWriteArrayList<>();
    }

    public Account addAccount() {
        Account tmp = new Account();
        synchronized (this) {
            accounts.add(tmp);
        }
        return tmp;
    }

    public int countOfAccounts() {
        return accounts.size();
    }

    public int amountOfAccount(int index) throws Exception {
        if (index >= accounts.size()) {
            throw new Exception("Wrong index.");
        }
        return accounts.get(index).getAmount();
    }

    public Account getAccount(int index) throws Exception {
        if (index >= accounts.size()) {
            throw new Exception("Wrong index.");
        }
        return accounts.get(index);
    }

    public void withdraw(int index, int count) throws Exception {
        if (index >= accounts.size()) {
            throw new Exception("Wrong index.");
        }
        accounts.get(index).withdraw(count);
    }

    public void deposite(int index, int count) throws Exception {
        if (index >= accounts.size()) {
            throw new Exception("Wrong index.");
        }
        accounts.get(index).deposit(count);
    }

    public void transfer(Account from, Account to, int amount) throws Exception {
        from.withdraw(amount);
        to.deposit(amount);
    }
}
