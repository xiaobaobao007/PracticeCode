package Arithmetic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LinesNum {

	//    String path = "C:\\Users\\Administrator\\Desktop\\Game-Server\\src";
	String path = "C:\\Users\\Administrator\\Desktop\\Game-Client\\src";

	int lins = 0;//总行数
	int fileNums = 0;//文件数量

	LinesNum() throws IOException {
		recursive(path);
		System.out.println("fileNums:" + fileNums);
		System.out.println("lins:" + lins);
	}

	public void recursive(String s) throws IOException {
		File file = new File(s);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File oneFile : files) {
				recursive(oneFile.getCanonicalPath());
			}
			return;
		}
		BufferedReader in = new BufferedReader(new FileReader(file));
		fileNums++;
		while (in.readLine() != null) {
			lins++;
		}
//        System.out.printf("%s:%d行\n",file.getName(),i);//测试输出每个文件的行数
		in.close();
	}

	public static void main(String[] args) throws IOException {
		new LinesNum();
	}
}
