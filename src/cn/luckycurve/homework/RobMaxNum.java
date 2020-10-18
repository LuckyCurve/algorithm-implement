package cn.luckycurve.homework;

import java.io.*;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 * @author LuckyCurve
 * @date 2020/10/18 19:38
 * 机器人动态规划问题：
 * 机器人Rob在一个有n*n个方格的方形区域F中收集样本。
 * （i,j）方格中样本的价值为v(i,j)，
 * 如图所示。Rob从方形区域F的左上角A点出发，向下或向右行走，
 * 直到右下角的B点，在走过的路上，收集方格中的样本。Rob从A点到B点共走两次，
 * 试找出Rob的2条行走路径，使其取得的样本总价值最大。
 * <p>
 * 解决思路：使用栈结构存储每次调用的中间路径过程，当结果不是最大额时候将其覆盖
 * 通过穷举法暴力穷举出最大的路径并将其路径置0。
 */
public class RobMaxNum {

    /**
     * 输入输出路径
     */
    private static final String INPUT_PATH = "G:\\IDEA-WorkPlace\\algorithm-demo\\src\\cn\\luckycurve\\homework\\input.txt";

    private static final String OUTPUT_PATH = "G:\\IDEA-WorkPlace\\algorithm-demo\\src\\cn\\luckycurve\\homework\\output.txt";

    /**
     * 调用栈
     */
    private static Stack<Map<Integer, Integer>> stack = new Stack<>();

    /**
     * 行数
     */
    private static Integer n;

    /**
     * 数据存储
     */
    private static Integer[][] data;

    /**
     * 外部调用接口，求取两次最大路径之和
     */
    public static Integer max() {
        Integer max = max(0, 0);
        clear();
        Integer max2 = max(0, 0);
        return max + max2;
    }

    /**
     * 数据初始化操作
     */
    private static void initData() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(INPUT_PATH)))) {
            n = Integer.parseInt(bufferedReader.readLine());
            data = new Integer[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    data[i][j] = 0;
                }
            }
            for (; ; ) {
                String s = bufferedReader.readLine();
                if (Objects.equals("0 0 0", s)) {
                    break;
                }
                String[] s1 = s.split(" ");
                data[Integer.parseInt(s1[0])][Integer.parseInt(s1[1])] = Integer.parseInt(s1[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入数据
     */
    private static void writeData(Integer data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(OUTPUT_PATH)))) {
            bufferedWriter.write(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 内部实现
     */
    private static Integer max(Integer i, Integer j) {
        int N = data.length;

        if (i >= data.length || j >= data.length) {
            return 0;
        }

        // 入栈操作
        stack.push(Collections.singletonMap(i, j));

        if (stack.size() > 2 * n - 1) {
            stack.clear();
        }

        return data[i][j] + Math.max(max(i + 1, j), max(i, j + 1));
    }

    /**
     * 实现第一遍遍历过后的清除
     */
    private static void clear() {
        stack.forEach(map -> {
            map.forEach((key, value) -> {
                data[key][value] = 0;
            });
        });
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        initData();
        Integer max = max();
        writeData(max);
    }
}
