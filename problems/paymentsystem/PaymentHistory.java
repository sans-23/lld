package lld.problems.paymentsystem;

public interface PaymentHistory {
    void addPayment(double amount, String date);
    void viewHistory();
}
