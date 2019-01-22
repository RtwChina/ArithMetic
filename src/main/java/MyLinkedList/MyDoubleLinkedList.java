package MyLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 双向链表
 * @author rtw
 * @since 2019/1/6
 */
public class MyDoubleLinkedList<E> implements Iterable<E> {

    //加载logger的语句- 加粗部分为log4j配置文件中配置的logger的名字
    Logger log = LoggerFactory.getLogger(MyDoubleLinkedList.class);

    int size;

    DoubleNode<E> first;

    DoubleNode<E> end;

    // 表头插入
    public void addFirst(E e) {
        DoubleNode node = new DoubleNode(e);
        if (isEmpty()) {
            // 第一次插入
            first = node;
            end = first;
        } else {
            node.last = first;
            first.pre = node;
            first = node;
        }
        size++;
    }
    // 表尾插入
    public void addEnd(E e) {
        DoubleNode node = new DoubleNode(e);
        if (isEmpty()) {
            // 第一次插入
            end = node;
            first = end;
        } else {
            node.pre = end;
            end.last = node;
            end = node;
        }
        size++;
    }

    // 取出第一个元素
    public E popFirst() {
        // 为空则返回null
        if (isEmpty()) {
            return null;
        }
        DoubleNode<E> node = first;
        size--;
        // 只有单个元素
        if (first == end) {
            first = null;
            end = null;
            return node.item;
        } else {
        // 多个元素
            first = first.last;
            first.pre = null;
            return node.item;
        }
    }

    // 取出最后一个元素
    public E popEnd() {
        // 为空则返回null
        if (isEmpty()) {
            return null;
        }
        DoubleNode<E> node = end;
        size--;
        // 只有单个元素
        if (first == end) {
            first = null;
            end = null;
            return node.item;
        } else {
            // 多个元素
            end = end.pre;
            end.last = null;
            return node.item;
        }
    }

    // 指定节点插入元素 position从0开始，插入元素后该元素位置就是队列的第position个位置（从0开始）
    public void add(int position, E e) {
        if (position < 0 || position > size) {
            log.error("position位置超出限制，position={}, size={}", position, size);
            throw new RuntimeException("position位置超出限制");
        }
        if (position==0) {
            this.addFirst(e);
            return;
        }
        if (position == size) {
            this.addEnd(e);
            return;
        }
        DoubleNode node = first;
        int i= 0;
        do {
            if (i == position) {
                DoubleNode nodePre = node.pre;
                DoubleNode nodeLast = node;
                DoubleNode doubleNode = new DoubleNode(e);
                nodePre.last = doubleNode;
                doubleNode.pre = nodePre;
                nodeLast.pre = doubleNode;
                doubleNode.last = nodeLast;
                size++;
                break;
            }
            i++;
            node = node.last;
        } while (node.last != null);
    }

    // 删除指定节点的元素
    public void delByPosition(int position) {
        if (position < 0 || position > size) {
            log.error("position位置超出限制，position={}, size={}", position, size);
            throw new RuntimeException("position位置超出限制");
        }
        if (position==0) {
            this.popFirst();
            return;
        }
        if (position == size) {
            this.popEnd();
            return;
        }
        DoubleNode node = first;
        int i= 0;
        do {
            if (i == position) {
                DoubleNode nodePre = node.pre;
                nodePre.last = node.last;
                size--;
                break;
            }
            i++;
            node = node.last;
        } while (node.last != null);
    }


    public Boolean isEmpty() {
        return size == 0;
    }

    /**
     * 实现Iterable接口，返回一个迭代器
     */
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    // 创建一个迭代器
    public class ListIterator implements Iterator<E> {
        private DoubleNode<E> current = first;
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         */
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E e = current.item;
            current = current.last;
            return e;
        }
    }

    public class DoubleNode<Item> {
        private Item item;
        private DoubleNode pre;
        private DoubleNode last;

        public DoubleNode(Item item) {
            this.item = item;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public DoubleNode getPre() {
            return pre;
        }

        public void setPre(DoubleNode pre) {
            this.pre = pre;
        }

        public DoubleNode getLast() {
            return last;
        }

        public void setLast(DoubleNode last) {
            this.last = last;
        }
    }
}
