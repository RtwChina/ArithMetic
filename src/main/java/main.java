import edu.princeton.cs.algs4.StdDraw;

/**
 * @author rtw
 * @since 2019/1/5
 */
public class main {
    public static void main(String[] args) {
        int N = 100;
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N*N);
        StdDraw.setPenRadius(.01);
        for (int i = 1; i<=N;i++){
            StdDraw.point(i,i);
            StdDraw.point(i, i*i);

        }
    }
}
