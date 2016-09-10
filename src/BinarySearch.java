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
				beginIndex = midIndex + 1;
			} else if (arr[midIndex] > val) {
				endIndex = midIndex - 1;
			} else {
				return midIndex;
			}
		}
		
		return -1;
	}
	
	public static void main (String[] args) {
		
		@SuppressWarnings("deprecation")
		int[] dataList = In.readInts(args[0]);
		String dataStr = "";
		
		Arrays.sort(dataList); 
		
		for (int i = 0; i < dataList.length; i ++) {
			dataStr += dataList[i] + " ";
		}
		
		StdOut.println("current data list: " + dataStr);
		
		while (!StdIn.isEmpty()) {
		
			int value = StdIn.readInt();
			
			int temp = rank(value, dataList);
		
			if (temp < 0) {
				StdOut.println(value + " is not found!");
			} else { 
				StdOut.println(value + " is found at index " + temp);
			}
		}
	}
	
}
