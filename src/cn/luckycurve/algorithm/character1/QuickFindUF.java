package cn.luckycurve.algorithm.character1;

import cn.luckycurve.util.StdRandom;

import java.util.Objects;

/**
 * @author LuckyCurve
 * @date 2020/9/28 18:25
 * union find 的 quickFind 版本，下标对应的相同的即是联通的
 */
public class QuickFindUF {
    /**
     * 存储触点情况，默认值是自己的下标
     */
    private final Integer[] id;

    /**
     * 联通分量的个数
     */
    private Integer count;

    public QuickFindUF(Integer initSize) {
        id = new Integer[initSize];
        // 完成数据初始化
        for (int i = 0; i < initSize; i++) {
            id[i] = i;
        }
        count = 0;
    }

    public void union(Integer p, Integer q) {
        // 处理未联通情况
        if (!Objects.equals(id[p], id[q])) {
            System.out.println(p + " " + q);

            // 赋值
            for (int i = 0; i < id.length; i++) {
                if (Objects.equals(id[i], id[p])) {
                    id[i] = id[q];
                }
            }

            count++;
        }
    }

    public Integer find(Integer p) {
        return id[p];
    }

    public Boolean connected(Integer p, Integer q) {
        return Objects.equals(id[p], id[q]);
    }

    public Integer count() {
        return count;
    }


    /**
     * 测试用例
     */
    public static void main(String[] args) {
        QuickFindUF uf = new QuickFindUF(3);

        for (int i = 0; i < 50; i++) {
            uf.union(StdRandom.nextInt(3)
                    , StdRandom.nextInt(3));
        }

        System.out.println(uf.count());
    }

}
