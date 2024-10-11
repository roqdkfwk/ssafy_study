import java.util.*;
class Solution {
    public long solution(int w, int h) {
        long totalSquares = (long) w * h;
        long unusableSquares = w + h - gcd(w, h);
        
        return totalSquares - unusableSquares;
    }
    
    int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}