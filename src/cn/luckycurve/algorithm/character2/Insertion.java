package cn.luckycurve.algorithm.character2;

import cn.luckycurve.util.ArrayUtil;
import cn.luckycurve.util.ComparableUtil;
import cn.luckycurve.util.StopwatchUtil;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author LuckyCurve
 * @date 2020/10/2 15:19
 * 插入排序实现
 */
public class Insertion {

    /**
     * 自己实现的插入排序，没有利用到已经排序的顺序性质
     */
    public static void sort(Comparable[] comparables) {
        for (int i = 1; i < comparables.length; i++) {

            // 会直接遍历完所有的数字，完全没有利用到前面已经排序的有序性质

            for (int j = i; j > 0; j--) {
                if (ComparableUtil.less(comparables[j], comparables[j - 1])) {
                    ArrayUtil.println(comparables);
                    System.out.println("发生交换下标：" + (j - 1) +" "+ j);
                    ComparableUtil.exch(comparables, j - 1, j);

                }
            }
        }
    }

    /**
     * 优化后的插入算法，使用了前面已经排序的数字的性质
     */
    public static void sortOPT(Comparable[] comparables) {
        for (int i = 1; i < comparables.length; i++) {


            for (int j = i; j > 0 && ComparableUtil.less(comparables[j], comparables[j - 1]); j--) {
                ArrayUtil.println(comparables);
                System.out.println("发生交换下标：" + (j - 1) +" "+ j);
                ComparableUtil.exch(comparables, j - 1, j);
            }
        }
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        Integer[] a = ArrayUtil.randomArray(500, 50);

        //StopwatchUtil.stopwatch(() -> sortOPT(a));

        StopwatchUtil.stopwatch(() -> sort(a));

        System.out.println("排序正确性："+ComparableUtil.isSorted(a));
    }
}
