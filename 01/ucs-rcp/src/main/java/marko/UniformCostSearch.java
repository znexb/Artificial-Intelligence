package marko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import marko.models.Edge;
import marko.models.Graph;
import marko.models.SearchNode;

public class UniformCostSearch {
    public static short computeCostFromAtoB(Graph graph, byte initialStateId, byte finalStateId) {
        if (graph.getAdjacencyListOf(initialStateId).isEmpty()) return -2;              // If initial node has no neighbours, skip the whole process
        PriorityQueue<SearchNode> frontier = initializePriorityQueue(initialStateId);   // Initiliaze priority queue to be used
        short[] bestCost = initializeBestCost(graph.getSize(), initialStateId);         // Initializes the bestCost array, which is used to store 'explored' nodes based on cost
        while(!frontier.isEmpty()) {
            SearchNode currentNode = frontier.poll();       // Get id of node in queue
            byte currentNodeId     = currentNode.getId();
            short currentCost      = currentNode.getCost();
            ArrayList<Edge> currentAdjacencyList = graph.getAdjacencyListOf(currentNodeId);
            if (currentCost > bestCost[currentNodeId])      // If cost of current node is 'outdated'
                continue;                                   //      Skip
            if (currentNodeId == finalStateId)              // If current node is the goal
                return currentNode.getCost();               //      Return the cummulative cost
            if (currentAdjacencyList.isEmpty())             // If node has no neighbours
                continue;                                   //      Skip
            for (Edge edge : currentAdjacencyList) {        // For every neighbour of current node
                byte neighbourId = edge.getDestinationId();
                short newCost = (short) (edge.getWeight() + currentCost);
                if (newCost < bestCost[neighbourId]) {                  // If new computed cost is 'better' than that of the neighbour
                    frontier.add(new SearchNode(neighbourId, newCost)); // Add it to the frontier
                    bestCost[currentNodeId] = newCost;
                }
            }
        }
        return -1;
    }

    private static PriorityQueue<SearchNode> initializePriorityQueue(byte initialStateId) { 
        PriorityQueue<SearchNode> pq = new PriorityQueue<SearchNode>();
        pq.add (new SearchNode (initialStateId, (short) 0));
        return pq;
    }

    private static short[] initializeBestCost(byte graphSize, byte initialStateId) {
        short[] bc = new short[graphSize];
        Arrays.fill(bc, Short.MAX_VALUE);
        bc[initialStateId] = 0;
        return bc;
    }
}
