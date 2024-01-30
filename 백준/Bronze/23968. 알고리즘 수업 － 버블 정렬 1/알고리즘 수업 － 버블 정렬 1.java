import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int k = sc.nextInt();
		int [] arr = new int [N];
		for(int i=0;i<N;i++) {
			arr[i]=sc.nextInt();
		}
		int temp=0;
		int cnt=0;
		int[] ans= {-1,-1};
		for(int i=N-1;i>=0;i--) {
			for(int j=0;j<i;j++) {
				if(arr[j]>arr[j+1]) {
					temp=arr[j+1];
					arr[j+1]=arr[j];
					arr[j]=temp;
					cnt++;
					if(cnt==k) {
						ans[0]=arr[j];
						ans[1]=arr[j+1];
					}
				}
			}
		}
		if(ans[0]!= -1)
			System.out.print(ans[0]+" "+ans[1]);
		else
			System.out.print(ans[0]);
	}
}