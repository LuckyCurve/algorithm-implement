package cn.luckycurve.homework;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LuckyCurve
 * @date 2020/10/4 13:47
 * 归并排序的顺序问题
 */
public class MergeNum {


    public static final String INPUT_PATH = "G:\\IDEA-WorkPlace\\algorithm-demo\\src\\cn\\luckycurve\\homework\\input.txt";

    public static final String OUTPUT_PATH = "G:\\IDEA-WorkPlace\\algorithm-demo\\src\\cn\\luckycurve\\homework\\output.txt";

    public static Integer maxMum(List<Integer> list) {
        list.sort(Comparator.reverseOrder());

        int temp = list.get(0);
        int sum = 0;

        for (int i = 1; i < list.size(); i++) {
            temp += list.get(i);
            sum = sum + temp - 1;
        }

        return sum;

    }

    public static Integer minNum(List<Integer> list) {
        list.sort(Integer::compareTo);

        int temp = list.get(0);
        int sum = 0;

        for (int i = 1; i < list.size(); i++) {
            temp += list.get(i);
            sum = sum + temp - 1;
        }

        return sum;
    }

    public static void main(String[] args) {
        File input = new File(INPUT_PATH);
        File output = new File(OUTPUT_PATH);
        // 文件操作
        try (FileReader fileReader = new FileReader(input);
             FileWriter fileWriter = new FileWriter(output);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            String s = bufferedReader.readLine();
            List<Integer> list = Arrays.stream(s.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            Integer maxMum = maxMum(list);
            Integer minNum = minNum(list);
            bufferedWriter.write(maxMum + " " + minNum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
