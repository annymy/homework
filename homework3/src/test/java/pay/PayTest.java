package pay;

import org.junit.jupiter.api.Test;
import pay.CardPayment;
import pay.CashPayment;
import pay.QRPayment;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//тк у меня нет никакой логики при оплате, то я просто проверяю, что методы запускаются
public class PayTest {
    @Test
    void whenCashPaymentCallMethodPay_thenExecutesAndNotThrowExceptions(){
        CashPayment payment = new CashPayment();
        assertDoesNotThrow(() -> payment.pay(500));
    }

    @Test
    void whenCardPaymentCallMethodPay_thenExecutesAndNotThrowExceptions(){
        CardPayment payment = new CardPayment();
        assertDoesNotThrow(() -> payment.pay(500));
    }

    @Test
    void whenQRPaymentCallMethodPay_thenExecutesAndNotThrowExceptions(){
        QRPayment payment = new QRPayment();
        assertDoesNotThrow(() -> payment.pay(500));
    }
}
