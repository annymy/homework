package ru.aston.hometask.helpers;

import ru.aston.hometask.pay.OrderPayment;

public class SpyPayment implements OrderPayment {
    public double lastSum;
    public int callCount = 0;

    @Override
    public void pay(double price) {
        this.lastSum = price;
        this.callCount++;
    }
}
