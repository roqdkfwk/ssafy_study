import java.util.*;
class Solution
{
    public int solution(String s)
    {
        int answer = 0;
        char[] ch = s.toCharArray();
        int length = s.length();
        
        for (int i = length; i > 0; i--) {              // 길이
            for (int j = 0; j <= length - i; j++) {     // 길이에 따라 반복할 횟수
                boolean isPallindrom = true;
                for (int k = 0; k < (i / 2); k++) {     // 반복 범위
                    if (ch[k + j] != ch[j + i - 1 - k]) {
                        isPallindrom = false;
                        break;
                    }
                }
                if (isPallindrom) {
                    return i;
                }
            }
        }
        
        return 1;
    }
}