package graph.bfs.questions;

import java.util.*;

public class WordLadder {

    /**
     * Word Ladder
     * A transformation sequence from word beginWord to word endWord
     * using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
     * Every adjacent pair of words differs by a single letter.
     * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
     * sk == endWord
     * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest
     * transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
     *
     * Constraints:
     * 1 <= beginWord.length <= 10
     * endWord.length == beginWord.length
     * 1 <= wordList.length <= 5000
     * wordList[i].length == beginWord.length
     * beginWord, endWord, and wordList[i] consist of lowercase English letters.
     * beginWord != endWord
     *
     *
     * All the words in wordList are unique.
     *
     * Input : beginWord = "hit", endWord = "cog",  wordList = {"hot","dot","dog","lot","log","cog"}
     * Output : 5
     * Expected Time Complexity: O( 26*n*m^2)
     *
     * here, m is the length of each word and n is the total number of words
     */
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot", "dot", "dog", "lot", "log", "cog"};

        int result = ladderLength(beginWord, endWord, wordList);
        System.out.println("Shortest transformation sequence length: " + result);
    }

    private static int ladderLength(String beginWord, String endWord, String[] wordList) {
        Graph graph = new Graph(beginWord, wordList);

        for (String s : wordList) {
            if (isSingleChange(beginWord, s)) {
                graph.addEdges(beginWord, s);
            }
        }

        for (int i = 0; i < wordList.length; i++) {
            for (int j = 0; j < wordList.length; j++) {
                if (j == i)
                    continue;

                String s1 = wordList[i];
                String s2 = wordList[j];
                if (isSingleChange(s1, s2)) {
                    graph.addEdges(s1, s2);
                }
            }
        }

        return graph.minCostBFS(beginWord, endWord);
    }

    private static boolean isSingleChange(String s1, String s2) {
        int changeCount = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                changeCount++;
            }

            if (changeCount > 1) {
                return false;
            }
        }

        return changeCount == 1; //only single changes
    }

    private static class Graph {
        HashMap<String, List<String>> adjs;

        public Graph(String beginWord, String[] wordList) {
            adjs = new HashMap<>();

            adjs.put(beginWord, new ArrayList<>());

            for (String word: wordList) {
                adjs.put(word, new ArrayList<>());
            }
        }

        public void addEdges(String s1, String s2) {
            addEdges(s1, s2, true);
        }

        public void addEdges(String s1, String s2, boolean isUndirected) {
            adjs.get(s1).add(s2);
            if (isUndirected) {
                adjs.get(s2).add(s1);
            }
        }

        public int minCostBFS(String src, String dest) {
            Queue<String> queue = new LinkedList<>();
            HashMap<String, Integer> dist = new HashMap<>();
            HashSet<String> visited = new HashSet<>();

            dist.put(src, 0);
            visited.add(src);
            queue.offer(src);

            while (!queue.isEmpty()) {
                String f = queue.poll();

                if (Objects.equals(dest, f)) {
                    return dist.get(f) + 1;
                }

                for (String word: adjs.get(f)) {
                    if (!visited.contains(word)) {
                        queue.offer(word);
                        dist.put(word, dist.get(f) + 1);
                        visited.add(word);
                    }
                }
            }
            return 0;
        }
    }
}
