import huce.Algorithm.Dijkstra;
import huce.Algorithm.Node.Node;
import huce.Exception.GraphvizFileFormatException;
import huce.Exception.PathNotFoundException;
import huce.Graphviz.Parser;

import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    final static String digraphPattern =
            "(\"(\\w+\\s*)+\")\\s*->\\s*(\"(\\w+\\s*)+\")(.)\"*[\\n}]";
    final static String graphPattern = "(\"(\\w+\\s*)+\")\\s*--\\s*(\"(\\w+\\s*)+\")(.)" +
            "\"*[\\n}]";
    private static final String getCostPattern = "([0-9]+)";
    private static final String getLabelPattern = "(label\\s*=\\s*\"\\s*[0-9]*\\s*\")";
    public static Map<String, Integer> toEdges(String dotFormat) throws GraphvizFileFormatException {
        TreeMap<String, Integer> edges = new TreeMap<>();
        // for edges

        Pattern pattern = null;
        if ( dotFormat.contains("digraph") ) {
            pattern = Pattern.compile(digraphPattern);
        }    else {
            pattern = Pattern.compile(graphPattern);
        }
        Matcher matcher = pattern.matcher(dotFormat);
        // get label="cost";
        Pattern pattern2 = Pattern.compile(getLabelPattern);
        // get cost
        Pattern pattern3 = Pattern.compile(getCostPattern);
        while (matcher.find()) {
            String edgesAndItsProp = matcher.group();
            Matcher matcher2 = pattern2.matcher(edgesAndItsProp);
            String label = matcher2.group();
            Matcher matcher3 = pattern3.matcher(label);
            boolean hasDistanceValue = matcher3.find();
            if (!hasDistanceValue) {
                throw new GraphvizFileFormatException();
            }
            edges.put(matcher.group(), Integer.parseInt(matcher3.group()));
        }

        return edges;
    }

    public static void main(String[] args) {
    }
}