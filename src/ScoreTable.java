import java.util.*;

public class ScoreTable {
    private HashMap<String, int[]> table = new HashMap<>();

    ScoreTable() {
        //(F)
        //key=랭크: value=점수 쌍의 테이블을 생성한다.
        table.put("A", new int[]{1, 11});
        for (int i = 1; i < 10; i++) {
            table.put(String.valueOf(i + 1), new int[]{i + 1});
        }
        table.put("J", new int[]{10});
        table.put("Q", new int[]{10});
        table.put("K", new int[]{10});

    }

    public int[] score(Card card) {
        //(G)
        //card 의 점수를 리턴한다. A의 경우 1 또는 11이며,
        // 이 경우에 해당 점수들로 구성된 length 가 2인 정수배열이 리턴된다.
        return table.get(card.getRank());
    }
}
