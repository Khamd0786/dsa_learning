package graph.acycle.questions;

import java.util.ArrayList;
import java.util.List;

public class FindAllPaths {

    public static void main(String[] args) {
        int[][] graph = {
                {1, 2},
                {3},
                {3},
                {}
        };
        List<List<Integer>> allPaths = allPathsSourceTarget(graph);
        System.out.println("All paths from source to target:");

        for (List<Integer> path : allPaths) {
            System.out.println(path);
        }
    }

    private static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(0, graph, path, result);

        return result;
    }

    private static void dfs(int node, int[][] graph, List<Integer> path, List<List<Integer>> result) {
        if (node == graph.length - 1) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int nbr : graph[node]) {
            path.add(nbr);
            dfs(nbr, graph, path, result);
            path.removeLast();
        }
    }
}
