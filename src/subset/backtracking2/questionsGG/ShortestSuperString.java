package subset.backtracking2.questionsGG;

public class ShortestSuperString {

    private static int count = Integer.MAX_VALUE;
    private static String r = "";
    public static void main(String[] args) {
        String[] words = {"abc", "cabcde", "ecdefg"};
        shortestSuperstring(words);
        System.out.println(r);
    }

    public static void shortestSuperstring(String[] words) {
        boolean[] tracker = new boolean[words.length];
        shortestSuperString(0, "", words, tracker);
    }

    private static void shortestSuperString(int k, String p, String[] words, boolean[] tracker) {
        if (k == words.length) {
            if (p.length() < count) {
                count = p.length();
                r = p;
            }
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!tracker[i]) {
                tracker[i] = true;
                int overlap = findOverlap(p, words[i]);
                String newSuperString = p + words[i].substring(overlap);
                shortestSuperString(k + 1, newSuperString, words, tracker);

                tracker[i] = false;
            }
        }

//        shortestSuperString(k + 1, p, words, tracker);
    }

    private static int findOverlap(String a, String b) {
        int maxOverlap = 0;
        int minLen = Math.min(a.length(), b.length());

        for (int i = 1; i <= minLen; i++) {
            if (a.endsWith(b.substring(0, i))) {
                maxOverlap = i;
            }
        }

        return maxOverlap;
    }

}
