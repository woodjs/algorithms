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
     * 随机打乱一个数组
     *
     * @param arr
     * @return
     */
    public static int[] mixArrary(int[] arr) {

        int len = arr.length;
        int temp;

        for (int i = 0; i < len; i++) {

            if (i >= len - 1) break;  // 防止越界

            int index = (int)Math.round(
                    Math.random() * ((len - 1) - (i + 1))
            ) + (i + 1);  // 生成[i + 1, len - 1]间的随机整数

            temp = arr[i];

            arr[i] = arr[index];
            arr[index] = temp;
        }

        return arr;
    }
}
