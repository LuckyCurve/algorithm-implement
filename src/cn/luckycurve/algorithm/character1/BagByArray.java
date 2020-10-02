package cn.luckycurve.algorithm.character1;

import java.util.Iterator;

/**
 * @author LuckyCurve
 * @date 2020/9/27 15:35
 * 背包：数组实现
 */
public class BagByArray<Item> implements Iterable<Item> {

    private Item[] data;

    private Integer size;

    public BagByArray() {
        data = (Item[]) new Object[10];
        size = 0;
    }

    public BagByArray(Integer initSize) {
        data = (Item[]) new Object[initSize];
        size = 0;
    }

    public void add(Item item) {
        if (size == data.length) {
            resize(data.length * 2);
        }

        data[size++] = item;
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

    public Boolean isEmpty() {
        return size == 0;
    }

    public Integer size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            Integer index = 0;

            @Override
            public boolean hasNext() {
                return index < data.length;
            }

            @Override
            public Item next() {
                return data[index++];
            }
        };
    }


    /**
     * 测试用例
     */
    public static void main(String[] args) {
        BagByArray<String> bag = new BagByArray<>();

        for (int i = 0; i < 100; i++) {
            bag.add(Integer.toString(i));
        }

        for (String s : bag) {
            System.out.println(s);
        }
    }
}
