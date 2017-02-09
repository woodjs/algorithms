import java.util.Arrays;

public class Sort {

    public static int[] bubbleSort(int[] arr) {  // 冒泡排序，先确定最大的数据

        int len = arr.length;

        for (int i = 0; i < len; i++) {

            for (int j = 0; j < len - 1 - i; j++) {  // i，已排序的数据个数，1，防止j + 1越界

                if (arr[j] > arr[j + 1]) {

                    int temp = arr[j + 1];

                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        return arr;
    }

    public static void main(String[] args) {

        int[] arr = {5, 4, 2, 3, 1};

        bubbleSort(arr);

        System.out.println(Arrays.toString(arr));
    }
}