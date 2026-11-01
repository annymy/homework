package numberPrinter;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

public class NumberPrinterTest {
    private PrintStream originalOut;
    private List<Throwable> uncaught;
    private ByteArrayOutputStream buffer;

    @BeforeEach
    void setUp(){
        originalOut = System.out;
        buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer, true));
        uncaught = new CopyOnWriteArrayList<>();
    }

    @AfterEach
    void tearDown(){
        System.setOut(originalOut);
    }

    private String runAndCollect(List<Thread> threads, int minChars) throws InterruptedException {
        try {
            threads.forEach(t-> t.setUncaughtExceptionHandler(
                    (th, e)->uncaught.add(e)));
            threads.forEach(Thread::start);

            await().atMost(2000, TimeUnit.MILLISECONDS)
                    .until(()->buffer.size()>=minChars);
            return buffer.toString();
        } finally {
            threads.forEach(Thread::interrupt);
            for (Thread t:threads) t.join(1000);
        }
    }

    @Test
    @Timeout(value = 5, unit = SECONDS)
    void whenCreateThreads_thenFirstSymbolOne() throws InterruptedException {
        NumberPrinter p = new NumberPrinter();
        String output = runAndCollect(
                List.of(new Thread(p::printOne), new Thread(p::printTwo)), 20);
        assertFalse(output.isEmpty());
        assertEquals('1', output.charAt(0));
    }

    @Test
    @Timeout(value = 5, unit = SECONDS)
    void whenCreateThreads_thenOneAndTwoAlternate() throws InterruptedException {
        NumberPrinter p = new NumberPrinter();
        String output = runAndCollect(
                List.of(new Thread(p::printOne), new Thread(p::printTwo)), 20);
        assertTrue(output.length()>=20);
        for (int i = 0; i < output.length(); i++) {
            char expected = (i%2==0)? '1':'2';
            assertEquals(expected, output.charAt(i));
        }
    }

    @Test
    @Timeout(value = 5, unit = SECONDS)
    void whenCreateThreads_thenNoTwoOnesInRow() throws InterruptedException {
        NumberPrinter p = new NumberPrinter();
        String output = runAndCollect(
                List.of(new Thread(p::printOne), new Thread(p::printTwo)), 20);
        assertFalse(output.contains("11"));
    }

    @Test
    @Timeout(value = 5, unit = SECONDS)
    void whenCreateThreads_thenNoTwoTwosInRow() throws InterruptedException {
        NumberPrinter p = new NumberPrinter();
        String output = runAndCollect(
                List.of(new Thread(p::printOne), new Thread(p::printTwo)), 20);
        assertFalse(output.contains("22"));
    }

    @Test
    @Timeout(value = 5, unit = SECONDS)
    void whenCreateThreads_thenCountsAreBalanced() throws InterruptedException {
        NumberPrinter p = new NumberPrinter();
        String output = runAndCollect(
                List.of(new Thread(p::printOne), new Thread(p::printTwo)), 20);
        long ones = output.chars()
                .filter(c->c == '1')
                .count();
        long twos = output.chars()
                .filter(c->c == '2')
                .count();
        assertTrue(Math.abs(ones-twos)<=1);
    }

    @Test
    @Timeout(value = 5, unit = SECONDS)
    void whenCreateThreads_thenOutputContainsOnlyOneAndTwo() throws InterruptedException {
        NumberPrinter p = new NumberPrinter();
        String output = runAndCollect(
                List.of(new Thread(p::printOne), new Thread(p::printTwo)), 20);
        for (char c:output.toCharArray()) {
            assertTrue(c=='1' ||c=='2');
        }
    }

    @Test
    @Timeout(value = 5, unit = SECONDS)
    void whenCreateThreads_thenNoUncaughtExceptions() throws InterruptedException {
        NumberPrinter p = new NumberPrinter();
        runAndCollect(List.of(new Thread(p::printOne), new Thread(p::printTwo)), 20);
        assertTrue(uncaught.isEmpty(), "Необработанные исключения: " + uncaught);
    }

    @Test
    @Timeout(value = 5, unit = SECONDS)
    void whenInterrupted_thenThreadsFinish() throws InterruptedException {
        NumberPrinter p = new NumberPrinter();
        Thread t1 = new Thread(p::printOne);
        Thread t2 = new Thread(p::printTwo);
        runAndCollect(List.of(t1, t2), 4);
        assertFalse(t1.isAlive());
        assertFalse(t2.isAlive());
    }
}
