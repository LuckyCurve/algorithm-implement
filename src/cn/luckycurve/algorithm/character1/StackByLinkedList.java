package cn.luckycurve.algorithm.character1;

import java.util.Iterator;

/**
 * @author LuckyCurve
 * @date 2020/9/27 17:48
 * 栈的链表实现
 */
public class StackByLinkedList<Item> implements Iterable<Item> {

    private Node<Item> first;

    private Integer size;

    public StackByLinkedList() {
        size = 0;
    }

    public void push(Item item) {
        Node<Item> node = new Node<>(item);
        Node<Item> oldFirst = first;

        first = node;
        first.next = oldFirst;

        size++;
    }

    public Item pop() {
        Node<Item> oldFirst = first;
        first = first.next;
        size--;
        return oldFirst.data;
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
        StackByLinkedList<String> stack = new StackByLinkedList<>();

        for (int i = 0; i < 100; i++) {
            stack.push(Integer.toString(i));
        }

        for (String s : stack) {
            System.out.println(s);
        }
    }
}
