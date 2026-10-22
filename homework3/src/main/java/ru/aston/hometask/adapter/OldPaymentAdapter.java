package ru.aston.hometask.adapter;

import ru.aston.hometask.pay.OrderPayment;

import java.util.Objects;

public class OldPaymentAdapter implements OrderPayment {
    private final OldPayment oldPayment;
    private final String email;

    public OldPaymentAdapter(OldPayment oldPayment, String email) {
        this.oldPayment = Objects.requireNonNull(oldPayment, "не выбран способ оплаты");
        this.email = Objects.requireNonNull(email, "Не введен адрес электронной почты");
    }


    @Override
    public void pay(double price) {
        //Разделим сумму на две части
        double bonusPart = price * 0.1;
        double mainPart = price - bonusPart;
        oldPayment.makeTransaction(mainPart, bonusPart, email);
    }
}
