package com.kk.distancematrix.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TSMService {
    public  int tsp(int[][] graph, boolean[] visited, int currentPos, int n, int count, int cost, int ans, List<Integer> path, List<Integer> minPath) {
        if (count == n && graph[currentPos][0] > 0) {
            if (ans > cost + graph[currentPos][0]) {
                ans = cost + graph[currentPos][0];
                minPath.clear();
                minPath.addAll(path);
                minPath.add(0);
            }
            return ans;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && graph[currentPos][i] > 0) {
                visited[i] = true;
                path.add(i);
                ans = tsp(graph, visited, i, n, count + 1, cost + graph[currentPos][i], ans, path, minPath);
                path.remove(path.size() - 1); // Backtrack: Remove the last node
                visited[i] = false;
            }
        }
        return ans;
    }
}
