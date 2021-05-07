import java.util.*;

public class BlackJack {
    static final Scanner scanner = new Scanner(System.in);

    public static int computeScoreUser(ScoreTable table, Vector<Card> cards) {
        //(H)
        // table 이용해 유저의 card 들의 점수 총합을 리턴, 키보드 입력을 받아 ACE의 점수를 결정하는 것 또한 수행한다.
        int sum = 0;
        for (Card card : cards) {
            int[] score = table.score(card);
            if (card.getRank().equals("A")) {
                System.out.print(card + " 의 점수를 선택해주세요. (1/11) >> ");
                score[0] = scanner.nextInt();
            }
            sum += (int) score[0];
        }
        return sum;
    }

    public static int computeScoreDealer(ScoreTable table, Vector<Card> cards) {
        //(I)
        //table 을 이용해 딜러의 card 들의 점수 총합를 리턴, ACE의 점수 계산
        int sum = 0;
        for (int i = 0; i < cards.size(); i++) {
            int[] score = table.score(cards.get(i));
            if (cards.get(i).getRank().equals("A")) {
                if (i == 0 || i == 1) {
                    score[0] = 11;
                } else {
                    score[0] = 1;
                }
            }
            sum += (int) score[0];
        }
        return sum;
    }

    public static boolean is_bust(int score) {
        //(J)
        // 점수를 받아 21점이 초과하는지 검사
        return score > 21;
    }

    public static boolean checkBlackjack(Vector<Card> cards) {
        //(K)
        // 카드들을 받아 블랙잭인지 아닌지 리턴
        ScoreTable scoreTable = new ScoreTable();
        if (cards.get(0).getRank().equals("A") && (cards.get(1).getRank().equals("J") || cards.get(1).getRank().equals("Q") || cards.get(1).getRank().equals("K") || cards.get(1).getRank().equals("10"))) {
            return true;
        } else if (cards.get(1).getRank().equals("A") && (cards.get(0).getRank().equals("J") || cards.get(0).getRank().equals("Q") || cards.get(0).getRank().equals("K") || cards.get(0).getRank().equals("10"))) {
            return true;
        }
        return false;
    }

    public static void sleep(int time) {
        //(K)
        // time 만큼 pause 후 재개, (단위 : 밀리센컨드)
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException ignored) {

        }
    }

    public static void main(String[] args) {
        //(L)
        // 메인 실행 부분
        int userScore = 0;
        int computerScore = 0;
        System.out.print("사용할 덱의 개수를 입력해주세요 >> ");
        int num = scanner.nextInt();
        //덱 생성
        CardPool cardPool = new CardPool(num);
        System.out.println(num + "개의 덱, " + num * 52 + "개의 카드를 사용합니다.");
        //Player 처음 2장 지급
        Player user = new Player();
        for (int i = 0; i < 2; i++) {
            user.addCard(cardPool.drawCard());
        }
        System.out.println("당신의 카드는 " + user.getHand());

        //딜러 카드 2장지급
        Player computer = new Player();
        for (int i = 0; i < 2; i++) {
            computer.addCard(cardPool.drawCard());
        }
        System.out.println("딜러가 공개한 카드는 " + computer.get(0) + "입니다. \n");

        //Blackjack 검사
        if (checkBlackjack(user.getHand())) {
            System.out.println("당신의 BlackJack 으로 승리했습니다.");
            System.exit(0);
        }


        //user 와 computer 의 카드 수
        int userj = 2;
        int computerj = 2;
        while (true) {
            System.out.println("당신의 차례입니다.");
            userScore = computeScoreUser(new ScoreTable(), user.getHand());
            //Hit Or Stand
            System.out.print("카드를 더 받으시겠습니까 ? (hit/stand) >> ");
            String hitOrStand = scanner.next();
            if (hitOrStand.equals("hit") || hitOrStand.equals("Hit")) {
                user.addCard(cardPool.drawCard());
                System.out.println("\n" + user.get(userj++) + " 를 받았습니다.");
                System.out.println("당신의 카드는 " + user.getHand() + "입니다.\n");
                //user is_bust
                if (is_bust(computeScoreUser(new ScoreTable(), user.getHand()))) {
                    System.out.println("패의 총합이 21을 초과하여 패배했습니다.\n");
                    System.exit(0);
                }
            } else if (hitOrStand.equals("stand") || hitOrStand.equals("Stand")) {
                System.out.println("\n당신의 차례가 끝났습니다.");
                //userScore And computerScore
                computerScore = computeScoreDealer(new ScoreTable(), computer.getHand());

                System.out.println("\n딜러의 차례입니다.");
                System.out.println("딜러의 카드는 " + computer.getHand() + " 입니다.\n");

                //blackjack 검사
                if (checkBlackjack(computer.getHand())) {
                    System.out.println("딜러의 BlackJack 으로 승리했습니다.");
                    System.exit(0);
                }
                //computer is_bust
                if (is_bust(computerScore)) {
                    System.out.println("딜러 패의 총합이 21을 초과하여 승리했습니다.\n");
                    System.exit(0);
                }
                while (true) {
                    if (computeScoreDealer(new ScoreTable(), computer.getHand()) <= 17) {
                        sleep(3);
                        computer.addCard(cardPool.drawCard());
                        System.out.println("\n딜러는" + computer.get(computerj++) + " 를 받았습니다.");
                        System.out.println("딜러의 카드는 " + computer.getHand() + " 입니다.\n");
                    } else {
                        computerScore = computeScoreDealer(new ScoreTable(), computer.getHand());
                        break;
                    }
                }

                //computer is_bust
                if (is_bust(computerScore)) {
                    System.out.println("딜러 패의 총합이 21을 초과하여 승리했습니다.\n");
                    System.exit(0);
                } else {
                    System.out.println("딜러의 차례가 끝났습니다.\n");
                    System.out.println("유저: " + userScore + " vs " + "딜러: " + computerScore + "\n");
                    if (userScore > computerScore) {
                        System.out.println("승리했습니다.");
                        System.exit(0);
                    } else if (userScore < computerScore) {
                        System.out.println("패배했습니다.");
                        System.exit(0);
                    } else {
                        System.out.println("비겼습니다.");
                        System.exit(0);
                    }
                }
            }
        }
    }
}
