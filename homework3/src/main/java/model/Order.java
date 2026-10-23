package model;

import pay.OrderPayment;

import java.util.List;
import java.util.Objects;

public class Order {
    private final List<Product> products;
    private final OrderPayment orderPayment;
    private final String promo;
    private final String address;
    private final String phone;
    private static final String PROMOCODE = "lalala";

    private Order(OrderBuilder orderBuilder){
        products = List.copyOf(orderBuilder.products);
        orderPayment = orderBuilder.orderPayment;
        promo = orderBuilder.promo;
        address = orderBuilder.address;
        phone = orderBuilder.phone;
    }

    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public String getPromo() {
        return promo;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public OrderPayment getOrderPayment() {
        return orderPayment;
    }

    public void payOrder(){
        double total = 0;
        for (Product list: products) {
            total+=list.getPrice();
        }

        if (promo!=null && !promo.isBlank()&& promo.equals(PROMOCODE)) {
            System.out.println("Применен промокод - 10%");
            total = total - 0.1 * total;
        }
        orderPayment.pay(total);
    }

    public static class OrderBuilder {
        private List<Product> products;
        private OrderPayment orderPayment;
        private String promo; //optional
        private String address; //optional
        private String phone; //optional


        public OrderBuilder(List<Product> products, OrderPayment orderPayment) {
            this.products = Objects.requireNonNull(products, "Товары не выбраны");
            this.orderPayment = Objects.requireNonNull(orderPayment, "Не выбран способ оплаты");
        }

        public OrderBuilder setPromo(String promo) {
            this.promo = Objects.requireNonNull(promo, "Поле не может быть null");
            return this;
        }

        public OrderBuilder setAddress(String address) {
            this.address = Objects.requireNonNull(address, "Поле не может быть null");
            return this;
        }

        public OrderBuilder setPhone(String phone) {
            this.phone = Objects.requireNonNull(phone, "Поле не может быть null");
            return this;
        }

        public Order build(){
            if(products.isEmpty()){
                throw new IllegalStateException("Список товаров пуст");
            }
            return new Order(this);
        }
    }
}
