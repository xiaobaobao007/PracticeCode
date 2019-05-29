package smallCalculator;

import java.util.Scanner;

/**
 * 小型计算器
 */
public class Main {
    private long type = 10;
    private long TenNum;
    private int operation = -1;

    private Main() {
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        int nums = 0;
        while (true) {
            if (nums != 0 && ++num > nums) {
                break;
            }
            String s = scanner.nextLine();
            if (nums == 0) {
                num = 0;
                nums = Integer.valueOf(s);
                continue;
            }
            String[] b = s.split(" ");
            switch (b[0]) {
                case "NUM":
                    setNum(b[1]);
                    break;
                case "ADD":
                    setOperation(0);
                    break;
                case "SUB":
                    setOperation(1);
                    break;
                case "MUL":
                    setOperation(2);
                    break;
                case "DIV":
                    setOperation(3);
                    break;
                case "MOD":
                    setOperation(4);
                    break;
                case "CHANGE":
                    changeType(b[1]);
                    break;
                case "EQUAL":
                    sout();
                    break;
                case "CLEAR":
                    clean();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    private void setOperation(int operation) {
        this.operation = operation;
    }

    private void setNum(String num) {
        if (operation == -1) {
            this.TenNum = Long.valueOf(num);
        } else {
            switch (operation) {
                case 0:
                    add(num);
                    break;
                case 1:
                    sub(num);
                    break;
                case 2:
                    mul(num);
                    break;
                case 3:
                    div(num);
                    break;
                case 4:
                    mod(num);
                    break;
            }
            operation = -1;
        }
    }

    private void sout() {
        System.out.println(tenToChange());
    }

    private void clean() {
        type = 10;
        TenNum = 0;
    }

    private void add(String num) {
        TenNum += changeToTen(num);
    }

    private void sub(String num) {
        TenNum -= changeToTen(num);
    }

    private void mul(String num) {
        TenNum *= changeToTen(num);
    }

    private void div(String num) {
        TenNum /= changeToTen(num);
    }

    private void mod(String num) {
        TenNum %= changeToTen(num);
    }

    private void changeType(String type) {
        this.type = changeToTen(type);
    }

    private StringBuffer tenToChange() {
        StringBuffer s = new StringBuffer();
        long num = TenNum;
        while (num > 0) {
            s.insert(0, getChar(num % type));
            num /= type;
        }
        return s;
    }

    private long changeToTen(String num) {
        if (type == 10) return Long.valueOf(num);
        int length = num.length();
        long a = 0;
        for (int i = length - 1, j = 0; i >= 0; i--, j++) {
            a += (long) (Math.pow(type, j) * getInt(num.charAt(i)));
        }
        return a;
    }

    private int getInt(char c) {
        return c <= '9' ? c - '0' : c - 55;
    }

    private char getChar(long i) {
        if (0 <= i && i <= 9) return (char) ('0' + i);
        else return (char) (55 + i);
    }
}