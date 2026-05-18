package marko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

import marko.models.Edge;
import marko.models.Graph;
import marko.models.SearchNode;
import marko.utils.io.OutputDevice;

public class UniformCostSearch {
    public static short computeCostFromAtoB(Graph graph, byte initialStateId, byte finalStateId) {
        return computeCostFromAtoB(graph, initialStateId, finalStateId, null);
    }

    public static short computeCostFromAtoB(Graph graph, byte initialStateId, byte finalStateId, String[] names) {
        if (graph.getAdjacencyListOf(initialStateId).isEmpty()) return -2;
        PriorityQueue<SearchNode> frontier = initializePriorityQueue(initialStateId);
        short[] bestCost = initializeBestCost(graph.getSize(), initialStateId);
        byte[] parent = initializeParent(graph.getSize());
        while (!frontier.isEmpty()) {
            SearchNode currentNode = frontier.poll();
            byte currentNodeId     = currentNode.getId();
            short currentCost      = currentNode.getCost();
            ArrayList<Edge> currentAdjacencyList = graph.getAdjacencyListOf(currentNodeId);
            if (currentCost > bestCost[currentNodeId])
                continue;
            if (currentNodeId == finalStateId) {
                printPath(graph, parent, initialStateId, finalStateId, names);
                return currentCost;
            }
            if (currentAdjacencyList.isEmpty())
                continue;
            for (Edge edge : currentAdjacencyList) {
                byte neighbourId = edge.getDestinationId();
                short newCost = (short) (edge.getWeight() + currentCost);
                if (newCost < bestCost[neighbourId]) {
                    bestCost[neighbourId] = newCost;
                    parent[neighbourId] = currentNodeId;
                    frontier.add(new SearchNode(neighbourId, newCost));
                }
            }
        }
        return -1;
    }

    private static void printPath(Graph graph, byte[] parent, byte start, byte goal, String[] names) {
        ArrayList<Byte> path = new ArrayList<>();
        byte curr = goal;
        while (curr != (byte) -1) {
            path.add(curr);
            curr = parent[curr];
        }
        Collections.reverse(path);
        for (int i = 0; i < path.size() - 1; i++) {
            byte from = path.get(i);
            byte to   = path.get(i + 1);
            short w   = getEdgeWeight(graph, from, to);
            OutputDevice.print(nodeName(from, names) + " ---" + w + "--- ");
        }
        OutputDevice.println(nodeName(path.get(path.size() - 1), names));
    }

    private static String nodeName(byte id, String[] names) {
        return (names != null && id >= 0 && id < names.length) ? names[id] : String.valueOf(id);
    }

    private static short getEdgeWeight(Graph graph, byte from, byte to) {
        for (Edge edge : graph.getAdjacencyListOf(from)) {
            if (edge.getDestinationId() == to) return edge.getWeight();
        }
        return -1;
    }

    private static PriorityQueue<SearchNode> initializePriorityQueue(byte initialStateId) {
        PriorityQueue<SearchNode> pq = new PriorityQueue<SearchNode>();
        pq.add(new SearchNode(initialStateId, (short) 0));
        return pq;
    }

    private static short[] initializeBestCost(byte graphSize, byte initialStateId) {
        short[] bc = new short[graphSize];
        Arrays.fill(bc, Short.MAX_VALUE);
        bc[initialStateId] = 0;
        return bc;
    }

    private static byte[] initializeParent(byte graphSize) {
        byte[] p = new byte[graphSize];
        Arrays.fill(p, (byte) -1);
        return p;
    }
}
