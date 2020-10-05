package cn.luckycurve.algorithm.character2;

import cn.luckycurve.util.ArrayUtil;
import cn.luckycurve.util.ComparableUtil;
import cn.luckycurve.util.StopwatchUtil;

/**
 * @author LuckyCurve
 * @date 2020/10/3 13:36
 * 希尔排序实现
 * 希尔排序的自我理解：将插入排序的相邻元素替换变成隔开指定距离元素替换
 * 通过减少元素交换次数来提升性能，
 */
public class ShellSort {

    public static void sort(Comparable[] comparables) {

        // 系数因子，可以随便替换，会影响到希尔排序的性能
        // 单具体影响的指标还未知
        int sin = 80;

        int n = comparables.length;
        int h = 1;
        // 初始化h
        while (h < n / sin) {
            h *= sin;
        }

        // 排序每一组，使用插入排序逻辑
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && ComparableUtil.less(comparables[j], comparables[j - h]); j -= h) {
                    ComparableUtil.exchange(comparables, j, j - h);
                }
            }

            h /= sin;
        }
    }

    /**
     * 测试用例，效率大量提升，对比于插入排序和选择排序
     */
    public static void main(String[] args) {
        Integer[] a = ArrayUtil.randomArray(5000, 50);

        StopwatchUtil.stopwatch(() -> sort(a));

        System.out.println("排序正确性：" + ComparableUtil.isSorted(a));

    }
}
