package sort.base;

/**
 * 优先队列APU
 * @author rtw
 * @since 2019/1/25
 */
public interface MyMaxPQ {
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
     * 插入一个元素
     * @param value
     */
    void insert(int value);

    /**
     * 返回最大值
     * @return
     */
    int max();

    /**
     * 删除其最大值
     * @return
     */
    int delMax();

    /**
     * 是否为空
     * @return
     */
    Boolean isEmpty();

    /**
     * 队列大小
     * @return
     */
    int size();
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
