package OA;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//input注意 line to int
public class test{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine();
        int n = Integer.parseInt(st);
        for(int i = 0; i < n; i++) {
            st = sc.nextLine();
            List<Integer> list = parseLineToIntArr(st);
            if (isTree(list) == true) System.out.println("Yes");
            else System.out.println("No");
        }
    }
    public static List<Integer> parseLineToIntArr(String s) {
        //字符串为空，则不作操作
        if (s.isEmpty()) {
            return new ArrayList<>();
        }
        String[] strArr = s.split(" ");
        int strArrLen = strArr.length;

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < strArrLen; i++) {

            int temp = Integer.parseInt(strArr[i]);
            list.add(temp);
        }
        return list;
    }
    public static boolean isTree(List<Integer> list){
        int size = list.size();
        int[] dp = new int[size];
        for(int i = size / 2; i < size; i++){
            dp[i] = list.get(i);
        }
        for(int i = size / 2 - 1; i >= 0; i--){
            int left = 0, right = 0;
            left = dp[i * 2 + 1];
            right = dp[i * 2 + 2];
            dp[i] = left + right;
            if(left == right) return true;
        }
        return false;
    }
}