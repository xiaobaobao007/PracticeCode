package per.bmy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@SuppressWarnings("ALL")
public class CarNumber {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String[] check = {"沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3", "沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3", "沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3", "沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3", "沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3", "沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3", "沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3", "沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3", "沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3", "沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3"};
        String[] tips = {"星期一", "星期二", "星期三", "星期四", "星期五"};
        String[] errorTips = {"车牌位数错误", "车牌号非法"};

        Map<Integer, List<String>> tipsMap = new HashMap<>(10, 0.8F);
        List<ErrorTips> errorTipsList = new ArrayList<>();
        for (String string : check) {
            if (string.length() != 7) {
                errorTipsList.add(new ErrorTips(string, 0));
                continue;
            }
            String substring = string.substring(1);
            if (!isTrue(substring)) {
                errorTipsList.add(new ErrorTips(string, 1));
                continue;
            }
            Integer num = number(substring);
            if (num == null) {
                errorTipsList.add(new ErrorTips(string, 1));
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
        for (ErrorTips error : errorTipsList) {
            System.out.println(error.getCarCode() + ":" + errorTips[error.getErrorCode()]);
        }
        for (Map.Entry<Integer, List<String>> entry : tipsMap.entrySet()) {
            System.out.print("\n" + tips[entry.getKey()] + ":");
            for (String s : entry.getValue()) {
                System.out.print(s + ",");
            }
        }
        System.out.println();
        System.out.println(check.length + "mm" + (System.currentTimeMillis() - start));
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

    static class ErrorTips {
        private String carCode;
        private int errorCode;

        public ErrorTips(String carCode, int errorCode) {
            this.carCode = carCode;
            this.errorCode = errorCode;
        }

        public String getCarCode() {
            return carCode;
        }

        public void setCarCode(String carCode) {
            this.carCode = carCode;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }
    }
}