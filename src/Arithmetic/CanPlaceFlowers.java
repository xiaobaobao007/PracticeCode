package Arithmetic;

import Util.CommonUtil;

/**
 * @descript:
 * @author: bao meng yang <932824098@qq.com>
 * @create: 2021-08-13 17:30
 */
public class CanPlaceFlowers {
    public static void main(String[] args) {
        System.out.println(new CanPlaceFlowers().canPlaceFlowers(CommonUtil.coverString2OneInt("[0,0,1,0,1]"), 1));
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n <= 0) {
            return true;
        }
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                continue;
            }
            if (i - 1 >= 0 && flowerbed[i - 1] == 1) {
                continue;
            }
            if (i + 1 < flowerbed.length && flowerbed[i + 1] == 1) {
                continue;
            }
            flowerbed[i] = 1;
            if (--n <= 0) {
                return true;
            }
        }
        return false;
    }
}
