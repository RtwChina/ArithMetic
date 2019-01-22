package sort.base;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

/**
 * @author rtw
 * @since 2019/1/19
 */
@Slf4j
public abstract class MySortAbstract implements MySort{
    protected int[] arg;
    protected int size;

    public int[] init(int n) {
        arg = new int[n];
        for (int i = 0; i<n;i++) {
            arg[i] = (int) (Math.random()*100);
        }
        size = n;
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
