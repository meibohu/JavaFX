package OA;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class input {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String st = sc.nextLine();
            String[] arr1 = st.split(" ");
            int m = Integer.parseInt(arr1[0]);
            int n = Integer.parseInt(arr1[1]);

            List<Integer> list = new ArrayList<>();
            st = sc.nextLine();
            String[] arr2 = st.split(" ");
            for(int i = 0; i < m; i++){
                list.add(Integer.parseInt(arr2[i]));
            }
            System.out.println(list);
        }
    }
}
