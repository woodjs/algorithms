import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BinarySearch {
	
	public static int rank (int val, int[] arr) {
		
		int beginIndex = 0;
		int endIndex = arr.length - 1;
		
		while (beginIndex <= endIndex) {
			int midIndex = beginIndex + (endIndex - beginIndex) / 2;
			
			if (arr[midIndex] < val) {
				endIndex = midIndex - 1;
			} else if (arr[midIndex] > val) {
				beginIndex = midIndex + 1;
			} else {
				return midIndex;
			}
		}
		
		return -1;
	}
	
	public static void main (String[] args) {
		
		@SuppressWarnings("deprecation")
		int[] dataList = In.readInts(args[0]);
		
		Arrays.sort(dataList);  // 归并排序
		
		while (!StdIn.isEmpty()) {
		
			int value = StdIn.readInt();
		
			if (rank(value, dataList) < 0) {  // 不存在时，向控制台输出
			
				StdOut.println(value);
			}
		}
		
	}
	
}
