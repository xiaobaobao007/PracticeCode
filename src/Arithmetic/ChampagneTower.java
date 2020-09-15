package Arithmetic;

class ChampagneTower {

	public static void main(String[] args) {
		System.out.println(new ChampagneTower().champagneTower(2, 2, 0));
	}

	public double champagneTower(int poured, int query_row, int query_glass) {
		double[] array = new double[query_row + 1];
		array[0] = poured;
		for (int i = 0; i < query_row; i++) {
			for (int j = i; j >= 0; j--) {
				if (j == i && array[j] <= 1.0) {
					array[j] = array[j + 1] = 0;
				} else if (array[j] > 1.0) {
					array[j + 1] += (array[j] - 1.0) / 2;
					array[j] = (array[j] - 1.0) / 2;
				} else {
					array[j] = 0;
				}

			}
		}
		if (array[query_glass] >= 1) {
			return 1.0;
		}
		return array[query_glass];
	}
}