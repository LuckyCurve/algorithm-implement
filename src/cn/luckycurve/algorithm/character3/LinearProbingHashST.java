package cn.luckycurve.algorithm.character3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author LuckyCurve
 * @date 2020/10/11 10:54
 * 基于线性探测法的散列表实现
 */
public class LinearProbingHashST<Key, Value> {

    /**
     * 当前元素个数
     */
    private Integer num = 0;
    /**
     * 总大小
     */
    private Integer size;

    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST(Integer initSize) {
        this.size = initSize;
        keys = (Key[]) new Object[initSize];
        values = (Value[]) new Object[initSize];
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % size) {
            if (Objects.equals(key, keys[i])) {
                return values[i];
            }
        }
        return null;
    }

    public void put(Key key, Value value) {
        if (num >= size / 2) {
            resize(size * 2);
        }

        int i;

        for (i = hash(key); keys[i] != null; i = (i + 1) % size) {
            if (Objects.equals(keys[i], key)) {
                values[i] = value;
                return;
            }
        }

        keys[i] = key;
        values[i] = value;
        num++;
    }

    public void resize(Integer newSize) {
        if (newSize <= size) {
            throw new IllegalArgumentException("newSize太小!");
        }
        keys = Arrays.copyOf(keys, newSize);
        values = Arrays.copyOf(values, newSize);
        size = newSize;
    }


    private Integer hash(Key key) {
        return Math.abs(key.hashCode()) % size;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        LinearProbingHashST<Integer, String> st = new LinearProbingHashST<>(16);

        for (int i = 0; i < 20; i++) {
            st.put(i, "hello world" + i);
        }

        System.out.println(st.get(2));

    }
}
