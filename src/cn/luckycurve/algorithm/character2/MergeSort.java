package cn.luckycurve.algorithm.character2;

import cn.luckycurve.util.ArrayUtil;
import cn.luckycurve.util.ComparableUtil;
import cn.luckycurve.util.StopwatchUtil;

import java.util.Objects;

/**
 * @author LuckyCurve
 * @date 2020/10/3 15:41
 * 归并排序
 */
public class MergeSort {


    /**
     * 归并所需的辅助空间
     */
    private static Comparable[] aux;

    public static void sort(Comparable[] src) {
        aux = new Comparable[src.length];
        // 不能在这里调用初始化函数，因为merge函数是有重叠部分的，对后续结果有影响
        //for (int i = 0; i < src.length; i++) {
        //    aux[i] = src[i];
        //}
        sort(src, 0, src.length - 1);
    }

    private static void sort(Comparable[] src, Integer low, Integer high) {
        if (Objects.equals(low, high)) {
            return;
        }

        Integer middle = (low + high) / 2;

        sort(src, low, middle);
        sort(src, middle + 1, high);
        merge(src, low, middle, high);
    }

    /**
     * 归并操作
     */
    public static void merge(Comparable[] src, Integer low, Integer middle, Integer high) {

        int i = low, j = middle + 1;

        for (int k = low; k <= high; k++) {
            aux[k] = src[k];
        }

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
     * 测试用例
     */
    public static void main(String[] args) {
        Integer[] a = ArrayUtil.randomArray(5000, 50);

        StopwatchUtil.stopwatch(() -> sort(a));

        System.out.println("排序正确性：" + ComparableUtil.isSorted(a));

    }

}
