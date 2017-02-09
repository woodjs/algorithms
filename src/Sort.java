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

    public static int[] insertionSort(int[] arr) {

        int len = arr.length;
        int prevIndex, current;

        for (int i = 1; i < len; i++) {

        }

        return arr;
    }

    public static int[] mixArrary(int[] arr) {  // 随机打乱数组

        int len = arr.length;
        int temp;

        for (int i = 0; i < len; i++) {

            int gap = (len - 1) - (i + 1);

            if (gap <= 0) break;  // 防止越界

            int index = (int)Math.round(
                    Math.random() * ((len - 1) - (i + 1))
            ) + (i + 1);  // 生成[i + 1, len - 1]间的随机整数

            temp = arr[i];

            arr[i] = arr[index];
            arr[index] = temp;
        }

        return arr;
    }

    public static void main(String[] args) {

        int[] arr = {5, 4, 2, 3, 1, 33, 355, 3, 2, 2, 1, 86, 3858, 8, 866, 123, 45};

        System.out.println("original array: " + Arrays.toString(arr));

        bubbleSort(arr);
        System.out.println("bubble sort: " + Arrays.toString(arr));

        mixArrary(arr);
        System.out.println("mixed array: " + Arrays.toString(arr));

        selectionSort(arr);
        System.out.println("selection sort: " + Arrays.toString(arr));

        mixArrary(arr);
        System.out.println("mixed array: " + Arrays.toString(arr));

        insertionSort(arr);
        System.out.println("insertion sort: " + Arrays.toString(arr));
    }
}