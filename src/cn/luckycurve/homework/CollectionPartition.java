package cn.luckycurve.homework;

import java.io.*;
import java.util.Objects;

/**
 * @author LuckyCurve
 * @date 2020/9/20 20:29
 * 集合划分问题
 * <p>
 * 给定正整数n和m，
 * 计算出n个元素的集合{1,2,3,...,n}
 * 可以划分为多少个不同的由m个非空子集组成的集合
 */
public class CollectionPartition {

    private static final String FILE_PATH = "D:\\temp\\";

    /**
     * @param n 集合元素个数
     * @param m 集合分组数
     * @return 分组结果
     */
    public static Integer collectionPartition(Integer n, Integer m) {


        if (n <= 2 || m == 1 || Objects.equals(n, m)) {
            return 1;
        }
        return collectionPartition(n - 1, m - 1) + m * collectionPartition(n - 1, m);
    }

    public static void main(String[] args) throws IOException {
        // 文件读取
        File file = new File(FILE_PATH + "input.txt");
        if (!file.exists()) {
            System.out.println("输入文件不存在！");
        }

        Integer result = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());
            result = collectionPartition(n, m);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 将结果回写到output.txt文件中去
        File file1 = new File(FILE_PATH + "output.txt");
        if (!file1.exists()) {
            file1.createNewFile();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file1))) {
            writer.write(result);
        }
    }
}
