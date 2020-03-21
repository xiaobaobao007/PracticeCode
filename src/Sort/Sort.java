package Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

abstract class SortChild {

	private int level;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "SortChild{" +
				"level=" + level +
				'}';
	}
}

/**
 * 排序，找出前几名，降序
 */
public class Sort<E extends SortChild> {

	//当天容量大小
	private int size;
	//容量限制
	private int limitNum;
	//最小阈值,用于优化排序
	private int levelMin;
	//数据
	private SortChild[] array;

	Sort(int limitNum) {
		this.limitNum = limitNum;
		array = new SortChild[limitNum];
	}

	@SuppressWarnings("unchecked")
	public List<E> getAll() {
		ArrayList<E> list = new ArrayList<>();
		for (Object o : array) {
			list.add((E) o);
		}
		return list;
	}

	public void add(E e) {
		if (size == 0) {
			array[size++] = e;
		} else if (size < limitNum) {
			if (!insert(e)) {
				array[size++] = e;
			} else {
				size++;
			}
		} else if (e.getLevel() > levelMin) {
			insert(e);
			levelMin = array[size - 1].getLevel();
		}
	}

	private boolean insert(E e) {
		for (int i = 0; i < size; i++) {
			if (array[i].getLevel() <= e.getLevel()) {
				for (int j = Math.min(size, limitNum - 1); j > i; j--) {
					array[j] = array[j - 1];
				}
				array[i] = e;
				return true;
			}
		}
		return false;
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			array[i] = null;
		}
		size = 0;
	}

	@Override
	public String toString() {
		return "Sort{" +
				"size=" + size +
				", limitNum=" + limitNum +
				", levelMin=" + levelMin +
				", array=" + Arrays.toString(array) +
				'}';
	}

	static class Demo extends SortChild {
		public Demo(int level) {
			super.setLevel(level);
		}
	}

	public static void main(String[] args) {
		Sort<Demo> sort = new Sort<Demo>(5);
		Random random = new Random();
		for (int i = 1; i <= 30; i++) {
			sort.add(new Demo(random.nextInt(100)));
			System.out.println(sort.toString());
			if (i % 10 == 0) {
				System.out.println(sort.toString() + "!!!!!!!!");
				sort.clear();
			}
		}
	}

}
