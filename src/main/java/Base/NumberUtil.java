package Base;

/**
 * 数字工具类
 * @author rtw
 * @since 2019/1/27
 */
public class NumberUtil {
    // 返回一个随机n 以内的数字
    public static int getHundred(int n) {
         return  (int) (Math.random()*n);
    }
}
