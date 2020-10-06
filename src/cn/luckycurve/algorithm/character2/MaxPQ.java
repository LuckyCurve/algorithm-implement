package cn.luckycurve.algorithm.character2;

import cn.luckycurve.util.ComparableUtil;

import java.util.Arrays;

/**
 * @author LuckyCurve
 * @date 2020/10/6 12:26
 * 基于栈的优先队列实现
 */
public class MaxPQ<Key extends Comparable<Key>> {

    /**
     * 存储数据
     */
    private Key[] pq;

    /**
     * 记录当前优先队列的大小
     */
    private Integer size = 0;

    public MaxPQ(int initSize) {
        // 因为下标为0的位置是不存储元素的
        pq = (Key[]) new Comparable[initSize + 1];
    }

    public Boolean isEmpty() {
        return size == 0;
    }

    public Integer size() {
        return size;
    }

    public void insert(Key v) {
        // 扩容操作
        if (size > pq.length / 2) {
            resize(pq.length * 2);
        }

        pq[++size] = v;
        // 进行上浮操作
        swim(size);

    }

    public Key delMax() {
        // 缩容操作
        if (size < pq.length / 4) {
            resize(pq.length / 2);
        }

        Key max = pq[1];

        ComparableUtil.exchange(pq, 1, size--);

        // 造成对象孤立，方便GC回收
        pq[size + 1] = null;

        // 下潜pq[1]的元素
        sink(1);

        return max;
    }

    /**
     * 对下标为k的元素进行上浮操作
     */
    private void swim(Integer k) {
        while (k > 1 && ComparableUtil.less(pq[k / 2], pq[k])) {
            ComparableUtil.exchange(pq, k, k / 2);
            k = k / 2;
        }
    }

    /**
     * 对下标为k的元素进行下沉操作，这个不是很懂，还得再看看
     */
    private void sink(Integer k) {
        while (2 * k <= size) {
            int j = 2 * k;
            // 取j和j+1中大的元素
            if (j < size && ComparableUtil.less(pq[j], pq[j + 1])) {
                j++;
            }

            if (!ComparableUtil.less(pq[k], pq[j])) {
                break;
            }

            ComparableUtil.exchange(pq, k, j);
            k = j;
        }
    }

    private void resize(Integer newSize) {
        pq = Arrays.copyOf(pq, newSize);
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        MaxPQ<Integer> pq = new MaxPQ<>(20);

        for (int i = 0; i < 2000; i++) {
            pq.insert(i);
        }

        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());


    }

}
