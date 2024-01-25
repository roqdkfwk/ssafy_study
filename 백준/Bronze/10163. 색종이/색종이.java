import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int[][] paper = new int[1001][1001];
        int N = sc.nextInt();
        int[] area = new int[N];
        
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int wed = sc.nextInt();
            int hei = sc.nextInt();
            
            for (int r = x; r < x + wed; r++) {
                for (int c = y; c < y + hei; c++) {
                    paper[r][c] = i + 1;
                }
            }
        }
        
        for (int i = 0; i < N; i++) {
        	for (int r = 0; r < 1001; r++) {
        		for (int c = 0; c < 1001; c++) {
        			if (paper[r][c] == i + 1)
        				area[i]++;
        		}
        	}
        	System.out.println(area[i]);
        }
     }
}