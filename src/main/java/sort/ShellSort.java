package sort;

import com.alibaba.fastjson.JSON;
import edu.princeton.cs.algs4.StdDraw;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import sort.base.MySortAbstract;

/**
 * 希尔排序
 *
 * @author rtw
 * @since 2019/1/21
 */
@Slf4j
public class ShellSort extends MySortAbstract {
    public int bijiao = 0;
    public int jiaohuan = 0;
    /**
     * 排序 希尔排序
     */
    @Override
    public void sort() {
        // 数组Size
        int N = arg.length;
        // 当前多数组的间隔
        int h = 1;
        // 先计算一波h的最大程度。
        while (h < N / 2) {
            h = 3 * h + 1;
        }
        // 最后就是一次简单地插入排序
        while (h >= 1) {
            // 取第一个h的最右值，然后逐步递增至N。
            for (int i = h; i < N; i++) {
                // i就是h的右值，然后i-h就是同一个组的前值，向前比较就可以了，因为后面的会随着i的递增给你比进去。
                for (int j = i; j >= h; j -= h) {
                    // 如果前一个数大于后一个数，则需要相互交换。
                    // 然后再j -= h，再次往前比较，当前一个数小于后一个数的时候则直接结束循环
                    bijiao++;
                    if (arg[j] < arg[j - h]) {
                        jiaohuan ++ ;
                        change(j, j - h);
                    } else {
                        break;
                    }
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        int N = 1000000000; // 数组长度
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, 200000); // 单位为毫秒
        StdDraw.setPenRadius(.001);

        for (int i = 0; i< N ; i += 1000000) {
            ShellSort sort = new ShellSort();
            sort.init(i);
            LocalDateTime one = LocalDateTime.now();
            sort.sort();
            Duration duration = Duration.between(one, LocalDateTime.now());
            long millis = duration.toMillis(); // 获得duration的毫秒
            StdDraw.point(i,millis);

            log.info("待排序数组长度={},比较次数={},交换次数={},所需时间={}", i, sort.bijiao, sort.jiaohuan, duration);
        }
    }
}
