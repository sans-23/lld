package lld.problems.ATM;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    String name;
    List<Card> issueCards;

    Bank(String name){
        this.name = name;
    }

    Bank(String name, List<Card> issueCards){
        this.name = name;
        this.issueCards = issueCards;
    }

    public void issueCard(Card c){
        if(issueCards==null){
            issueCards = new ArrayList<>();
        }
        issueCards.add(c);
    }

    public boolean authenticate(Request req){
        Card c = req.card;
        String enteredPin = req.enteredPin;
        if (c.isBlocked == true) return false;
        return enteredPin == c.pin;
    }

    public boolean isBalanceAvailable(Request req){
        return req.amount <= req.card.getBalance();
    }
}
