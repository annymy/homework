package liveLock;

public class LiveLock {
    public static void main(String[] args) {
        int parties = 2;
        Dinner dinner = new Dinner(parties);

        Thread thread1 = new Thread(()-> dinner.eating("Anna"));

        Thread thread2 = new Thread(()-> dinner.eating("Varya"));

        thread1.start();
        thread2.start();
    }
}
