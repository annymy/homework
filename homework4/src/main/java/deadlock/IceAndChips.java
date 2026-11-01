package deadlock;

public class IceAndChips {
    private final Object iceCream = new Object();
    private final Object chips = new Object();

    public Thread anyaThread(){
        return new Thread(()-> {
            synchronized (iceCream){
                System.out.println("Anya ate an ice cream");
                sleep(1000);

                System.out.println("Anya wants chips");
                synchronized (chips){
                    System.out.println("Anya has had enough to eat");
                }
            }
        }, "Anya");
    }

    public Thread varyaThread(){
        return new Thread(()->{
            synchronized (chips){
                System.out.println("Varya ate a chips");
                sleep(1000);

                System.out.println("Varya wants ice cream");
                synchronized (iceCream){
                    System.out.println("Varya has had enough to eat");
                }
            }
        }, "Varya");
    }

    private void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
