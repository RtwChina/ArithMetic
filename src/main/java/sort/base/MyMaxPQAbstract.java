package sort.base;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

/**
 * @author rtw
 * @since 2019/1/25
 */
@Slf4j
public abstract class MyMaxPQAbstract implements MyMaxPQ {
    protected int[] arg;
    protected int size;

    public MyMaxPQAbstract(int maxN) {
        arg = new int[maxN + 1];
    }

    /**
     * 初始化前n个元素的数组
     *
     * @param n
     */
    @Override
    public int[] init(int n) {
        for (int i = 0; i<n;i++) {
            arg[i] = (int) (Math.random()*100);
        }
        return arg;
    }

    /**
     * 将传入的数组 【复制】到属性中
     *
     * @param arrays
     */
    @Override
    public void init(int[] arrays) {
        arg = Arrays.copyOf(arrays, arrays.length);
        size = arrays.length;
    }

    /**
     * 是否为空
     *
     * @return
     */
    @Override
    public Boolean isEmpty() {
        return size == 0;
    }

    /**
     * 队列大小
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }
    public Boolean isSort() {
        for (int i = 0; i<size-1;i++) {
            if (arg[i] > arg[i+1]) {
                log.info("数组未排序，item={},arg={}", i, arg);
                return false;
            }
        }
        return true;
    }

    /**
     * 数组 两者相互交换
     *
     * @param i
     * @param j
     * @return
     */
    public void change(int i, int j) {
        int temp = arg[i];
        arg[i] = arg[j];
        arg[j] = temp;
    }
}
