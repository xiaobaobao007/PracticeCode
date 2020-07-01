package Arithmetic;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileStream {

	public static void main(String[] args) throws IOException {
		FileStream main = new FileStream();
		System.out.println("================Stream");
		main.Stream();
		System.out.println();
		System.out.println("================Reader");
		main.Reader();
		System.out.println();
		System.out.println("================StreamReader");
		main.StreamReader();
		System.out.println();
		System.out.println("================BufferedStream");
		main.BufferedStream();
		System.out.println();
		System.out.println("================BufferedReader");
		main.BufferedReader();
	}

	String path = "./src/resouse/sord.txt";

	/**
	 * 一次性读取多大的文件
	 *
	 * @throws IOException
	 */
	public void Stream() throws IOException {
		FileInputStream stream = new FileInputStream(path);
		byte[] bytes = new byte[2018];
		int n = -1;
		while ((n = stream.read(bytes, 0, bytes.length)) != -1) {
			String str = new String(bytes, 0, n, StandardCharsets.UTF_8);
			System.out.print(str + "//");
		}
		stream.close();
	}

	/**
	 * 一个字节的读取
	 *
	 * @throws IOException
	 */
	public void Reader() throws IOException {
		Reader in = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
		int len = -1;
		while ((len = in.read()) != -1) {
			System.out.print(len + "//");
		}
		in.close();
	}

	/**
	 * 建议使用字符转换
	 *
	 * @throws IOException
	 */
	public void StreamReader() throws IOException {
		InputStreamReader in = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
		int len = -1;
		while ((len = in.read()) != -1) {
			System.out.print(len + "//");
		}
		in.close();
	}

	/**
	 * 效率更高
	 *
	 * @throws IOException
	 */
	public void BufferedStream() throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));
		byte[] bytes = new byte[2048];
		int n = -1;
		while ((n = in.read(bytes, 0, bytes.length)) != -1) {
			//转换成字符串
			String str = new String(bytes, 0, n, StandardCharsets.UTF_8);
			System.out.print(str + "//");
		}
		in.close();
	}

	/**
	 * 直接读取一行
	 *
	 * @throws IOException
	 */
	public void BufferedReader() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
		String str = null;
		while ((str = in.readLine()) != null) {
			System.out.print(str + "//");
		}
		in.close();
	}

	/**
	 * 直接读取一行
	 *
	 * @throws IOException
	 */
	public void BufferedReader2() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(path));
		String str = null;
		while ((str = in.readLine()) != null) {
			System.out.print(str + "//");
		}
		in.close();
	}

	public void write() throws IOException {
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("d:\\2.txt"));
		byte[] bytes = new byte[2048];
		int n = -1;
		out.write(bytes, 0, n);
		out.flush();
		out.close();
	}
}
