package Others;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 检查车牌号
 * 业务分析:
 * 1. 先对车牌号的位数  处理, 车牌号必须为5位. 如果不为5位, 则车牌位数不正确
 * 2. 再对车牌号进行正则判断, 当车牌号 全是字母或者包含非数字和字母的字符  则车牌号非法
 * 3. 再对车牌号最后一位做判断, 当为数字的时候 去判断是限行的哪一天, 如果不为数字,则往前判断一位,继续判断是否为数字
 * 4. 得到第三步骤的数字 再根据当天限行的号码 进行判断 如果车牌中没有数字 添加到非法集合当中 其他的添加到相应的限行集合当中
 */
public class CheckCarNum {
    public static void main(String[] args) {
        long start =System.currentTimeMillis();
        String[] strArr = {"沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3","沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3","沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3","沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3","沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3","沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3","沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3","沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3","沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3","沪A79K8", "沪B809P", "沪B8098C", "沪A8092", "沪A7097", "沪B70#7", "沪A45KKK", "沪A45K3", "沪A67R0",
                "沪A79S5", "沪A09K1", "沪A357T6", "沪A7SS4", "沪A79K0", "沪B79K3", "沪B3K3"};

        //周一到周五限行车辆的集合
        List<String> one = new ArrayList<>();
        List<String> two = new ArrayList<>();
        List<String> three = new ArrayList<>();
        List<String> four = new ArrayList<>();
        List<String> five = new ArrayList<>();

        //截取后的集合,但是未进行处理
        List<String> trueList = subStringArr(strArr);
        //车牌位数不正确的集合
        List<String> falseList = checkLength(trueList);
        //非法车牌号的集合
        List<String> feifaList = isTrue(trueList);
        //对正确的车牌进行限行判断
        for (int i = 0; i < trueList.size(); i++) {
            String s = trueList.get(i);
            //获取最后一个数字
            Integer lastNumber = getLastNumber(s);
            if (lastNumber == null) {//车牌号中没有数字
                feifaList.add(s);
            } else if (lastNumber == 1 || lastNumber == 6) {//周一限行
                one.add(s);
            } else if (lastNumber == 2 || lastNumber == 8) {//周二限行
                two.add(s);
            } else if (lastNumber == 3 || lastNumber == 9) {//周三限行
                three.add(s);
            } else if (lastNumber == 4 || lastNumber == 0) {//周四限行
                four.add(s);
            } else if (lastNumber == 5 || lastNumber == 7) {//周五限行
                five.add(s);
            }
        }
        System.out.println("以下为一周内每天限行的牌照: ");
        System.out.println("            周一限行: " + bianli(one));
        System.out.println("            周二限行: " + bianli(two));
        System.out.println("            周三限行: " + bianli(three));
        System.out.println("            周四限行: " + bianli(four));
        System.out.println("            周五限行: " + bianli(five));
        System.out.println("            ***************************");
        System.out.println("            车牌位数不正确: " + bianli(falseList));
        System.out.println("            车牌号非法: " + bianli(feifaList));
        System.out.println("pp："+strArr.length+(System.currentTimeMillis()-start));
    }


    //遍历集合输出
    public static String bianli(List<String> list) {
        StringBuilder sb = new StringBuilder("");
        for (String s : list) {
            String str = "沪" + s + "  ";
            sb.append(str);
        }
        return sb.toString();
    }

    //对车牌号将当地的首汉字解析
    public static List<String> subStringArr(String[] arr) {
        if (arr.length == 0) {
            System.out.println("数组中没有要检查的车牌");
            return null;
        } else {
            List<String> list = new ArrayList<>();
            //将数组中的元素遍历 截取处理
            for (int i = 0; i < arr.length; i++) {
                String s = arr[i];
                //对车牌号进行截取,然后添加到集合当中
                String str = s.substring(1, s.length());
                list.add(str);
            }
            return list;
        }
    }

    //检查车牌的长度
    public static List<String> checkLength(List<String> list) {
        List<String> falseList = new ArrayList<>();
        //对车牌号位数正确进行处理
        for (int i = list.size() - 1; i >= 0; i--) {
            String s = list.get(i);
            if (s.length() != 5) {//如果车牌位数不是五位
                list.remove(i);//删除不是五位的车牌
                falseList.add(s);//添加到车牌不是五位的集合
            }
        }
        return falseList;
    }


    //返回的是车牌号中最后一位数字,如果没有数字则返回null
    public static Integer getLastNumber(String substring) {
        Integer number = null;
        for (int i = substring.length() - 1; i > 0; i--) {
            char c = substring.charAt(i);
            //遍历字符串,判断是否是数字
            if (c >= '0' && c <= '9') {
                //截取后的char不能直接转换为Integer类型
                number = c - '0';
                return number;
            }
        }
        return null;
    }

    //根据正则表达式判断车牌的后五位是否是大写字母和数字的组合
    public static List<String> isTrue(List<String> list) {
        List<String> feifaList = new ArrayList<>();//非法的集合
        //正则校验公式
        String pattern = "^[A-Z0-9]{5}$";
        for (int i = list.size() - 1; i >= 0; i--) {
            String s = list.get(i);
            boolean flag = Pattern.matches(pattern, s);//对车牌进行非法字符判断,如果符合为true,否则为false
            if (!flag) {//
                list.remove(i);
                feifaList.add(s);
            }
        }
        return feifaList;
    }

}
