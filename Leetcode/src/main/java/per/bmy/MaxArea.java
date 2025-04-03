package per.bmy;

public class MaxArea {


    public static void main(String[] args) {
        System.out.println(new MaxArea().maxArea(new int[]{1, 2, 4, 3}));
    }

    public int maxArea(int[] height) {
        int max = 0, l = 0, r = height.length - 1;
        while (l < r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }

}
