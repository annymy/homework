package liveLock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DinnerTest {
    @Test
    void whenHaveTwoThreads_thenReproduceLiveLock() throws InterruptedException {
        Dinner dinner = new Dinner(2);
        Thread anna = new Thread(() -> dinner.eating("Anna"));
        Thread varya = new Thread(() -> dinner.eating("Varya"));
        anna.start();
        varya.start();

        boolean livelock = false;
        try {
            long deadline = System.nanoTime() + 5_000_000_000L;
            while (System.nanoTime() < deadline) {
                Thread.sleep(20);
                if (!dinner.isHungry) break;
                if (dinner.getFailedAttempts() >= 4
                        && anna.isAlive() && varya.isAlive()) {
                    livelock = true;
                    break;
                }
                if (!anna.isAlive() || !varya.isAlive()) break;
            }
        } finally {
            anna.interrupt();
            varya.interrupt();
            anna.join(2000);
            varya.join(2000);
        }

        assertTrue(livelock, "livelock не воспроизведён: attempts="
                + dinner.getFailedAttempts()
                + ", annaAlive=" + anna.isAlive()
                + ", varyaAlive=" + varya.isAlive());
    }

    @Test
    void whenOneThread_tnenShouldStopWhenBarrierTimeout() throws InterruptedException {
        Dinner dinner = new Dinner(2);

        Thread anna = new Thread(() -> dinner.eating("Anna"));

        anna.start();

        try {
            anna.join(2500);
            assertFalse(anna.isAlive(), "Поток не завершился после таймаута"
            );

            assertEquals(0, dinner.getFailedAttempts());

        } finally {
            anna.interrupt();
            anna.join(1000);
        }
    }

}
