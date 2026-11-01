package deadlock;

import org.junit.jupiter.api.*;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoDeadLockTest {
    @Test
    @Timeout(10)
    void whenThreadsStart_thenTheyDeadlockedAndWaitMonitorReleased() throws InterruptedException {
        IceAndChips iceAndChips = new IceAndChips();

        Thread anya = iceAndChips.anyaThread();
        Thread varya = iceAndChips.varyaThread();

        anya.setDaemon(true);
        varya.setDaemon(true);

        anya.start();
        varya.start();

        long deadline = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < deadline) {
            if (anya.getState() == Thread.State.BLOCKED
                    && varya.getState() == Thread.State.BLOCKED) break;
            Thread.sleep(50);
        }

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadLockedThreads = threadMXBean.findDeadlockedThreads();

        assertNotNull(deadLockedThreads, "Threads must be deadlocked!");
        assertTrue(deadLockedThreads.length>=2, "Two threads Deadlocked");

        ThreadInfo[] infos = threadMXBean.getThreadInfo(deadLockedThreads);
        boolean anyaFound = false, varyaFound = false;

        for (ThreadInfo info:infos) {
            if("Anya".equals(info.getThreadName())) anyaFound = true;
            if("Varya".equals(info.getThreadName())) varyaFound = true;
        }

        assertTrue(anyaFound);
        assertTrue(varyaFound);
    }
}
