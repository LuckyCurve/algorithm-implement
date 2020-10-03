package cn.luckycurve.algorithm.character2;

import cn.luckycurve.util.ArrayUtil;
import cn.luckycurve.util.ComparableUtil;
import cn.luckycurve.util.StopwatchUtil;

/**
 * @author LuckyCurve
 * @date 2020/10/2 15:05
 * 选择排序的实现
 */
public class SelectionSort {

    public static void sort(Comparable[] comparables) {
        for (int i = 0; i < comparables.length - 1; i++) {

            int min = i;

            for (int j = i + 1; j < comparables.length; j++) {
                if (ComparableUtil.less(comparables[j], comparables[min])) {
                    min = j;
                }
            }
            if (min != i) {
                ComparableUtil.exchange(comparables, min, i);
            }
        }
    }


    /**
     * 测试用例
     */
    public static void main(String[] args) {
        Integer[] a = ArrayUtil.randomArray(5000, 50);

        StopwatchUtil.stopwatch(() -> sort(a));

        System.out.println("排序正确性：" + ComparableUtil.isSorted(a));

    }
}
