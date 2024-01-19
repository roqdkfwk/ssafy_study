import java.util.Scanner;

public class Main {
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
        int[] numArray = new int[10];
        int[] remain = new int[42];
        int R = 0;
        int count = 0;
        for (int i = 0; i < 10; i++) {
            numArray[i] = sc.nextInt();
            R = numArray[i] % 42;
            remain[R]++;
        }
        for (int j = 0; j < 42; j++) {
            if (remain[j] == 0)
                count++;
        }
        System.out.print(42 - count);
	}
}