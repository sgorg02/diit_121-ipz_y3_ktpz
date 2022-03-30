import java.rmi.*;

public interface BillingService extends Remote  {
    void addNewCard(Card card) throws RemoteException; // Регистрация новой карты
    void addMoney(Card card, double money) throws RemoteException; // Добавление денежных средств на карту
    void subMoney(Card card, double money) throws RemoteException; // Снятие денежных средств с карты
    double getCardBalance(Card card) throws RemoteException; // Получение баланса карты
}