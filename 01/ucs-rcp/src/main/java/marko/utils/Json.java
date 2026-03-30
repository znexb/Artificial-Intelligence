package marko.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import marko.models.Edge;
import marko.models.Graph;

public class Json {
    // GRAPH <-> JSON

    public static String toJson(Graph graph) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (byte i = 0; i < graph.getSize(); i++) {
            ArrayList<Edge> list = graph.getAdjacencyListOf(i);

            sb.append("[");
            for (int j = 0; j < list.size(); j++) {
                Edge e = list.get(j);

                sb.append("[")
                  .append(e.getDestinationId())
                  .append(",")
                  .append(e.getWeight())
                  .append("]");

                if (j < list.size() - 1) sb.append(",");
            }
            sb.append("]");

            if (i < graph.getSize() - 1) sb.append(",");
        }

        sb.append("]");
        return sb.toString();
    }

    public static Graph fromJson(String json) {
        json = json.trim();

        if (json.length() < 2) return new Graph((byte) 0);

        // remove outer []
        json = json.substring(1, json.length() - 1).trim();

        List<ArrayList<Edge>> nodes = new ArrayList<>();

        int i = 0;
        while (i < json.length()) {
            if (json.charAt(i) == '[') {
                int start = i;
                int depth = 0;

                while (i < json.length()) {
                    if (json.charAt(i) == '[') depth++;
                    else if (json.charAt(i) == ']') depth--;

                    if (depth == 0) break;
                    i++;
                }

                String nodeStr = json.substring(start + 1, i); // remove []
                nodes.add(parseNode(nodeStr));
            }
            i++;
        }

        Graph g = new Graph((byte) nodes.size());

        for (int idx = 0; idx < nodes.size(); idx++) {
            ArrayList<Edge> edges = nodes.get(idx);
            for (Edge e : edges) {
                g.getAdjacencyListOf((byte) idx).add(e);
            }
        }

        return g;
    }

    private static ArrayList<Edge> parseNode(String nodeStr) {
        ArrayList<Edge> edges = new ArrayList<>();

        int i = 0;
        while (i < nodeStr.length()) {
            if (nodeStr.charAt(i) == '[') {
                int start = i;
                while (nodeStr.charAt(i) != ']') i++;

                String pair = nodeStr.substring(start + 1, i);
                String[] parts = pair.split(",");

                byte dest = Byte.parseByte(parts[0].trim());
                short weight = Short.parseShort(parts[1].trim());

                edges.add(new Edge(dest, weight));
            }
            i++;
        }

        return edges;
    }

    // FILE OPERATIONS

    public static void appendGraphToFile(Graph graph, String filePath) throws IOException {
        List<String> graphs = readRawGraphs(filePath);
        graphs.add(toJson(graph));

        String result = "[\n" + String.join(",\n", graphs) + "\n]";

        Files.writeString(
            Path.of(filePath),
            result,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING
        );
    }

    public static List<Graph> readGraphsFromFile(String filePath) throws IOException {
        List<String> raw = readRawGraphs(filePath);
        List<Graph> graphs = new ArrayList<>();

        for (String g : raw) {
            graphs.add(fromJson(g));
        }

        return graphs;
    }

    public static Graph readGraphByIndex(String filePath, int index) throws IOException {
        List<Graph> graphs = readGraphsFromFile(filePath);

        if (index < 0 || index >= graphs.size()) {
            throw new IndexOutOfBoundsException("Invalid graph index: " + index);
        }

        return graphs.get(index);
    }

    private static List<String> readRawGraphs(String filePath) throws IOException {
        Path path = Path.of(filePath);

        List<String> result = new ArrayList<>();

        if (!Files.exists(path) || Files.size(path) == 0) return result;

        String content = Files.readString(path).trim();

        if (content.length() <= 2) return result;

        content = content.substring(1, content.length() - 1); // remove []

        int i = 0;
        while (i < content.length()) {
            if (content.charAt(i) == '[') {
                int start = i;
                int depth = 0;

                while (i < content.length()) {
                    if (content.charAt(i) == '[') depth++;
                    else if (content.charAt(i) == ']') depth--;

                    if (depth == 0) break;
                    i++;
                }

                result.add(content.substring(start, i + 1));
            }
            i++;
        }

        return result;
    }
}