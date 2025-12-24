package per.bmy.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * 时间工具方便测试输出结果
 *
 * @author baomengyang
 * @date 2025/12/24 14:43:09
 */
public class TimeUtil {
    public static void test(int[] t, Function<int[], Boolean>... functions) {
        List<Result> results = new LinkedList<>();
        for (Function<int[], Boolean> function : functions) {
            Result resultRecord = new Result();
            results.add(resultRecord);

            long now = System.nanoTime();
            Boolean result = function.apply(t);
            resultRecord.time = System.nanoTime() - now;
            resultRecord.result = result;
        }

        System.out.printf("%15s   =>   ", Arrays.toString(t));

        boolean first = results.get(0).result;
        boolean allSame = results.stream().allMatch(r -> Objects.equals(r.result, first));
        if (!allSame) {
            for (Result resultRecord : results) {
                System.out.printf("%8s：-%10s   ", resultRecord.result, resultRecord.time);
            }
            System.out.println();
        } else {
            for (Result resultRecord : results) {
                System.out.printf("%10s   ", resultRecord.time);
            }
            System.out.printf("所有结果一致：%s\n", first);
        }
    }

    static class Result {
        private boolean result;
        private long time;
    }

}
