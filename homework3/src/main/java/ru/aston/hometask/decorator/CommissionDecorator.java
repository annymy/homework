package ru.aston.hometask.decorator;

import ru.aston.hometask.pay.OrderPayment;

public class CommissionDecorator extends PaymentDecorator{
    private final int commission = 50;

    public CommissionDecorator(OrderPayment wrappedPayment) {
        super(wrappedPayment);
    }

    @Override
    public void pay(double price) {
        double newPrice = price+commission;
        System.out.println("[DECORATOR] Комиссия за платеж " + newPrice);
        wrappedPayment.pay(newPrice);

    }
}
