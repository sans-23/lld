package lld.problems.ATM;

public class Request {

    public Card card;
    public String enteredPin;
    public int amount;

    Request(Card c, String enteredPin, int amount){
        this.card = c;
        this.enteredPin = enteredPin;
        this.amount = amount;
    }
}
