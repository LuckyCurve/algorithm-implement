package cn.luckycurve.algorithm.character3;

import cn.luckycurve.util.ComparableUtil;

import javax.print.attribute.standard.NumberOfInterveningJobs;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LuckyCurve
 * @date 2020/10/10 8:22
 * 基于二分查找的查找算法，查找算法一般都是KV对
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;

    private Value[] values;

    private Integer size;

    /**
     * 初始化操作
     */
    public BinarySearchST(Integer initSize) {
        keys = (Key[]) new Comparable[initSize];
        values = (Value[]) new Object[initSize];
        size = 0;
    }

    /**
     * 获取数据容量
     */
    public Integer size() {
        return size;
    }

    /**
     * @param key 带查找元素
     * @return 查找到的下标
     */
    public Integer rank(Key key) {
        return rank(key, 0, size - 1);
    }

    public Value get(Key key) {
        if (isEmpty() || key == null) {
            return null;
        }

        return values[rank(key)];
    }

    public Boolean isEmpty() {
        return size == 0;
    }

    public void put(Key key, Value value) {
        Integer index = rank(key);

        // 如果键存在，直接覆盖即可
        if (index < size && ComparableUtil.equals(key, keys[index])) {
            values[index] = value;
        }

        // 如果键不存在则插入其中
        for (int i = size; i > index; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        keys[index] = key;
        values[index] = value;

        size++;
    }

    private Integer rank(Key key, Integer low, Integer high) {
        while (low <= high) {
            int middle = (low + high) / 2;

            if (Objects.equals(key, keys[middle])) {
                return middle;
            } else if (ComparableUtil.less(key, keys[middle])) {
                high = middle - 1;
            } else if (ComparableUtil.less(keys[middle], key)) {
                low = middle + 1;
            }
        }

        return low;
    }


    /**
     * 测试用例
     */
    public static void main(String[] args) {
        BinarySearchST<Integer, String> st = new BinarySearchST<>(10);
        for (int i = 0; i < 10; i++) {
            st.put(i, "hello world" + i);
        }

        System.out.println(st.get(9));
    }


}
