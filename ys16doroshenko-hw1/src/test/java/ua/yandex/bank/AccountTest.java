package ua.yandex.bank;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Dmitry Doroshenko.
 *         Yandex SHAD.
 */
public class AccountTest {

    @Test
    public void testWithdrawOnWorking() {
        int tmp = 100;
        Account account = new Account();
        boolean exp = true;

        try {
            account.deposit(tmp);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        boolean act = account.withdraw(tmp);
        assertEquals(exp, act);
    }

    @Test
    public void testWithdrawOnFail() {
        int tmp = 666;
        Account account = new Account();
        boolean exp = false;

        boolean act = account.withdraw(tmp);
        assertEquals(exp, act);
    }

    @Test
    public void testDepositeOnWorking() {
        int exp = 666;
        Account account = new Account();

        try {
            account.deposit(exp);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        int act = account.getAmount();
        assertEquals(exp, act);

    }
}
