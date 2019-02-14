package find;

import Base.NumberUtil;
import find.base.MyMapAbstract;

/**
 * @author rtw
 * @since 2019/1/29
 */
public class MyBinaryTreeSearch<Key extends Comparable<Key>, Value> extends MyMapAbstract<Key, Value> {

    private Node root; // 二叉查找树的父节点

    private class Node {
        private Key key;      // 键
        private Value value;  // 值
        private int N;        // 以该键为根的节点数的数量
        private Node left,right; // 左右子节点

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }
    /**
     * 放入key 和 value
     *
     * @param key
     * @param value
     */
    @Override
    public void put(Key key, Value value) {
        root = this.put(root, key, value);
    }

    // 若key存在于以x为根节点的子树中，则更新它的值。
    // 否则将以key和value为键值对的新结点插入到该子树。
    private Node put(Node x, Key key, Value value) {
        // 待插入的节点为空，则新创建一个节点并返回
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0 ) {
            x.left = put(x.left, key,value);
        } else if (cmp > 0 ) {
            x.right = put(x.right, key,value);
        } else {
            x.value = value;
        }
        // 对于添加好的node，需要更新其父类的N, 每一个路过的节点需要更新其N
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * 通过key获取value
     *
     * @param key
     */
    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    // 根据节点查找相应值，以node为根节点，查找key值
    private Value get(Node node, Key key) {
        // 找到不到key值相对应的节点，返回null
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return this.get(node.right, key);
        } else if (cmp < 0) {
            return this.get(node.left, key);
        } else {
            return node.value;
        }
    }

    /**
     * 删除最小值
     */
    public void deleteMin() {
        this.deleteMin(root);
    }

    // 返回入参值，只有当入参node已经是子节点的最小值，则返回其右儿子
    private Node deleteMin(Node node) {
        // 当该节点的 左儿子 为空时，则直接返回右儿子，将其赋值到上一个递归的左儿子。
        if (node.left == null) {
            return node.right;
        }
        node.left = this.deleteMin(node.left);
        // 更新node的N
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * 删除key的相应值
     *
     * @param key
     */
    @Override
    public void delete(Key key) {
        root = this.delete(root, key);
    }

    // 删除对于key的节点
    private Node delete(Node x, Key key) {
        // 找到不到key值相对应的节点，返回null
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            // 最后有可能递归返回 相等key的节点的左右子节点，这里要接住
            return x.right = this.delete(x.right, key);
        } else if (cmp < 0) {
            return x.left = this.delete(x.left, key);
        } else {
            if (x.left == null) {
                return x.right;
            }
            if (x.right == null) {
                return x.left;
            }
            Node t = x;
            // 取x右侧的最小值，x则将替换掉被删除的节点。
            x = min(t.right);
            // 代替者x的右节点 有可能变成t的右节点的右节点(t的右节点的左节点是最小值被删除了），有可能是右节点
            x.right = deleteMin(t.right);
            // 代替者x的左节点还是t的左节点
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public static void main(String[] args) {
        MyBinaryTreeSearch<Integer, String> myBinaryTreeSearch = new MyBinaryTreeSearch<Integer, String>();
        Integer key;
        key = NumberUtil.getHundred(100);
        myBinaryTreeSearch.put(key, key + "value");
        key = NumberUtil.getHundred(100);
        myBinaryTreeSearch.put(key, key + "value");
        key = NumberUtil.getHundred(100);
        myBinaryTreeSearch.put(key, key + "value");
        key = NumberUtil.getHundred(100);
        myBinaryTreeSearch.put(key, key + "value");
        key = NumberUtil.getHundred(100);
        myBinaryTreeSearch.put(key, key + "value");
        System.out.println("ddd");

        myBinaryTreeSearch.deleteMin();
    }


    /**
     * 当前node的数量
     *
     * @return
     */
    @Override
    public int size() {
        return this.size(root);
    }
    // 对某一节点的数量
    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return this.min(x.left);
    }
    /**
     * 获得key在数组中位置
     *
     * @param key
     */
    @Override
    public int rank(Key key) {
        return 0;
    }

    /**
     * 表中所有键的集合
     */
    @Override
    public Iterable<Key> keys() {
        return null;
    }
}
