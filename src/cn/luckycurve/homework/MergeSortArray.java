package cn.luckycurve.homework;


import java.util.Arrays;

/**
 * 合并数组
 */
public class MergeSortArray {

    public static Integer[] mergeSortArray(Integer[] obj, Integer k) {
        int key1 = 0;
        int key2 = k + 1;

        while (key1 != key2) {
            // 需要发生交换
            if (obj[key1] > obj[key2]) {
                int temp = obj[key2];
                obj[key2] = obj[key1];
                obj[key1] = temp;
                if (key2 < obj.length - 1) {
                    key2++;
                }
            }
            key1++;
        }

        return obj;
    }
    
    /**
     * 测试用例
     */
    public static void main(String[] args) {
        Integer[] obj = {5, 6, 1, 2, 3};
        mergeSortArray(obj, 1);
        System.out.println(Arrays.toString(obj));
    }
}
