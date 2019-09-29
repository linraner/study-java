package com.lin.algorithm.queueandstack.problem;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-29 11:12
 * @Description:
 **/
/*
给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
你可以假设网格的四个边均被水包围。

示例 1:

输入:
11110
11010
11000
00000

输出: 1
示例 2:

输入:4 5
11000
11000
00100
00011

输出: 3

4 5
11000
11000
00100
00011
 */
public class IslandsNumber {


    public static void main(String[] args) throws IOException {
        Scanner cin = new Scanner(System.in);
        int n, m;
        n = cin.nextInt();
        m = cin.nextInt();
        char[][] ch = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = cin.next();
            System.out.println(str);
            for (int j = 0; j < str.length() - 1; j++) {
                char t = str.charAt(j);
                ch[i][j] = t;
            }
        }
//        System.out.println("\n==============");
//        for (int i = 0; i < ch.length; i++) {
//            for (int j = 0; j < ch[0].length; j++) {
//                System.out.println(ch[i][j]);
//            }
//            System.out.println();
//        }
        System.out.println(numIslands(ch));
    }

    public static int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void bfs(char[][] grid, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            i = cur[0];
            j = cur[1];
            if (0 <= i && i < grid.length && 0 <= j && j < grid[0].length && grid[i][j] == '1') {
                grid[i][j] = '0';
                // 下一步
                q.add(new int[]{i - 1, j});
                q.add(new int[]{i, j - 1});
                q.add(new int[]{i + 1, j});
                q.add(new int[]{i, j + 1});
            }
        }
    }

}
