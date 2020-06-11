package com.shenzc.sjjg.array;

/**
 * @author shenzc
 * @create 2020-06-09-14:59
 */
public class SparseArray {

    /**
     * 棋盘 （0：代表没有棋子  1：代表黑棋   2：代表蓝棋）
     * 二维数组转稀疏数组
     */
    public static void main(String[] args) {
        int[][] array = new int[11][11];
        array[1][2] = 1;
        array[2][3] = 2;
        int sum =0;
        for (int i=0 ; i<array.length;i++){
            for (int j=0;j<array[i].length;j++){
                if (array[i][j] > 0){
                    sum++;
                }
            }
            //System.out.println();
        }

        int[][] sparse = new int[sum+1][3];
        //有几行
        sparse[0][0] = array.length;
        //有几列
        sparse[0][1] = array[0].length;
        //一共有几个棋子
        sparse[0][2] = sum;
        //计数器
        int count = 1;
        for (int i=0 ; i<array.length;i++){
            for (int j=0;j<array[i].length;j++){
                if (array[i][j] > 0){
                    sparse[count][0] = i;
                    sparse[count][1] = j;
                    sparse[count][2] = array[i][j];
                    count++;
                }
            }
        }
        for (int i=0 ; i<sparse.length;i++){
            for (int j=0;j<sparse[i].length;j++){
                System.out.printf("%d\t",sparse[i][j]);
            }
            System.out.println();
        }


        //将稀疏数组转换为二维数组
        int[][] array2 = new int[sparse[0][0]][sparse[0][1]];
        for (int i=1;i<sparse.length;i++){
                array2[sparse[i][0]][sparse[i][1]] = sparse[i][2];
        }

        for (int i=0 ; i<array2.length;i++){
            for (int j=0;j<array2[i].length;j++){
                System.out.printf("%d\t",array2[i][j]);
            }
            System.out.println();
        }

    }

}
