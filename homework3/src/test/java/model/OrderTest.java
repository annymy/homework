package model;

import model.Order;
import model.Product;
import helpers.SpyPayment;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    @Test
    void whenPayOrder_thenPaymentReceivesTotalSum(){
        Product dress = new Product("Dress", 2000, true);
        Product divan = new Product("Divan", 30000, true);
        List<Product> products = List.of(dress, divan);

        SpyPayment spyPayment = new SpyPayment();

        Order order = new Order.OrderBuilder(products, spyPayment)
                .build();

        order.payOrder();

        assertEquals(32000, spyPayment.lastSum);
        assertEquals(1, spyPayment.callCount);
    }

    @Test
    void whenGetProducts_thenReturnedListImmutable(){
        Product dress = new Product("Dress", 2000, true);
        SpyPayment spyPayment = new SpyPayment();
        Order order = new Order.OrderBuilder(List.of(dress), spyPayment)
                .build();

        List<Product> newList = order.getProducts();

        assertThrows(UnsupportedOperationException.class,
                () -> newList.add(new Product("Cake", 100, true)));
        assertThrows(UnsupportedOperationException.class,
                newList::removeFirst);
        assertThrows(UnsupportedOperationException.class,
                newList::clear);
    }

    @Test
    void whenChangeOriginalList_thenNotChangeOrderList(){
        Product dress = new Product("Dress", 2000, true);
        SpyPayment spyPayment = new SpyPayment();
        List<Product> products = new ArrayList<>();
        products.add(dress);
        Order order = new Order.OrderBuilder(products, spyPayment)
                .build();
        int sizeBefore = products.size();
        Product cake = new Product("Cake", 100, true);
        products.add(cake);

        assertEquals(sizeBefore, order.getProducts().size());
    }

    @Test
    void whenPromoMatches_thenSumIsReducedBy10Percent() {
        Product dress = new Product("Dress", 2000, true);
        List<Product> products = new ArrayList<>();
        products.add(dress);
        SpyPayment spyPayment = new SpyPayment();
        Order order = new Order.OrderBuilder(products, spyPayment)
                .setPromo("lalala")
                .build();
        order.payOrder();
        assertEquals(1800, spyPayment.lastSum); // 2000 - 10%
    }

    @Test
    void whenPromoDoesNotMatch_thenSumIsUnchanged() {
        Product dress = new Product("Dress", 2000, true);
        List<Product> products = new ArrayList<>();
        products.add(dress);
        SpyPayment spyPayment = new SpyPayment();
        Order order = new Order.OrderBuilder(products, spyPayment)
                .build();
        order.payOrder();
        assertEquals(2000, spyPayment.lastSum); // 32000 - 10%
    }
}
