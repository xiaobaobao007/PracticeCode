package per.bmy.other;

import java.util.HashMap;
import java.util.List;

/**
 * @author xiaobaobao
 * @date 2020/9/29，23:00
 */
public class LeastBricks {

    public int leastBricks(List<List<Integer>> wall) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (List<Integer> row : wall) {
            int sum = 0;
            for (int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);
                if (map.containsKey(sum))
                    map.put(sum, map.get(sum) + 1);
                else
                    map.put(sum, 1);
            }
        }
        int res = wall.size();
        for (int key : map.keySet())
            res = Math.min(res, wall.size() - map.get(key));
        return res;
    }

}
