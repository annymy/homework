package pay;

public class QRPayment implements OrderPayment{
    @Override
    public void pay(double price) {
        System.out.println("Оплата по QR-коду");
        System.out.println("Наведите камеру на QR-код");
        System.out.println("Сумма " + price + " будет списана с помощью вашего банковского приложения");
    }
}
