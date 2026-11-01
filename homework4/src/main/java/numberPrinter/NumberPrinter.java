package numberPrinter;

public class NumberPrinter {
    private boolean inFirst = true;

    public synchronized void printOne(){
        while (true){
            while (!inFirst){
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.print("1");
            inFirst = false;
            notifyAll();
        }
    }

    public synchronized void printTwo(){
        while (true){
            while (inFirst){
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.print("2");
            inFirst = true;
            notifyAll();
        }
    }

    public static void main(String[] args){
        NumberPrinter numberPrinter = new NumberPrinter();

        Thread t1 = new Thread(numberPrinter::printOne);
        Thread t2 = new Thread(numberPrinter::printTwo);

        t1.start();
        t2.start();
    }
}
