package huce.Algorithm.Node;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Node implements Comparable<Node> {
    private String name;
    private int estimate;
    /**
     * the adjacentNodes between {@code this}
     */
    private HashMap<Node, Integer> adjacentNodes;
    private Set<Node> blocked;
    public Node pre;
    public Node(String name, int estimate) {
        this(name);
        this.estimate = estimate;
    }
    public Node(String name) {
        this.name = name;
        this.adjacentNodes = new HashMap<>();
        this.blocked = new HashSet<>();
        this.estimate = Integer.MAX_VALUE;
        this.pre = null;
    }

    public void resetEstimate() {
        this.estimate = Integer.MAX_VALUE;
    }

    public void addAdjacency(Node node, int distance, boolean undirected) {
        // distance from (this) to node.
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
    public void block(Node other) {
        if ( this.adjacentNodes.containsKey(other) ) {
            this.blocked.add(other);
        }
    }
    public Set<Node> getBlocked() {
        return this.blocked;
    }
    public int getEstimate() {
        return this.estimate;
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

