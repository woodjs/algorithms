package bugong;

public class Helper {

    /**
     * 创建数组
     *
     * @param len  要创建的数组长度
     * @param min  数组的最小值
     * @param max  数组的最大值
     * @return
     */
    public static int[] createArray(int len, int min, int max) {

        int[] arr = new int[len];
        int gap = max - min;

        for (int i = 0; i < len; i++) {
            arr[i] = gap > 0 ? (int)Math.round(Math.random() * gap + min) : 0;
        }

        return arr;
    }

    /**
     * 随机打乱数组
     *
     * @param arr
     * @return
     */
    public static int[] mixArray(int[] arr) {

        int len = arr.length;

        for (int i = 0; i < len; i++) {

            if (i >= len - 1) break;  // 防止越界

            int index = (int)Math.round(
                    Math.random() * ((len - 1) - (i + 1))
            ) + (i + 1);  // 生成[i + 1, len - 1]间的随机整数

            swap(arr, i, index);
        }

        return arr;
    }

    /**
     * 反转数组
     *
     * @param arr
     * @return
     */
    public static int[] reverseArray(int[] arr) {

        int len = arr.length;

        for (int i = 0; i < len / 2; i++) {

            swap(arr, i, len - 1 - i);
        }
        return arr;
    }

    /**
     * 交换数组中的两个元素
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
