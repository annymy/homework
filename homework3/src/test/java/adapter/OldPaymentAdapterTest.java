package adapter;

import helpers.SpyOldPayment;
import org.junit.jupiter.api.Test;
import pay.OrderPayment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OldPaymentAdapterTest {
    @Test
    void whenPay_thenConvertPriceAndPassEmail(){
        SpyOldPayment spyOldPayment = new SpyOldPayment();
        OrderPayment adapter = new OldPaymentAdapter(spyOldPayment, "user@mail.ru");
        adapter.pay(1000);

        assertEquals(1000, spyOldPayment.lastPrice1 + spyOldPayment.lastPrice2);
        assertEquals("user@mail.ru", spyOldPayment.lastEmail);
    }

    @Test
    void whenOldPaymentNull_thenThrowNPE(){
        assertThrows(NullPointerException.class,
                ()-> new OldPaymentAdapter(null, "user@mail.ru"));
    }

    @Test
    void whenOldEmailNull_thenThrowNPE(){
        assertThrows(NullPointerException.class,
                ()-> new OldPaymentAdapter(new SpyOldPayment(), null));
    }

}
