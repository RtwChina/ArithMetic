package sort;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import sort.base.MySort;
import sort.base.MySortAbstract;

/**
 * 自顶向下 归并排序 todo https://www.cnblogs.com/skywang12345/p/3602369.html#a42
 * 共有两种算法实现：
 * 1. 递归返回的是 in[]值
 * 拆分数组时也需要进行遍历
 * 2. 递归是对入参进行处理
 * 递归直接把入参进行处理，会在整合时，多进行一次啊赋值。
 * @author rtw
 * @since 2019/1/10
 */
@Slf4j
public class MergerSort extends MySortAbstract {

    private int[] temp;

    public static void main(String[] args) {
        MySort mySort = new MergerSort();
        mySort.init(100);
        mySort.sort();
        log.info("排序是否成功={}", mySort.isSort());
    }

    /**
     * 排序 归并排序，第一种方式
     */
    @Override
    public void sort() {
        // 初始化一次 temp就够了
        temp = new int[arg.length];
        mergeSortTwo(super.arg, 0, arg.length -1);
    }

    public void sort2() {
        mergeSortOne(super.arg);
    }

    /*
     * 1. 递归返回的是 in[]值
     * 参数说明：
     *     a -- 待排序的数组
     *     start -- 数组的起始地址
     *     endi -- 数组的结束地址
     */
    public int[] mergeSortOne(int[] a) {
        if(a==null || a.length == 1 || a.length == 0)
            return a;

        int size = a.length;
        int mid = size/2;
        int[] first = new int[mid];
        int[] last = new int[a.length - mid];
        int item = 0;
        int firstItem = 0;
        int lastItem = 0;

        while (item <= size) {
            if (firstItem < mid) {
                first[firstItem++] = a[item++];
            } else if (item < size){
                last[lastItem++] = a[item++];
            } else {
                break;
            }
        }

        int[] firstMerge = mergeSortOne(first); // 递归排序a[start...mid]
        int[] lastMerge = mergeSortOne(last); // 递归排序a[mid+1...end]
        // a[start...mid] 和 a[mid...end]是两个有序空间，
        // 将它们排序成一个有序空间a[start...end]
        return merge(firstMerge, lastMerge);
    }

    /*
     * 1. 递归返回的是 in[]值
     * 将一个数组中的两个相邻有序区间合并成一个
     *
     * 参数说明：
     *     a -- 包含两个有序区间的数组
     *     start -- 第1个有序区间的起始地址。
     *     mid   -- 第1个有序区间的结束地址。也是第2个有序区间的起始地址。
     *     end   -- 第2个有序区间的结束地址。
     */
    public static int[] merge(int[] first, int[] end) {

        int firstSize = first.length; // 第一个数组的大小
        int lastSize = end.length;    // 第二个数组的大小
        // 用于存放的临时数组
        int[] temp = new int[firstSize + lastSize];
        int firstItem = 0;
        int lastItem = 0;
        int k = 0; // 临时数组的位置

        while (firstItem < firstSize && lastItem < lastSize) {
            if (first[firstItem] < end[lastItem]) {
                temp[k++] = first[firstItem++]; // 必须使用 i++，需要先赋值再自增
            } else {
                temp[k++] = end[lastItem++];  // 必须使用 i++，需要先赋值再自增
            }
        }
        if (firstItem < firstSize) {
            while (firstItem < firstSize) {
                temp[k++] = first[firstItem++]; // 必须使用 i++，需要先赋值再自增
            }
        }
        if (lastItem < lastSize) {
            while (lastItem < lastSize) {
                temp[k++] = end[lastItem++];  // 必须使用 i++，需要先赋值再自增
            }
        }
        return temp;
    }
    /*
     * 2. 递归是对入参进行处理
     * 参数说明：
     *     a -- 待排序的数组
     *     start -- 数组的起始地址
     *     endi -- 数组的结束地址
     */
    public void mergeSortTwo(int[] a, int start, int end) {
        if(a==null || start >= end)
            return ;

        int mid = (end + start)/2;
        mergeSortTwo(a, start, mid); // 递归排序a[start...mid]
        mergeSortTwo(a, mid+1, end); // 递归排序a[mid+1...end]
        // 前左右两个数组已经有序了，则可以不进行merge。
        if (a[mid] < a[mid + 1]) {
            return;
        }
        // a[start...mid] 和 a[mid...end]是两个有序空间，
        // 将它们排序成一个有序空间a[start...end]
        merge(a, start, mid, end);
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
