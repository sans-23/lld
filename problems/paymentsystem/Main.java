package lld.problems.paymentsystem;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("Testing Low-Level Design of Payment System");

        // Create instances of CreditCard and DebitCard
        HashMap<String, PaymentMethod> paymentMethods = new HashMap<>();

        paymentMethods.put("credit", new CreditCard("1234-5678-9012-3456", "John Doe", "12/25"));
        paymentMethods.put("debit", new DebitCard("9876-5432-1098-7654", "Jane Doe", "11/24"));

        for (String key : paymentMethods.keySet()) {
            PaymentMethod method = paymentMethods.get(key);
            System.out.println("Using " + key + " card:");
            method.pay(100.0);
        }
        
    }
}