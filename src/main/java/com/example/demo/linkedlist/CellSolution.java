package com.example.demo.linkedlist;

/**
 * @author yao
 * @date 2020/1/3
 */

public class CellSolution {

    public static class Cells {

        public int addCells(int n) {
            return aCells(n) + bCells(n) + cCells(n);
        }

        public int aCells(int n) {
            if (n == 0) {
                return 1;
            }
            if (n == 1) {
                return 1;
            }
            return aCells(n - 1) + bCells(n - 1) + cCells(n - 1);
        }

        public int bCells(int n) {
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            return aCells(n - 1);
        }

        public int cCells(int n) {
            if (n == 0 || n == 1) {
                return 0;
            }
            if (n == 2) {
                return 1;
            }
            return bCells(n - 1);
        }
    }

    public static void main(String[] args) {
        Cells cells = new Cells();
        for (int n = 0; n <= 3; n++) {
            int all = cells.addCells(n);
            if (n == 0) {
                System.out.println("细胞刚开始有 " + all + " 个。");
            } else {
                System.out.println("细胞在经历了 " + n + " 小时后，细胞分裂成了 " + all + "个。");
            }
        }
    }
}
