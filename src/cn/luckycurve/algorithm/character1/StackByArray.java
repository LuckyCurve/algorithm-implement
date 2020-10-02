package cn.luckycurve.algorithm.character1;

import java.util.Iterator;

/**
 * @author LuckyCurve
 * @date 2020/9/27 17:40
 * 栈的数组实现
 */
public class StackByArray<Item> implements Iterable<Item> {

    private Item[] data;

    private Integer size;

    public StackByArray() {
        data = (Item[]) new Object[10];
        size = 0;
    }

    public StackByArray(Integer initSize) {
        data = (Item[]) new Object[initSize];
        size = 0;
    }

    public void push(Item item) {
        if (size == data.length) {
            resize(data.length * 2);
        }
        data[size++] = item;
    }

    public Item pop() {
        return data[--size];
    }

    public Boolean isEmpty() {
        return size == 0;
    }

    public Integer size() {
        return size;
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

            private Integer i = size - 1;

            @Override
            public boolean hasNext() {
                return i >= 0;
            }

            @Override
            public Item next() {
                return data[i--];
            }
        };
    }


    /**
     * 测试用例
     */
    public static void main(String[] args) {
        StackByArray<String> stack = new StackByArray<>();

        for (int i = 0; i < 100; i++) {
            stack.push(Integer.toString(i));
        }

        for (String s : stack) {
            System.out.println(s);
        }
    }

}
