package Arithmetic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LinesNum {

    //    String path = "C:\\Users\\Administrator\\Desktop\\Game-Server\\src";
    String path = "E:\\PracticeCode\\src";
    static Set<String> ignoreDirectorySet = new HashSet<>();

    static {
        ignoreDirectorySet.add("E:\\PracticeCode\\src\\NIO\\PB");
        ignoreDirectorySet.add("E:\\PracticeCode\\src\\com\\intion\\app\\packets");
    }

    int lins = 0;//总行数
    int fileNums = 0;//文件数量

    LinesNum() throws IOException {
        recursive(path);
        System.err.println("fileNums:" + fileNums);
        System.err.println("lines:" + lins);
    }

    public void recursive(String s) throws IOException {
        File file = new File(s);
        if (file.isDirectory()) {
            if (ignoreDirectorySet.contains(file.getPath())) {
                return;
            }
            File[] files = file.listFiles();
            for (File oneFile : files) {
                recursive(oneFile.getCanonicalPath());
            }
            return;
        }
        if (!file.getName().endsWith(".java")) {
            return;
        }
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            fileNums++;
            int i = 0;
            while (in.readLine() != null) {
                lins++;
                i++;
            }
            System.out.printf("%-35s  %5d行\n", file.getName(), i);//测试输出每个文件的行数
        }
    }

    public static void main(String[] args) throws IOException {
        new LinesNum();
    }
}
