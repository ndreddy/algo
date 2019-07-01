package algo.treesandgraphs;

import java.util.*;

/**
 * Find shortest path between two nodes. A shortest path from Home ->
 */
public class ShortestPathBtwNodes {

    public static String[] bfsGetPath(Map<String, String[]> graph, String startNode, String endNode) {

        if (!graph.containsKey(startNode)) {
            throw new IllegalArgumentException("Start node not in graph");
        }
        if (!graph.containsKey(endNode)) {
            throw new IllegalArgumentException("End node not in graph");
        }

        /* We're using an ArrayDeque instead of an ArrayList because we want an
        efficient first-in-first-out (FIFO) structure with O(1) inserts and removes.
        If we used an ArrayList, appending would be O(1),
        but removing elements from the front would be O(n)*/

        Queue<String> queue = new ArrayDeque<>();

        // Add to the end of queue
        queue.add(startNode);

        // keep track of how we got to each node
        // we'll use this to reconstruct the shortest path at the end
        // we'll ALSO use this to keep track of which nodes we've
        // already visited
        Map<String, String> path = new HashMap<>();

        // How we reached the node
        path.put(startNode, null);

        while (!queue.isEmpty()) {
            // Remove from the front of the queue
            String r = queue.remove();

            // stop when we reach the end node
            if (r.equals(endNode)) {
                return reconstructPath(path, startNode, endNode);
            }

            // Get adjacent vertices
            for (String n : graph.get(r)) {
                if (!path.containsKey(n)) {
                    queue.add(n);
                    path.put(n, r);
                }
            }
        }

        // if we get here, then we never found the end node
        // so there's NO path from startNode to endNode
        return null;
    }

    private static String[] reconstructPath(Map<String, String> previousNodes,
                                            String startNode, String endNode) {

        List<String> reversedShortestPath = new ArrayList<>();

        // start from the end of the path and work backwards
        String currentNode = endNode;

        while (currentNode != null) {
            reversedShortestPath.add(currentNode);
            currentNode = previousNodes.get(currentNode);
        }

        // reverse our path to get the right order
        // by flipping it around, in place
        Collections.reverse(reversedShortestPath);
        return reversedShortestPath.toArray(new String[reversedShortestPath.size()]);
    }

    public static void main(String[] args) {
        Map<String, String[]> network = new HashMap<String, String[]>() {{
            put("Min", new String[]{"William", "Jayden", "Omar"});
            put("William", new String[]{"Min", "Noam"});
            put("Jayden", new String[]{"Min", "Amelia", "Ren", "Noam"});
            put("Ren", new String[]{"Jayden", "Omar"});
            put("Amelia", new String[]{"Jayden", "Adam", "Miguel"});
            put("Adam", new String[]{"Amelia", "Miguel", "Sofia", "Lucas"});
            put("Miguel", new String[]{"Amelia", "Adam", "Liam", "Nathan"});
            put("Noam", new String[]{"Nathan", "Jayden", "William"});
            put("Omar", new String[]{"Ren", "Min", "Scott"});
        }};
        String[] path = bfsGetPath(network, "Jayden", "Adam");
        Arrays.stream(path).forEach(System.out::println); //  { "Jayden", "Amelia", "Adam" }
    }

}
