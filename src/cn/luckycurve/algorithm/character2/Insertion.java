package cn.luckycurve.algorithm.character2;

import cn.luckycurve.util.ComparableUtil;

import java.util.Arrays;

/**
 * @author LuckyCurve
 * @date 2020/10/2 15:19
 * 插入排序实现
 */
public class Insertion {

    public static void sort(Comparable[] comparables) {
        for (int i = 2; i < comparables.length; i++) {
            for (int j = i ; j > 0; j--) {
                System.out.println("i=" + i + "\t" + "j=" + j);

                if (ComparableUtil.less(comparables[j], comparables[j - 1])) {
                    System.out.println(Arrays.toString(comparables));
                    System.out.println("发生交换下标：" + i + j);
                    ComparableUtil.exch(comparables, j - 1, j);

                }
            }
        }
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        Integer[] a = {5, 8, 2, 1, 4, 5};

        sort(a);

        System.out.println(Arrays.toString(a));

        System.out.println(ComparableUtil.isSorted(a));
    }
}
