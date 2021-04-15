package me.lwan.algorithm.lab15;

/**
 * 并查集问题<br/>
 * 省份数量<br/>
 * @see <a href="https://leetcode-cn.com/problems/number-of-provinces/">省份数量</a>
 */
public class C15_CircleNum {

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.size();
    }

    private static class UnionFind {
        private final int[] parents;
        private final int[] sizes;
//        private final int[] tmp;
        private int setSize; // 集合数量
        UnionFind(int n) {
            this.parents = new int[n];
            this.sizes = new int[n];
//            this.tmp = new int[n];
            for (int i = 0; i < n; i++) {
                this.parents[i] = i;
                this.sizes[i] = 1;
            }
            this.setSize = n;
        }

        private int findAncestor(int i) {
//            int n = 0;
            while (i != parents[i]) {
//                tmp[n++] = i;
                i = parents[i];
            }
//            while (n-- > 0) {
//                parents[tmp[n]] = i;
//            }
            return i;
        }

        public void union(int i, int j) {
            int ancestorI = findAncestor(i);
            int ancestorJ = findAncestor(j);
            if (ancestorI == ancestorJ) {
                return;
            }
            // big
            i = sizes[ancestorI] >= sizes[ancestorJ] ? ancestorI : ancestorJ;
            // small
            j = i == ancestorI ? ancestorJ : ancestorI;
            parents[j] = i;
            sizes[i] += sizes[j];
            sizes[j] = 0;
            setSize--;
        }

        public int size() {
            return setSize;
        }

    }

}
