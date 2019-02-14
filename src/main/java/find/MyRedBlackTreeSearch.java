package find;

import find.base.MyMapAbstract;

/**
 * @author rtw
 * @since 2019/1/30
 */
public class MyRedBlackTreeSearch<Key extends Comparable<Key>, Value> extends MyMapAbstract<Key, Value> {
    private Node root; // 二叉查找树的父节点

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private class Node {
        private Key key;      // 键
        private Value value;  // 值
        private int N;        // 以该键为根的节点数的数量
        private Node left,right; // 左右子节点
        private boolean color; // 与其父节点链接的颜色

        public Node(Key key, Value value, int n, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
            N = n;
        }
    }
    // 左旋转
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    // 右旋转
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    // 转换一个节点两个红色子节点的颜色,注意根节点都是黑色的
    private void flipColors(Node h) {
//        h.color = RED;
//        h.left.color = BLACK;
//        h.right.color = BLACK;
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
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
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value value) {
        // 默认新创建的节点为红色
        if (h == null) {
            return new Node(key, value, 1, RED);
        }
        // 这部分主要往二叉树的子节点上插入结点
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = this.put(h.left, key, value);
        } else if (cmp > 0) {
            h.right = this.put(h.right, key, value);
        } else {
            h.value = value;
        }
        // 插入节点后，需要按照三种不同的情况进行处理了
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }
    /**
     * 通过key获取value
     *
     * @param key
     */
    @Override
    public Value get(Key key) {
        return null;
    }

    /**
     * 删除key的相应值
     *
     * @param key
     */
    @Override
    public void delete(Key key) {

    }

    // 删除最小键
    private Node deleteMin(Node node) {
        // 递归结束条件
        if (node.left == null) {
            return null;
        }
        //保证node或node.left为红节点，注意是从父节点入手
        // node的左儿子为黑色，node的左儿子的左儿子为黑色
        if (!isRed(node.left) && !isRed(node.left.left))
            node = moveRedLeft(node);

        //递归地在左子树中删除
        node.left = deleteMin(node.left);
        //删除后修复红色右节点（链接）
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    // 对于当前结点，从其兄弟结点（父节点的右节点）中借一个节点
    private Node moveRedLeft(Node node) {
        // 先颜色转化
        flipColors(node);
        // node的右孩子左孩子是非红，才会进行左右旋。
        if (isRed(node.right.left)) {
            //判断其兄弟节点的左孩子是否为红，若是，对当前节点的父节点进行左-右双旋转且颜色要转换
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    //删除最大键
    private Node deleteMax(Node node){
        // 使树出现红色右链接
        if(isRed(node.left))
            node = rotateRight(node);
        //注意上下两者的顺序不能错
        //递归结束条件，达到了右边界
        if(node.right == null)
            return null;

        //保证node或node.right为红节点，注意也是从父节点入手
        if(!isRed(node.right) && !isRed(node.right.left))
            //因为3节点是用红节点来模拟的，红节点不可能是右孩子，所以不可能是h.right.right，通过上面的一次左旋转，可以获得与h.right.right同样的效果
            node = moveRedRight(node);
        //递归在右子树中删除
        node.right = deleteMax(node.right);
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }
    //对于当前节点，从其兄弟节点（当前节点父节点左孩子）中借一个节点
    private Node moveRedRight(Node node){
        //颜色转换
        flipColors(node);
        if(isRed(node.left.left)){
            //判断其兄弟节点的左孩子是否为红，若是，对当前节点的父节点进行左旋转且颜色要转换
            //每次都是判断兄弟节点的左孩子，因为在借之前，在其父节点下面的节点只能有红色左链接
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    /**
     * key值数量
     *
     * @return
     */
    @Override
    public int size() {
        return 0;
    }

    // 对某一节点的数量
    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
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
    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }
}
