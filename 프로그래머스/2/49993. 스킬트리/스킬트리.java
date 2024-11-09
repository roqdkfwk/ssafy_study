import java.util.*;
class Solution {
    // 선행 스킬이 있으니까 위상 정렬?
    
    List<Character> skills;
    Set<Character> set;
    Queue<Character> queue;
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        skills = new ArrayList<>();
        set = new HashSet<>();
        char[] ch = skill.toCharArray();
        for (char c : ch) {
            skills.add(c);
            set.add(c);
        }
        
        for (int i = 0; i < skill_trees.length; i++) {
            skill = skill_trees[i];
            boolean flag = true;
            
            queue = new LinkedList<>(skills);
            for (int j = 0; j < skill.length(); j++) {
                if (isInSkill(skill.charAt(j))) {   // j번 스킬이 선행 순서가 있는 스킬인 경우
                    if (queue.poll() != skill.charAt(j)) {
                        flag = false;
                        continue;
                    }
                }
            }
            
            if (flag)
                answer++;
        }
        // 선행 조건이 있는 스킬인지 확인
        
        // 선행 조건이 없는 스킬인 경우 그냥 배울 수 있다.
        
        // 선행 조건이 있는 스킬인 경우 배울 수 있는지 확인해야 한다.
        // 스킬을 배운 경우 큐에서 제거
        
        return answer;
    }
    
    boolean isInSkill(char ch) {
        return set.contains(ch);
    }
}