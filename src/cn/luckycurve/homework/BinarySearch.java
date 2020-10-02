package cn.luckycurve.homework;

/**
 * 二分查找
 */
public class BinarySearch {

    public static Data binarySearch(Integer[] obj, Integer x) {
        int low = 0;
        int high = obj.length - 1;

        while (low <= high) {
            int middle = (low + high) / 2;
            if (x > obj[middle]) {
                low = middle + 1;
            } else if (x < obj[middle]) {
                high = middle - 1;
            } else {
                return new Data(middle, middle);
            }
        }
        return new Data(high, low);
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        Integer[] obj = {1, 2, 3, 4, 5, 6, 8, 9, 10};
        System.out.println(binarySearch(obj, 3));
        System.out.println(binarySearch(obj, -1));
        System.out.println(binarySearch(obj, 7));
        System.out.println(binarySearch(obj, 11));
    }

    static class Data {
        private Integer i;
        private Integer j;

        public Data(Integer i, Integer j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }
}
