package model;

import model.Order;
import model.Product;
import org.junit.jupiter.api.Test;
import pay.CashPayment;
import pay.QRPayment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderBuilderTest {
    @Test
    void whenCreateOrderWithValidData_thenOrderIsCreated(){
        Product product1 = new Product("Dress", 2000, true);
        Product product2 = new Product("Divan", 30000, true);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        QRPayment payment = new QRPayment();

        Order order = new Order.OrderBuilder(products, payment)
                .build();

        assertNotNull(order);
        assertEquals(products, order.getProducts());
        assertEquals(order.getOrderPayment(), payment);
    }

    @Test
    void whenCreateOrderWithNullListProducts_thenNullPointException(){
        assertThrows(NullPointerException.class,
                ()-> new Order.OrderBuilder(null, new QRPayment())
                        .build());
    }

    @Test
    void whenCreateOrderWithNullOrderPayment_thenNullPointException(){
        Product product1 = new Product("Dress", 2000, true);
        Product product2 = new Product("Divan", 30000, true);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        assertThrows(NullPointerException.class,
                ()-> new Order.OrderBuilder(products, null)
                        .build());
    }

    @Test
    void whenCreateOrderWithEmptyListProducts_thenIllegalStateException(){
        assertThrows(IllegalStateException.class,
                ()-> new Order.OrderBuilder(List.of(), new QRPayment())
                        .build());
    }

    @Test
    void whenSetPromoNull_thenNullPointException(){
        Product product1 = new Product("Dress", 2000, true);
        Product product2 = new Product("Divan", 30000, true);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        assertThrows(NullPointerException.class,
                ()->new Order.OrderBuilder(products, new QRPayment())
                        .setPromo(null)
                        .build());
    }

    @Test
    void whenSetAddressNull_thenNullPointException(){
        Product product1 = new Product("Dress", 2000, true);
        Product product2 = new Product("Divan", 30000, true);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        assertThrows(NullPointerException.class,
                ()->new Order.OrderBuilder(products, new QRPayment())
                        .setAddress(null)
                        .build());
    }

    @Test
    void whenSetPhoneNull_thenNullPointException(){
        Product product1 = new Product("Dress", 2000, true);
        Product product2 = new Product("Divan", 30000, true);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        assertThrows(NullPointerException.class,
                ()->new Order.OrderBuilder(products, new QRPayment())
                        .setPhone(null)
                        .build());
    }

    @Test
    void whenFluentChantSet_thenExecutesAndNotThrowExceptions(){
        Product product1 = new Product("Dress", 2000, true);
        Product product2 = new Product("Divan", 30000, true);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Order order = new Order.OrderBuilder(products, new CashPayment())
                .setPromo("aaaa")
                .setAddress("Moscow")
                .setPhone("88005553535")
                .build();

        assertNotNull(order);
    }

    @Test
    void whenOptionalFieldsNotSet_thenReturnNull(){
        Product product1 = new Product("Dress", 2000, true);
        Product product2 = new Product("Divan", 30000, true);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Order order = new Order.OrderBuilder(products, new CashPayment())
                .build();

        assertNull(order.getAddress());
        assertNull(order.getPromo());
        assertNull(order.getPhone());
    }


}
