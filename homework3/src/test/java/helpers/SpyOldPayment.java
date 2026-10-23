package helpers;

import adapter.OldPayment;

public class SpyOldPayment extends OldPayment {
    public double lastPrice1;
    public double lastPrice2;
    public String lastEmail;

    @Override
    public void makeTransaction(double price1, double price2, String email) {
        this.lastPrice1 = price1;
        this.lastPrice2 = price2;
        this.lastEmail = email;
    }
}
