package cn.luckycurve.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author LuckyCurve
 * @date 2020/9/20 19:42
 * 比赛安排问题
 * <p>
 * <p>
 * <p>
 * 网球循环赛日程表
 * 设有n个运动员要进行网球循环赛。设计一个满足以下要求的比赛日程表：
 * （1）每个选手必须与其他n-1个选手各赛一次。
 * （2）每个选手一天只能赛一次
 * （3）当n是偶数时循环赛进行n-1天，当n是奇数时循环赛进行n天。
 */
public class MatchSchedule {


    /**
     * @param n 运动员人数
     * @return 比赛安排结果
     */
    public static List<Data> schedule(Integer n) {

        // 比赛天数判定
        int date = n % 2 == 0 ? n - 1 : n;

        // 记录已经比赛的场次，防止重复
        List<String> scheduled = new ArrayList<>();

        // 返回结果
        ArrayList<Data> result = new ArrayList<>();

        // 循环每一天
        for (int i = 1; i <= date; i++) {
            final List<Integer> list = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());

            Data data = new Data(i, new ArrayList<>());
            do {
                int index1 = (int) (Math.random() * list.size());
                int index2 = (int) (Math.random() * list.size());

                if (index1 == index2) {
                    continue;
                }

                Integer obj1 = list.get(index1);
                Integer obj2 = list.get(index2);
                String temp = obj1 + "-" + obj2;
                if (result.contains(temp)) {
                    continue;
                }
                // 结果记录
                data.getInfo().add(temp);
                // 将这两只队伍从List中移除，先删除大的再删除小的
                if (index1 > index2) {
                    list.remove(index1);
                    list.remove(index2);
                } else {
                    list.remove(index2);
                    list.remove(index1);
                }
            } while (list.size() != 0 && list.size() != 1);

            result.add(data);
        }

        return result;
    }


    /**
     * 奇偶同时测试
     */
    public static void main(String[] args) {
        List<Data> schedule1 = schedule(9);
        schedule1.forEach(System.out::println);

        System.out.println("===============================");

        List<Data> schedule2 = schedule(10);
        schedule2.forEach(System.out::println);
    }


    /**
     * 记录一天的比赛安排结果
     */
    static class Data {
        /**
         * 第几天
         */
        private Integer date;

        /**
         * 当前队伍比赛情况，如“1-2”即运动员1与2进行比赛
         */
        private List<String> info;

        public Data(Integer date, List<String> info) {
            this.date = date;
            this.info = info;
        }

        public Integer getDate() {
            return date;
        }

        public void setDate(Integer date) {
            this.date = date;
        }

        public List<String> getInfo() {
            return info;
        }

        public void setInfo(List<String> info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "date=" + date +
                    ", info=" + info +
                    '}';
        }
    }
}
