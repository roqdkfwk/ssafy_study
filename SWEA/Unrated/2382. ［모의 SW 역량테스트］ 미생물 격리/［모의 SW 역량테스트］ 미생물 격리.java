import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static class Group {
        int R, C, count, direction;

        public Group(int R, int C, int count, int direction) {
            this.R = R;
            this.C = C;
            this.count = count;
            this.direction = direction;
        }
    }   // Group

    static int T, N, M, K;
    static Group[][][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            answer = 0; // 테스트케이스마다 정답 초기화
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new Group[N][N][2];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                Group group = new Group(
                        Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1
                );

                map[group.R][group.C][0] = group;
            }

            int[][] maxCount;
            for (int i = 0; i < M; i++) {
                maxCount = new int[N][N];
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        if (map[r][c][i % 2] == null) {
                            continue;
                        }

                        Group grp = checkDirection(map[r][c][i % 2]);   // 이동시키고
                        if (grp == null) {                              // 군집이 사라졌다면 다음 단계 진행 X
                            map[r][c][i % 2] = null;
                            continue;
                        }
                        map[r][c][i % 2] = null;                        // 비운다.
                        if (map[grp.R][grp.C][(i + 1) % 2] != null) {   // 다음 칸에 군집이 있는 경우
                            if (maxCount[grp.R][grp.C] > grp.count) {   // 원래 있던 군집이 더 큰 경우 합치고 방향은 원래 있던 군집의 방향을 따름
                                map[grp.R][grp.C][(i + 1) % 2] = new Group(grp.R, grp.C, map[grp.R][grp.C][(i + 1) % 2].count + grp.count, map[grp.R][grp.C][(i + 1) % 2].direction);
                            } else {                                    // 다음 순서 군집이 더 큰 경우 합치고 방향은 다음 순서 군집의 방향을 따름
                                map[grp.R][grp.C][(i + 1) % 2] = new Group(grp.R, grp.C, map[grp.R][grp.C][(i + 1) % 2].count + grp.count, grp.direction);
                                maxCount[grp.R][grp.C] = grp.count;
                            }
                        } else {                                        // 다음 칸에 군집이 없는 경우
                            map[grp.R][grp.C][(i + 1) % 2] = grp;       // 그냥 위치시키고
                            maxCount[grp.R][grp.C] = grp.count;         // 해당 위치의 군집 크기를 설정
                        }

                    }
                }
            }   // 격리 시간

            if (M % 2 == 0) {   // 격리 시간이 짝수인 경우 군집들은 첫 번째 평면에 저장
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        if (map[r][c][0] != null) {
                            answer += map[r][c][0].count;
                        }
                    }
                }
            } else {            // 격리 시간이 홀수인 경우 군집들은 두 번째 평면에 저장
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        if (map[r][c][1] != null) {
                            answer += map[r][c][1].count;
                        }
                    }
                }
            }

            sb.append("#" + t + " " + answer + "\n");
        }   // t에 대한 for문

        System.out.print(sb);
    }   // main

    private static Group checkDirection(Group grp) {
        int nr = grp.R + dr[grp.direction];
        int nc = grp.C + dc[grp.direction];

        if (nr == 0) {              // 제일 위 row에 도착한 경우
            if (checkCount(grp.count / 2)) {
                return new Group(nr, nc, grp.count / 2, 1);
            }

            return null;
        } else if (nr == N - 1) {   // 제일 아래 row에 도착한 경우
            if (checkCount(grp.count / 2)) {
                return new Group(nr, nc, grp.count / 2, 0);
            }

            return null;
        } else if (nc == 0) {       // 제일 왼쪽 col에 도착한 경우
            if (checkCount(grp.count / 2)) {
                return new Group(nr, nc, grp.count / 2, 3);
            }

            return null;
        } else if (nc == N - 1){    // 제일 오른쪽 col에 도착한 경우
            if (checkCount(grp.count / 2)) {
                return new Group(nr, nc, grp.count / 2, 2);
            }

            return null;
        } else {
            return new Group(nr, nc, grp.count, grp.direction);
        }
    }   // checkDirection

    private static boolean checkCount(int count) {
        if (count == 0) {
            return false;
        }

        return true;
    }   // checkCount
}