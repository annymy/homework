package deadlock;

public class DemoDeadLock {
    public static void main(String[] args){
        IceAndChips iceAndChips = new IceAndChips();
        Thread anya = iceAndChips.anyaThread();
        Thread varya = iceAndChips.varyaThread();

        anya.start();
        varya.start();
    }
}
