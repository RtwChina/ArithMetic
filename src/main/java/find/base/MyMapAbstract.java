package find.base;

import com.apple.laf.AquaTableUI;

/**
 * @author rtw
 * @since 2019/1/26
 */
public abstract class MyMapAbstract<Key, Value> implements MyMap<Key, Value> {
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
     * 是否为空
     *
     * @return
     */
    @Override
    public Boolean isEmpty() {
        return null;
    }
}
