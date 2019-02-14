package find.base;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * 符号表
 * @author rtw
 * @since 2019/1/26
 */
public interface MyMap<Key, Value> {
    /**
     * 放入key 和 value
     */
    void put(Key key, Value value);

    /**
     * 通过key获取value
     */
    Value get(Key key);

    /**
     * 删除key的相应值
     */
    void delete(Key key);

    /**
     * 判断key值上是否有值
     */
    Boolean contains(Key Key);

    /**
     * 是否为空
     * @return
     */
    Boolean isEmpty();

    /**
     * key值数量
     * @return
     */
    int size();

    /**
     * 获得key在数组中位置
     */
    int rank(Key key);
    /**
     * 表中所有键的集合
     */
    Iterable<Key> keys();
}
