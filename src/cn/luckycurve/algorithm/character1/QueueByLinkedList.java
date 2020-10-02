package cn.luckycurve.algorithm.character1;

import java.util.Iterator;

/**
 * @author LuckyCurve
 * @date 2020/9/27 17:19
 * 队列：由链表实现
 */
public class QueueByLinkedList<Item> implements Iterable<Item> {

    private Node<Item> first;

    private Node<Item> last;

    private Integer size;

    public QueueByLinkedList() {
        size = 0;
    }

    /**
     * 从链表尾部插入
     */
    public void enqueue(Item item) {
        Node<Item> node = new Node<>(item);
        if (last == null) {
            first = last = node;
        } else {
            Node<Item> oldLast = last;
            last = node;
            oldLast.next = last;
        }

        size++;
    }

    public Item dequeue() {
        Node<Item> oldFirst = first;
        first = first.next;

        return oldFirst.data;
    }

    public Integer size() {
        return size;
    }

    public Boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private Node<Item> temp = first;

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
        QueueByLinkedList<String> queue = new QueueByLinkedList<>();

        for (int i = 0; i < 100; i++) {
            queue.enqueue(Integer.toString(i));
        }

        for (String s : queue) {
            System.out.println(s);
        }

    }
}
