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
        int prevIndex, temp;

        for (int i = 1; i < len; i++) {
            prevIndex = i - 1;

            for (int j = len - i; j > 0; j--) {

                if (prevIndex < 0) break;

                if (arr[j] < arr[prevIndex]) {
                    temp = arr[j];
                    arr[j] = arr[prevIndex];
                    arr[prevIndex] = temp;

                    prevIndex--;
                } else {
                    break;
                }
            }
        }

        return arr;
    }

    public static void main(String[] args) {

        int[] arr = Helper.createArray(100, 1, 10);

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
    }
}