package MyLinkedList;

import java.util.Iterator;

/**
 * 下压栈 FILO
 * @author rtw
 * @since 2019/1/6
 */
public class MyStack<E>{

    int size;

    Node<E> first;

    // 往下压栈中添加元素
    public void push(E e) {
        Node oldFirst = first;
        first = new Node<E>();
        first.setItem(e);
        first.setNextNode(oldFirst);
        size++;
    }

    // 往下压栈中拿出元素
    public E pop() {
      Node<E> returnNode = first;
      first = first.getNextNode();
      size--;
      return returnNode.getItem();
    }

    public Boolean isEmpty() {
        return size==0;
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
