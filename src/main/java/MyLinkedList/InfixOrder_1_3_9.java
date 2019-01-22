package MyLinkedList;

import javafx.beans.binding.StringBinding;

/**
 * @author rtw
 * @since 2019/1/6
 */
public class InfixOrder_1_3_9 {
    private static final char LEFT_PAREN     = '(';
    private static final char RIGHT_PAREN    = ')';

    // 数值栈
    private static MyStack<String> valueStack = new MyStack();
    // 操作数栈
    private static MyStack<String> operandStack = new MyStack();

    public static String supplement(String src) {
        for (int i = 0; i < src.length(); i++) {
            Character character = src.charAt(i);
            if (character == '+' || character == '-' || character == '*' || character == '/') {
                operandStack.push(character.toString());
            } else if (character == ')') {
                // 当是一个右括号的时候，取出两个数值 一个操作合成一串数据
                String str = ')' + valueStack.pop() + operandStack.pop() + valueStack.pop() + '(';
                // 把他当做一个元素，将其添加到操作数栈
                valueStack.push(str);
            } else {
                valueStack.push(character.toString());
            }
        }

        // 合成一个完整的字符串
      String fanxuStr = valueStack.pop();
        String seq = "";
        for (int i = fanxuStr.length()-1; i >= 0; i--){
            seq += fanxuStr.charAt(i);
        }
        return seq;
    }

    public static void main(String[] args) {
        String r1 = supplement("1+2)");
        String r2 = supplement("1+2)*3-4)*5-6)))");
        String r3 = supplement("1+2)+5)");
    }
}
