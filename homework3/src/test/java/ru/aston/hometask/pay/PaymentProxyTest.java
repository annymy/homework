package ru.aston.hometask.pay;

import ru.aston.hometask.helpers.SpyPayment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentProxyTest {
    @Test
    void whenPayWithValidPrice_thenDelegateToRealPayment(){
        SpyPayment spy = new SpyPayment();
        PaymentProxy proxy = new PaymentProxy(spy);

        proxy.pay(500);

        assertEquals(1, spy.callCount);
        assertEquals(500, spy.lastSum);
    }

    @Test
    void whenPayWithZeroPrice_thenIllegalArgumentException(){
        SpyPayment spy = new SpyPayment();
        PaymentProxy proxy = new PaymentProxy(spy);

        assertThrows(IllegalArgumentException.class,
                ()->proxy.pay(0));
        assertEquals(0, spy.callCount);
    }

    @Test
    void whenPayWithNegativePrice_thenIllegalArgumentException(){
        SpyPayment spy = new SpyPayment();
        PaymentProxy proxy = new PaymentProxy(spy);

        assertThrows(IllegalArgumentException.class,
                ()->proxy.pay(-100));
    }

    @Test
    void whenCreateProxyWithNull_thenThrowNPE(){
        assertThrows(NullPointerException.class,
                ()-> new PaymentProxy(null));
    }

    @Test
    void whenWrapDiffStrategies_thenProxyWorksTheSame(){
        SpyPayment spyPayment1 = new SpyPayment();
        SpyPayment spyPayment2 = new SpyPayment();

        PaymentProxy proxy1 = new PaymentProxy(spyPayment1);
        PaymentProxy proxy2 = new PaymentProxy(spyPayment2);

        proxy1.pay(100);
        proxy2.pay(200);

        assertEquals(100, spyPayment1.lastSum);
        assertEquals(200, spyPayment2.lastSum);
    }

    @Test
    void whenPay_thenPriceIsPassedWithoutModifications(){
        SpyPayment spyPayment = new SpyPayment();
        new PaymentProxy(spyPayment).pay(500);
        assertEquals(500, spyPayment.lastSum);
    }
}
