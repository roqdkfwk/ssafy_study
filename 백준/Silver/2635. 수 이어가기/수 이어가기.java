import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        
	        // 첫 번째 수를 입력받고
	        int N = sc.nextInt();
	        // 가장 길게 이어졋을 때, 출력되는 숫자의 최대 개수를 저장할 변수
	        int max = 0;
	        // 출력되는 숫자가 최대가 되도록 하는 두 번째 정수
	        int maxNum = 0;
	        List<Integer> number = new ArrayList<>();
	        
	        // 두 번째 숫자로 첫 번째 숫자인 N보다 작은 숫자를 모두 받아서 
	        for (int n = 0; n <= N; n++) {
	        	// 배열의 첫 번째 인덱스에는 N을 할당하고
	            number.add(0, N);
	            // 배열의 두 번째 인덱스에는 두 번째 숫자인 n을 할당
	            number.add(1, n);
	            // 배열의 세 번째 인덱스에는 첫번째 숫자에서 두 번째 숫자를 뺸 값을 할당
	            int idx = 2;
	            number.add(2, number.get(0) - number.get(1));
	            
	            // 두 숫자의 차이가 0보다 크거나 같으면 while문을 계속 반복
	            while (number.get(idx) >= 0) {
	                number.add(idx + 1, number.get(idx - 1) - number.get(idx));
	                idx++;
	            }
	            if (number.size() >= max) {
	                max = number.size();
	                maxNum = n;
	            }
	            number.clear();
	        }
	        
	        number.add(N);
	        number.add(1, maxNum);
	        int idx = 2;
	        number.add(idx, number.get(0) - number.get(1));
	        
	        while (number.get(idx) >= 0) {
	            number.add(idx + 1, number.get(idx - 1) - number.get(idx));
	            idx++;
	        }
	        
	        if (number.get(number.size() - 1) >= 0) {
	            System.out.println(number.size());
	            for (int i = 0; i < number.size(); i++) {
	                System.out.print(number.get(i) + " ");
	            }
	        }
	        else {
	            System.out.println(number.size() - 1);
	            for (int i = 0; i < number.size() - 1; i++) {
	                System.out.print(number.get(i) + " ");
	            }
	        }
	}
}