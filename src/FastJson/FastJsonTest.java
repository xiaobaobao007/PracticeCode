package FastJson;

import org.junit.Test;

import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;

/**
 * @author xiaobaobao
 * @date 2020/7/27，15:48
 */
public class FastJsonTest {
	@Test
	public void q() {
		System.out.println(Arrays.deepToString(convertArrayToListListItems("[[1,0,1],[2,5,3],[3,0,10]]", 3)));
	}

	//[[1,0,1],[2,5,3],[3,0,10]]
	public static int[][] convertArrayToListListItems(String str,int size) {
		JSONArray jsonArray = JSONArray.parseArray(str);
		int[][] ints = new int[jsonArray.size()][size];
		for (int i = jsonArray.size() - 1; i >= 0; i--) {
			Object[] objects = jsonArray.getJSONArray(i).toArray();
			for (int j = objects.length - 1; j >= 0; j--) {
				ints[i][j] = (int) objects[j];
			}
		}
		return ints;
	}

}
