package per.bmy.excel;

/**
 * @descript:
 * @author: bao meng yang <932824098@qq.com>
 * @create: 2021-08-26 09:13
 */

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Objects;
import org.apache.poi.util.IOUtils;

public class CharSetChangeUtil {

    //使用示例，从utf-16转换为utf-8
    public static void main(String[] args) throws Exception {
        String[] s = {
                "D:\\Users\\Desktop\\a",
        };
        for (String s1 : s) {
            // change(new File(s1), Charset.forName("GBK"), StandardCharsets.UTF_8);

            System.out.println(s1);

            File file = new File(s1);

            if (file.isDirectory()) {
                for (File f : Objects.requireNonNull(file.listFiles())) {
                    FileInputStream inputStream = new FileInputStream(f);
                    byte[] bytes = new byte[100];
                    System.out.println(inputStream.read(bytes));
                    System.out.println(Arrays.toString(bytes));
                }
            }
        }
    }

    /**
     * @param f           文件目录或文件
     * @param charSetFrom 原编码格式
     * @param charSetTo   转换后编码格式
     */
    public static void change(File f, Charset charSetFrom, Charset charSetTo) {
        if (f.isDirectory()) {
            for (File file : Objects.requireNonNull(f.listFiles())) {
                change(file, charSetFrom, charSetTo);
            }
            return;
        }
        try {
            InputStreamReader input = new InputStreamReader(new FileInputStream(f), charSetFrom);
            BufferedReader bReader = new BufferedReader(input);
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = bReader.readLine()) != null) {
                sb.append(s).append("\n");
            }
            bReader.close();
            String str = sb.toString();


            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), charSetTo)));
            out.append(str);
            out.flush();
            out.close();
            System.out.printf("%-20s 转换格式成功\n", f.getName());
        } catch (Exception e) {
            System.out.printf("%-20s 转换格式出错\n", f.getName());
        }
    }

    public static String codeString(File fileName) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(
                new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        String code = null;

        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
        IOUtils.closeQuietly(bin);
        return code;
    }

}