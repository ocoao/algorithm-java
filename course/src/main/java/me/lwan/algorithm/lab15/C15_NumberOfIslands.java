package me.lwan.algorithm.lab15;

/**
 * 岛屿数量<br/>
 * @see <a href="https://leetcode-cn.com/problems/number-of-islands/">岛屿数量</a>
 */
public class C15_NumberOfIslands {

    // 深度优先遍历方法
    public int numIslands(char[][] grid) {
        int ans = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    dfs(grid, i, j);
                }
            }
        }
        return ans;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
    }

    // 并查集方法
    public int numIslands2(char[][] grid) {
        UnionFind uf = new UnionFind(grid);
        int rows = grid.length;
        int cols = grid[0].length;
        // row 1
        for (int j = 1; j < cols; j++) {
            if (grid[0][j] == '1' && grid[0][j - 1] == '1') {
                uf.union(0, j, 0, j - 1);
            }
        }
        // col 1
        for (int i = 1; i < rows; i++) {
            if (grid[i][0] == '1' && grid[i - 1][0] == '1') {
                uf.union(i, 0, i - 1, 0);
            }
        }
        // others
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (grid[i][j] != '1') {
                    continue;
                }
                if (grid[i][j - 1] == '1') { // left
                    uf.union(i, j, i, j - 1);
                }
                if (grid[i - 1][j] == '1') { // up
                    uf.union(i, j, i - 1, j);
                }
            }
        }

        return uf.size();
    }

    private static class UnionFind {
        private final int[] parents;
        private final int[] sizes;
        private final int cols;
        private int setSize; // 集合数量
        UnionFind(char[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            int n = rows * cols;
            this.cols = cols;
            this.parents = new int[n];
            this.sizes = new int[n];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] != '1') {
                        continue;
                    }
                    int index = indexOf(i, j);
                    this.parents[index] = index;
                    this.sizes[index] = 1;
                    this.setSize++;
                }
            }
        }

        private int indexOf(int i, int j) {
            return i * cols + j;
        }

        private int findAncestor(int index) {
            while (index != parents[index]) {
                index = parents[index];
            }
            return index;
        }

        private void union(int i1, int j1, int i2, int j2) {
            int index1 = indexOf(i1, j1);
            int index2 = indexOf(i2, j2);
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
            sizes[index2] = 0;
            setSize--;
        }

        public int size() {
            return setSize;
        }
    }


}
