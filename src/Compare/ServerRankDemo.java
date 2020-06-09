package Compare;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author xiaobaobao
 * @date 2020/6/9，11:21
 */
public class ServerRankDemo {

	@Test
	public void main() {
		ServerRank serverRank = new ServerRank();
		serverRank.set(100, 3);
		serverRank.set(200, 6);
		serverRank.set(300, 9);

		serverRank.resize();

		Assert.assertSame(-1, serverRank.getIndexByParam(50, 60));
		Assert.assertSame(3, serverRank.getIndexByParam(50, 100));
		Assert.assertSame(3, serverRank.getIndexByParam(50, 101));
		Assert.assertSame(6, serverRank.getIndexByParam(50, 200));
		Assert.assertSame(6, serverRank.getIndexByParam(50, 201));
		Assert.assertSame(9, serverRank.getIndexByParam(50, 300));
		Assert.assertSame(9, serverRank.getIndexByParam(50, 301));

		Assert.assertSame(-1, serverRank.getIndexByParam(100, 60));
		Assert.assertSame(-1, serverRank.getIndexByParam(100, 100));
		Assert.assertSame(-1, serverRank.getIndexByParam(100, 101));
		Assert.assertSame(6, serverRank.getIndexByParam(100, 200));
		Assert.assertSame(6, serverRank.getIndexByParam(100, 201));
		Assert.assertSame(9, serverRank.getIndexByParam(100, 300));
		Assert.assertSame(9, serverRank.getIndexByParam(100, 301));

		Assert.assertSame(-1, serverRank.getIndexByParam(101, 60));
		Assert.assertSame(-1, serverRank.getIndexByParam(101, 100));
		Assert.assertSame(-1, serverRank.getIndexByParam(101, 101));
		Assert.assertSame(6, serverRank.getIndexByParam(101, 200));
		Assert.assertSame(6, serverRank.getIndexByParam(101, 201));
		Assert.assertSame(9, serverRank.getIndexByParam(101, 300));
		Assert.assertSame(9, serverRank.getIndexByParam(101, 301));

		Assert.assertSame(-1, serverRank.getIndexByParam(200, 60));
		Assert.assertSame(-1, serverRank.getIndexByParam(200, 100));
		Assert.assertSame(-1, serverRank.getIndexByParam(200, 101));
		Assert.assertSame(-1, serverRank.getIndexByParam(200, 200));
		Assert.assertSame(-1, serverRank.getIndexByParam(200, 201));
		Assert.assertSame(9, serverRank.getIndexByParam(200, 300));
		Assert.assertSame(9, serverRank.getIndexByParam(200, 301));

		Assert.assertSame(-1, serverRank.getIndexByParam(201, 60));
		Assert.assertSame(-1, serverRank.getIndexByParam(201, 100));
		Assert.assertSame(-1, serverRank.getIndexByParam(201, 101));
		Assert.assertSame(-1, serverRank.getIndexByParam(201, 200));
		Assert.assertSame(-1, serverRank.getIndexByParam(201, 201));
		Assert.assertSame(9, serverRank.getIndexByParam(201, 300));
		Assert.assertSame(9, serverRank.getIndexByParam(201, 301));

		Assert.assertSame(-1, serverRank.getIndexByParam(300, 60));
		Assert.assertSame(-1, serverRank.getIndexByParam(300, 100));
		Assert.assertSame(-1, serverRank.getIndexByParam(300, 101));
		Assert.assertSame(-1, serverRank.getIndexByParam(300, 200));
		Assert.assertSame(-1, serverRank.getIndexByParam(300, 201));
		Assert.assertSame(-1, serverRank.getIndexByParam(300, 300));
		Assert.assertSame(-1, serverRank.getIndexByParam(300, 301));

		Assert.assertSame(-1, serverRank.getIndexByParam(301, 60));
		Assert.assertSame(-1, serverRank.getIndexByParam(301, 100));
		Assert.assertSame(-1, serverRank.getIndexByParam(301, 101));
		Assert.assertSame(-1, serverRank.getIndexByParam(301, 200));
		Assert.assertSame(-1, serverRank.getIndexByParam(301, 201));
		Assert.assertSame(-1, serverRank.getIndexByParam(301, 300));
		Assert.assertSame(-1, serverRank.getIndexByParam(301, 301));
	}

	static class ServerRank {
		int index;
		int[] paramArray = new int[100];
		int[] idArray = new int[100];

		public void set(int param, int id) {
			paramArray[index] = param;
			idArray[index++] = id;
		}

		public void resize() {
			int[] array = new int[index];
			System.arraycopy(paramArray, 0, array, 0, index);
			paramArray = array;

			array = new int[index];
			System.arraycopy(idArray, 0, array, 0, index);
			idArray = array;
		}

		public int getIndexByParam(int startParam, int endParam) {
			int head = 0;
			int tail = index - 1;
			while (head < tail) {
				if (head + 1 == tail) {
					if (paramArray[tail] <= endParam && paramArray[tail] > startParam) {
						return idArray[tail];
					}
					if (paramArray[head] <= endParam && paramArray[head] > startParam) {
						return idArray[head];
					}
					return -1;
				}
				int mid = (head + tail) / 2;
				if (paramArray[mid] < endParam) {
					head = mid;
				} else if (paramArray[mid] > endParam) {
					tail = mid;
				} else if (startParam < paramArray[mid]) {
					return idArray[mid];
				} else {
					return -1;
				}
			}
			return -1;
		}
	}

}