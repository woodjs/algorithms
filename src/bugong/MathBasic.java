package bugong;

public class MathBasic {

    /**
     * 判断一个数是否是素数
     *
     * @param num
     * @return
     */
    public static boolean isPrime(int num) {

        if (num < 2)
            return false;

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0)
                return false;
        }

        return true;
    }

    /**
     * 求最大公约数
     * gcd（最大公约数，greatest common divisor），欧几里德算法
     *
     * @param a
     * @param b
     * @return
     */
    public static int getGCD(int a, int b) {

        if (b == 0)
            return a;

        int temp = a % b;
        return getGCD(b, temp);
    }

    /**
     * 求最小公倍数
     * lcm（最小公倍数，lowest common multiple）
     *
     * @param a
     * @param b
     * @return
     */
    public static int getLCM(int a, int b) {

        return a * b / getGCD(a, b);
    }

    public static void main(String[] args) {

        int[] arr = {3, 8, 4};

        if (isPrime(3))
            System.out.println("3 is prime!");

        if (!isPrime(6))
            System.out.println("6 is not prime!");

        System.out.printf("the gcd of 48 and 42 is %d\n", getGCD(48, 42));

        System.out.printf("the lcm of 48 and 42 is %d\n", getLCM(48, 42));
    }

}