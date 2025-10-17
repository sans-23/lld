package lld.problems.ATM.sanskar;

public class Card {
    String number;
    String holder;
    String pin;
    int balance;
    boolean isBlocked;

    Card(String number, String holder, String pin, int balance, boolean isBlocked){
        this.number = number;
        this.holder = holder;
        this.pin = pin;
        this.balance = balance;
        this.isBlocked = isBlocked;
    }

    public int getBalance(){
        return this.balance;
    }
}
