package cn.luckycurve.algorithm.character4;

import cn.luckycurve.util.GraphUtil;

import java.util.Arrays;

/**
 * @author LuckyCurve
 * @date 2020/10/15 19:19
 * 使用深度遍历算法实现对图中连通分量的展示
 */
public class CC {
    /**
     * 是否被遍历过，dfs中需要使用
     */
    private final Boolean[] marked;

    /**
     * 记载连通分量的id，使得处于同一连通分量的id值相同
     */
    private final Integer[] id;

    /**
     * 连通分量个数
     */
    private Integer count;

    /**
     * 完成数据的初始化
     */
    public CC(Graph graph) {
        marked = new Boolean[graph.node()];
        Arrays.fill(marked, false);
        id = new Integer[graph.node()];

        count = 0;

        // 将部分深度遍历得逻辑提前到构造函数当中来了
        for (int i = 0; i < graph.node(); i++) {
            // 是否需要深度遍历
            if (!marked[i]) {
                dfs(graph, i);
                count++;
            }
        }
    }

    /**
     * 深度遍历算法
     */
    private void dfs(Graph graph, Integer s) {
        // 设置marked值
        marked[s] = true;
        id[s] = count;
        for (Integer temp : graph.adj(s)) {
            if (!marked[temp]) {
                dfs(graph, temp);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(i).append(":[");
            for (int j = 0; j < id.length; j++) {
                if (id[j] == i) {
                    builder.append(" ").append(j).append(" ");
                }
            }
            builder.append("]").append("\n");
        }

        return builder.toString();
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        Graph graph = new Graph(10);
        for (int i = 0; i < 1; i++) {
            graph.addEdge(i, i + 1);
        }

        for (int i = 2; i < 4; i++) {
            graph.addEdge(i, i + 1);
        }

        for (int i = 7; i < 9; i++) {
            graph.addEdge(i, i + 1);
        }

        CC cc = new CC(graph);
        System.out.println(cc);
    }
}
