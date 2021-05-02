import java.util.*;

public class Player {
    private Vector<Card> hand = new Vector<Card>();

    public void addCard(Card card){
        //(A)
        //카드를 추가한다.
        hand.add(card);
    }
    public Card get(int item){
        //(B)
        // Item 번째 카드를 리턴한다.
        return hand.get(item);
    }
    public Vector<Card> getHand(){
        // (C)
        // 가지고 있는 패를 모두 리턴한다.
        return hand;
    }
}
