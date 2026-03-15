package marko.models;

public class Edge implements Comparable<Edge>{
    private byte destinationId;
    private short weight;

    public Edge(byte destinationId, short weight) {
        this.destinationId = destinationId;
        this.weight = weight;
    }

    public void setDestinationId(byte destinationId) { this.destinationId = destinationId; }

    public byte getDestinationId() { return destinationId; }

    public void setWeight(short weight) { this.weight = weight; }

    public short getWeight() { return weight; }


    public int compareTo(Edge other) {
        short otherWeight = other.getWeight(); return weight < otherWeight ? -1 : weight > otherWeight ? 1 : 0;
    }

    @Override
    public String toString() { return "(" + destinationId + "," + weight + ")"; }
}
