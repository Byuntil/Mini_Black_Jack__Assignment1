import java.util.*;

public class BlackJack {
    public static int computeScore(ScoreTable table, Vector<Card> cards){
        //(H)
        // table 이용해 card 들의 점수 총합을 리턴

        return 0;
    }

    public static boolean is_bust(ScoreTable table, Vector<Card> cards){
        //(I)
        // table 이용해 card 들의 점수 총합이 21을 초과하는지 아닌지 리턴

        return false;
    }

    public static boolean checkBlackjack(Vector<Card> cards){
        //(J)
        // 카드들을 받아 블랙잭인지 아닌지 리턴
        return false;
    }

    public static void sleep(int time){
        //(K)
        // time 만큼 pause 후 재개, (단위 : 밀리센컨드)

    }

    public static void main(String[] args) {
        //(L)
        // 메인 실행 부분
        Scanner scanner = new Scanner(System.in);
        System.out.print("사용할 덱의 개수를 입력해주세요 >> ");
        int num = scanner.nextInt();
        CardPool cardPool = new CardPool(num);
        System.out.println(num + "개의 덱, "+ num*52 +"개의 카드를 사용합니다.");

        while (true){
            Player user = new Player();
            user.addCard(cardPool.drawCard());

        }
    }
}
