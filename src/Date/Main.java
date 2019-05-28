package Date;

import java.util.Date;

public class Main {

    Main() {
        long now=System.currentTimeMillis();
        Date date = new Date(now);
        System.out.println(date.toString());
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}