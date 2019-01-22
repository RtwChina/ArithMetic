package sort;

import lombok.extern.slf4j.Slf4j;
import sort.base.MySort;
import sort.base.MySortAbstract;

/**
 * 选择排序
 * @author rtw
 * @since 2019/1/21
 */
@Slf4j
public class SelectSort extends MySortAbstract {
    /**
     * 选择排序
     * 排序
     */
    @Override
    public void sort() {
        for (int i = super.size; i > 0; i--) {
            int max = super.arg[i - 1];
            int index = i - 1;
            //求出范围内的最大值max，最大值所在坐标就是index.
            for (int j = 0; j < i; j++) {
                //如果最后面的数 比 前面的某一个数字小 交换两个数字
                if (max < super.arg[j]) {
                    max = super.arg[j];
                    index = j;
                }
            }
            //交换index坐标上 和 i-1 上的数据，如果最大值任然在i-1上，交换后还是不动
            super.change(i - 1, index);
        }
    }

    public static void main(String[] args) {
        MySort mySort = new SelectSort();
        mySort.init(100);
        mySort.sort();
        log.info("排序是否成功={}", mySort.isSort());
    }
}
