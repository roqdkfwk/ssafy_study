import java.util.*;
import java.io.*;
public class Main {
	
	static class Mogi {
		int inTime, outTime;
		
		public Mogi(int inTime, int outTime) {
			this.inTime = inTime;
			this.outTime = outTime;
		}
	}
	
	static int N;
	static List<Mogi> mogis;
	static Set<Integer> times;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		mogis = new ArrayList<>();
		times = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int E = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			
			mogis.add(new Mogi(E, X));
			times.add(E);
			times.add(X);
		}
		
		// 좌표 종류 저장
		List<Integer> sortedTimes = new ArrayList<>(times);
		Collections.sort(sortedTimes);
		
		// 좌표 순서 저장(압축)
		// <시각, 순서>
		Map<Integer, Integer> compressedTimes = new HashMap<>();
		for (int i = 0; i < sortedTimes.size(); i++) {
			compressedTimes.put(sortedTimes.get(i), i);
		}
		
		int[] diff = new int[sortedTimes.size() + 1];
        for (Mogi mogi : mogis) {
            diff[compressedTimes.get(mogi.inTime)]++;
            diff[compressedTimes.get(mogi.outTime)]--;
        }

        // prefix sum으로 mogiTable 계산
        int[] mogiTable = new int[sortedTimes.size()];
        mogiTable[0] = diff[0];
        int max = mogiTable[0];
        for (int i = 1; i < sortedTimes.size(); i++) {
            mogiTable[i] = mogiTable[i - 1] + diff[i];
            max = Math.max(max, mogiTable[i]);
        }
		
		int firstTime = 0;
		int lastTime = 0;
		out:
		for (int i = 0; i < mogiTable.length; i++) {
			if (mogiTable[i] == max) {
				firstTime = i;
				lastTime = i;
				while (lastTime < mogiTable.length && mogiTable[lastTime] == max) {
					lastTime++;
				}
				
				break out;
			}
		}
		
		System.out.println(max);
		System.out.println(sortedTimes.get(firstTime) + " " + sortedTimes.get(lastTime));
	}
}
