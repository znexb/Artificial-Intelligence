package marko;

import java.util.ArrayList;

public class Edge {
    private byte destinationId;
    private short weight;

    public Edge(byte destinationId, short weight) {
        this.destinationId = destinationId;
        this.weight = weight;
    }

    public void setDestinationId(byte destinationId) { this.destinationId = destinationId; }

    public void setWeight(short weight) { this.weight = weight; }
}
