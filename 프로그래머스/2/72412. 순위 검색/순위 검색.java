import java.util.*;
class Solution {
    
    Map<String, List<Integer>> map;
    public int[] solution(String[] info, String[] query) {        
        int numOfInfo = info.length;
        int numOfQuery = query.length;
        
        int[] answer = new int[numOfQuery];
        map = new HashMap<>();
        
        // info를 map에 저장
        for (int i = 0; i < numOfInfo; i++) {
            String[] str = info[i].split(" ");
            // System.out.println(str[0] + " " + str[1] + " " + str[2] + " " + str[3]);
            String[] langs = {str[0], "-"};
            String[] jobs = {str[1], "-"};
            String[] exps = {str[2], "-"};
            String[] foods = {str[3], "-"};
            int score = Integer.parseInt(str[4]);
            
            for (String lang : langs) {
                for (String job : jobs) {
                    for (String exp : exps) {
                        for (String food : foods) {
                            String key = lang + job + exp + food;
                            List<Integer> arr = map.getOrDefault(key, new ArrayList<>());
                            
                            arr.add(score);
                            map.put(key, arr);
                        }
                    }
                }
            }
        }
        
        for (List<Integer> arr : map.values()) {
            Collections.sort(arr);
        }
        
        // query를 통해 map에서 검색
        for (int i = 0; i < numOfQuery; i++) {
            String[] str = query[i].split(" and ");
            String lang = str[0];
            String job = str[1];
            String exp = str[2];
            String food = str[3].split(" ")[0];
            int score = Integer.parseInt(str[3].split(" ")[1]);
            
            answer[i] = 0;
            String key = lang + job + exp + food;
            if (map.containsKey(key)) {
                List<Integer> list = map.get(key);
                
                // 특정 점수 이상인 지원자를 구하기 위하는 과정에서 시간을 줄이기 위해
                // 선형 탐색이 아닌 이분 탐색을 활용하면 시간이 줄어들 것
                // for (int j = 0; j < size; j++) {
                //     if (map.get(key).get(j) >= score) {
                //         answer[i]++;
                //     }
                // }
                int left = 0, right = list.size();
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (list.get(mid) >= score) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                
                answer[i] = list.size() - right;
            }          
        }
        
        return answer;
    }
}