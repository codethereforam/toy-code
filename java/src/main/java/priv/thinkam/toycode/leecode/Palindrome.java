package priv.thinkam.toycode.leecode;

public class Palindrome {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else {
            int reverse = 0;
            for (int num = x; num > 0; num = num / 10) {
                reverse = reverse * 10 + num % 10 ;
            }
            return x == reverse;
        }
    }

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.isPalindrome(1234));
        System.out.println(palindrome.isPalindrome(121));
    }
}
