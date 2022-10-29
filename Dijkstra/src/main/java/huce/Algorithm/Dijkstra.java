package huce.Algorithm;

import com.mindfusion.diagramming.DiagramLink;
import com.mindfusion.drawing.Brushes;
import huce.Algorithm.Node.Node;
import huce.Exception.PathNotFoundException;

import java.util.*;

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
            boolean isUpdated = neighbor.updateEstimate(newEstimate);
            if (isUpdated) {
                neighbor.pre = new ArrayList<>();
                neighbor.pre.add(src);
            }
            if ( neighbor.getEstimate() == newEstimate) {
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
        helper(src, dest, new HashSet<>(), new PriorityQueue<>());
        if ( dest.pre.size() == 0 ) {
            throw new PathNotFoundException("Cannot find any path from %s to %s".formatted(src.getName(), dest.getName()));
        }
    }
    private static void getPath(Node dest, PriorityQueue<TreeSet<Node>> paths,
                        TreeSet<Node> path) {
        if (dest.pre.size() == 0) {
            paths.add(path);
            return;
        }
        var preNodes = new TreeSet<>(dest.pre);
        for ( Node preNode : preNodes ) {
            var newPath = new TreeSet<>(path);
            newPath.add(preNode);
            getPath(preNode, paths, newPath);
        }
    }
    public static PriorityQueue<TreeSet<Node>> extractPaths(Node dest) {
        PriorityQueue<TreeSet<Node>> paths = new PriorityQueue<>((o1, o2) -> {
            if (o1.size() > o2.size()) {
                return 1;
            } else if (o1.size() < o2.size()) {
                return -1;
            }
            return 0;
        });
        var newArrList = new TreeSet<Node>();
        newArrList.add(dest);
        getPath(dest, paths, newArrList);
        return  paths;
    }
    public static void reset(TreeMap<String, Node> nodes) {
        for ( var nodeName : nodes.keySet() ) {
            Node crr = nodes.get(nodeName);
            Dijkstra.resetRootState( crr);
            crr.pre = new ArrayList<>();
        }
    }
    public static void setAsRoot(Node node) {
        node.updateEstimate(0);
    }

    public static void resetRootState(Node root) {
        root.resetEstimate();
    }
}

