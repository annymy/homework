package pay;

//реализация паттерна стратегия
//в зависимости от выбранного типа платежа классы выполняют разные действия
public interface OrderPayment {
    void pay(double price);
}
