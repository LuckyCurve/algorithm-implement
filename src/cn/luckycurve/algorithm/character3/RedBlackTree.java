package cn.luckycurve.algorithm.character3;

import cn.luckycurve.util.ComparableUtil;

import javax.imageio.stream.ImageInputStreamImpl;
import java.awt.*;
import java.awt.image.renderable.RenderableImage;
import java.util.Objects;

/**
 * @author LuckyCurve
 * @date 2020/10/10 15:05
 * 红黑树的Java实现
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {

    private Node root;

    public Integer size() {
        return size(root);
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        // ？？ 强行将根节点的颜色转换成黑色
        root.color = BLACK;
    }

    /************************************************
     * 私有方法信息  *
     ************************************************/
    private Boolean isRed(Node node) {
        // 默认末尾节点是黑的
        if (node == null) {
            return false;
        }
        return Objects.equals(node.color, RED);
    }

    private Integer size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    /**
     * 只有两个红节点之间可以发生旋转操作
     * 左旋当前节点形成的树并返回新树的根节点（右子节点成为根节点）
     */
    private Node rotateLeft(Node head) {
        Node node = head.right;

        // 实质发生的连接信息交换
        head.right = node.left;
        node.left = head;

        // 颜色调整
        node.color = head.color;
        head.color = RED;

        // 大小调整
        node.size = head.size;
        head.size = size(head.left) + size(head.right) + 1;

        return node;
    }

    /**
     * 发生右旋操作（左子节点变成根节点）
     */
    private Node rotateRight(Node head) {
        Node node = head.left;

        //连接信息交换
        head.left = node.right;
        node.right = head;

        // 颜色变换
        node.color = head.color;
        head.color = RED;

        // 数据大小交换
        node.size = head.size;
        head.size = size(head.left) + size(head.right) + 1;

        return node;
    }

    /**
     * 颜色转换
     */
    private void flipColors(Node head) {
        // 条件判断
        if (Objects.equals(head.left.color, BLACK) || Objects.equals(head.right.color, BLACK) || Objects.equals(head.color, RED)) {
            throw new IllegalArgumentException("条件不满足，无法进行颜色转换");
        }

        head.color = RED;
        head.left.color = BLACK;
        head.right.color = BLACK;
    }

    /**
     * 待递归的put操作
     */
    private Node put(Node head, Key key, Value value) {
        if (head == null) {
            // 默认使用红链接连接
            return new Node(key, value, 1, RED);
        }

        // 查找递归操作
        if (ComparableUtil.less(key, head.key)) {
            head.left = put(head.left, key, value);
        } else if (ComparableUtil.less(head.key, key)) {
            head.right = put(head.right, key, value);
        } else {
            head.value = value;
        }

        // 通过旋转操作保持红链接在左边
        if (!isRed(head.left) && isRed(head.right)) {
            head = rotateLeft(head);
        }
        if (isRed(head.left) && isRed(head.left.left)) {
            head = rotateRight(head);
        }
        if (isRed(head.left) && isRed(head.right)) {
            flipColors(head);
        }

        // 改变size信息
        head.size = size(head.left) + size(head.right) + 1;

        return head;

    }

    /************************************************
     * 数据结构信息  *
     ************************************************/
    private static final Boolean RED = true;

    private static final Boolean BLACK = false;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        Integer size;
        Boolean color;

        public Node(Key key, Value value, Integer size, Boolean color) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.color = color;
        }
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();

        for (int i = 0; i < 10; i++) {
            tree.put(i, "hello world" + i);
        }

        System.out.println(true);
    }
}
