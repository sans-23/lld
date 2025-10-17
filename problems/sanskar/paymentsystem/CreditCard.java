package lld.problems.sanskar.paymentsystem;

public class CreditCard extends Card{
    public CreditCard(String cardNumber, String cardHolderName, String expiryDate) {
        super(cardNumber, cardHolderName, expiryDate);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Payment of " + amount + " made using credit card: " + getCardNumber());
    }

    @Override
    public void addPayment(double amount, String description) {
        System.out.println("Added credit card payment of " + amount + " with description: " + description);
    }

    @Override
    public void viewHistory() {
        System.out.println("Viewing credit card payment history for card: " + getCardNumber());
    }
}
