package Sort;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * 各种排序
 */
public class Main {

    private int[] testNums;
    private String path = "./src/resouse/sord.txt";
    private boolean isTest = true;
    private long findTimes;
    private long changeTimes;

    private Main() {
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();

//        main.write();
        main.read();

        //交换排序
        main.bubbleSort("冒泡   排序");
        main.cocktailSort("鸡尾酒 排序");
        main.quicklySort("快速   排序");
        //插入排序
        main.insertionSort("插入   排序");
        main.shellSort("希尔   排序");
        //选择排序
        main.selectionSort("选择   排序");
        //归并排序
        main.mergeSort("归并   排序");
        //非比较排序
        main.countingSort("计数   排序");
        //鬼畜排序
//        main.sleepSort("睡觉   排序");
    }

    /**
     * 冒泡排序
     *
     * @param name
     */
    private void bubbleSort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.currentTimeMillis();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                findTimes++;
                if (nums[i] > nums[j]) {
                    nums[i] = nums[i] ^ nums[j];
                    nums[j] = nums[i] ^ nums[j];
                    nums[i] = nums[i] ^ nums[j];
                    changeTimes++;
                }
            }
        }
        sout(name, nums, time);
    }

    /**
     * 鸡尾酒排序
     *
     * @param name
     */
    private void cocktailSort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.currentTimeMillis();
        for (int i = 0; i < nums.length / 2; i++) {
            for (int j = i; j < nums.length - i - 1; j++) {
                findTimes++;
                if (nums[j] > nums[j + 1]) {
                    changeTimes++;
                    nums[j + 1] = nums[j + 1] ^ nums[j];
                    nums[j] = nums[j + 1] ^ nums[j];
                    nums[j + 1] = nums[j + 1] ^ nums[j];
                }
            }
            for (int j = nums.length - i - 2; j > i; j--) {
                findTimes++;
                if (nums[j - 1] > nums[j]) {
                    changeTimes++;
                    nums[j - 1] = nums[j - 1] ^ nums[j];
                    nums[j] = nums[j - 1] ^ nums[j];
                    nums[j - 1] = nums[j - 1] ^ nums[j];
                }
            }
        }
        sout(name, nums, time);
    }

    /**
     * @param name
     */
    private void quicklySort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.currentTimeMillis();
        quickly(nums, 0, nums.length - 1);
        sout(name, nums, time);
    }

    private void quickly(int[] nums, int min, int max) {
        if (min >= max) {
            return;
        }
        int i = min;
        int j = max;
        int point = nums[i];
        while (i < j) {
            while (i < j && nums[j] >= point) {
                findTimes++;
                j--;
            }
            while (i < j && nums[i] <= point) {
                findTimes++;
                i++;
            }
            if (i < j) {
                changeTimes++;
                nums[j] = nums[i] ^ nums[j];
                nums[i] = nums[i] ^ nums[j];
                nums[j] = nums[i] ^ nums[j];
            }
        }
        nums[min] = nums[i];
        nums[i] = point;
        quickly(nums, min, i - 1);
        quickly(nums, j + 1, max);
    }

    /**
     * 插入排序
     * 从后往前比较插入
     *
     * @param name
     */
    private void insertionSort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.currentTimeMillis();
        for (int i = 1; i < nums.length; i++) {
            int min = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                findTimes++;
                if (nums[j] > min) {
                    nums[j + 1] = nums[j];
                    nums[j] = min;
                    changeTimes++;
                } else {
                    break;
                }
            }
        }
        sout(name, nums, time);
    }

    /**
     * 增量排列，序列：3b+1
     *
     * @param name
     */
    private void shellSort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.currentTimeMillis();
        int d = nums.length;

        int addNum = 0;
        while (true) {
            addNum = addNum * 3 + 1;
            if (addNum > d) {
                break;
            }
        }
        while (addNum > 0) {
            addNum = (addNum - 1) / 3;//增量值递减
            for (int x = 0; x < addNum; x++) {//
                for (int i = x + addNum; i < nums.length; i += addNum) {
                    int temp = nums[i];
                    findTimes++;
                    int j;
                    for (j = i - addNum; j >= 0 && nums[j] > temp; j -= addNum) {
                        changeTimes++;
                        nums[j + addNum] = nums[j];
                    }
                    changeTimes++;
                    nums[j + addNum] = temp;
                }
            }
        }
        sout(name, nums, time);
    }

    /**
     * 选择排序
     * 找到最值进行排序
     *
     * @param name
     */
    private void selectionSort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.currentTimeMillis();
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                findTimes++;
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex == i) {
                continue;
            }
            nums[i] = nums[i] ^ nums[minIndex];
            nums[minIndex] = nums[i] ^ nums[minIndex];
            nums[i] = nums[i] ^ nums[minIndex];
            changeTimes++;
        }
        sout(name, nums, time);
    }

    /**
     * 归并排序
     *
     * @param name
     */
    private void mergeSort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.currentTimeMillis();
        merge(nums, 0, nums.length);
        sout(name, nums, time);
    }

    private void merge(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        if (left + 1 == right) {
            if (right != nums.length && nums[left] > nums[right]) {
                nums[left] = nums[left] ^ nums[right];
                nums[right] = nums[left] ^ nums[right];
                nums[left] = nums[left] ^ nums[right];
            }
            return;
        }
        int middle = (left + right) / 2;
        merge(nums, left, middle);
        merge(nums, middle, right);
        int x = left, y = middle, i = 0;
        int[] array = new int[right - left];
        while (true) {
            if (x == middle && y == right) {
                break;
            } else if (x == middle && y != right) {
                findTimes = ++changeTimes;
                array[i++] = nums[y++];
            } else if (x != middle && y == right) {
                findTimes = ++changeTimes;
                array[i++] = nums[x++];
            } else if (nums[x] > nums[y]) {
                findTimes = ++changeTimes;
                array[i++] = nums[y++];
            } else if (nums[x] < nums[y]) {
                findTimes = ++changeTimes;
                array[i++] = nums[x++];
            } else {
                findTimes = ++changeTimes;
                array[i++] = nums[x++];
                array[i++] = nums[y++];
            }
        }
        for (int j = left; j < right; j++) {
            nums[j] = array[j - left];
        }

    }

    /**
     * 计数算法
     *
     * @param name
     */
    private void countingSort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.currentTimeMillis();
        int[] counts = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            counts[nums[i]] = 1;
        }
        sout(name, nums, time);
    }

    /**
     * 计数算法
     *
     * @param name
     */
    private void sleepSort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.currentTimeMillis();
        for (Integer num : nums) {
            new Thread(() -> {
                try {
                    Thread.sleep(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (num) {
                    System.out.println(num);
                }
            }).start();
        }
        sout(name, nums, time);
    }

    private void sout(String name, int[] nums, long time) {
        System.out.printf("%s：%5dms,%13d次查找,%10d次交换,", name, System.currentTimeMillis() - time, findTimes, changeTimes);
        if (isTest) {
            for (int num : nums) {
                System.out.printf("%3d", num);
            }
        }
        System.out.println();
    }

    private void write() throws IOException {
        int length = 10000;
        int[] num = new int[length];
        for (int i = 0; i < length; i++) {
            num[i] = i;
        }
        Random random = new Random();
        for (int i = 0; i < length / 2; i++) {
            int a = random.nextInt(length);
            int b = random.nextInt(length);
            num[a] = num[a] ^ num[b];
            num[b] = num[a] ^ num[b];
            num[a] = num[a] ^ num[b];
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(num[i]).append(',');
        }
        byte[] bytes = stringBuffer.toString().substring(0, stringBuffer.length() - 1).getBytes(StandardCharsets.UTF_8);
        FileOutputStream fileOut = new FileOutputStream(path);
        BufferedOutputStream out = new BufferedOutputStream(fileOut);
        out.write(bytes);
        out.flush();
        fileOut.close();
        out.close();
    }

    private void read() throws IOException {
        if (isTest) {
            testNums = new int[]{6, 10, 11, 15, 8, 0, 9, 12, 18, 19, 5, 7, 3, 1, 2, 16, 17, 4, 13, 14};
            System.out.printf("排序%d个随机数\n", testNums.length);
            return;
        }
        BufferedReader in = new BufferedReader(new FileReader(path));
        String str = in.readLine();
        String[] split = str.split(",");
        testNums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            testNums[i] = Integer.parseInt(split[i]);
        }
        System.out.printf("排序%d个随机数\n", split.length);
        in.close();
    }
}