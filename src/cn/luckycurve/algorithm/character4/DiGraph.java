package cn.luckycurve.algorithm.character4;

import java.util.HashSet;
import java.util.Set;

/**
 * @author LuckyCurve
 * @date 2020/10/16 16:47
 * 有向图的具体实现
 */
public class DiGraph {
    /**
     * 存储节点个数
     */
    private final Integer nodeSize;

    /**
     * 存储边的个数
     */
    private Integer sideSize;

    /**
     * 存储边的信息
     */
    private Set<Integer>[] adj;

    public DiGraph(Integer nodeSize) {
        this.nodeSize = nodeSize;
        sideSize = 0;
        // 初始化adj
        adj = (Set<Integer>[]) new Set[nodeSize];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new HashSet<>();
        }
    }

    /**
     * 获取节点个数
     */
    public Integer getNodeSize() {
        return nodeSize;
    }

    /**
     * 获取边的个数
     */
    public Integer getSideSize() {
        return sideSize;
    }

    /**
     * 添加边w -> v
     * 仅仅只是单向增加调用链路
     */
    public void addEdge(Integer w, Integer v) {
        if (!adj[w].contains(v)) {
            sideSize++;
        }
        adj[w].add(v);
    }

    /**
     * 获取当前节点所指向的节点集合
     */
    public Set<Integer> adj(Integer w) {
        return adj[w];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        // 遍历每一个顶点
        for (int i = 0; i < nodeSize; i++) {
            builder.append(i).append(" : ").append(" [ ");
            for (Integer temp : adj[i]) {
                builder.append(temp).append(" ");
            }
            builder.append("]").append("\n");
        }

        return builder.toString();
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        DiGraph graph = new DiGraph(10);
        graph.addEdge(2, 3);
        graph.addEdge(2, 3);
        System.out.println(graph);
        System.out.println(graph.getSideSize());
    }


}
