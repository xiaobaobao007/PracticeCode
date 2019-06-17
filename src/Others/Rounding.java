package Others;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaobaobao
 * @date 2019/6/17 14:06
 */
public class Rounding {
    public static void main(String[] args) {
//        String name="qwe";
//        if ("qw".equals(name)){
//            System.out.println("123");
//        }
//        Math.floor(1.5);
        System.out.println(Math.round(1.5));
        System.out.println(Math.round(-1.5));
        List<Integer> roundingModes = new ArrayList<>();
        roundingModes.add(4);
        roundingModes.add(5);
        roundingModes.add(6);
        List<BigDecimal> list = new ArrayList<>();
        list.add(new BigDecimal("1.44"));
        list.add(new BigDecimal("-1.45"));
        list.add(new BigDecimal("-1.55"));

//        for (RoundingMode value : RoundingMode.values()) {
//            for (BigDecimal decimal : list) {
//                BigDecimal i = decimal.setScale(1, value);
//                System.out.println(value + ":" + i + ",");
//            }
//            System.out.println();
//        }

        for (Integer value : roundingModes) {
            RoundingMode mode = RoundingMode.valueOf(value);
            System.out.printf("%-10s:", mode);
            for (BigDecimal decimal : list) {
                BigDecimal i = decimal.setScale(1, mode);
                System.out.printf("%6s,", i);
            }
            System.out.println();
        }

        //BigDecimal.ROUND_UP;远于0
        //BigDecimal.ROUND_DOWN;趋向0
        //BigDecimal.ROUND_CEILING;趋向正无穷
        //ROUND_FLOOR;趋向负无穷
        //ROUND_HALF_UP;四入五入
        //ROUND_HALF_DOWN;四舍五入
        //ROUND_HALF_EVEN;银行家算法，舍去位后不为零进，否则看前一位，奇进偶退
        //ROUND_UNNECESSARY;
    }
}