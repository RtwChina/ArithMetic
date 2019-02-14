package Base;

/**
 * 数组类工具
 * @author rtw
 * @since 2019/1/21
 */
public class ArraysUtil {
    // 返回一个随机100 以内的数组
    public static int[] getArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i<n;i++) {
            array[i] = (int) (Math.random()*100);
        }
        return array;
    }
}
