import java.net.MalformedURLException;
import java.rmi.*;
public class BillingClient  {

    double[] moneys = {135790.0, 24680.0};
    String[][] cards = {
            {"Ivanov", "1213-456-7890"},
            {"Petrov", "1987-654-3210"}
    };

    public BillingClient() {
        try {
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            BillingService bs = (BillingService) Naming.lookup("rmi://localhost:1888/BillingService");
            System.out.println("\nRegister cards ...");
            registerCards(bs);
            System.out.println("Add moneys ...");
            addMoney(bs);
            System.out.println("Get balance ...\n");
            getBalance(bs);
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.err.println("NotBoundException : " +
                    e.getMessage());
        }
    }

    private Card createCard (final int idx) {
        return new Card(cards[idx][0], cards[idx][1], 0);
    }

    private void registerCards(BillingService bs) {
        for (int i = 0; i < cards.length; i++) {
            Card card = createCard (i);
            try {
                bs.addNewCard(card);
            } catch (RemoteException e) {
                System.err.println("RemoteException : " +  e.getMessage());
            }
        }
    }

    private void addMoney(BillingService bs)  {
        for (int i = 0; i < cards.length; i++) {
            Card card = createCard (i);
            try {
                bs.addMoney(card, moneys[i]);
            } catch (RemoteException e) {
                System.err.println("RemoteException : " + e.getMessage());
            }
        }
    }

    private void getBalance(BillingService bs)  {
        for (int i = 0; i < cards.length; i++) {
            Card card = createCard (i);
            try {
                System.out.println("card : " + card.getNumber() + ", balance = " + bs.getCardBalance(card));
            } catch (RemoteException e) {
                System.err.println("RemoteException : " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new BillingClient();
        System.exit(0);
    }
}