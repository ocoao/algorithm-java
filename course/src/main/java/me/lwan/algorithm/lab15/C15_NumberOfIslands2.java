package me.lwan.algorithm.lab15;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Number of Islands
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand
 * operation which turns the water at position (row, col) into a land. Given a list of positions to
 * operate, count the number of islands after each addLand operation. An island is surrounded by
 * water and is formed by connecting adjacent lands horizontally or vertically. You may assume all
 * four edges of the grid are all surrounded by water.
 * </pre>
 * @see <a href="https://leetcode-cn.com/problems/number-of-islands-ii/">岛屿数量2</a>
 */
public class C15_NumberOfIslands2 {

    public List<Integer> numIslands(int m, int n, int[][] positions) {
        UnionFind uf = new UnionFind(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int[] p : positions) {
            uf.addLand(p[0], p[1]);
            ans.add(uf.size());
        }
        return ans;
    }

    private static class UnionFind {
        private final int[] parents;
        private final int[] sizes;
        private final int[] tmp;
        private final int rows;
        private final int cols;
        private int setSize; // 集合数量
        UnionFind(int rows, int cols) {
            int n = rows * cols;
            this.rows = rows;
            this.cols = cols;
            this.parents = new int[n];
            this.sizes = new int[n];
            this.tmp = new int[n];
        }

        private int indexOf(int i, int j) {
            return i * cols + j;
        }

        private int findAncestor(int index) {
            int n = 0;
            while (index != parents[index]) {
                tmp[n++] = index;
                index = parents[index];
            }
            while (n-- > 0) {
                parents[tmp[n]] = index;
            }
            return index;
        }

        private void union(int i1, int j1, int i2, int j2) {
            if (i1 < 0 || i1 >= rows || j1 < 0 || j1 >= cols) {
                return;
            }
            if (i2 < 0 || i2 >= rows || j2 < 0 || j2 >= cols) {
                return;
            }
            int index1 = indexOf(i1, j1);
            int index2 = indexOf(i2, j2);
            if (sizes[index1] == 0 || sizes[index2] == 0) {
                return;
            }

            int ancestor1 = findAncestor(index1);
            int ancestor2 = findAncestor(index2);
            if (ancestor1 == ancestor2) {
                return;
            }

            // big
            index1 = sizes[ancestor1] >= sizes[ancestor2] ? ancestor1 : ancestor2;
            // small
            index2 = index1 == ancestor1 ? ancestor2 : ancestor1;

            parents[index2] = index1;
            sizes[index1] += sizes[index2];
//            sizes[index2] = 0;
            setSize--;
        }

        public void addLand(int i, int j) {
            int index = indexOf(i, j);
            if (sizes[index] > 0) {
                return;
            }
            parents[index] = index;
            sizes[index] = 1;
            setSize++;

            union(i - 1, j, i, j);
            union(i + 1, j, i, j);
            union(i, j - 1, i, j);
            union(i, j + 1, i, j);
        }

        public int size() {
            return setSize;
        }
    }
}
