package huce.Algorithm;

import huce.Algorithm.Node.Node;
import huce.Exception.PathNotFoundException;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
public class Dijkstra {
    private static void helper(Node src, Node dest, Set<Node> visited,
                               PriorityQueue<Node> queue) {
        if (src == dest) {
            return;
        }
        var srcAdj = src.getAdjacentNodes();
        for (var neighbor : srcAdj.keySet()) {
            if ( src.getBlocked().contains(neighbor) ) {
                continue;
            }
            int newEstimate = src.getEstimate() + srcAdj.get(neighbor);
            if ( neighbor.getEstimate() == newEstimate ) {
                neighbor.pre.add(src);
            }
            boolean isUpdated = neighbor.updateEstimate(newEstimate);
            if (isUpdated) {
                neighbor.pre.add(src);
            }
            if (!visited.contains(neighbor)) {
                if (!queue.contains(neighbor)) {
                    queue.offer(neighbor);
                }
            }

        }
        visited.add(src);
        if (queue.size() == 0) {
            return;
        }
        Node top = queue.poll();
        helper(top, dest, visited, queue);
    }

    public static void travel(Node src, Node dest) throws PathNotFoundException {
        if (src == dest) {
            return;
        }
        helper(src, dest, new HashSet<>(), new PriorityQueue<Node>());
        if ( dest.pre == null ) {
            throw new PathNotFoundException("Cannot find any path from %s to %s".formatted(src.getName(), dest.getName()));
        }
    }

    public static String getPath(Node dest, String path) {
        if (dest.pre.size() == 0) {
            return path;
        }
        return getPath(dest.pre.get(0), dest + " >> " + path);
    }

    public static void setAsRoot(Node node) {
        node.updateEstimate(0);
    }

    public static void resetRootState(Node root) {
        root.resetEstimate();
    }
}

