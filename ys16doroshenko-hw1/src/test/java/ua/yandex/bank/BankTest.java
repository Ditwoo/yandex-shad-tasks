package ua.yandex.bank;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Dmitry Doroshenko.
 *         Yandex SHAD.
 */
public class BankTest {

    @Test
    public void testTransfer() throws Exception {
        Bank bank = new Bank();
        for (int i = 0; i < 10; i++) {
            bank.addAccount();
            bank.deposite(i, i + 100);
        }

        int expFirst = 0;
        int expSecond = 209;
        try {
            bank.transfer(bank.getAccount(0), bank.getAccount(9), 100);
        }
        catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

        int actFirst = bank.amountOfAccount(0);
        int actSecond = bank.amountOfAccount(9);

        assertEquals(expFirst, actFirst);
        assertEquals(expSecond, actSecond);
    }

}
