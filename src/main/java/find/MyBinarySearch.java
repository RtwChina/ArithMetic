package find;

import Base.NumberUtil;
import find.base.MyMapAbstract;

/**
 * @author rtw
 * @since 2019/1/26
 */
public class MyBinarySearch<Key extends Comparable<Key>, Value> extends MyMapAbstract<Key, Value> {
    private Key[] keys;
    private Value[] values;
    private int N;

    public MyBinarySearch(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    /**
     * 放入key 和 value
     *
     * @param key
     * @param value
     */
    @Override
    public void put(Key key, Value value) {
        int i = rank(key);
        // 表示key已经存在，更新下value值就可以了
        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        // 当key不存在时，就需要创建一个性的key值，会在插入新元素前将所有较大的键往后移动一格。
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    /**
     * 通过key获取value
     *
     * @param key
     */
    @Override
    public Value get(Key key) {
        if (isEmpty())
            return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return values[i];
        } else {
            return null;
        }
    }

    /**
     * 删除key的相应值
     *
     * @param key
     */
    @Override
    public void delete(Key key) {

    }

    /**
     * 判断key值上是否有值
     *
     * @param Key
     */
    @Override
    public Boolean contains(Key Key) {
        return null;
    }

    /**
     * 获得key在数组中位置
     *
     * @param key
     */
    @Override
    public int rank(Key key) {
//        return this.iterationRank(key);
        return this.rank(key, 0, N - 1);
    }

    /**
     * 迭代的二分法
     */
    private int iterationRank(Key key) {
        int lo = 0;
        int hi = N - 1;
        // 这里要求的和递归其实是一样的，不过 一个是符合继续，一个不符合跳出
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    /**
     * 递归的二分法
     */
    private int rank(Key key, int lo, int hi) {
        // 当hi == lo时任然需要比较，因为递归的是 rank(mid - 1 )哦
        if (hi < lo) {
            // 当最后没有一个key满足时，返回lo，也就是小于key键的值的位置
            return lo;
        }
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0) {
            return rank(key, lo, mid - 1);
        } else if (cmp > 0) {
            return rank(key, mid + 1, hi);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        MyBinarySearch<Integer, String> myBinarySearch = new MyBinarySearch<Integer, String>(200);
        Integer key;
        key = NumberUtil.getHundred(100);
        myBinarySearch.put(key, key + "value");
        key = NumberUtil.getHundred(100);
        myBinarySearch.put(key, key + "value");
        key = NumberUtil.getHundred(100);
        myBinarySearch.put(key, key + "value");
        key = NumberUtil.getHundred(100);
        myBinarySearch.put(key, key + "value");
        key = NumberUtil.getHundred(100);
        myBinarySearch.put(key, key + "value");
        System.out.println("ddd");
    }

    /**
     * 是否为空
     *
     * @return
     */
    @Override
    public Boolean isEmpty() {
        return N == 0;
    }

    /**
     * key值数量
     *
     * @return
     */
    @Override
    public int size() {
        return N;
    }

    /**
     * 表中所有键的集合
     */
    @Override
    public Iterable keys() {
        return null;
    }
}
