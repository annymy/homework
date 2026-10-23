package decorator;

import helpers.SpyPayment;
import org.junit.jupiter.api.Test;
import pay.OrderPayment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommissionDecoratorTest {
    @Test
    void whenCreateCommissionDecoratorWithNull_ThenThrowNPE(){
        assertThrows(NullPointerException.class,
                ()-> new CommissionDecorator(null));
    }

    @Test
    void whenApplyCommission_thenPriceIncreasesBy50(){
        SpyPayment spyPayment = new SpyPayment();
        OrderPayment commission = new CommissionDecorator(spyPayment);
        commission.pay(100);

        assertEquals(150, spyPayment.lastSum);
    }

    @Test
    void whenCommissionWrapsDiscount_thenOrderOfOperationsIsCorrect() {
        SpyPayment spy = new SpyPayment();
        OrderPayment decorated = new CommissionDecorator(
                new DiscountDecorator(spy, 10)
        );
        decorated.pay(1000);
        assertEquals(945, spy.lastSum); // (1000 + 50) * 0.9 = 945
    }
}
