package cn.luckycurve;

import cn.luckycurve.util.ArrayUtil;
import cn.luckycurve.util.ComparableUtil;
import cn.luckycurve.util.StopwatchUtil;

/**
 * @author LuckyCurve
 * @date 2020/8/23 21:11
 * 每日一遍，算法实现
 */
public class Test {

    /**
     * 归并排序需要的额外空间
     */
    private static Comparable[] aux;

    /**
     * 插入排序
     */
    public static void insertSort(Comparable[] src) {
        for (int i = 1; i < src.length; i++) {
            for (int j = i; j > 0 && ComparableUtil.less(src[j], src[j - 1]); j--) {
                ComparableUtil.exchange(src, j, j - 1);
            }
        }
    }

    /**
     * 希尔排序
     */
    public static void shellSort(Comparable[] src) {
        // 确定参数因子
        int sin = 3;
        int length = src.length;
        int h = 1;

        while (h >= length / sin) {
            h *= sin;
        }

        while (h >= 1) {
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h && ComparableUtil.less(src[j], src[j - h]); j -= h) {
                    ComparableUtil.exchange(src, j, j - h);
                }
            }
            h /= sin;
        }
    }

    /**
     * 归并排序
     */
    public static void mergeSort(Comparable[] src) {
        // 完成额外空间的初始化
        aux = new Comparable[src.length];

        mergeSort(src, 0, src.length - 1);
    }

    private static void mergeSort(Comparable[] src, Integer start, Integer end) {
        if (start >= end) {
            return;
        }

        int middle = (start + end) / 2;

        mergeSort(src, start, middle);
        mergeSort(src, middle + 1, end);
        merge(src, start, middle, end);
    }

    private static void merge(Comparable[] src, Integer start, Integer middle, Integer end) {
        // 赋初值
        for (int i = start; i <= end; i++) {
            aux[i] = src[i];
        }

        int i = start, j = middle + 1;

        for (int k = start; k <= end; k++) {
            if (i > middle) {
                src[k] = aux[j++];
            } else if (j > end) {
                src[k] = aux[i++];
            } else if (ComparableUtil.less(aux[i], aux[j])) {
                src[k] = aux[i++];
            } else {
                src[k] = aux[j++];
            }
        }


    }


    /**
     * 测试用例
     */
    public static void main(String[] args) {
        Integer[] a = ArrayUtil.randomArray(5000, 50);

        StopwatchUtil.stopwatch(() -> mergeSort(a));

        System.out.println("排序正确性：" + ComparableUtil.isSorted(a));

    }

}
