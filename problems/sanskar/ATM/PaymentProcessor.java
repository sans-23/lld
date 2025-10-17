package lld.problems.sanskar.ATM;

public class PaymentProcessor {

    int rupee;
    int balance;
    PaymentProcessor next;

    PaymentProcessor(int rupee, int balance){
        this.rupee = rupee;
        this.balance = balance;
        this.next = null;
    }

    PaymentProcessor(int rupee, int balance, PaymentProcessor next){
        this.rupee = rupee;
        this.balance = balance;
        this.next = next;
    }

    public void processRequest(Request r) {
        if(balance<r.amount){
            next.processRequest(r);
        }

        int noOfNotes = r.amount/rupee;
        balance -= r.amount;

        r.amount = r.amount%rupee;

        System.out.println(noOfNotes + " notes of " + rupee+ " are being fetched");

        if(r.amount>0){
            next.processRequest(r);
        }
    }

}
