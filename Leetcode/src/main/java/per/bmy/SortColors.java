package per.bmy;

import org.junit.Test;

/**
 * @author xiaobaobao
 * @date 2020/8/27，22:13
 */
public class SortColors {

    @Test
    public void main() {
        sortColors(new int[]{0, 0, 2, 0, 1});
    }

    public void sortColors(int[] nums) {
        int left = 0, right = nums.length - 1, index = 0;
        for (int num : nums) {
            if (num != 0) {
                break;
            }
            index = ++left;
        }
        while (index <= right) {
            switch (nums[index]) {
                case 0:
                    swap(nums, index++, left++);//能保证最小值一定在左，所以index++
                    break;
                case 1:
                    index++;
                    break;
                case 2:
                    swap(nums, index, right--);//不能保证最小值一定在左，所以不能index++
                    break;
            }
        }
    }

    public void swap(int[] nums, int a, int b) {
        int c = nums[a];
        nums[a] = nums[b];
        nums[b] = c;
    }

}
