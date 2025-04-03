package per.bmy.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author xiaobaobao
 * @date 2020/8/31，19:45
 */
public class Main {
    public static void main(String[] args) {
        int times = 100;
        int testSize = 5000;
        Random random = new Random();
        long now = System.currentTimeMillis();
        for (int j = 0; j < times; j++) {
            ConcurrentLinkedRank rank = new ConcurrentLinkedRank();
            List<ConcurrentLinkedRank.Entry> rankList = new ArrayList<>(testSize);
            for (int i = 0; i < testSize; i++) {
                rankList.add(rank.add(random.nextInt(testSize), i));
            }
            for (int i = 0; i < testSize; i++) {
                rank.addScoreByEntry(rankList.get(random.nextInt(testSize)), random.nextInt(testSize));
            }
        }
        System.out.print(System.currentTimeMillis() - now);
        System.out.println();
        now = System.currentTimeMillis();
        for (int j = 0; j < times; j++) {
            Rankbord2 rankbord2 = new Rankbord2();
            List<Integer> intList = new ArrayList<>(testSize);
            for (int i = 0; i < testSize; i++) {
                intList.add(i);
                rankbord2.updateScore(i, random.nextInt(testSize));
            }
            for (int i = 0; i < testSize; i++) {
                rankbord2.updateScore(intList.get(random.nextInt(testSize)), random.nextInt(testSize));
            }
        }
        System.out.print(System.currentTimeMillis() - now);
    }
}
