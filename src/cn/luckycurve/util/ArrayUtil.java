package cn.luckycurve.util;

import java.util.Arrays;

/**
 * @author LuckyCurve
 * @date 2020/10/3 13:23
 * 数组工具
 */
public class ArrayUtil {

    /**
     * @param num 元素个数
     * @param max 最大值
     * @return 生成数组
     */
    public static Integer[] randomArray(Integer num, Integer max) {
        Integer[] result = new Integer[num];

        for (int i = 0; i < result.length; i++) {
            result[i] = StdRandom.nextInt(max);
        }

        return result;
    }

    public static void println(Object[] objects) {
        System.out.println(Arrays.toString(objects));
    }

    public static void shuffle(Object[] src) {
        for (int i = 0; i < src.length; i++) {
            int index = StdRandom.nextInt(src.length);
            Object temp = src[i];
            src[i] = src[index];
            src[index] = temp;
        }
    }
}
