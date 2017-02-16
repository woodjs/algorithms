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

        for (int i = 0; i < len; i++) {

            for (int j = 1; j < len - i; j++) {  // i，已排序的数据个数

                if (arr[j - 1] > arr[j]) {

                    Helper.swap(arr, j - 1, j);
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
        int minIndex;

        for (int i = 0; i < len - 1; i++) {  // 1，免去最后一次循环

            minIndex = i;

            for (int j = i + 1; j < len; j++) {

                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            Helper.swap(arr, i, minIndex);
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

        for (int i = 0; i < len - 1; i++) {  // 1，防止arr[i + 1]越界

            for (int j = i + 1; j > 0 && arr[j] < arr[j - 1]; j--) {

                Helper.swap(arr, j - 1, j);
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


        while (gap < len / 3) {  // 动态定义间隔序列
            gap = gap * 3 + 1;
        }

        for (; gap > 0; gap = (int) Math.floor(gap / 3)) {

            for (int i = gap; i < len; i++) {

                for (int j = i - gap; j >= 0 && arr[j] > arr[i]; j -= gap) {

                    Helper.swap(arr, j, j + gap);
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

        if (len < 2) {
            return arr;
        } else if (len == 2) {

            if (arr[0] > arr[1]) {

                Helper.swap(arr, 0, 1);
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

        if (left > right) return arr;

        partitionIndex = partition(arr, left, right);

        if (left < partitionIndex - 1)
            quickSort(arr, left, partitionIndex - 1);
        if (partitionIndex + 1 < right)
            quickSort(arr, partitionIndex + 1, right);

        return arr;
    }

    private static int partition(int[] arr, int left, int right) {

        int pivot = left;
        int index = pivot + 1;

        for (int i = index; i <= right; i++) {

            if (arr[i] < arr[pivot]) {

                Helper.swap(arr, i, index);
                index++;
            }
        }

        Helper.swap(arr, pivot, index - 1);

        return index - 1;
    }

    public static void main(String[] args) {

        long startTime, endTime;

        int[] arr = Helper.createArray(10000, 1, 100);

//        System.out.println("original array: " + Arrays.toString(arr));

        startTime = System.currentTimeMillis();
        bubbleSort(arr);
        endTime = System.currentTimeMillis();
//        System.out.println("bubble sort result: " + Arrays.toString(arr));
        System.out.println("bubble sort spend: " + (endTime - startTime) + "ms");

        Helper.mixArray(arr);
        startTime = System.currentTimeMillis();
        selectionSort(arr);
        endTime = System.currentTimeMillis();
//        System.out.println("selection sort: " + Arrays.toString(arr));
        System.out.println("selection sort spend: " + (endTime - startTime) + "ms");

        Helper.mixArray(arr);
        startTime = System.currentTimeMillis();
        insertionSort(arr);
        endTime = System.currentTimeMillis();
//        System.out.println("insertion sort: " + Arrays.toString(arr));
        System.out.println("insertion sort spend: " + (endTime - startTime) + "ms");

        Helper.mixArray(arr);
        startTime = System.currentTimeMillis();
        hillSort(arr);
        endTime = System.currentTimeMillis();
//        System.out.println("hill sort: " + Arrays.toString(arr));
        System.out.println("hill sort spend: " + (endTime - startTime) + "ms");

        Helper.mixArray(arr);
        startTime = System.currentTimeMillis();
        mergeSort(arr);
        endTime = System.currentTimeMillis();
//        System.out.println("merge sort: " + Arrays.toString(mergeSort(arr)));
        System.out.println("merge sort spend: " + (endTime - startTime) + "ms");

        Helper.mixArray(arr);
        startTime = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        endTime = System.currentTimeMillis();
//        System.out.println("quick sort: " + Arrays.toString(arr));
        System.out.println("quick sort spend: " + (endTime - startTime) + "ms");
    }
}