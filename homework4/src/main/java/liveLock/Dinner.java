package liveLock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Dinner {
    private final Lock broccoli = new ReentrantLock();
    private final Lock potato   = new ReentrantLock();
    volatile boolean isHungry = true;
    private final CyclicBarrier barrier;
    private final AtomicInteger count = new AtomicInteger();
    private final AtomicInteger failedAttempts = new AtomicInteger();//переменная для тестов

    public Dinner(int parties) {
        this.barrier = new CyclicBarrier(parties);
    }

    public void eating(String name){
        int number = count.getAndIncrement();
        eating(name, number % 2 == 0);
    }

    private void eating(String name, boolean reverse){
        while(isHungry) {
            boolean attempted = reverse
                    ? tryEat(name, broccoli, potato)
                    : tryEat(name, potato, broccoli);
            if (!attempted) return;
            reverse = !reverse;
        }
    }

    private boolean awaitBarrier() {
        try {
            barrier.await(1, TimeUnit.SECONDS);
            return  true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } catch (BrokenBarrierException | TimeoutException e){
            return false;
        }
    }

    private boolean tryEat(String name, Lock lock1, Lock lock2) {
        lock1.lock();
        try {
            if(!awaitBarrier()) return false;
            boolean gotSecond = lock2.tryLock();
            if (!awaitBarrier()) {
                if (gotSecond) lock2.unlock();
                return false;
            }
            if (!gotSecond) {
                System.out.println(name + " gave away food");
                failedAttempts.incrementAndGet();
                return true;
            }
            try {
                System.out.println(name + " eat broccoli and potato");
                isHungry = false;
            } finally {
                lock2.unlock();
            }
        } finally {
            lock1.unlock();
        }
        return false;
    }

    int getFailedAttempts() {
        return failedAttempts.get();
    }
}

