package lld.problems.paymentsystem;

import java.util.Map;

public class Card implements PaymentMethod, PaymentHistory {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;

    public Card(String cardNumber, String cardHolderName, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public void addPayment(double amount, String description) {
        System.out.println("Added payment of " + amount + " with description: " + description);
    }

    @Override
    public void viewHistory() {
        System.out.println("Viewing payment history for card: " + cardNumber);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Payment of " + amount + " made using card: " + cardNumber);
    }

    public void hello(){
        System.out.println("hello");
    }
}
