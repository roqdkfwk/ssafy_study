import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> queueR = new LinkedList<Integer>();
        Queue<Integer> queueC = new LinkedList<Integer>();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        
        int N = Integer.parseInt(br.readLine());
        int maxgaro = Integer.MIN_VALUE;
        int maxsaero = Integer.MIN_VALUE;
        
        queueR.add(0);
        queueC.add(0);
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int garosaero = Integer.parseInt(st.nextToken());
            
            if (garosaero == 0) queueR.add(Integer.parseInt(st.nextToken()));
            else queueC.add(Integer.parseInt(st.nextToken()));
        }
        
        queueR.add(R);
        queueC.add(C);
        
        int sizeR = queueR.size();
        int sizeC = queueC.size();
        
        int[] cutR = new int[sizeR];
        int[] cutC = new int[sizeC];
        
        for (int i = 0; i < sizeR; i++) cutR[i] = queueR.poll();        
        for (int i = 0; i < sizeC; i++) cutC[i] = queueC.poll();
        
        Arrays.sort(cutR);
        Arrays.sort(cutC);
        
        for (int i = 0; i < sizeR - 1; i++) {
            if (maxgaro < cutR[i + 1] - cutR[i]) maxgaro = cutR[i + 1] - cutR[i];
        }
        
        for (int i = 0; i < sizeC - 1; i++) {
            if (maxsaero < cutC[i + 1] - cutC[i]) maxsaero = cutC[i + 1] - cutC[i];
        }
        
        System.out.print(maxgaro * maxsaero);
    }    
}