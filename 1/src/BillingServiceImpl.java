import java.io.Serial;
import java.util.List;
import java.util.ArrayList;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class BillingServiceImpl extends UnicastRemoteObject implements BillingService {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<Card> cards;
    // инициализация сервера
    public BillingServiceImpl() throws RemoteException {
        super();
        cards = new ArrayList<>();
    }

    @Override
    public void addNewCard(Card card) throws RemoteException {
        cards.add(card);
        System.out.println("register card : " + card.getNumber());
    }

    @Override
    public void addMoney(Card card, double money) throws RemoteException {
        for (var crd : cards) {
            if (crd.equals(card)) {
                crd.setMoney(crd.getMoney() + money);
                System.out.println("add money : " + "card " + card.getNumber() + ", summa = " + money);
                break;
            }
        }
    }

    @Override
    public void subMoney(Card card, double money) throws RemoteException {
        for (var crd : cards) {
            if (crd.equals(card)) {
                crd.setMoney(crd.getMoney() - money);
                System.out.println("sub money : " + "card : " + card.getNumber() + ", summa = " + money);
                break;
            }
        }
    }

    @Override
    public double getCardBalance(Card card) throws RemoteException {
        double balance = 0;
        for (var crd : cards) {
            if (crd.equals(card)) {
                balance = crd.getMoney();
                System.out.println("balance : " + "card : " + card.getNumber() + ", summa = " + balance);
                break;
            }
        }
        return balance;
    }

    public static void main (String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            System.out.println("Initializing BillingService");

            Registry registry = LocateRegistry.createRegistry(1888);
            registry.rebind("BillingService", new BillingServiceImpl());

            System.out.println("Start BillingService");
        } catch (RemoteException e) {
            System.err.println("RemoteException : "+e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Exception : " + e.getMessage());
            System.exit(2);
        }
    }
}