import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class test {
    public static void main(String[] args) throws IOException {
//        BigDecimal bigDecimal = new BigDecimal("123");
//        System.out.println(bigDecimal.unscaledValue());
//        System.out.println(bigDecimal.compareTo(new BigDecimal("128")));
//        float a = (float) (1.0 / 2.0);
//        System.out.println(a);
//        System.out.println(Math.sin(Math.PI * 90 / 180));
//        int b=10;
//        System.out.println(-b);
//        float c=90f;
//        System.out.printf("%.2f",c);
//        test test = new test();
        int[] a={1,2,3,4,5};
        int[] b = a;
        b=a;
        for (int i : b) {
            System.out.println(i);
        }
    }

    private test() throws IOException {
        int length = 1000;
        int[] num = new int[length];
        for (int i = 0; i < length; i++) {
            num[i] = i;
        }
        Random random = new Random();
        for (int i = 0; i < length / 2; i++) {
            int a = random.nextInt(length);
            int b = random.nextInt(length);
            num[a] = num[a] ^ num[b];
            num[b] = num[a] ^ num[b];
            num[a] = num[a] ^ num[b];
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(num[i] + ",");
        }
        byte[] bytes = stringBuffer.toString().substring(0, stringBuffer.length() - 1).getBytes(StandardCharsets.ISO_8859_1);
        FileOutputStream fileOut = new FileOutputStream("./src/resouse/sord.text");
        BufferedOutputStream out = new BufferedOutputStream(fileOut);
        out.write(bytes);
        out.flush();
        fileOut.close();
        out.close();

    }

    public void read() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("./src/resouse/sord.text"));
        String str = in.readLine();
        String[] split = str.split(",");
        int[] qq = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            qq[i] = Integer.parseInt(split[i]);
        }
        in.close();
    }
}