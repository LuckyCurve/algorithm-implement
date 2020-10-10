package cn.luckycurve.util;

import java.util.Arrays;

/**
 * @author LuckyCurve
 * @date 2020/10/2 15:07
 * Comparable工具类
 */
public class ComparableUtil {

    /**
     * c1是否小于c2
     */
    public static Boolean less(Comparable c1, Comparable c2) {
        return c1.compareTo(c2) < 0;
    }

    public static Boolean equals(Comparable c1, Comparable c2) {
        return c1.compareTo(c2) == 0;
    }


    /**
     * 完成交换指定下标元素
     */
    public static void exchange(Comparable[] src, Integer index1, Integer index2) {
        Comparable temp = src[index1];
        src[index1] = src[index2];
        src[index2] = temp;
    }

    /**
     * 是否是升序排序
     */
    public static Boolean isSorted(Comparable[] src) {
        for (int i = 0; i < src.length - 1; i++) {
            if (less(src[i + 1], src[i])) {
                return false;
            }
        }
        return true;
    }
}
