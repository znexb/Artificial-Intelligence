package marko;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Edge>[] graph;
    private byte size = -1;             // -1 means that it was not computed yet. = sentinel value

    public byte getSize() {
        if (size == -1) size = computeSize();
        return size;
    }

    public byte computeSize() {
        byte size = 1;
        for(ArrayList<Edge> adjacencyList : graph) {
            for(Edge edge : adjacencyList) {
                byte destinationId = edge.getDestinationId();
                size = destinationId >= size ? (byte) (destinationId + 1) : size;
            }
        }
        return size;
    }
}
