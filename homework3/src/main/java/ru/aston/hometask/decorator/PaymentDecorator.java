package ru.aston.hometask.decorator;

import ru.aston.hometask.pay.OrderPayment;

import java.util.Objects;

public abstract class PaymentDecorator implements OrderPayment{
    protected final OrderPayment wrappedPayment;

    public PaymentDecorator(OrderPayment wrappedPayment) {
        this.wrappedPayment = Objects.requireNonNull(wrappedPayment);
    }
}
