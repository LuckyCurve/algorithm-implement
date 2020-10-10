package cn.luckycurve.algorithm.character3;

import cn.luckycurve.util.ComparableUtil;

import java.util.Random;

/**
 * @author LuckyCurve
 * @date 2020/10/10 8:55
 * 二叉树查找实现
 */
public class BST<Key extends Comparable<Key>, Value> {

    /**
     * 只需有一个根节点即可
     */
    private Node root;

    /**
     * 整个树的大小
     */
    public Integer size() {
        return size(root);
    }

    private Integer size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    // get/put方法实现
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {

        // 递归出口
        if (node == null) {
            return null;
        }
        Key temp = node.key;

        if (ComparableUtil.less(key, temp)) {
            return get(node.left, key);
        } else if (ComparableUtil.less(temp, key)) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    /**
     * 新调整数据结构并且返回根节点信息
     */
    private Node put(Node node, Key key, Value value) {

        // 递归出口
        if (node == null) {
            return new Node(key, value, 1);
        }

        Key temp = node.key;
        if (ComparableUtil.less(key, temp)) {
            node.left = put(node.left, key, value);
        } else if (ComparableUtil.less(temp, key)) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        // 调整树的大小
        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    /*************************************************************************
     * 核心方法到此为止，剩下方法锦上添花居多
     **********************************************************************/

    public Value max() {
        return max(root);
    }

    private Value max(Node node) {
        if (node.right != null) {
            return max(node.right);
        }

        return node.value;

    }

    public Value min() {
        return min(root);
    }

    private Value min(Node node) {
        if (node.left != null) {
            return min(node.left);
        }

        return node.value;
    }


    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private Integer size;

        public Node(Key key, Value value, Integer size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }


    /**
     * 测试代码
     */
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();

        for (int i = 0; i < 10; i++) {
            bst.put(i, "hello world" + i);
        }


        System.out.println(bst.max());
        System.out.println(bst.min());

    }

}
