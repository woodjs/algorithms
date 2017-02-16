package bugong;

import java.util.Arrays;

public class Sort {

    /**
     * 冒泡排序，先确定最右侧（最大）的数据
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {

        int len = arr.length;
        int temp;

        for (int i = 0; i < len; i++) {

            for (int j = 0; j < len - 1 - i; j++) {  // i，已排序的数据个数，1，防止j + 1越界

                if (arr[j] > arr[j + 1]) {

                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        return arr;
    }

    /**
     * 选择排序，先确定最左侧（最小）的数据
     *
     * @param arr
     * @return
     */
    public static int[] selectionSort(int[] arr) {

        int len = arr.length;
        int minIndex, temp;

        for (int i = 0; i < len - 1; i++) {  // 1，防止minIndex + 1越界

            minIndex = i;

            for (int j = minIndex + 1; j < len; j++) {

                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }

        return arr;
    }

    /**
     * 插入排序
     *
     * @param arr
     * @return
     */
    public static int[] insertionSort(int[] arr) {

        int len = arr.length;
        int temp;

        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {

                temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }
        }

        return arr;
    }

    /**
     * 希尔排序
     *
     * @param arr
     * @return
     */
    public static int[] hillSort(int[] arr) {

        int len = arr.length;
        int gap = 1;
        int temp;

        while (gap < len / 3) {  // 动态定义间隔序列
            gap = gap * 3 + 1;
        }

        for (; gap > 0; gap = (int) Math.floor(gap / 3)) {

            for (int i = gap; i < len; i++) {
                temp = arr[i];

                for (int j = i - gap; j >= 0 && arr[j] > temp; j -= gap) {

                    temp = arr[j + gap];
                    arr[j + gap] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        return arr;
    }

    /**
     * 归并排序
     *
     * @param arr
     * @return
     */
    public static int[] mergeSort(int[] arr) {

        int len = arr.length;
        int temp;

        if (len < 2) {
            return arr;
        } else if (len == 2) {

            if (arr[0] > arr[1]) {

                temp = arr[1];
                arr[1] = arr[0];
                arr[0] = temp;
            }
            return arr;
        }

        int midIndex = len / 2 - 1;

        int[] arr1 = Arrays.copyOfRange(arr, 0, midIndex + 1);
        int[] arr2 = Arrays.copyOfRange(arr, midIndex + 1, len);

        return merge(mergeSort(arr1), mergeSort(arr2));
    }

    private static int[] merge(int[] arr1, int[] arr2) {

        int[] arr = new int[arr1.length + arr2.length];
        int len = arr.length;
        int i = 0;
        int j = 0;

        for (int k = 0; k < len; k++) {

            if (i < arr1.length && j < arr2.length) {

                if (arr1[i] < arr2[j]) {

                    arr[k] = arr1[i];
                    i++;
                } else {

                    arr[k] = arr2[j];
                    j++;
                }
            } else if (i < arr1.length) {

                arr[k] = arr1[i];
                i++;
            } else if (j < arr2.length) {

                arr[k] = arr2[j];
                j++;
            }
        }

        return arr;
    }

    /**
     * 快速排序
     *
     * @param arr
     * @return
     */
    public static int[] quickSort(int[] arr, int left, int right) {

        int partitionIndex;

        while (left < right) {

            partitionIndex = partition(arr, left, right);

            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }

        return arr;
    }

    private static int partition(int[] arr, int left, int right) {

        int pivot = left;
        int index = pivot + 1;
        int temp;

        for (int i = index; i <= right; i++) {

            if (arr[i] < arr[pivot]) {
                temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;

                index++;
            }
        }

        temp = arr[pivot];
        arr[pivot] = arr[index - 1];
        arr[index - 1] = temp;

        return index - 1;
    }

    public static void main(String[] args) {

        int[] arr = Helper.createArray(4, 1, 100);

        System.out.println("original array: " + Arrays.toString(arr));

        bubbleSort(arr);
        System.out.println("bubble sort: " + Arrays.toString(arr));

        Helper.mixArrary(arr);
        System.out.println("mixed array: " + Arrays.toString(arr));

        selectionSort(arr);
        System.out.println("selection sort: " + Arrays.toString(arr));

        Helper.mixArrary(arr);
        System.out.println("mixed array: " + Arrays.toString(arr));

        insertionSort(arr);
        System.out.println("insertion sort: " + Arrays.toString(arr));

        Helper.mixArrary(arr);
        System.out.println("mixed array: " + Arrays.toString(arr));

        hillSort(arr);
        System.out.println("hill sort: " + Arrays.toString(arr));

        Helper.mixArrary(arr);
        System.out.println("mixed array: " + Arrays.toString(arr));

        System.out.println("merge sort: " + Arrays.toString(mergeSort(arr)));

        Helper.mixArrary(arr);
        System.out.println("mixed array: " + Arrays.toString(arr));

        quickSort(arr, 0, arr.length - 1);
        System.out.println("quick sort: " + Arrays.toString(arr));
    }
}