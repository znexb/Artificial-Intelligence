package marko;

public class Edge {
    private byte destinationId;
    private short weight;

    public Edge(byte destinationId, short weight) {
        this.destinationId = destinationId;
        this.weight = weight;
    }
}
