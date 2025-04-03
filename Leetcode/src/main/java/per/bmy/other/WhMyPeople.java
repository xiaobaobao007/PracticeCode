package per.bmy.other;

/**
 * @author xiaobaobao
 * @date 2020/4/6，21:21
 * <p>
 * 幼儿园45个小朋友围成一圈。从班长开始顺时针1报数，报到3出列，
 * 下一个从1开始，来回这样报数后，最后剩下的是班长顺时针方向旁边第几个同学？
 */
public class WhMyPeople {

    public static void main(String[] args) {
        new WhMyPeople().doIt(5);
    }

    public void doIt(int num) {
        int[] people = new int[num];
        for (int i = 0, j = num - 1; i < num; j = i++) {
            people[j] = i;
        }
        int size = num;
        int point = 0;
        while (--size > 0) {
            int last = people[point];
            point = people[last] = people[people[last]];
        }
        System.out.println(point);
    }
}
