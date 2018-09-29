package algo.treesandgraphs;

import java.util.LinkedList;

/**
 * Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a
 * route between two nodes.
 */
public class RouteBtwNodes {

    public enum State {
        Unvisited, Visited, Visiting;
    }


    /**
     * This problem can be solved by just simple graph traversal, such as depth-first search or breadth-first search.
     * We start with one of the two nodes and, during traversal, check if the other node is found. We should mark
     * any node found in the course of the algorithm as "already visited" to avoid cycles and repetition of the
     * nodes.
     *
     * @param g
     * @param start
     * @param end
     * @return
     */
    public static boolean search(Graph g, Node start, Node end) {
        LinkedList<Node> q = new LinkedList<Node>();
        for (Node u : g.getNodes()) {
            u.state = State.Unvisited;
        }
        start.state = State.Visiting;
        q.add(start);
        Node u;
        while (!q.isEmpty()) {
            u = q.removeFirst();
            if (u != null) {
                for (Node v : u.getAdjacent()) {
                    if (v.state == State.Unvisited) {
                        if (v == end) {
                            return true;
                        } else {
                            v.state = State.Visiting;
                            q.add(v);
                        }
                    }
                }
                u.state = State.Visited;
            }
        }
        return false;
    }

    public static void main(String a[]) {
        Graph g = createNewGraph();
        Node[] n = g.getNodes();
        Node start = n[3];
        Node end = n[5];
        System.out.println(search(g, start, end));
    }

    public static Graph createNewGraph() {
        Graph g = new Graph();
        Node[] temp = new Node[6];

        temp[0] = new Node("a", 3);
        temp[1] = new Node("b", 0);
        temp[2] = new Node("c", 0);
        temp[3] = new Node("d", 1);
        temp[4] = new Node("e", 1);
        temp[5] = new Node("f", 0);

        temp[0].addAdjacent(temp[1]);
        temp[0].addAdjacent(temp[2]);
        temp[0].addAdjacent(temp[3]);
        temp[3].addAdjacent(temp[4]);
        temp[4].addAdjacent(temp[5]);
        for (int i = 0; i < 6; i++) {
            g.addNode(temp[i]);
        }
        return g;
    }


}

class Graph {
    public static int MAX_VERTICES = 6;
    private Node vertices[];
    public int count;

    public Graph() {
        vertices = new Node[MAX_VERTICES];
        count = 0;
    }

    public void addNode(Node x) {
        if (count < vertices.length) {
            vertices[count] = x;
            count++;
        } else {
            System.out.print("Graph full");
        }
    }

    public Node[] getNodes() {
        return vertices;
    }
}

class Node {
    private Node adjacent[];
    public int adjacentCount;
    private String vertex;
    public RouteBtwNodes.State state;

    public Node(String vertex, int adjacentLength) {
        this.vertex = vertex;
        adjacentCount = 0;
        adjacent = new Node[adjacentLength];
    }

    public void addAdjacent(Node x) {
        if (adjacentCount < adjacent.length) {
            this.adjacent[adjacentCount] = x;
            adjacentCount++;
        } else {
            System.out.print("No more adjacent can be added");
        }
    }

    public Node[] getAdjacent() {
        return adjacent;
    }

    public String getVertex() {
        return vertex;
    }
}
