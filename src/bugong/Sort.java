package bugong;

import java.util.Arrays;
import java.util.ArrayList;

public class Sort {

    /**
     * 冒泡排序，先确定右侧数据
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {

        int len = arr.length;

        for (int i = 0; i < len; i++) {

            for (int j = 0; j < len - i - 1; j++) {  // i，已排序的数据个数，1，防止j + 1越界

                if (arr[j] > arr[j + 1]) {

                    Helper.swap(arr, j, j + 1);
                }
            }
        }

        return arr;
    }

    /**
     * 选择排序，先确定左侧数据
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
     * 插入排序，先确定左侧数据
     *
     * @param arr
     * @return
     */
    public static int[] insertionSort(int[] arr) {

        int len = arr.length;

        for (int i = 0; i < len - 1; i++) {  // 1，防止arr[j]越界

            for (int j = i + 1; j >= 1 && arr[j] < arr[j - 1]; j--) {  // 1，防止arr[j - 1]越界

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
    public static int[] shellSort(int[] arr) {

        int len = arr.length;
        int gap = 1;

        while (gap < len / 3) {  // 动态定义间隔序列
            gap = gap * 3 + 1;
        }

        for (; gap > 0; gap = (int) Math.floor(gap / 3)) {

            for (int i = gap - 1; i < len - 1; i++) {  // 1，防止arr[j]越界

                for (int j = i + 1; j >= gap && arr[j] < arr[j - gap]; j -= gap) {  // gap，防止arr[j - gap]越界

                    Helper.swap(arr, j - gap, j);
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

        int midIndex = len / 2;

        int[] arr1 = Arrays.copyOfRange(arr, 0, midIndex);
        int[] arr2 = Arrays.copyOfRange(arr, midIndex, len);

        return merge(mergeSort(arr1), mergeSort(arr2));
    }

    private static int[] merge(int[] arr1, int[] arr2) {

        int len = arr1.length + arr2.length;
        int[] arr = new int[len];

        int i = 0;
        int j = 0;

        for (int k = 0; k < len; k++) {

            if (i >= arr1.length) {

                arr[k] = arr2[j];
                j++;
            } else if (j >= arr2.length) {

                arr[k] = arr1[i];
                i++;
            } else if (arr1[i] < arr2[j]) {

                arr[k] = arr1[i];
                i++;
            } else {

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

        if (arr.length == 0) return arr;

        partitionIndex = partition(arr, left, right);

        if (left < partitionIndex - 1)
            quickSort(arr, left, partitionIndex - 1);
        if (partitionIndex + 1 < right)
            quickSort(arr, partitionIndex + 1, right);

        return arr;
    }

    private static int partition(int[] arr, int left, int right) {

        int pivot = left;
        int next = left + 1;

        for (int i = left + 1; i <= right; i++) {

            if (arr[i] < arr[pivot]) {

                Helper.swap(arr, i, next);
                next++;
            }
        }

        Helper.swap(arr, pivot, next - 1);

        return next - 1;
    }

    /**
     * 计数排序
     * 数据必须是有确定范围的非负整数
     *
     * @param arr
     * @param maxValue
     * @return
     */
    public static int[] countingSort(int[] arr, int maxValue) {

        int len = arr.length;
        int[] bucket = new int[maxValue + 1];
        int sortedIndex = 0;

        for (int i = 0; i < len; i++) {

            bucket[arr[i]]++;
        }

        for (int j = 0; j < bucket.length; j++) {

            while (bucket[j] > 0) {

                arr[sortedIndex++] = j;
                bucket[j]--;
            }
        }

        return arr;
    }

    /**
     * 桶排序
     *
     * @param arr
     * @param bucketRange 每个桶中，最大值和最小值的差值
     * @return
     */
    public static int[] bucketSort(int[] arr, int bucketRange) {

        int len = arr.length;
        int minValue = arr[0];
        int maxValue = arr[0];
        int i;

        for (i = 0; i < len; i++) {

            if (arr[i] < minValue) {

                minValue = arr[i];
            } else if (arr[i] > maxValue) {

                maxValue = arr[i];
            }
        }

        int bucketCount = (int) Math.floor((maxValue - minValue) / bucketRange) + 1;  // 桶的数量

        ArrayList[] buckets = new ArrayList[bucketCount];

        for (i = 0; i < buckets.length; i++) {

            buckets[i] = new ArrayList();
        }

        for (i = 0; i < len; i++) {

            int bucketIndex = (int) Math.floor((arr[i] - minValue) / bucketRange);

            buckets[bucketIndex].add(arr[i]);
        }

        int sortedIndex = 0;

        for (i = 0; i < buckets.length; i++) {

            int[] bucket = new int[buckets[i].size()];

            for (int j = 0; j < buckets[i].size(); j++) {

                bucket[j] = (int) buckets[i].get(j);
            }

            insertionSort(bucket);

            for (int j = 0; j < bucket.length; j++) {

                arr[sortedIndex++] = bucket[j];
            }
        }

        return arr;
    }

    /**
     * 基数排序
     *
     * @param arr
     * @param maxDigit 最大值数据位数
     * @return
     */
    public static int[] radixSort(int[] arr, int maxDigit) {

        int len = arr.length;
        int mod = 10;
        int dev = 1;

        for (int i = 0; i < maxDigit; i++, mod *= 10, dev *= 10) {

            ArrayList[] counter = new ArrayList[10];

            for (int j = 0; j < len; j++) {

                int bucket = arr[j] % mod / dev;  // 123 % 10 / 1 => 3

                if (counter[bucket] == null)

                    counter[bucket] = new ArrayList();

                counter[bucket].add(arr[j]);
            }

            int pos = 0;

            for (int j = 0; j < counter.length; j++) {

                if (counter[j] != null) {

                    for (int k = 0; k < counter[j].size(); k++) {

                        arr[pos++] = (int) counter[j].get(k);
                    }
                }
            }
        }

        return arr;
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
        shellSort(arr);
        endTime = System.currentTimeMillis();
//        System.out.println("shell sort: " + Arrays.toString(arr));
        System.out.println("shell sort spend: " + (endTime - startTime) + "ms");

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

        Helper.mixArray(arr);
        startTime = System.currentTimeMillis();
        countingSort(arr, 1000);
        endTime = System.currentTimeMillis();
//        System.out.println("counting sort: " + Arrays.toString(arr));
        System.out.println("counting sort spend: " + (endTime - startTime) + "ms");

        Helper.mixArray(arr);
        startTime = System.currentTimeMillis();
        bucketSort(arr, 10);
        endTime = System.currentTimeMillis();
//        System.out.println("bucket sort: " + Arrays.toString(bucketSort(arr, 10)));
        System.out.println("bucket sort spend: " + (endTime - startTime) + "ms");

        Helper.mixArray(arr);
        startTime = System.currentTimeMillis();
        radixSort(arr, 3);
        endTime = System.currentTimeMillis();
//        System.out.println("radix sort: " + Arrays.toString(arr));
        System.out.println("radix sort spend: " + (endTime - startTime) + "ms");
    }
}