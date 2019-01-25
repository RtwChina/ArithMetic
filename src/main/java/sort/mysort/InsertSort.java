package sort.mysort;

import com.alibaba.fastjson.JSON;
import sort.base.MySortAbstract;

/**
 * @author rtw
 * @since 2019/1/19
 */
public class InsertSort extends MySortAbstract {
    /**
     * 排序  插入排序
     */
    @Override
    public void sort() {
        int i; //序列中的标记，左侧的队列都是一件排序好的
        int j;
        int temp;
        for (i = 0; i < size -1 ; i++) {
            for (j = i; j >= 0; j--) {
                // 处理当前有序队列 右边第一位一位数字 即 需要进行排序的元素
                int sortItem = j + 1;
                int sortM = arg[sortItem]; // 需要进行排序的元素
                // 当待排序元素 小于 前元素，则将两者位置交换
                if (sortM < arg[j]) {
                    // 交换 sortItem 和 j上面的元素
                    change(sortItem, j);
                } else {
                    // 一直到0-i的序列有序
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        insertSort.init(20);
        insertSort.sort();
        System.out.println(JSON.toJSON(insertSort.arg));
        System.out.println(insertSort.isSort());
    }
}
