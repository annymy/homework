package ru.aston.hometask;


import ru.aston.hometask.decorator.CommissionDecorator;
import ru.aston.hometask.decorator.DiscountDecorator;
import ru.aston.hometask.model.Order;
import ru.aston.hometask.model.Product;

import ru.aston.hometask.check.*;
import ru.aston.hometask.pay.CardPayment;
import ru.aston.hometask.pay.OrderPayment;
import ru.aston.hometask.pay.PaymentProxy;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product("Dress", 2000, true);
        Product product2 = new Product("Divan", 30000, true);

        CheckHandler check = new CheckChainBuilder()
                .addHandler(new StockCheckHandler())
                .addHandler(new PriceCheckHandler())
                .addHandler(new ProductCheckHandler())
                .build();

        List<Product> products = List.of(product1, product2);



        if(check.handle(products)){
            OrderPayment orderPayment = new CardPayment();
            OrderPayment withDiscount = new DiscountDecorator(orderPayment, 10);
            OrderPayment withCommissionAndDiscount = new CommissionDecorator(withDiscount);

            OrderPayment finalPayment = new PaymentProxy(withCommissionAndDiscount);


            Order order = new Order.OrderBuilder(products, finalPayment)
                    .setPhone("83737")
                    .build();
            System.out.println("Заказ создан");
            order.payOrder();
        } else {
            System.out.println("Заказ отменен");
        }

//        OldPayment oldPayment = new OldPayment();
//        // Email — часть реквизитов для старого API, передаётся при создании адаптера
//        OrderPayment orderPayment = new OldPaymentAdapter(oldPayment, "lalalala@gmail.com");
//        Order order = new Order.OrderBuilder(products, orderPayment).build();
//        order.payOrder();


    }
}
