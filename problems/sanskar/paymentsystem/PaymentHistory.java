package lld.problems.sanskar.paymentsystem;

public interface PaymentHistory {
    void addPayment(double amount, String date);
    void viewHistory();
}
