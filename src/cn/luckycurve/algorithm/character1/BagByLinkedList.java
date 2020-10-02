package cn.luckycurve.algorithm.character1;

import java.util.Iterator;

/**
 * @author LuckyCurve
 * @date 2020/9/27 16:39
 * 背包：链表实现
 */
public class BagByLinkedList<Item> implements Iterable<Item> {

    private Integer size;

    private Node<Item> first;

    public BagByLinkedList() {
        size = 0;
    }

    public void add(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<>(item);

        first.next = oldFirst;
        size++;
    }

    public Boolean isEmpty() {
        return size == 0;
    }

    public Integer size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            Node<Item> temp = first;

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public Item next() {
                Item data = temp.data;
                temp = temp.next;
                return data;
            }
        };
    }

    private class Node<Item> {
        Item data;
        Node<Item> next;

        public Node(Item data) {
            this.data = data;
        }
    }


    /**
     * 测试用例
     */
    public static void main(String[] args) {
        BagByLinkedList<String> bag = new BagByLinkedList<>();

        for (int i = 0; i < 300; i++) {
            bag.add(Integer.toString(i));
        }

        for (String s : bag) {
            System.out.println(s);
        }

    }

}
