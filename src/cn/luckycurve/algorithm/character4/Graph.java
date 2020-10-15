package cn.luckycurve.algorithm.character4;

import java.util.HashSet;
import java.util.Set;

/**
 * @author LuckyCurve
 * @date 2020/10/13 19:29
 * 图的邻接表数组的实现
 */
public class Graph {

    /**
     * 节点个数
     */
    private final Integer nodeSize;
    /**
     * 边的个数（使用的较少）
     */
    private Integer sideSize;
    private Set<Integer>[] adj;


    /**
     * 完成初始化操作
     */
    public Graph(Integer nodeSize) {
        this.nodeSize = nodeSize;
        sideSize = 0;
        // 只能通过强转的方式来保证泛型
        adj = (Set<Integer>[]) new HashSet[nodeSize];
        // 实现对adj的赋值操作
        for (int i = 0; i < nodeSize; i++) {
            adj[i] = new HashSet<>();
        }
    }

    /**
     * 返回顶点数
     */
    public Integer node() {
        return nodeSize;
    }

    /**
     * 边的个数
     */
    public Integer side() {
        return sideSize;
    }

    /**
     * 实现对w节点和v节点之间的连线
     */
    public void addEdge(Integer w, Integer v) {
        adj[w].add(v);
        adj[v].add(w);
        sideSize++;
    }

    /**
     * 返回当前节点的连接信息
     */
    public Set<Integer> adj(Integer w) {
        return adj[w];
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder(nodeSize + " nodeSize\t" + sideSize + "sideSize\n");
        for (int i = 0; i < adj.length; i++) {
            info.append(i).append(" : ");
            for (Integer integer : adj(i)) {
                info.append(integer).append(" ");
            }
            info.append("\n");
        }

        return info.toString();
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge(2, 3);
        System.out.println(graph);
    }


}
