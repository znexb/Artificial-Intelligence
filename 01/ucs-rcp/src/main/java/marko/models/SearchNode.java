package marko.models;

public class SearchNode implements Comparable<SearchNode> {
    private byte id;
    private short cost;     // Storing cumulative costs for node, computed in the UCS function of main

    public SearchNode(byte id, short cost) {
        this.id = id;
        this.cost = cost;
    }

    public byte getId() { return id; }

    public short getCost() { return cost; }

    @Override
    public int compareTo(SearchNode other) { return Short.compare(this.cost, other.cost); }

    @Override
    public String toString() { return "(" + id + "," + cost + ")"; }
}
