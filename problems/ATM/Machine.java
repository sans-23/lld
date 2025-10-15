package lld.problems.ATM;

public class Machine {
    Bank bank;
    PaymentProcessor paymentProcessor;

    Machine(Bank bank, PaymentProcessor p){
        this.bank = bank;
        this.paymentProcessor = p;
    }

    public boolean processRequest(Request r){
        // autenticate with Bank if valid card
        if(!bank.authenticate(r)){
            System.out.println("Invalid Request: Either card is blocked or invalid pin");
            return false;
        }

        System.out.println("Processing your request");
        if(!bank.isBalanceAvailable(r)){
            System.out.println("Insufficient Balance");
            return false;
        }

        paymentProcessor.processRequest(r);
        return true;
    }
}
