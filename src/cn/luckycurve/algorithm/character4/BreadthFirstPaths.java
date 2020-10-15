package cn.luckycurve.algorithm.character4;

import cn.luckycurve.algorithm.character1.QueueByArray;
import cn.luckycurve.util.GraphUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author LuckyCurve
 * @date 2020/10/15 18:49
 * 广度优先遍历图的算法
 */
public class BreadthFirstPaths {

    private final Boolean[] marked;

    private final Integer[] edgeTo;

    private final Integer s;

    /**
     * 完成对数据的初始化
     */
    public BreadthFirstPaths(Graph graph, Integer s) {

        // 完成对marked的初始化，默认都没有遍历过
        marked = new Boolean[graph.node()];
        Arrays.fill(marked, false);

        // 完成对edgeTo的初始化，应该是只需要初始化下标为0的edgeTo
        edgeTo = new Integer[graph.node()];
        edgeTo[0] = 0;

        this.s = s;

        bfs(graph, s);
    }

    public Boolean hasPathTo(Integer v) {
        return marked[v];
    }

    public List<Integer> pathTo(Integer v) {
        if (!hasPathTo(v)) {
            return null;
        }

        ArrayList<Integer> list = new ArrayList<>();

        // 开始反向遍历数组edgeTo
        for (int i = edgeTo[v]; i != s; i = edgeTo[i]) {
            list.add(i);
        }
        list.add(s);

        Collections.reverse(list);

        return list;
    }

    /**
     * 广度优先遍历算法的核心，在创建对象的时候调用
     * 对edgeTo赋值
     */
    private void bfs(Graph graph, Integer index) {
        // 当前节点的标志位改为true
        marked[index] = true;

        // 使用队列而不是栈来完成对图的遍历操作
        QueueByArray<Integer> queue = new QueueByArray<>();
        queue.enqueue(index);

        while (!queue.isEmpty()) {
            Integer temp = queue.dequeue();
            for (Integer integer : graph.adj(temp)) {
                // 如果没有被遍历到过
                if (!marked[integer]) {
                    // 数据记录
                    marked[integer] = true;
                    edgeTo[integer] = temp;
                    queue.enqueue(integer);
                }

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
        System.out.println(GraphUtil.listPath(path.pathTo(5)));
    }
}
