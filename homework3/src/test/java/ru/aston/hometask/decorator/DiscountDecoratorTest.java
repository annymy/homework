package ru.aston.hometask.decorator;

import ru.aston.hometask.helpers.SpyPayment;
import org.junit.jupiter.api.Test;
import ru.aston.hometask.pay.OrderPayment;
import ru.aston.hometask.decorator.DiscountDecorator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiscountDecoratorTest {
    @Test
    void whenApplyTenPercentDiscount_thenReturnsNineHundred(){
        SpyPayment spyPayment = new SpyPayment();
        OrderPayment decorator = new DiscountDecorator(spyPayment, 10);
        decorator.pay(1000);

        assertEquals(900,spyPayment.lastSum);
        assertEquals(1, spyPayment.callCount);
    }

    @Test
    void whenApplyZeroPercentDiscount_thenReturnsTheSame(){
        SpyPayment spyPayment = new SpyPayment();
        OrderPayment decorator = new DiscountDecorator(spyPayment, 0);
        decorator.pay(1000);

        assertEquals(1000,spyPayment.lastSum);
    }

    @Test
    void whenApplyNegativePercentDiscount_thenReturnsIllegalArgumentException(){
        SpyPayment spyPayment = new SpyPayment();
        OrderPayment decorator = new DiscountDecorator(spyPayment, -10);

        assertThrows(IllegalArgumentException.class,
                () -> decorator.pay(100));
    }

    @Test
    void whenApplyMoreThan100PercentDiscount_thenReturnsIllegalArgumentException(){
        SpyPayment spyPayment = new SpyPayment();
        OrderPayment decorator = new DiscountDecorator(spyPayment, 101);

        assertThrows(IllegalArgumentException.class,
                () -> decorator.pay(100));
    }

    @Test
    void whenCreateDiscountDecoratorWithNull_ThenThrowNPE(){
        assertThrows(NullPointerException.class,
                ()-> new DiscountDecorator(null, 100));
    }
}
