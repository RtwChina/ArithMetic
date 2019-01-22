package Base;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author rtw
 * @since 2019/1/18
 */
public class UnionFind {
    private int[] id; // 分量id,以触点作为索引
    private int count; // 分量数量

    // 初始化N个节点
    public void UF(int n){
        // 初始化
        count = n;
        id =new int[n];
        for (int i = 0;i<n;i++) {
            id[i] = i;
        }

    }
    // 在p和q之间添加链接
    public void union(int p, int q){
        int pId = find(p);
        int qId = find(q);
        // 已经相同就直接返回
        if (pId == qId) {
            return;
        }
        for (int i = 0;i < id.length; i++) {
            // 将所有已经与p相连的节点 都设置为 qId
            if (find(i) == pId) {
                id[i] = qId;
                count --;
            }
        }

    }
    // 如何在p和q之间存在同一个分量就返回true
    public Boolean connected(int p, int q){
        return find(p) == find(q);
    }
    // 联通分量的数量
    public int count() {
        return count;
    }
    // 找到X的联通分量
    public int find(int x) {
        return id[x];
    }

    public static void main(String[] args) throws Exception {

        In in = new In("/Users/rtw/Desktop/algs4-data/tinyUF.txt");
        int[] tiantian = in.readAllInts();

        UnionFind unionFind = new UnionFind();
        // 初始化
        unionFind.UF(10);
        int i= 0;
        while (true) {
            if (i >= tiantian.length -1 ) {
                break;
            }
            int p = i++;
            int q = i++;

            int pp = tiantian[p];
            int qq = tiantian[q];
            if (unionFind.connected(pp,qq)) {
                continue;
            }
            unionFind.union(pp,qq);
            StdOut.println(pp + " " + qq);
        }
        System.out.println("最终" + unionFind.count);
    }
}
