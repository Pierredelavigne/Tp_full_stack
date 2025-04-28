package org.example.exercices.exercice3.test;

import org.example.exercices.exercice3.model.BankAccount;
import org.example.exercices.exercice3.service.BankService;
import org.example.exercices.exercice3.exception.NotEnoughMoneyException;

import static org.junit.jupiter.api.Assertions.*; // Les assertions JUnit

import org.junit.jupiter.api.*; // Les annotations JUnit
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.time.Duration;

class BankServiceTest {

    private BankService service;
    private BankAccount accountFrom;
    private BankAccount accountTo;

    @BeforeEach
    void setup() {
        service = new BankService();
        accountFrom = new BankAccount("from", 500);
        accountTo = new BankAccount("to", 200);
    }

    @Test
    void successfulTransfer() {
        boolean result = service.transfer(accountFrom, accountTo, 100.0);
        assertTrue(result);
        assertEquals(400.0, accountFrom.getBalance());
        assertEquals(300.0, accountTo.getBalance());
    }

    @Test
    void transferFailsWhenFromAccountIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.transfer(null, accountTo, 100.0);
        });
    }

    @Test
    void transferFailsWhenToAccountIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.transfer(accountFrom, null, 100.0);
        });
    }

    @Test
    void transferFailsWhenAmountIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.transfer(accountFrom, accountTo, null);
        });
    }

    @Test
    void transferFailsWhenAmountIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.transfer(accountFrom, accountTo, -50.0);
        });
    }

    @Test
    void transferFailsWhenNotEnoughMoney() {
        BankAccount poorAccount = new BankAccount("poor", 10);
        assertThrows(NotEnoughMoneyException.class, () -> {
            service.transfer(poorAccount, accountTo, 100.0);
        });
    }

    @Test
    void successfulTransferWithinOneSecond() {
        assertTimeout(Duration.ofSeconds(1), () -> {
            service.transfer(accountFrom, accountTo, 100.0);
        });
    }

    @Test
    void multipleAssertionsWithAssertAll() {
        service.transfer(accountFrom, accountTo, 50.0);

        assertAll("Check balances after transfer",
            () -> assertEquals(450.0, accountFrom.getBalance()),
            () -> assertEquals(250.0, accountTo.getBalance())
        );
    }

    @Nested
    class NestedTransactionTests {
        @Test
        void transferFailsWhenAmountZero() {
            assertThrows(IllegalArgumentException.class, () -> {
                service.transfer(accountFrom, accountTo, 0.0);
            });
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {100, 200, 50, -10})
    void parameterizedTestOnAmounts(Double amount) {
        if (amount <= 0) {
            assertThrows(IllegalArgumentException.class, () -> {
                service.transfer(accountFrom, accountTo, amount);
            });
        } else {
            assertTrue(service.transfer(accountFrom, accountTo, amount));
        }
    }
}
