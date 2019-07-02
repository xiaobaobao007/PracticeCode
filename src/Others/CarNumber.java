package Others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CarNumber {
    public static void main(String[] args) {
        String[] check = {"沪A79K82", "沪B8039P", "沪b8098C", "沪A82092", "沪A79037", "沪A704#7", "沪A45KKK", "沪A45K43", "沪A675R0", "沪A794S5", "沪A09K61", "沪AE57T6", "沪A7SS4", "沪A796K3", "沪B35K3",};
        String[] tips = {"星期一", "星期二", "星期三", "星期四", "星期五"};
        Map<Integer, List<String>> tipsMap=new HashMap<>(10,0.8F);
        for (String string : check) {
            if (string.length() != 7) {
                System.out.println(string + "车牌位数错误");
                continue;
            }
            String substring = string.substring(1);
            if (!isTrue(substring)) {
                System.out.println(string + "车牌号非法");
                continue;
            }
            Integer num = number(substring);
            if (num == null) {
                System.out.println(string + "车牌号非法");
                continue;
            }
            int tipsIndex = dayLimit(num);
            String oneTip = string;
            List<String> list = tipsMap.get(tipsIndex);
            if (list == null) {
                list = new ArrayList<>();
                tipsMap.put(tipsIndex, list);
            }
            list.add(oneTip);
        }
        for (Map.Entry<Integer, List<String>> entry : tipsMap.entrySet()) {
            System.out.print("\n"+tips[entry.getKey()]+":");
            for (String s : entry.getValue()) {
                System.out.print(s+",");
            }
        }
    }

    public static int dayLimit(int num) {
        if (num == 5 || num == 0) {
            return 4;
        }
        if (num > 5) {
            return 9 - num;
        }
        return num - 1;
    }

    public static Integer number(String substring) {// 传定义好的substring
        Integer number = null;
        for (int i = 0; i < substring.length(); i++) {
            if (substring.charAt(i) >= '0' && substring.charAt(i) <= '9') {
                number = substring.charAt(i) - '0';
            }
        }
        if (number == null) {
            return null;
        }
        return number;
    }

    public static boolean isTrue(String string) {
        String pattern = "^[A-Z0-9]{6}$";
        return Pattern.matches(pattern, string);
    }
}