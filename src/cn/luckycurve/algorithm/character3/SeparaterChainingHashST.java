package cn.luckycurve.algorithm.character3;

import java.util.LinkedHashMap;

/**
 * @author LuckyCurve
 * @date 2020/10/11 9:42
 * 基于拉链法的散列表实现（直接使用到了链表实现LinkedList，没有再去单独实现了）
 */
public class SeparaterChainingHashST<Key, Value> {

    /**
     * 链表矢量
     */
    private final Integer num;

    /**
     * 存放数据信息，链表实现
     */
    private LinkedHashMap<Key, Value>[] element;

    /**
     * 完成数据初始化操作
     */
    public SeparaterChainingHashST(Integer num) {
        this.num = num;
        // 完成对Element的初始化
        element = (LinkedHashMap<Key, Value>[]) new LinkedHashMap[num];
        for (int i = 0; i < num; i++) {
            element[i] = new LinkedHashMap<>();
        }
    }

    public Value get(Key key) {
        return element[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        element[hash(key)].put(key, value);
    }

    public void remove(Key key) {
        element[hash(key)].remove(key);
    }

    private Integer hash(Key key) {
        return Math.abs(key.hashCode()) % num;
    }


    /**
     * 测试用例
     */
    public static void main(String[] args) {
        SeparaterChainingHashST<Integer, String> st = new SeparaterChainingHashST<>(20);

        for (int i = 0; i < 20; i++) {
            st.put(i, "hello world" + i);
        }

        st.remove(5);

        System.out.println(st.get(5));

    }
}


