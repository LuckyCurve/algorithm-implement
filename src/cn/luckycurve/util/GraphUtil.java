package cn.luckycurve.util;

import java.util.List;

/**
 * @author LuckyCurve
 * @date 2020/10/15 19:10
 * 图的工具类
 */
public class GraphUtil {

    public static String listPath(List<Integer> list) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size() - 1; i++) {
            builder.append(list.get(i)).append("->");
        }
        builder.append(list.get(list.size() - 1));
        return builder.toString();
    }
}
