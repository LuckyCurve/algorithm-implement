package cn.luckycurve.algorithm.character1;

import java.util.Iterator;

/**
 * @author LuckyCurve
 * @date 2020/9/27 17:07
 * 队列的数组实现
 */
public class QueueByArray<Item> implements Iterable<Item> {

    private Item[] data;

    private Integer size;

    public QueueByArray() {
        data = (Item[]) new Object[10];
        size = 0;
    }

    public QueueByArray(Integer initSize) {
        data = (Item[]) new Object[initSize];
        size = 0;
    }

    public void enqueue(Item item) {
        if (size == data.length) {
            resize(data.length * 2);
        }

        data[size++] = item;
    }

    public Item dequeue() {
        if (size == 0) {
            throw new IllegalCallerException("队列为空");
        }
        return data[--size];
    }

    public Integer size() {
        return size;
    }

    public Boolean isEmpty() {
        return size == 0;
    }

    public void resize(Integer max) {
        if (max < data.length) {
            throw new IllegalArgumentException("入参Max太小");
        }
        Item[] obj = (Item[]) new Object[max];

        for (int i = 0; i < data.length; i++) {
            obj[i] = data[i];
        }
        data = obj;
    }


    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            Integer temp = 0;

            @Override
            public boolean hasNext() {
                return temp < size;
            }

            @Override
            public Item next() {
                return data[temp++];
            }
        };
    }


    /**
     * 测试用例
     */
    public static void main(String[] args) {
        QueueByArray<String> queue = new QueueByArray<>();
        for (int i = 0; i < 50; i++) {
            queue.enqueue(Integer.toString(i));
        }

        for (String s : queue) {
            System.out.println(s);
        }
    }


}
