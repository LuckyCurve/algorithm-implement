package cn.luckycurve;

import cn.luckycurve.algorithm.character2.InsertionSort;
import cn.luckycurve.util.ArrayUtil;
import cn.luckycurve.util.ComparableUtil;
import cn.luckycurve.util.StopwatchUtil;

import java.util.Arrays;
import java.util.SortedMap;

/**
 * @author LuckyCurve
 * @date 2020/8/23 21:11
 * 每日一遍，算法实现
 */
public class Test {

    /**
     * 归并排序的额外空间
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
     * 希尔排序，避免频繁移动
     */
    public static void shellSort(Comparable[] src) {
        // 系数因子
        int sin = 4;
        int h = 1;
        // 初始化h
        while (h < src.length / sin) {
            h *= sin;
        }

        while (h >= 1) {
            for (int i = h; i < src.length; i++) {
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
        // 初始化额外空间
        aux = new Comparable[src.length];

        mergeSort(src, 0, src.length - 1);
    }

    private static void mergeSort(Comparable[] src, Integer low, Integer high) {
        if (low >= high) {
            return;
        }

        int middle = (low + high) / 2;

        mergeSort(src, low, middle);
        mergeSort(src, middle + 1, high);
        merge(src, low, middle, high);
    }

    private static void merge(Comparable[] src, Integer low, Integer middle, Integer high) {

        // 数据复制
        for (int i = low; i <= high; i++) {
            aux[i] = src[i];
        }

        int i = low, j = middle + 1;

        // 开始归并操作
        for (int k = low; k <= high; k++) {
            if (i > middle) {
                src[k] = aux[j++];
            } else if (j > high) {
                src[k] = aux[i++];
            } else if (ComparableUtil.less(aux[i], aux[j])) {
                src[k] = aux[i++];
            } else {
                src[k] = aux[j++];
            }
        }
    }

    /**
     * 快速排序
     */
    public static void quickSort(Comparable[] src) {

        quickSort(src, 0, src.length - 1);
    }

    private static void quickSort(Comparable[] src, Integer low, Integer high) {
        if (low >= high) {
            return;
        }

        Integer k = partition(src, low, high);
        quickSort(src, low, k - 1);
        quickSort(src, k + 1, high);
    }

    private static Integer partition(Comparable[] src, Integer low, Integer high) {
        Comparable temp = src[low];

        int i = low, j = high + 1;

        while (true) {
            // 找到左边大于temp的
            while (ComparableUtil.less(src[++i], temp)) {
                if (i > high) {
                    break;
                }
            }

            // 找到右边小于temp的
            while (ComparableUtil.less(temp, src[--j])) {
                if (j < low) {
                    break;
                }
            }

            // 递归出口
            if (i >= j) {
                break;
            }

            ComparableUtil.exchange(src, i, j);
        }

        ComparableUtil.exchange(src, low, j);

        return j;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        Integer[] a = ArrayUtil.randomArray(5000, 50);

        StopwatchUtil.stopwatch(() -> quickSort(a));

        ArrayUtil.println(a);

        System.out.println("排序正确性：" + ComparableUtil.isSorted(a));
    }
}
