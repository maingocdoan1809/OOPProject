package huce.Graphviz;

import java.util.TreeMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import huce.Algorithm.Node.Node;
public class Parser {
    private static final String getLabelPattern = "(label\\s*=\\s*\"\\s*[0-9]*\\s*\")";
    private static final String getEdgesPattern = "(\"(\\w+\\s*\\w*)\")(\\s*->\\s*)(\"(\\w+\\s*\\w*)\")";
    private static final String getNodePattern = "(\\w+\\s*\\w*)";
    private static final String getcostPattern = "([0-9]+)";

    public static TreeMap<String, Node> toNodes(TreeMap<String, Integer> edges) {
        TreeMap<String, Node> nodes = new TreeMap<>();
        Pattern pattern = Pattern.compile(getNodePattern);
        for (var edge : edges.keySet()) {
            Matcher matcher = pattern.matcher(edge);
            // find fist node
            matcher.find();
            // get node name
            String nodeName = matcher.group();

            if (!nodes.containsKey(nodeName)) {
                nodes.put(nodeName, new Node(nodeName));
            }
            // if 'nodeName' exists in nodes
            // get that node from nodes
            Node currNode = nodes.get(nodeName);
            // find seconde node
            matcher.find();
            // get second node's name
            String adjacentNode = matcher.group();
            // if nodes hasn't had 'adjacentNode' yet:
            if (!nodes.containsKey(adjacentNode)) {
                // create a new Node
                Node aNode = new Node(adjacentNode);
                // put it to nodes
                nodes.put(adjacentNode, aNode);

                currNode.addAdjacency(aNode, edges.get(edge), false);
            } else {
                currNode.addAdjacency(nodes.get(adjacentNode), edges.get(edge), false);
            }

        }

        return nodes;
    }

    public static Map<String, Integer> toEdges(String dotFormat) {
        TreeMap<String, Integer> edges = new TreeMap<>();
        // for edges
        Pattern pattern = Pattern.compile(getEdgesPattern);
        Matcher matcher = pattern.matcher(dotFormat);
        // get lable="cost";
        Pattern pattern2 = Pattern.compile(getLabelPattern);
        Matcher matcher2 = pattern2.matcher(dotFormat);
        // get cost
        Pattern pattern3 = Pattern.compile(getcostPattern);
        while (matcher.find() && matcher2.find()) {
            String label = matcher2.group();
            Matcher matcher3 = pattern3.matcher(label);
            matcher3.find();
            edges.put(matcher.group(), Integer.parseInt(matcher3.group()));
        }

        return edges;
    }
}