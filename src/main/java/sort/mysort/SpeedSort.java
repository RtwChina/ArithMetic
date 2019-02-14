package sort.mysort;

import lombok.extern.slf4j.Slf4j;
import sort.base.MySort;
import sort.base.MySortAbstract;

/**
 * 快速排序
 * @author rtw
 * @since 2019/1/22
 */
@Slf4j
public class SpeedSort extends MySortAbstract {

    public static void main(String[] args) {
        MySort mySort = new SpeedSort();
        int[] a = new int[]{1,3,3,3,3,3,2,3,3,3};
        mySort.init(a);
        mySort.sort();
        log.info("排序是否成功={}", mySort.isSort());
    }
    /**
     * 排序 快速排序
     */
    @Override
    public void sort() {
        sort(arg, 0,size - 1);

//        threeSort(arg, 0,size - 1);
    }

    /**
     * 快速排序 递归
     */
    private void sort(int[] a, int lo, int hi) {
        // 递归终止条件，当只有一个数字时直接返回
        if (hi <= lo ) {
            return;
        }
        // 进行切分，保证a[lo] ~ a[j-1]都小于a[j],a[j+1]~a[hi]都大于a[j]
        int j = partition(a, lo, hi);
        // a[lo] ~ a[j-1]再次进行切分排序
        this.sort(a, lo, j -1 );
        // a[j+1]~a[hi]再次进行切分排序
        this.sort(a, j + 1, hi);
    }

    /**
     * 快速排序 递归 三分法，可解决当数组有大量的重复元素时的复杂线性对数降到 线性级别
     */
    private void threeSort(int[] a, int lo, int hi) {
        // 递归终止条件，当只有一个数字时直接返回
        if (hi <= lo ) {
            return;
        }
        int compare = a[lo];
        int i = lo + 1;
        // 比切分元素小的下标
        int lt = lo;
        // 比切分元素大的下标
        int ht = hi;
        while (i <= ht) {
            if (compare < a[i]) {
                // 当遇到一个元素大于切分元素时，将该元素放到末尾去，
                // 然后把末尾的元素放到i的位置上，继续使用i的元素与切分元素判读
                super.change(i, ht--);
            } else if (compare > a[i]) {
                // 当遇到一个元素小于切分元素时，将该元素放到开头去，然后把i和lt走自增
                super.change(i++, lt++);
            } else {
                // 遇到与切分元素相同的情况，只增加i.
                i++;
            }
        }
        // a[lo] ~ a[j-1]再次进行切分排序
        this.threeSort(a, lo, lt - 1);
        // a[j+1]~a[hi]再次进行切分排序
        this.threeSort(a, ht + 1, hi);
    }

    /**
     * 数据切分
     * @param a 待排序数组
     * @param lo 数组下标
     * @param hi 数组上标
     * @return 最后的切分元素所在的位置
     */
    private int partition(int[] a, int lo, int hi) {
        // 切分元素
        int compare = a[lo];
        int i = lo;
        int j = hi + 1;

        while (true) {
            // 一直++i直到遇到一个元素大于 compare
            while (a[++i] > compare) {
                if (i == hi) {
                    break;
                }
            }
            // 一直--j直到遇到一个元素小于 compare
            while (a[--j] < compare) {
                if (j == lo) {
                    break;
                }
            }
            // i左边的数都小于 compare,
            // j右边的数都大于 compare,
            // 例如 10 1 2 3 11; i等于4，j等于3，直接跳出循环，将0和j互换下就可以了
            if (i >= j) {
                break;
            }
            super.change(i,j);
        }
        super.change(lo,j);
        return j;
    }
}
