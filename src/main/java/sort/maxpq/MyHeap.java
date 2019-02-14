package sort.maxpq;

import sort.base.MyMaxPQAbstract;

/**
 * 堆有序
 * @author rtw
 * @since 2019/1/25
 */
public class MyHeap extends MyMaxPQAbstract {
    public static void main(String[] args) {
        MyHeap myHeap = new MyHeap(100);
        for (int i = 0; i < 20; i++) {
            myHeap.insert((int) (Math.random()*100));
        }
        System.out.println(myHeap.max());
        System.out.println(myHeap.max());
        System.out.println("sads");
    }

    public MyHeap(int maxN) {
        super(maxN);
    }

    /**
     * 插入一个元素
     *
     * @param value
     */
    @Override
    public void insert(int value) {
        super.arg[++size] = value;
        swim(size);
    }

    /**
     * 返回最大值
     *
     * @return
     */
    @Override
    public int max() {
        // 注意开始是在1的位置哦
        int max = arg[1];
        // 将最后一个元素放到第一个位置上。并且把size-1
        change(1, size--);
        // 需要把移动掉的元素设置为0
        arg[size +1] = 0;
        sink(1);
        return max;
    }

    /**
     * 删除其最大值
     *
     * @return
     */
    @Override
    public int delMax() {
        return 0;
    }

    /**
     * 将K位置上的元素 上浮。
     * @param k
     */
    private void swim(int k) {
        // 一直循环直到k到达了头结点
        while (k > 1 && (arg[k/2] < arg[k])) {
            // 因为k的头结点比k的元素小，所以需要先将两者调换一下
            super.change(k/2,k);
            k=k/2;
        }
    }

    /**
     * 将K位置上的元素 下沉
     * @param k
     */
    private void sink(int k) {
        while (2*k<=size) {
            // 取得左儿子的位置
            int j = 2 * k;
            // 当还有一个右儿子的时候，比较一下左儿子和右儿子，j赋值为大儿子
            if (j < size && arg[j] < arg[j + 1]) {
                j++;
            }
            // 当k值大于等于大儿子时，结束循环
            if (arg[k] >= arg[j]) {
                break;
            }
            // 当大儿子大于k值时，交换两者位置
            super.change(k, j);
            k = j;
        }
    }
}

