package decorator;

import pay.OrderPayment;

public class DiscountDecorator extends PaymentDecorator{
    private final double discountPercent;

    public DiscountDecorator(OrderPayment wrappedPayment, double discountPercent) {
        super(wrappedPayment);
        this.discountPercent = discountPercent;
    }

    @Override
    public void pay(double price){
        if(discountPercent<0 || discountPercent>=100){
            throw new IllegalArgumentException("Скидка должна быть от 0 до 100 процентов");
        } else {
            double discountPrice = price - price / 100 * discountPercent;
            System.out.println("[DECORATOR] Применена скидка " + discountPercent + "%: " + discountPrice);
            wrappedPayment.pay(discountPrice);
        }
    }
}
