package OA;
import java.util.*;
public class huaweiSubset {
    //dfs 相当于 subsets
    //10 5
    //1 1 4 2
    //2 4 5 3
    //3 6 7 6
    //4 1 2 1
    //5 4 7 2
    public static void dfs(List<int[]> list, List<Integer> temp, int index, List<List<Integer>> res){
        if(!temp.isEmpty()){
            res.add(new ArrayList<>(temp));
        }
        for(int i = index; i < list.size(); i++) {
            if(!temp.isEmpty() && ((list.get(temp.get(temp.size() - 1))[2] + list.get(temp.get(temp.size() - 1))[3]) > list.get(i)[2])) continue;
            temp.add(i);
            dfs(list, temp, index + 1, res);
            temp.remove(temp.size() - 1);
        }

    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int E = in.nextInt();
            int N = in.nextInt();
            List<int[]> list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                int[] arr = new int[4];
                for (int j = 0; j < 4; j++) {
                    arr[j] = in.nextInt();
                }
                if(arr[2] > E || arr[2] + arr[3] > E) continue;
                list.add(arr);
            }
            Collections.sort(list, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[3] + a[2] - b[3] - b[2];
                }
            });

//            System.out.println(list.size());
            int price = 0;
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> temp = new ArrayList<>();
            dfs(list, temp, 0, res);


//            System.out.println(res.size());
            List<List<Integer>> target = new ArrayList<>();
            for(List<Integer> l: res){
                int priceT = 0;
                for(int num: l){
                    priceT += list.get(num)[1];
                }
                if(priceT == price) target.add(new ArrayList<>(l));
                else if(priceT > price) {
                    target.clear();
                    target.add(new ArrayList<>(l));
                    price = priceT;
                }
            }
            System.out.println(target.size());

            for(List<Integer> l: target){
                Collections.sort(l, new Comparator<Integer>() {
                    @Override
                    public int compare(Integer a, Integer b) {
                        return list.get(a)[0] - list.get(b)[0];
                    }
                });
            }
            Collections.sort(target, new Comparator<List<Integer>>() {
                @Override
                public int compare(List<Integer> a, List<Integer> b) {
                    if(a.size() != b.size()) return a.size() - b.size();
                    else{
                        for(int i = 0; i < a.size(); i++){
                            if(list.get(a.get(i))[0] == list.get(b.get(i))[0]) continue;
                            return list.get(a.get(i))[0] - list.get(b.get(i))[0];
                        }
                    }
                    return 0;
                }
            });

            List<Integer> l = target.get(0);
            int pr = 0;
            System.out.println(l.size());
            for(int num: l){
                pr += list.get(num)[1];
                System.out.println(list.get(num)[0]);
            }
            System.out.println(pr);
        }
    }
}
////greedy
//            for(int i = 0; i < list.size(); i++){
//                List<Integer> temp = new ArrayList<>();
//                temp.add(i);
//                int priceT = list.get(i)[1];

//                int x_end = list.get(i)[2] + list.get(i)[3];
//                for(int j = i + 1; j < list.size(); j++){
//                    int start = list.get(j)[2];
//                    if(start >= x_end){
//                        temp.add(j);
//                        x_end = list.get(j)[2] + list.get(j)[3];
//                        priceT += list.get(j)[1];
//                    }
//                }
//                System.out.println(priceT);
//                if(priceT == price) res.add(temp);
//                else if(priceT > price){
//                    res.clear();
//                    res.add(temp);
//                    price = priceT;
//                }
//            }
//            System.out.println(res.size());



////O(n2)   dp
//    int[] p = new int[list.size()];
//            for(int i = 1; i < list.size(); i++){
//                for(int j = i - 1; j >= 0; j--){
//                    int[] arr1 = list.get(j);
//                    int[] arr2 = list.get(i);
//                    if(arr1[3] + arr1[2] < arr2[2]){
//                        p[i] = j;
//                        break;
//                    }
//                }
//            }
//            int[] optimal = new int[list.size()];
//            int[][] record = new int[list.size()][2];
//            for(int i = 1; i < list.size(); i++){
//                if(optimal[p[i]] + list.get(i)[1] >= optimal[i - 1]){
//                    optimal[i] = optimal[p[i]] + list.get(i)[1];
//                    record[i][0] = 1;
//                    record[i][1] = p[i];
//                }else{
//                    optimal[i] = optimal[i - 1];
//                    record[i][0] = 0;
//                    record[i][1] = i - 1;
//                }
//            }
//            for(int i = list.size() - 1; i >= 0; i = record[i][1]){
//                if(record[i][0] == 1){
//                    System.out.println(list.get(i)[0]);
//                }
//            }
