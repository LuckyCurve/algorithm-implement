package cn.luckycurve.homework;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LuckyCurve
 * @date 2020/10/4 13:06
 * 设n是一个正整数。现在要求将n分解为若干不相同的自然数的和，且使这些自然数的乘积最大。
 */
public class MaximumProduct {

    public static final String INPUT_PATH = "G:\\IDEA-WorkPlace\\algorithm-demo\\src\\cn\\luckycurve\\homework\\input.txt";

    public static final String OUTPUT_PATH = "G:\\IDEA-WorkPlace\\algorithm-demo\\src\\cn\\luckycurve\\homework\\output.txt";

    /**
     * 返回最大的乘积因子
     */
    public static List<Integer> maximumProduct(Integer target) {
        // 余数
        int more = 0;

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 2; ; i++) {
            if (sum(result) + i <= target) {
                result.add(i);
            } else {
                more = target - sum(result);
                break;
            }
        }

        int size = result.size();

        for (int i = size - 1; i >= 0; i--) {
            if (more > 0) {
                Integer integer = result.get(i);
                integer++;
                result.set(i, integer);
                more--;
            }
        }

        return result;

    }

    private static Integer sum(List<Integer> src) {
        return src.stream().reduce(0, Integer::sum);
    }

    private static Integer multiplication(List<Integer> src) {
        final int result = 1;
        return src.stream().reduce(1, (integer, integer2) -> integer * integer2);

    }


    /**
     * 测试用例
     */
    public static void main(String[] args) {
        File input = new File(INPUT_PATH);
        File output = new File(OUTPUT_PATH);
        // 文件操作
        try (FileReader fileReader = new FileReader(input);
             FileWriter fileWriter = new FileWriter(output);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            int anInt = Integer.parseInt(bufferedReader.readLine());
            List<Integer> list = maximumProduct(anInt);
            Integer result = multiplication(list);
            bufferedWriter.write(Integer.toString(result));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
