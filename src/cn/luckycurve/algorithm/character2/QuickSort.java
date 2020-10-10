package cn.luckycurve.algorithm.character2;

import cn.luckycurve.util.ArrayUtil;
import cn.luckycurve.util.ComparableUtil;
import cn.luckycurve.util.StopwatchUtil;

import java.util.Objects;

/**
 * @author LuckyCurve
 * @date 2020/10/5 9:04
 * 快速排序算法
 */
public class QuickSort {

    public static void sort(Comparable[] src) {
        ArrayUtil.shuffle(src);
        sort(src, 0, src.length - 1);
    }

    private static void sort(Comparable[] src, Integer start, Integer end) {
        if (start >= end) {
            return;
        }

        int j = partition(src, start, end);
        sort(src, start, j - 1);
        sort(src, j + 1, end);

    }

    private static Integer partition(Comparable[] src, Integer start, Integer end) {
        Comparable temp = src[start];
        int i = start, j = end + 1;
        while (true) {
            // 左边找打大的
            while (ComparableUtil.less(src[++i], temp)) {
                if (Objects.equals(end, i)) {
                    break;
                }
            }
            while (ComparableUtil.less(temp, src[--j])) {
                if (Objects.equals(j, start)) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }

            ComparableUtil.exchange(src, i, j);
        }

        ComparableUtil.exchange(src, j, start);


        return j;
    }


    /**
     * 测试用例
     */
    public static void main(String[] args) {
        Integer[] a = ArrayUtil.randomArray(5000, 50);

        StopwatchUtil.stopwatch(() -> sort(a));

        ArrayUtil.println(a);

        System.out.println("排序正确性：" + ComparableUtil.isSorted(a));
    }
}
