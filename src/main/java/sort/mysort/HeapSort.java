package sort.mysort;

import lombok.extern.slf4j.Slf4j;
import sort.base.MySort;
import sort.base.MySortAbstract;

/**
 * 堆排序
 *
 * @author rtw
 * @since 2019/1/26
 */
@Slf4j
public class HeapSort extends MySortAbstract {
    public static void main(String[] args) {
        HeapSort mySort = new HeapSort();
        mySort.init(222);
        mySort.sort();
        log.info("排序是否成功={}", mySort.isSort());
    }

    @Override
    public int[] init(int n) {
        arg = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            arg[i] = (int) (Math.random() * 100);
        }
        size = n;
        return arg;
    }

    /**
     * 排序 堆排序
     */
    @Override
    public void sort() {
        int N = size;
        // 实现堆有序
        for (int i = N / 2; i > 0; i--) {
            // 对所有父节点，从最后一个父节点到根节点，依次下沉排序
            sink(arg, i, N);
        }
        // 堆有序后，实现数组有序
        while (N > 1) {
            // 将最大的节点放到最后
            super.change(1, N);
            N--;
            // 对头结点下沉处理
            sink(arg, 1, N);
        }

    }

    /**
     * 将K位置上的元素 下沉
     *
     * @param k 堆中的节点位置
     * @param N 堆中的节点总数
     */
    private void sink(int[] a, int k, int N) {
        while (2 * k <= N) {
            // 取得左儿子的位置
            int j = 2 * k;
            // 当还有一个右儿子的时候，比较一下左儿子和右儿子，j赋值为大儿子
            if (j < N && a[j] < a[j + 1]) {
                j++;
            }
            // 当k值大于等于大儿子时，结束循环
            if (a[k] >= a[j]) {
                break;
            }
            // 当大儿子大于k值时，交换两者位置
            super.change(k, j);
            k = j;
        }
    }
}
