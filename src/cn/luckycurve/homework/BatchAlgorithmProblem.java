package cn.luckycurve.homework;

import java.io.*;
import java.util.Objects;

/**
 * @author LuckyCurve
 * @date 2020/10/18 20:18
 * 问题描述：在一台超级计算机上，编号为1，2，...，n的n个作业等待批处理。批处理的任务就是将这n个作业分成若干批，每批包含相邻的若干作业。
 * 从时刻0开始，分批加工这些作业。在每批作业开始前，机器需要启动时间S，而完成这批作业所需的时间是单独完成批中各个作业需要时间的总和。
 * 单独完成第i个作业所需的时间是ti,所需的费用是它的完成时刻乘以一个费用系数fi。同一批作业将在同一时刻完成。
 * 例如，如果在时刻T开始一批作业x,x+1,...,x+k,则这一批作业的完成时刻均为T+S+(tx+tx+1+...+tx+k)。
 * 最优批处理问题就是要确定总费用最小的批处理方案。例如，假定有5个作业等待批处理，且S=1,(t1,t2,t3,t4,t5)=(1,3,4,2,1),(f1,f2,f3,f4,f5)=(3,2,3,3,4)
 * 如果采用批处理方案{1,2}，{3}，{4,5}，则各作业的完成时间分别为（5,5,10,14,14），各作业的费用分别为（15,10,30,42,56），因此，这个批处理方案总费用是153。
 * <p>
 * 解决思路：可以将多个工作打包在一起，这时候我就要遍历这些工作的不同打包方式(单独成批，两个打包成批…，以此类推)，找出最小费用，即最优的打包方法，可以花费最少的费用。
 */
public class BatchAlgorithmProblem {

    /**
     * 输入输出路径
     */
    private static final String INPUT_PATH = "G:\\IDEA-WorkPlace\\algorithm-demo\\src\\cn\\luckycurve\\homework\\input.txt";

    private static final String OUTPUT_PATH = "G:\\IDEA-WorkPlace\\algorithm-demo\\src\\cn\\luckycurve\\homework\\output.txt";


    /**
     * 任务数量
     */
    private static Integer n;

    /**
     * 启动事件
     */
    private static Integer s;

    /**
     * 单独任务所需要时间
     */
    private static int[] t;

    /**
     * 所需的费用系数
     */
    private static int[] f;


    /**
     * 辅助空间
     */
    private static int[] tt;
    private static int[] ff;
    private static int[] dp;

    /**
     * 核心算法
     */
    private static Integer solve(Integer n, Integer s, int[] t, int[] f) {
        for (int i = n; i >= 1; i--) {
            tt[i] = tt[i + 1] + t[i];
            ff[i] = ff[i + 1] + f[i];
        }
        dp[n + 1] = 0;
        for (int i = n; i >= 1; i--) {
            dp[i] = -1;
            for (int j = i + 1; j <= n + 1; j++) {
                int temp = dp[j] + ff[i] * (s + tt[i] - tt[j]);
                if (dp[i] == -1) dp[i] = temp;
                else dp[i] = Math.min(dp[i], temp);
            }
        }
        return dp[1];
    }

    /**
     * 数据初始化
     */
    private static void initData() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(INPUT_PATH)))) {
            n = Integer.parseInt(bufferedReader.readLine());
            s = Integer.parseInt(bufferedReader.readLine());
            // 辅助空间初始化
            tt = new int[n + 10];
            ff = new int[n + 10];
            dp = new int[n + 10];
            t = new int[n + 10];
            f = new int[n + 10];
            for (int i = 0; i < n; i++) {
                String[] s = bufferedReader.readLine().split(" ");
                t[i] = Integer.parseInt(s[0]);
                f[i] = Integer.parseInt(s[1]);
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
     * 测试用例
     */
    public static void main(String[] args) {
        initData();
        Integer solve = solve(n, s, t, f);
        writeData(solve);
    }
}
