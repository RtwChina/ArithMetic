package MyLinkedList;

import java.util.Stack;

/**
 * @author rtw
 * @since 2019/1/6
 */
public class Symbolic_1_3_4 {
    private static final char LEFT_PAREN     = '(';
    private static final char RIGHT_PAREN    = ')';
    private static final char LEFT_BRACE     = '{';
    private static final char RIGHT_BRACE    = '}';
    private static final char LEFT_BRACKET   = '[';
    private static final char RIGHT_BRACKET  = ']';


    public static Boolean isBalanced(String s) {
        MyStack<Character> myStack = new MyStack<Character>();
        for (int i = 0; i <s.length(); i++) {
            Character character = s.charAt(i);

            if (character == LEFT_PAREN ||
                    character == LEFT_BRACE ||
                    character == LEFT_BRACKET) {
                myStack.push(character);
                continue;
            }
            if (myStack.isEmpty()) {
                return false;
            }
            // },],) 的话，要求拿出一个对象，与其相匹配。
            if (character == RIGHT_PAREN) {
                Character c = myStack.pop();
                if (c != LEFT_PAREN) {
                    return false;
                }
            }

            if (character == RIGHT_BRACE) {
                Character c = myStack.pop();
                if (c != LEFT_BRACE) {
                    return false;
                }
            }
            if (character == RIGHT_BRACKET) {
                Character c = myStack.pop();
                if (c != LEFT_BRACKET) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(isBalanced("[{}])"));
    }
}
