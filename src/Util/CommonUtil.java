package Util;

import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;

/**
 * @author xiaobaobao
 * @date 2020/10/4，21:07
 */
public class CommonUtil {

	public static int[] coverString2OneInt(String s) {
		JSONArray array = JSONArray.parseArray(s);
		int[] num = new int[array.size()];
		for (int i = array.size() - 1; i >= 0; i--) {
			num[i] = (int) array.get(i);
		}
		return num;
	}

	public static int[][] coverString2TwoInt(String s) {
		JSONArray array = JSONArray.parseArray(filter(s));
		int[][] num = new int[array.size()][];
		for (int i = array.size() - 1; i >= 0; i--) {
			num[i] = coverString2OneInt(array.get(i).toString());
		}
		return num;
	}

	public static char[] coverString2OneChar(String s) {
		JSONArray array = JSONArray.parseArray(s);
		char[] num = new char[array.size()];
		for (int i = array.size() - 1; i >= 0; i--) {
			num[i] = array.get(i).toString().charAt(0);
		}
		return num;
	}

	public static char[][] coverString2TwoChar(String s) {
		JSONArray array = JSONArray.parseArray(filter(s));
		char[][] num = new char[array.size()][];
		for (int i = array.size() - 1; i >= 0; i--) {
			num[i] = coverString2OneChar(array.get(i).toString());
		}
		return num;
	}

	public static String filter(String s) {
		s = s.replaceAll("\n", "");
		return s.replaceAll(" ", "");
	}

	public static void main(String[] args) {
		// System.out.println(Arrays.toString(coverString2OneInt("[4,1,-1,2,-1,2,3]")));
		System.out.println(Arrays.deepToString(coverString2TwoChar("[\n" +
																		   "  ['A','B','C','E'],\n" +
																		   "  ['S','F','C','S'],\n" +
																		   "  ['A','D','E','E']\n" +
																		   "]")));
	}
}
