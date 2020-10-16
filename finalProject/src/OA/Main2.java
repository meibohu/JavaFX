package OA;
import java.util.*;
public class Main2{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Map<String, List<String>> map = new HashMap<>();
        map.put("root", new ArrayList<>());
        while(sc.hasNext()){
            String str = sc.nextLine();
            String[] arr = str.split(" ");

            if(arr[0].equals("mkdir")){
                //String ini = "";    //start
                String prev = "root";
                String temp = arr[1];
                String[] tempArr = temp.split("/");
                for(String st: tempArr){
                    //init += ch;
                    if(prev.equals("root")){
                        map.get("root").add(st);
                    }else{
                        if(!map.containsKey(prev)){
                            map.put(prev, new ArrayList<>());
                        }
                        map.get(prev).add(st);
                    }
                    prev = st;
                }
            }else if(arr[0].equals("rm")){
                String temp = arr[1];
                String[] tempArr = temp.split("/");
                String target = tempArr[tempArr.length - 1];
                List<String> list = dfs(target, map, new ArrayList<>());
                for(String st: list){
                    if(map.containsKey(st)) map.remove(st);
                }
                if(tempArr.length == 0) map.clear();

            }else if(arr[0].equals("ls")){
                String start = "/";

                bfsPrint(map);
            }else if(arr[0].equals("mv")){
                String temp1 = arr[1];
                String temp2 = arr[2];
                String[] temp1Arr = temp1.split("/");
                String[] temp2Arr = temp2.split("/");
                if(!isValid1(temp1Arr, map, 0) || !isValid2(temp2Arr, map, 0)) continue;
                for(String st: map.get(temp1Arr[temp1Arr.length - 1])){
                    map.get(temp1Arr[temp2Arr.length - 1]).add(st);
                }
                map.remove(temp1Arr[temp1Arr.length - 1]);
            }else if(arr[0].equals("exit")){
                System.out.println("");
            }

        }
    }
    public static void bfsPrint(Map<String, List<String>> map){
        Queue<String> que = new LinkedList<>();
        que.offer("root");
        String start = "/";
        System.out.println(start);
        while(!que.isEmpty()){
            String cur = que.poll();
            if(!map.containsKey(cur)) continue;
            for(String temp: map.get(cur)){
                String ss = start + temp;
                System.out.println(ss);
                que.offer(temp);
            }
        }
    }
    public static boolean isValid1(String[] ini, Map<String, List<String>> map, int idx){
        if(idx == ini.length) return true;
        String target = ini[idx];
        if(!map.containsKey(target)) return false;
        List<String> list = map.get(target);
        idx++;
        for(String st: list){
            if(st.equals(target)) isValid1(ini, map, idx);
        }
        return false;
    }
    public static boolean isValid2(String[] ini, Map<String, List<String>> map, int idx){
        if(idx == ini.length - 1) return true;
        String target = ini[idx];
        if(!map.containsKey(target)) return false;
        List<String> list = map.get(target);
        idx++;
        for(String st: list){
            if(st.equals(target)) isValid1(ini, map, idx);
        }
        return false;
    }
    public static List<String> dfs(String init, Map<String, List<String>> map, List<String> res){
        res.add(init);
        if(!map.containsKey(init)) return res;
        List<String> list = map.get(init);
        for(String tt: list){
            if(map.containsKey(tt)){
                dfs(tt, map, res);
            }
        }
        return res;
    }
}