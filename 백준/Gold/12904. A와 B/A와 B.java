import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder str1 = new StringBuilder(br.readLine());
        StringBuilder str2 = new StringBuilder(br.readLine());

        int length1 = str1.length();
        int length2 = str2.length();
        int difference = length2 -length1;

        length2--;
        for (int i = 0; i < difference; i++) {
            if (str2.charAt(length2) == 'A') {
                str2.deleteCharAt(length2);
            } else {
                str2.deleteCharAt(length2);
                str2.reverse();
            }
            length2--;
        }

        System.out.println(str1.toString().equals(str2.toString()) ? 1 : 0);
    }
}