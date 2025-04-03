package per.bmy.LeetCode_Hot;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import per.bmy.util.CommonUtil;

/**
 * <pre>
 *     		给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 			示例 1:
 *
 * 			输入: nums = [1,1,1,2,2,3], k = 2
 * 			输出: [1,2]
 * 			示例 2:
 *
 * 			输入: nums = [1], k = 1
 * 			输出: [1]
 *
 * 			来源：力扣（LeetCode）
 * 			链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 * 			著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * </pre>
 *
 * @author xiaobaobao
 * @date 2020/10/4，0:42
 */
public class TopKFrequent {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TopKFrequent().topKFrequent(CommonUtil.coverString2OneInt("[4,1,-1,2,-1,2,3]"), 2)));
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.compute(num, (K, V) -> V == null ? 1 : V + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (Integer key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }
        int[] res = new int[pq.size()];
        int i = 0;
        while (!pq.isEmpty()) {
            res[i++] = pq.remove();
        }
        return res;
    }

}
