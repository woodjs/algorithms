import java.util.Arrays;

public class StaticSetOfInt {

	private int[] list;

	public StaticSetOfInt(int[] arr) {

		list = new int[arr.length];

		for (int i = 0; i < arr.length; i++) {
			list[i] = arr[i];
		}

		Arrays.sort(list);
	}

	public boolean contains(int value) {

		return rank(value) != -1;
	}

	public int rank(int value) {

		int beginIndex = 0;
		int endIndex = list.length - 1;

		while (beginIndex <= endIndex) {
			int midIndex = beginIndex + (endIndex - beginIndex) / 2;
			if (value < list[midIndex]) {
				endIndex = midIndex - 1;
			} else if (value > list[midIndex]) {
				beginIndex = midIndex + 1;
			} else {
				return midIndex;
			}
		}

		return -1;
	}

}