package bugong;

/**
 * 二分查找
 */
public class BinarySearch {

    public static int binarySearch(int[] arr, int item) {

        arr = Sort.quickSort(arr, 0, arr.length - 1);

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;  // int mid = (left + right) / 2;  可能出现溢出错误

            if (arr[mid] == item) return mid;

            if (arr[mid] > item) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        long startTime, endTime;

        int[] arr = Helper.createArray(10000, 1, 10000);

        startTime = System.currentTimeMillis();
        System.out.println("binary search result: " + binarySearch(arr, 876));
        endTime = System.currentTimeMillis();
        System.out.println("binary search spend: " + (endTime - startTime) + "ms");
    }
}
