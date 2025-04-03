package per.bmy.Sort;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * 各种排序
 */
public class Main {

    //测试数量
    private int length = 10000;
    private int[] testNums;
    //测试文本路径
    private String path = "resources\\Serialize\\sord.txt";
    //	private String path = "";
    //fasle用文本数据
    private boolean isTest = true;
    private long findTimes;
    private long changeTimes;

    private Main() {
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();

//		main.write();
        main.read();

        //双重循环单方向遍历
        main.bubbleSort("冒泡   排序");
        //双重循环，内层循环两个方向遍历
        main.cocktailSort("鸡尾酒 排序");
        //递归，根据锚点分为两堆，再分别排序
        main.quicklySort("快速   排序");
        //遍历元素，找其合适的位置
        main.insertionSort("插入   排序");
        //3n+1的滑窗排序
        main.shellSort("希尔   排序");
        //选择最值进行排序
        main.selectionSort("选择   排序");
        //分成小堆，再一一合并
        main.mergeSort("归并   排序");
        //找桶
//		main.countingSort("计数   排序");
        // 大顶堆
        main.heapSort("堆     排序");
        //定时输出
//   	main.sleepSort("睡觉   排序");
        //猴子排序，随机打乱
    }

    /**
     * 冒泡排序
     */
    private void bubbleSort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.nanoTime();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                findTimes++;
                if (nums[i] > nums[j]) {
                    swap(nums, i, j);

                }
            }
        }
        sout(name, nums, time);
    }

    /**
     * 鸡尾酒排序
     */
    private void cocktailSort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.nanoTime();
        for (int i = 0; i < nums.length / 2; i++) {
            for (int j = i; j < nums.length - i - 1; j++) {
                findTimes++;
                if (nums[j] > nums[j + 1]) {

                    swap(nums, j + 1, j);
                }
            }
            for (int j = nums.length - i - 2; j > i; j--) {
                findTimes++;
                if (nums[j - 1] > nums[j]) {

                    swap(nums, j - 1, j);
                }
            }
        }
        sout(name, nums, time);
    }

    /**
     * 选取锚点左边比锚点小右边比锚点大
     */
    private void quicklySort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.nanoTime();
        quickly(nums, 0, nums.length - 1);
        sout(name, nums, time);
    }

    private void quickly(int[] nums, int min, int max) {
        if (min >= max) {
            return;
        }
        int point = nums[min];
        int i = min;
        int j = max;
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
                swap(nums, i, j);
            }
        }
        if (i != min) {
            changeTimes++;
            nums[min] = nums[i];
            nums[i] = point;
        }
        quickly(nums, min, i - 1);
        quickly(nums, i + 1, max);
    }

    /**
     * 插入排序
     * 从后往前比较插入
     */
    private void insertionSort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.nanoTime();
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
     */
    private void shellSort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.nanoTime();
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
     */
    private void selectionSort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.nanoTime();
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
            swap(nums, i, minIndex);

        }
        sout(name, nums, time);
    }

    /**
     * 归并排序
     */
    private void mergeSort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.nanoTime();
        merge(nums, 0, nums.length);
        sout(name, nums, time);
    }

    private void merge(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        if (left + 1 == right) {
            if (right != nums.length && nums[left] > nums[right]) {
                swap(nums, right, left);
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
            } else if (x == middle) {
                findTimes = ++changeTimes;
                array[i++] = nums[y++];
            } else if (y == right) {
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
        if (right - left >= 0) {
            System.arraycopy(array, 0, nums, left, right - left);
        }
    }

    /**
     * 计数算法
     */
    private void countingSort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.nanoTime();
        int[] counts = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            counts[nums[i]]++;
        }
        soutByCount(name, counts, time);
    }

    /**
     * 睡眠算法
     */
    private void sleepSort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.nanoTime();
        for (Integer num : nums) {
            new Thread(() -> {
                try {
                    Thread.sleep(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this) {
                    System.out.println(num);
                }
            }).start();
        }
        sout(name, nums, time);
    }

    /**
     * 堆排序
     */
    public void heapSort(String name) {
        findTimes = 0;
        changeTimes = 0;
        int[] nums = testNums.clone();
        long time = System.nanoTime();
        //从最后一个非叶子节点开始遍历寻找是否满足上大于下的问题
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            judgeHeap(nums, i, nums.length);
        }
        for (int j = nums.length - 1; j > 0; j--) {
            swap(nums, 0, j);
            judgeHeap(nums, 0, j);
        }
        sout(name, nums, time);
    }

    public void judgeHeap(int[] array, int i, int j) {
        int temp = array[i];
        for (int k = i * 2 + 1; k < j; k = k * 2 + 1) {
            findTimes++;
            //找到当前i节点下的两个最大的节点
            if (k + 1 < j && array[k] < array[k + 1]) {
                k++;
            }
            //更大交换
            if (array[k] > temp) {
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp;
    }

    public void swap(int[] array, int i, int j) {
        changeTimes++;
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }


    private void sout(String name, int[] nums, long time) {
        System.out.printf("%s：%15fms,%13d次查找,%13d次交换,", name, (System.nanoTime() - time) * 1.0 / 1000000.0, findTimes, changeTimes);
        if (isTest) {
            for (int num : nums) {
                System.out.printf("%3d", num);
            }
        }
        System.out.println();
    }

    private void soutByCount(String name, int[] nums, long time) {
        System.out.printf("%s：%15fms,%13d次查找,%13d次交换,", name, (System.nanoTime() - time) * 1.0 / 1000000.0, findTimes, changeTimes);
        if (isTest) {
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                if (num <= 0) {
                    continue;
                }
                while (num-- > 0) {
                    System.out.printf("%3d", i);
                }
            }
        }
        System.out.println();
    }

    private void write() throws IOException {
        int[] num = new int[length];
        for (int i = 0; i < length; i++) {
            num[i] = i;
        }
        Random random = new Random();
        for (int i = 0; i < length / 2; i++) {
            int a = random.nextInt(length);
            int b = random.nextInt(length);
            swap(num, a, b);
        }
        if (path == null || "".equals(path)) {
            testNums = num;
            System.out.println("本地已建好数据,测试" + length + "组数据");
            return;
        }
        StringBuilder stringBuffer = new StringBuilder();
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
        if (!isTest && (path == null || "".equals(path))) {
            return;
        }
        if (isTest) {
            testNums = new int[]{27, 1, 2, 10, 4, 20, 6, 7, 37, 20, 38, 25, 15, 12, 32, 3, 14, 15, 13, 31, 30, 9, 21, 22,
                    23, 30, 11, 26, 39, 25, 35, 24, 19, 33, 5, 18, 36, 8, 29, 17};
        } else {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String str = in.readLine();
            String[] split = str.split(",");
            testNums = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                testNums[i] = Integer.parseInt(split[i]);
            }
            in.close();
        }
        length = testNums.length;
        System.out.printf("排序%d个随机数\n", length);
    }
}