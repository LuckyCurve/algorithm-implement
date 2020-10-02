package cn.luckycurve.algorithm.character1;

import cn.luckycurve.util.StdRandom;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author LuckyCurve
 * @date 2020/9/30 10:00
 * union find 的quickUnion 优化版本
 * 将小数加到大数上面去
 */
public class QuickUnionUFOPT {

    private final Integer[] id;

    private Integer count;

    private final Integer[] size;

    /**
     * 完成参数的初始化
     */
    public QuickUnionUFOPT(Integer initSize) {

        id = new Integer[initSize];

        size = new Integer[initSize];

        count = 0;

        // 完成数据初始化
        for (int i = 0; i < initSize; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }


    /**
     * 寻找到根节点
     */
    public Integer find(Integer p) {
        while (!Objects.equals(p, id[p])) {
            p = id[p];
        }

        return p;
    }

    public Boolean connected(Integer p, Integer q) {
        return Objects.equals(find(p), find(q));
    }

    /**
     * 进行插入优化，将小数插入到大数当中去
     */
    public void union(Integer p, Integer q) {
        if (!Objects.equals(find(p), find(q))) {
            System.out.println(p + " " + q);

            // 判断哪个数大
            if (size[p] > size[q]) {
                id[q] = find(p);
                size[p] += size[q];
            } else {
                id[p] = find(q);
                size[q] += size[p];
            }
            id[p] = find(q);

            count++;
        }
    }

    public Integer count() {
        return count;
    }

    public static void main(String[] args) {
        final Integer num = 20;

        QuickUnionUFOPT uf = new QuickUnionUFOPT(num);

        for (int i = 0; i < 50; i++) {
            uf.union(StdRandom.nextInt(num)
                    , StdRandom.nextInt(num));
        }

        System.out.println(uf.count());

        System.out.println("===================");

        System.out.println(Arrays.toString(uf.size));
    }


}
