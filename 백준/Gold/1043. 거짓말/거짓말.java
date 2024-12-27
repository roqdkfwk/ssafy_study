import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static HashSet<Integer> knowPeople;
    static List<Set<Integer>> party;
    static int answer;

    public static void main(String[] args) throws IOException {
        InputHandler();

        Solution();

        printResult();
    }

    static void printResult() {
        System.out.println(answer);
    }

    static void Solution() {
        // 진실을 확장하는 논리를 반복적으로 수행
        boolean[] visited = new boolean[M]; // 각 파티의 방문 여부
        boolean changed = true;

        while (changed) {
            changed = false;

            for (int i = 0; i < M; i++)
            {
            	// 이미 처리한 파티는 건너뜀
                if (visited[i]) continue;
                for (int person : party.get(i))
                {
                	// 파티에 진실을 아는 사람이 있는 경우
                    if (knowPeople.contains(person))
                    {
                        visited[i] = true;					// 해당 파티는 진실을 말해야 함
                        knowPeople.addAll(party.get(i));	// 파티 참석자 모두 진실을 알게 됨
                        changed = true;						// 변경이 있었음을 표시
                        break;
                    }
                }
            }
        }

        // 방문되지 않은 파티의 개수를 계산 (과장된 이야기를 할 수 있는 파티 수)
        for (boolean isVisited : visited) {
            if (!isVisited) answer++;
        }
    }

    static void InputHandler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 사람 수와 파티 수 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 진실을 아는 사람 정보 입력
        knowPeople = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        for (int i = 0; i < size; i++)
            knowPeople.add(Integer.parseInt(st.nextToken()));

        // 각 파티의 참석자 정보 입력
        party = new ArrayList<>();
        for (int i = 0; i < M; i++)
            party.add(new HashSet<>());

        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(br.readLine());
            size = Integer.parseInt(st.nextToken());
            for (int j = 0; j < size; j++)
                party.get(i).add(Integer.parseInt(st.nextToken()));
        }

        // 초기 정답은 0으로 설정 (Solution에서 계산)
        answer = 0;
    }
}