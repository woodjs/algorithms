package bugong;

import edu.princeton.cs.algs4.StdOut;

public class Basic {

    public static int[] reverse(int[] arr) {

        int len = arr.length;

        for (int i = 0; i < len / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[len - 1 - i];
            arr[len - 1 - i] = temp;
        }
        return arr;
    }

    public static boolean isPrime(int num) {

        if (num < 2)
            return false;

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0)
                return false;
        }

        return true;
    }

    public static int getGCD(int a, int b) { // gcd（最大公约数，greatest common divisor），欧几里德算法

        if (b == 0)
            return a;

        int temp = a % b;
        return getGCD(b, temp);
    }

    public static int getLCM(int a, int b) { // lcm（最小公倍数，lowest common multiple）

        return a * b / getGCD(a, b);
    }

    public static double[][] matrix(double[][] arr1, double[][] arr2) {

        int len = arr1.length;
        double[][] result = new double[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < len; k++) {
                    result[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[] arr = {3, 8, 4};

        StdOut.println("before reverse a[0] is: " + arr[0]);

        reverse(arr);

        StdOut.println("after reverse a[0] is: " + arr[0]);

        if (isPrime(3))
            StdOut.println("3 is prime!");

        if (!isPrime(6))
            StdOut.println("6 is not prime!");

        StdOut.printf("the gcd of 48 and 42 is %d\n", getGCD(48, 42));

        StdOut.printf("the lcm of 48 and 42 is %d\n", getLCM(48, 42));
    }

}