import java.util.Arrays;

public class Sort {

    public static int[] bubbleSort(int[] arr) {  // 冒泡排序，先确定最右侧（最大）的数据

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

    public static int[] selectionSort(int[] arr) {  // 选择排序，先确定最左侧（最小）的数据

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

    public static void main(String[] args) {

        int[] arr = {5, 4, 2, 3, 1, 33, 355, 3, 2, 2, 1, 86, 3858, 8, 866, 123, 45};

        System.out.println("original array: " + Arrays.toString(arr));

        // bubbleSort(arr);
        // System.out.println("bubble sort: " + Arrays.toString(arr));

        selectionSort(arr);
        System.out.println("selection sort: " + Arrays.toString(arr));
    }
}