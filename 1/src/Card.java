import java.io.Serializable;

public class Card implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name ;
    private final String number;
    private double money ;
    
    public Card(String name, String number, double money) {
        super();
        this.name = name;
        this.number = number;
        this.money = money;
    }

    @Override
    public boolean equals(Object card) {
        Card crd = (Card)card;
        return this.getNumber().equals(crd.getNumber());
    }

    public String getNumber() {
        return number;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}