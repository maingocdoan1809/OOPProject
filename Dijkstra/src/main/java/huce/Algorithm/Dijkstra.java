package huce.Algorithm;

import huce.Algorithm.Node.Node;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {
    private static void helper(Node src, Node dest, Set<Node> visited, PriorityQueue<Node> queue) {
        if (src == dest) {
            return;
        }
        var srcAdj = src.getAdjacentNodes();
        for (var neighbor : srcAdj.keySet()) {
            if ( src.getBlocked().contains(neighbor) ) {
                continue;
            }
            boolean isUpdated = neighbor.updateEstimate(src.getEstimate() + srcAdj.get(neighbor));
            if (!visited.contains(neighbor)) {
                if (!queue.contains(neighbor))
                    queue.offer(neighbor);
            }
            if (isUpdated) {
                neighbor.pre = src;
            }
        }
        visited.add(src);
        if (queue.size() == 0) {
            return;
        }
        Node top = queue.poll();
        helper(top, dest, visited, queue);
    }

    public static void travel(Node src, Node dest) {
        if (src == dest) {
            return;
        }
        helper(src, dest, new HashSet<>(), new PriorityQueue<Node>());
    }

    public static String getPath(Node dest, String path) {
        if (dest == null) {
            return path;
        }
        return getPath(dest.pre, dest + " >> " + path);
    }

    public static void setAsRoot(Node node) {
        node.updateEstimate(0);

    }

    public static void resetRootState(Node root) {
        root.resetEstimate();
    }
}

