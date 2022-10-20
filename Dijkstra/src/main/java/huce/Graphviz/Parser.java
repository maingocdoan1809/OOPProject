package huce.Graphviz;

import huce.Algorithm.Node.Node;
import huce.Exception.GraphvizFileFormatException;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Parser {
    final static String digraphPattern =
            "(\"(\\w+\\s*)+\")\\s*->\\s*(\"(\\w+\\s*)+\")(.)*[\\n}]";
    final static String graphPattern = "(\"(\\w+\\s*)+\")\\s*--\\s*(\"(\\w+\\s*)+\")(.)*[\\n}]";
    private static final String getLabelPattern = "(label\\s*=\\s*\"\\s*[0-9]*\\s*\")";
    private static final String getEdgesPattern = "(\"(\\w+\\s*\\w*)\")(\\s*(->|--)" +
            "\\s*)(\"" +
            "(\\w+\\s*\\w*)\")";
    private static final String getNodePattern = "(\\w+\\s*\\w*)";
    private static final String getCostPattern = "([0-9]+)";

    public static TreeMap<String, Node> toNodes(String dotFile) throws GraphvizFileFormatException{
        String strPattern = null;
        boolean isDigraph = dotFile.contains("digraph");
        if ( isDigraph) {
            strPattern = digraphPattern;
            if ( dotFile.contains("--") ) {
                throw  new GraphvizFileFormatException("Invalid character: --");
            }
        } else {
            strPattern = graphPattern;
            if ( dotFile.contains("->") ) {
                throw  new GraphvizFileFormatException("Invalid character: ->");
            }
        }
        TreeMap<String, Integer> edges =
                (TreeMap<String, Integer>) Parser.toEdges(dotFile, isDigraph,
        strPattern);
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
                currNode.addAdjacency(nodes.get(adjacentNode), edges.get(edge), !isDigraph);
            } else {
                currNode.addAdjacency(nodes.get(adjacentNode), edges.get(edge), !isDigraph);
            }

        }

        return nodes;
    }

    private static Map<String, Integer> toEdges(String dotFormat, boolean isDigraph,
                                                String strPattern) throws GraphvizFileFormatException {
        TreeMap<String, Integer> edges = new TreeMap<>();
        // for edges
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(dotFormat);

        Pattern edgePattern = Pattern.compile(getEdgesPattern);
        Pattern labelPattern = Pattern.compile(getLabelPattern);
        Pattern costPattern = Pattern.compile(getCostPattern);
        while (matcher.find()) {
            String edgeAndDistance = matcher.group();
            Matcher edgeMatcher = edgePattern.matcher(edgeAndDistance);
            Matcher labelMatcher = labelPattern.matcher(edgeAndDistance);
            Matcher costMatcher = costPattern.matcher(edgeAndDistance);

            boolean hasEdge = edgeMatcher.find();
            boolean hasLabel = labelMatcher.find();
            boolean hasCost = costMatcher.find();
            if ( !(hasEdge&&hasLabel&&hasCost) ) {
                throw new GraphvizFileFormatException();
            }
            String edge = edgeMatcher.group();
            String label = labelMatcher.group();
            Integer cost = Integer.parseInt(costMatcher.group());
            edges.put(edge, cost);
        }

        return edges;
    }
}