package lld.problems.ATM;

public class Main {
    public static void main(String args[]){
        Card c1 = new Card("bxpk", "sanskar", "223233", 2300, false);
        Card c2 = new Card("affp", "sanskar", "279333", 200, true);
        Bank baroda = new Bank("baroda");
        baroda.issueCard(c1);
        baroda.issueCard(c2);

        Machine atm = new Machine(baroda, new PaymentProcessor(100, 1000, new PaymentProcessor(50, 5000)));

        // user makes a request
        Request r = new Request(c1, "223233", 2300);

        atm.processRequest(r);
    }
}
