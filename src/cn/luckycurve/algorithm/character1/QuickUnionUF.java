package cn.luckycurve.algorithm.character1;

import cn.luckycurve.util.StdRandom;

import java.util.Objects;

/**
 * @author LuckyCurve
 * @date 2020/9/28 19:04
 * union find 的 quickUnion 版本，结构类似于链表
 */
public class QuickUnionUF {

    private Integer[] id;

    private Integer count;

    public QuickUnionUF(Integer initSize) {
        id = new Integer[initSize];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
        count = 0;
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

    public void union(Integer p, Integer q) {
        if (!Objects.equals(find(p), find(q))) {
            System.out.println(p + " " + q);

            id[p] = find(q);

            count++;
        }
    }

    public Integer count() {
        return count;
    }


    /**
     * 测试用例
     */
    public static void main(String[] args) {
        QuickUnionUF uf = new QuickUnionUF(3);

        for (int i = 0; i < 50; i++) {
            uf.union(StdRandom.nextInt(3)
                    , StdRandom.nextInt(3));
        }

        System.out.println(uf.count());
    }
}
