package MyLinkedList;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * 链表 FIFO 单向链表 塞东西往队尾塞，取数据从队头取
 * @author rtw
 * @since 2019/1/6
 */
public class MyLinkedList<E> {
    int size;

    Node<E> first;

    Node<E> last;

    public Boolean isEmpty() {
        return size == 0;
    }

    // 向表尾添加元素

    /**
     * 为空的时候添加，则fist = last,且两者的nextNode都为null.
     * 第二次添加，修改了oldLast的nextNode,那么first也随之改变了。
     * @param e
     */
    public void enqueue(E e) {
        // 为空
        Node<E> oldLast = last;
        Node<E> node = new Node<E>();
        node.setItem(e);
        last = node;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.setNextNode(node);
        }
        size++;
    }
    // 从表头删除元素
    public E dequeue() {
        Node<E> node = first;
        first = first.getNextNode();
        if (isEmpty()) {
            last = null;
        }
        size--;
        return node.getItem();
    }

    // 删除最后一个元素
    public void delLast() {
        Node node = first;
        // 遍历链表
       while (true) {
           if (node.getNextNode() == null) {
               // 表示只有一个元素
               first = null;
               last = null;
               size--;
               break;
           }
           if (node.getNextNode().getNextNode() == null) {
               last = node.getNextNode();
               size--;
               break;
           }
           node = node.getNextNode();
       }
    }

    public class Node<E> {
        private E item;

        private Node<E> nextNode;

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }

        public Node<E> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }
    }
}
