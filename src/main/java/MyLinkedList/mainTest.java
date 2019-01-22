package MyLinkedList;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * @author rtw
 * @since 2019/1/6
 */
public class mainTest {
    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        testMyDoubleLinkedList();
        System.out.println(stopwatch.elapsedTime());

    }
    public static void testMyDoubleLinkedList() {
        MyDoubleLinkedList<String> list = new MyDoubleLinkedList();
        for (int i = 0; i < 6; i ++) {
            list.addFirst("shuai" + i);
        }
        for (int i = 0; i < 6; i ++) {
            list.addEnd("尾巴" + i);
        }
        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("over");
    }

    public static void testMyLinkedList() {
        MyLinkedList myLinkedList = new MyLinkedList();
        for (int i = 0; i < 6; i ++) {
            myLinkedList.enqueue("帅" + i);
        }
        myLinkedList.delLast();

        System.out.println("fff");
    }
}
