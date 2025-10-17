package lld.problems.sanskar.paymentsystem;

public class DebitCard extends Card {
    public DebitCard(String cardNumber, String cardHolderName, String expiryDate) {
        super(cardNumber, cardHolderName, expiryDate);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Payment of " + amount + " made using debit card: " + getCardNumber());
    }

    @Override
    public void addPayment(double amount, String description) {
        System.out.println("Added debit card payment of " + amount + " with description: " + description);
    }

    @Override
    public void viewHistory() {
        System.out.println("Viewing debit card payment history for card: " + getCardNumber());
    }
    
}
