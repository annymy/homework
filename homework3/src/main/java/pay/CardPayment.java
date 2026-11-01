package pay;

public class CardPayment implements OrderPayment{
    @Override
    public void pay(double price) {
        System.out.println("Оплата картой");
        System.out.println("Сумма к оплате " + price);
        System.out.println("Приложите карту к терминалу");
    }
}
