package huce.Algorithm.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;


public class Node implements Comparable<Node> {
    private final String name;
    private int estimate;
    /**
     * the adjacentNodes between {@code this}
     */
    private final HashMap<Node, Integer> adjacentNodes;
    private Set<Node> blocked;
    public ArrayList<Node> pre;
    public Node(String name, int estimate) {
        this(name);
        this.estimate = estimate;
    }
    public Node(String name) {
        this.name = name;
        this.adjacentNodes = new HashMap<>();
        this.blocked = new HashSet<>();
        this.estimate = Integer.MAX_VALUE;
        this.pre = new ArrayList<>();
    }
    public void resetEstimate() {
        this.estimate = Integer.MAX_VALUE;
    }

    public void addAdjacency(Node node, int distance, boolean undirected) {
        // distance from (this) to node.
        if (this == node) {
            return;
        }
        this.adjacentNodes.put(node, distance);
        if (undirected) {
            node.addAdjacency(this, distance, !undirected);
        }
    }
    public boolean updateEstimate(int newEstimate) {
        if (this.estimate > newEstimate) {
            this.estimate = newEstimate;
            return true;
        }
        return false;
    }
    private void blockOrRemoveHelper(Node other, HashSet<Node> visited,
                                     Consumer<Set<Node>> funct) {
        if (this == other) {
            return;
        }
        funct.accept(this.blocked);
        visited.add(this);
        for (var adjacentNode : this.adjacentNodes.keySet()) {
            if ( !visited.contains(adjacentNode) ) {
                visited.add(adjacentNode);
                adjacentNode.blockOrRemoveHelper(other, visited, funct);
            }
        }
    }
    public void blockNode(Node other) {
        blockOrRemoveHelper(other, new HashSet<>(), nodes -> {
            nodes.add(other);
        });
    }
    public void removeBlock(Node other) {
        blockOrRemoveHelper(other, new HashSet<>(), nodes -> {
            nodes.remove(other);
        });
    }
    public Set<Node> getBlocked() {
        return this.blocked;
    }
    public int getEstimate() {
        return this.estimate;
    }
    public String getName() {
        return this.name;
    }
    public void resetBlockingNodes() {
        this.blocked = new HashSet<>();
    }
    public HashMap<Node, Integer> getAdjacentNodes() {
        return this.adjacentNodes;
    }

    @Override
    public int compareTo(Node b) {
        if (this.estimate < b.estimate) {
            return -1;
        } else if (this.estimate > b.estimate) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object b) {
        if ( b instanceof Node ) {
            if (this.name.equals(((Node) b).name)) {
                return this.estimate == ((Node) b).estimate;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.name + ", " + estimate + "]";
    }
}