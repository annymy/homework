package ru.aston.hometask.pay;

public class CashPayment implements OrderPayment{
    @Override
    public void pay(double price) {
        System.out.println("Оплата наличными");
        System.out.println("Сумма к оплате " + price);
    }
}
