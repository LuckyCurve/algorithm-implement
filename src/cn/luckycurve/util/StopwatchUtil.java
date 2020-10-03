package cn.luckycurve.util;

import java.util.function.Consumer;

/**
 * @author LuckyCurve
 * @date 2020/10/3 13:15
 * 时间统计工具
 */
public class StopwatchUtil {

    /**
     * 返回程序执行时间，单位为ms
     *
     * @param runnable 需要执行的任务
     */
    public static void stopwatch(Runnable runnable) {
        long startTime = System.currentTimeMillis();

        runnable.run();

        long endTime = System.currentTimeMillis();


        System.out.println("花费时间：" + (endTime - startTime)+" ms");
    }
}
