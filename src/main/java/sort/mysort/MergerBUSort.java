package sort.mysort;

import lombok.extern.slf4j.Slf4j;
import sort.base.MySort;
import sort.base.MySortAbstract;

/**
 * 归并排序，自底向上
 * @author rtw
 * @since 2019/1/22
 */
@Slf4j
public class MergerBUSort extends MySortAbstract {
    private int[] temp;


    public static void main(String[] args) {
        MySort mySort = new MergerBUSort();
        mySort.init(100);
        mySort.sort();
        log.info("排序是否成功={}", mySort.isSort());
    }

    /**
     * 排序 归并排序，自底向上
     */
    @Override
    public void sort() {
        int N = arg.length;
        temp = new int[N];
        // sz 是子数组的大小
        for (int sz = 1; sz < N; sz = sz + sz) {
            // lo子数组索引, 每次增加一个sz,进行0-sz,sz-2*sz,2*sz-3sz的排序。
            for (int lo = 0; lo< N -sz;lo = lo +  sz + sz) {
                merge(arg, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    /*
     * 2. 递归是对入参进行处理
     * 参数说明：
     *     a -- 包含两个有序区间的数组
     *     start -- 第1个有序区间的起始地址。
     *     mid   -- 第1个有序区间的结束地址。也是第2个有序区间的起始地址。
     *     end   -- 第2个有序区间的结束地址。
     */
    private void merge(int[] a, int lo, int mid, int hi) {
        int i = lo;            // 第1个有序区的索引
        int j = mid + 1;        // 第2个有序区的索引
        int k = 0;                // 临时区域的索引
        // 将a的数组都复制到tmp中
        for (int p = lo; p <= hi; p++) {
            temp[p] = a[p];
        }

        for (int p = lo; p <= hi; p++) {
            if (i > mid) {
                // 表示左边的数组已经比较消耗完毕，现在直接把右边数组都放到a数组中就可以了。
                a[p] = temp[j++];
            } else if (j > hi) {
                // 表示右边的数组已经比较消耗完毕，现在直接把左边数组都放到a数组中就可以了。
                a[p] = temp[i++];
            } else if (temp[i] < temp[j]) {
                // 将小的数字放入到a中，进行排序。
                a[p] = temp[i++];
            } else {
                // (tmp[i] > tmp[j])
                a[p] = temp[j++];
            }
        }
    }
}
