package cn.luckycurve.util;

import java.util.Random;

/**
 * @author LuckyCurve
 * @date 2020/9/28 18:41
 * 产生随机数
 */
public class StdRandom {
    private static Random random = new Random();

    private static Long seed;

    static {
        seed = System.currentTimeMillis();
        random.setSeed(seed);
    }

    public static Integer nextInt(Integer min, Integer max) {
        int i = random.nextInt(max - min);
        return min + i;
    }

    public static Integer nextInt(Integer max) {
        return random.nextInt(max);
    }

    public static Integer nextInt() {
        return random.nextInt();
    }

    public static Double nextDouble() {
        return random.nextDouble();
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(nextInt(3));
        }
    }
}
