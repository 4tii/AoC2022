import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class Day12 {

    public static void main(String[] args) throws IOException {
        Node[][] nodes = readInput();
        Node source = findNode('S', nodes);
        Node goal = findNode('E', nodes);
        List<Node> lowest = findNodes('a', nodes);
        Graph graph = initGraph(nodes);
        calculateShortestPathFromSource(source);

        System.out.println("Shortest Path: " + goal.getShortestPath().size());

        int shortestPath = Integer.MAX_VALUE;
        for(Node node: lowest) {
            nodes = readInput();
            findNode('S', nodes);
            goal = findNode('E', nodes);
            graph = initGraph(nodes);
            node=findNode(graph, node.getPosition().x, node.getPosition().y);
            calculateShortestPathFromSource(node);
            goal = findNode(graph, goal.getPosition().x, goal.getPosition().y);
            if(goal.getShortestPath().size()<shortestPath && goal.getShortestPath().size()>0)
                shortestPath=goal.getShortestPath().size();
        }

        System.out.println("Shortest Path: " + shortestPath);
    }

    private static Node findNode(Graph graph, int x, int y) {
        for(Node node: graph.getNodes()){
            if(node.getPosition().x==x && node.getPosition().y==y)
                return node;
        }
        return null;
    }

    private static List<Node> findNodes(char s, Node[][] input) {
        List<Node> list = new ArrayList<>();
        for (Node[] nodes : input) {
            for (Node node : nodes) {
                if (node.getValue() == s) {
                    list.add(node);
                }
            }
        }
        return list;
    }

    private static Node findNode(char s, Node[][] input) {
        for (Node[] nodes : input) {
            for (Node node : nodes) {
                if (node.getValue() == s) {
                    if (s == 'S')
                        node.setValue('a');
                    if (s == 'E')
                        node.setValue('z');
                    return node;
                }
            }
        }
        return null;
    }

    private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
    private static Node getLowestDistanceNode(Set < Node > unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
    public static void calculateShortestPathFromSource(Node source) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry< Node, Integer> adjacencyPair:
                    currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
    }
    public static Graph initGraph(Node[][] input) {
        Graph graph = new Graph();

        for(int i=0; i<input.length; i++){
            for(int j=0; j<input[i].length; j++){
                //right node
                if(j<input[i].length-1){
                    if((input[i][j+1].getValue()-input[i][j].getValue())<=1)
                        input[i][j].addDestination(input[i][j+1], 1);
                }
                //left node
                if(j>0){
                    if((input[i][j-1].getValue()-input[i][j].getValue())<=1)
                        input[i][j].addDestination(input[i][j-1], 1);
                }
                //bottom node
                if(i<input.length-1){
                    if((input[i+1][j].getValue()-input[i][j].getValue())<=1)
                        input[i][j].addDestination(input[i+1][j], 1);
                }
                //top node
                if(i>0){
                    if((input[i-1][j].getValue()-input[i][j].getValue())<=1)
                        input[i][j].addDestination(input[i-1][j], 1);
                }
                graph.addNode(input[i][j]);
            }
        }
        return graph;
    }

    public static Node[][] readInput() throws IOException {
        Path filePath = Path.of("./input/input_12.txt");
        String content = Files.readString(filePath);
        BufferedReader bufReader = new BufferedReader(new StringReader(content));
        char[][] input=new char[41][80];
        Node[][] arr = new Node[41][80];
        String line;
        int i=0;
        while((line=bufReader.readLine())!=null){
            input[i]=line.toCharArray();
            for(int j=0; j<line.length(); j++)
                arr[i][j]=new Node(new Point(i,j), input[i][j]);
            i++;
        }
        return arr;
    }

    public static class Graph {

        private Set<Node> nodes = new HashSet<>();
        public Graph(){

        }
        public void addNode(Node node) {
            nodes.add(node);
        }
        public Set<Node> getNodes(){
            return nodes;
        }

    }
    public static class Node{
        private Point position;
        private char value;
        private List<Node> shortestPath = new LinkedList<>();
        private Integer distance = Integer.MAX_VALUE;
        Map<Node, Integer> adjacentNodes = new HashMap<>();

        public void addDestination(Node destination, int distance) {
            adjacentNodes.put(destination, distance);
        }
        public Node(Point position, char value) {
            this.position = position;
            this.value = value;
        }
        public Point getPosition() {
            return position;
        }
        public Map<Node, Integer> getAdjacentNodes() {
            return adjacentNodes;
        }
        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }
        public List<Node> getShortestPath() {
            return shortestPath;
        }
        public void setShortestPath(List<Node> shortestPath) {
            this.shortestPath = shortestPath;
        }
        public char getValue() {
            return value;
        }

        public void setValue(char value) {
            this.value = value;
        }
    }

}

