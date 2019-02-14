package sort;

import edu.princeton.cs.algs4.StdDraw;
import java.awt.Color;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import Base.ArraysUtil;
import sort.base.MySort;
import sort.mysort.InsertSort;
import sort.mysort.MergerSort;
import sort.mysort.SelectSort;
import sort.mysort.ShellSort;
import sort.mysort.SpeedSort;

/**
 * 算法之间的比较哦
 * @author rtw
 * @since 2019/1/21
 */
@Slf4j
public class CompareSort {
    public static void main(String[] args) {
        try {
            CompareSort.MergeAndShellAndSpeed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 希尔算法 和  插入算法 的比较
    public static void ShellAndInsert() {
        int N = 10000000; // 数组长度
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, 20000); // 单位为毫秒
        StdDraw.setPenRadius(.001);

        for (int i = 0; i< N ; i += 5000) {
            // 先创建一个i长度的数组
            int[] arrays = ArraysUtil.getArray(i);

            ShellSort sort = new ShellSort();
            sort.init(arrays);
            LocalDateTime shellTime1 = LocalDateTime.now();
            sort.sort();
            Duration shellDuration = Duration.between(shellTime1, LocalDateTime.now());
            long shellMillis = shellDuration.toMillis(); // 获得duration的毫秒

            InsertSort insertSort = new InsertSort();
            insertSort.init(arrays);
            LocalDateTime insertTIme1 = LocalDateTime.now();
            insertSort.sort();
            Duration insertDuration = Duration.between(insertTIme1, LocalDateTime.now());
            long insertMillis = insertDuration.toMillis(); // 获得duration的毫秒


            // shell为红色，insert为黑色
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(i,shellMillis * 100);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.point(i,insertMillis);


            log.info("希尔：待排序数组长度={},所需时间={}", i, shellDuration);
            log.info("插入：待排序数组长度={},所需时间={}", i, insertDuration);
        }
    }

    // 插入、选择、希尔、归并算法
    public static void MergeAndShell() {
        int N = 1000000; // 数组长度
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, 1000); // 单位为毫秒
        StdDraw.setPenRadius(.001);
        for (int i = 0; i< N ; i += 500) {
            // 先创建一个i长度的数组
            int[] arrays = ArraysUtil.getArray(i);
            sortAndPointInMillis(ShellSort.class, Color.RED, arrays);
            sortAndPointInMillis(InsertSort.class, Color.CYAN, arrays);
            sortAndPointInMillis(SelectSort.class, Color.ORANGE, arrays);
            sortAndPointInMillis(MergerSort.class, Color.BLACK, arrays);
            log.info("==========================");
        }
    }

    // 希尔、归并、快速
    public static void MergeAndShellAndSpeed() {
        int N = 1000000; // 数组长度
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, 200); // 单位为毫秒
        StdDraw.setPenRadius(.001);
        for (int i = 0; i< N ; i += 500) {
            // 先创建一个i长度的数组
            int[] arrays = ArraysUtil.getArray(i);
            sortAndPointInMillis(ShellSort.class, Color.RED, arrays);
            sortAndPointInMillis(MergerSort.class, Color.BLACK, arrays);
            sortAndPointInMillis(SpeedSort.class, Color.BLUE, arrays);
            log.info("==========================");
        }
    }

    /**
     * 通过排序算法对相对应的 数组排序、计算时间、绘画
     * @param src
     * @param color
     * @param arrays
     */
    public static void sortAndPointInMillis(Class<? extends MySort> src, Color color, int[] arrays) {
        MySort mySort;
        try {
            mySort = (MySort) src.newInstance();
            mySort.init(arrays);
            LocalDateTime startTime = LocalDateTime.now();
            mySort.sort();
            Duration duration = Duration.between(startTime, LocalDateTime.now());
            long durationMillis = duration.toMillis(); // 获得duration的毫秒
            log.info("排序方式={}：待排序数组长度={},所需时间={}ms", src.getName(),arrays.length, durationMillis);

            StdDraw.setPenColor(color);
            StdDraw.point(arrays.length, durationMillis);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
