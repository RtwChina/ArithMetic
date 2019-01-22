package sort.base;

/**
 * @author rtw
 * @since 2019/1/19
 */
public interface MySort {

    /**
     * 初始化n个元素的数组
     * @param n
     */
    int[] init(int n);

    /**
     * 将传入的数组 【复制】到属性中
     */
    void init(int[] arrays);
    /**
     * 排序
     */
    void sort();

    /**
     * 是否已有序
     * @return
     */
    Boolean isSort();

    /**
     * 数组 两者相互交换
     * @return
     */
    void change(int i, int j);
}
