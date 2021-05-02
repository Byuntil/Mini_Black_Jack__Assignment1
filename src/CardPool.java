import java.util.*;

public class CardPool {
    private Stack<Card> cards = new Stack<Card>();

    CardPool(int num_desks){
        //(D)
        //num_desks 개의 덱을 생성하고 셔플 한 후 추가한다.
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        String[] rank = {"A","1","2","3","4","5","6","7","8","9","J","Q","K"};
        for(int i=0;i<4;i++){
            for(int j=0; j<13; j++)
            {
                Card card = new Card(suits[i], rank[j]);
                cards.push(card);
            }
        }
        Collections.shuffle(cards);

    }
    public Card drawCard(){
        //(E)
        //카드들로부터 카드를 한장 뽑는다.

        return null;
    }

}
