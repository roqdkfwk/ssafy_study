import java.util.*;
class Solution {
    
    String[] cities;
    int answer;
    int size;
    Queue<String> cache;
    Map<String, Boolean> map;
    
    public int solution(int cacheSize, String[] cities) {
        answer = 0;
        this.cities = cities;
        size = cities.length;
        cache = new LinkedList<>();

        if (cacheSize == 0) {
            return 5 * size;
        }
        
        map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            String str = cities[i].toLowerCase();
            
            map.put(str, map.getOrDefault(str, false));
            
            // 캐시에 저장되어 있는 개수가 용량을 초과하지 않은 경우
            if (cache.size() < cacheSize) {
                if (map.get(str)) {  // 초과하지 않았지만 현재 도시가 캐시에 저장되어 있는 경우
                    cache.remove(str);
                    cache.add(str);
                    answer++;        // 실행 시간을 1만큼 더한다.
                } else {             // 현재 도시가 캐시에 저장되어 있지 않은 경우
                    cache.add(str);  // 현재 도시를 캐시에 추가하고
                    map.put(str, true);
                    answer += 5;     // 실행 시간을 5만큼 더한다.
                }
                
            // 캐시 용량이 다 찬 경우
            } else {              
                if (map.get(str)) {  // 현재 도시가 캐시에 있는 경우
                    cache.remove(str);
                    cache.add(str);
                    answer++;        // 실행 시간을 1만큼 더한다.
                } else {             // 현재 도시가 캐시에 없는 경우 실행 시간을 5만큼 더하고
                    answer += 5;     // 실행 시간을 5만큼 더하고
                    map.put(cache.poll(), false);
                    cache.add(str);  // 현재 도시를 캐시에 추가
                    map.put(str, true);
                }
            }
        }
        
        return answer;
    }
}