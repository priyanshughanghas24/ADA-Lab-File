package ADACodesFiles;

public class NaiveStringMatching {
    public static void search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        // Loop to slide pattern over text one by one
        for (int i = 0; i <= n - m; i++) {
            int j;

            // Check for pattern match at current index i
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }

            // If pattern matches the text at current index
            if (j == m) {
                System.out.println("Pattern found at index " + i);
            }
        }
    }

    public static void main(String[] args) {
        String text = "AABAACAADAABAAABAA";
        String pattern = "AABA";
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        search(text, pattern);
    }
}