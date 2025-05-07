import java.io.*;
import java.util.*;

class Solution {
    
    static String[][] miro;   // 미로
    static int[][] distance;   // 거리
    static boolean[][] visit;  // 방문체크 배열
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] S, L, E;  // 시작, 레버, 끝 위치를 저장할 배열
    
    public int solution(String[] maps) {
        
        S = new int[2];
        L = new int[2];
        E = new int[2];
        
        miro = new String[maps.length][maps[0].length()];
        for (int r = 0; r < maps.length; r++) {
            String[] str = maps[r].split("");
            
            for (int c = 0; c < maps[0].length(); c++) {
                miro[r][c] = str[c];
                
                if (str[c].equals("S")) {
                    S[0] = r;
                    S[1] = c;
                }
                
                if (str[c].equals("L")) {
                    L[0] = r;
                    L[1] = c;
                }
                
                if (str[c].equals("E")) {
                    E[0] = r;
                    E[1] = c;
                }
            }
        }   // miro
        
        int res1 = BFS(S, L);
        int res2 = BFS(L, E);
        
        // res1 또는 res2 가 -1이라면 START 또는 레버가 있는 지점에서 EXIT지점까지 도달할 수 없다는 뜻
        if (res1 == -1 || res2 == -1)
            return -1;
        
        return res1 + res2;
    }   // solution
    
    public int BFS(int[] start, int[] end) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {start[0], start[1]});
        
        visit = new boolean[miro.length][miro[0].length];
        visit[start[0]][start[1]] = true;
        
        distance = new int[miro.length][miro[0].length];
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int r = now[0];
            int c = now[1];
            
            if (r == end[0] && c == end[1]) return distance[r][c];
            
            for (int i = 0; i < 4; i++) {
                int rNext = r + dr[i];
                int cNext = c + dc[i];  
                
                if (!(rNext >= 0 && rNext < miro.length && cNext >= 0 && cNext < miro[0].length && !visit[rNext][cNext]))
                    continue;
                if (miro[rNext][cNext].equals("X")) continue;
                
                queue.add(new int[] {rNext, cNext});
                distance[rNext][cNext] = distance[r][c] + 1;
                visit[rNext][cNext] = true;
            }
        }   // while
        
        // while문에서 return하지 못했다면 start지점에서 end지점에 도달하지 못한 것
        return -1;
    }   // BFS
}   
















