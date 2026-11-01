package adapter;

// устаревший сервис (менять его нельзя, это чужая библиотека)

public class OldPayment {
    public void makeTransaction(double mainAmount, double bonusAmount, String email) {
        double total = mainAmount+bonusAmount;
        System.out.println("К оплате " + total);
        System.out.println("Квитанция будет выслана на " + email);
    }
}
