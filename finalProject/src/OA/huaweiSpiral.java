package OA;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class huaweiSpiral {
    //leetcode59
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int size1 = in.nextInt();
            int size2 = in.nextInt();
            int[][] matrix = new int[size1][size2];
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < size1; i++){
                for(int j = 0; j < size2; j++){
                    list.add(in.nextInt());
                }
            }
            dfs(0, size1, size2, 0, matrix, list);
            for(int m = 0; m < size1; m++){
                for(int n = 0; n < size2; n++){
                    System.out.print(matrix[m][n] + " ");
                }
                System.out.println();
            }

        }
    }
    public static void dfs(int index, int size1, int size2, int offset, int[][] matrix, List<Integer> list){

        if(size1 == 0 || size2 == 0) return;
//        if(size1 == 1){
//            for(int i = 0; i < size2; i++){
//                matrix[offset][offset + i] = list.get(index);
//                index++;
//            }
//            return;
//        }
//        if(size2 == 1){
//            for(int i = 0; i < size1; i++){
//                matrix[offset + i][offset] = list.get(index);
//                index++;
//            }
//            return;
//        }
        if(size1 == 1 && size2 == 1){
            matrix[offset][offset] = list.get(index);
            return;
        }

        for(int i = 0; i < size2 - 1; i++){
            matrix[offset][offset + i] = list.get(index);
            index++;
        }
        for(int i = 0; i < size1 - 1; i++){
            matrix[offset + i][offset + size2 - 1] = list.get(index);
            index++;
        }
        for(int i = 0; i < size2 - 1; i++){
            matrix[offset + size1 - 1][offset + size2 - 1 - i] = list.get(index);
            index++;
        }
        for(int i = 0; i < size1 - 1; i++){
            matrix[offset + size1 - 1- i][offset] = list.get(index);
            index++;
        }
        dfs(index, size1 - 2, size2 - 2, offset + 1, matrix, list);
    }
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
//            int size1 = in.nextInt();
//            int size2 = in.nextInt();
//            int[][] matrix = new int[size1][size2];
//            List<Integer> list = new ArrayList<>();
//            for(int i = 0; i < size1; i++){
//                for(int j = 0; j < size2; j++){
//                    list.add(in.nextInt());
//                }
//            }
//            int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
//            int i = 0, j = 0, idx = 0;
//            for(int k = 0; k < size1 * size2; k++){
//                matrix[i][j] = list.get(k);
//                int x = i + dir[idx][0];
//                int y = j + dir[idx][1];
//                if(x < 0 || x >= size1 || y >= size2 || y < 0 || matrix[x][y] != 0){
//                    idx = (idx + 1) % 4;
//                    x = i + dir[idx][0];
//                    y = j + dir[idx][1];
//                }
//                i = x;
//                j = y;
//            }
//            for(int m = 0; m < size1; m++){
//                for(int n = 0; n < size2; n++){
//                    System.out.print(matrix[m][n] + " ");
//                }
//                System.out.println();
//            }
////            System.out.println(matrix);
//        }
//    }
}
