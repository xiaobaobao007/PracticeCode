
/**
 * 螺旋数组
 * 7  8  9
 * 6  1  2
 * 5  4  3
 * ===========
 * 21 22 23 24 25
 * 20 7  8  9  10
 * 19 6  1  2  11
 * 18 5  4  3  12
 * 17 16 15 14 13
 *
 * @author xiaobaobao
 * @date 2019/6/28 14:38
 */
public class SpiralArry {

    public static int length;
    public static int[][] map;
    public static int nowStep;
    public static int allStep;

    public static void main(String[] args) {
        new SpiralArry(2);
        new SpiralArry(3);
        new SpiralArry(4);
    }

    SpiralArry(int n) {
        length = 2 * n - 1;
        allStep = length * length;
        map = new int[length][length];
        nowStep = 1;

        doIt(n - 1, n - 1, 2, 1, 1, 0);
        sout();
    }

    /**
     * 1  0 right
     * 0  1 down
     * -1  0 left
     * 0 -1 up
     * 1  0 right
     *
     * @param x
     * @param y
     * @param lineAllStep
     * @param step
     * @param xAdd
     * @param yAdd
     */
    public void doIt(int x, int y, int lineAllStep, int step, int xAdd, int yAdd) {
        if (nowStep > allStep) {
            return;
        }
        map[y][x] = nowStep++;
        if (lineAllStep > step) {
            doIt(x + xAdd, y + yAdd, lineAllStep, step + 1, xAdd, yAdd);
        } else {
            xAdd = xAdd ^ yAdd;
            yAdd = xAdd ^ yAdd;
            xAdd = xAdd ^ yAdd;
            if (yAdd == 0) {
                xAdd *= -1;
                doIt(x + xAdd, y + yAdd, lineAllStep + 1, 2, xAdd, yAdd);
            } else {
                doIt(x + xAdd, y + yAdd, lineAllStep, 2, xAdd, yAdd);
            }
        }
    }

    public void sout() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.printf("%-3d", map[i][j]);
            }
            System.out.println();
        }
        System.out.println("===========");
    }

}