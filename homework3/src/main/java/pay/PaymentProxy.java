package pay;

import java.util.Objects;

//реализация паттерна прокси
public class PaymentProxy implements OrderPayment{
    private final OrderPayment orderPayment;

    public PaymentProxy(OrderPayment orderPayment) {
        this.orderPayment = Objects.requireNonNull(orderPayment, "Не выбран способ оплаты");
    }

    @Override
    public void pay(double price) {
        if(price<=0){
            throw new IllegalArgumentException("Сумма к оплате должна быть больше нуля");
        }
        System.out.println("[PROXY] Заказ на сумму " + price);
        orderPayment.pay(price);
        System.out.println("[PROXY] Оплата прошла успешно");
    }
}
