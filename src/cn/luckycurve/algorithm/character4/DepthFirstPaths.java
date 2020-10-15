package cn.luckycurve.algorithm.character4;

import javax.imageio.stream.IIOByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author LuckyCurve
 * @date 2020/10/15 18:22
 * 基于深度优先的遍历算法，在对象初始化的 时候就需要进行深度遍历了
 */
public class DepthFirstPaths {

    /**
     * 是否被遍历到
     */
    private Boolean[] marked;

    /**
     * 当前节点的末尾节点，遍历信息存储在这里面
     */
    private Integer[] edgeTo;

    /**
     * 起始节点
     */
    private final Integer s;


    /**
     * 完成初始化工作
     */
    public DepthFirstPaths(Graph graph, Integer s) {
        // 完成对marked的初始化操作
        marked = new Boolean[graph.node()];
        Arrays.fill(marked, false);

        // 完成对edgeTo的初始化
        edgeTo = new Integer[graph.node()];
        edgeTo[0] = 0;

        this.s = s;

        // 在初始化对象的时候即对图进行深度搜索
        dfs(graph, s);
    }

    /**
     * 是否可以遍历到该节点
     */
    public Boolean hasPathTo(Integer index) {
        return marked[index];
    }

    public List<Integer> pathTo(Integer index) {
        if (!hasPathTo(index)) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = index; i != s; i = edgeTo[i]) {
            list.add(i);
        }
        list.add(s);

        Collections.reverse(list);

        return list;
    }

    /**
     * 深度遍历算法核心
     */
    private void dfs(Graph graph, Integer index) {
        // 记录该点被遍历到过
        marked[index] = true;

        for (Integer i : graph.adj(index)) {
            // 如果没有被遍历到过，就进行遍历
            if (!marked[i]) {
                edgeTo[i] = index;
                dfs(graph, i);
            }
        }
    }


    /**
     * 测试用例
     */
    public static void main(String[] args) {
        Graph graph = new Graph(10);
        for (int i = 0; i < 9; i++) {
            graph.addEdge(i, i + 1);
        }

        DepthFirstPaths path = new DepthFirstPaths(graph, 9);
        System.out.println(path.pathTo(5));
    }


}
