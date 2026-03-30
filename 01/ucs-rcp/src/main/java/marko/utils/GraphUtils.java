package marko.utils;

import java.io.IOException;

import marko.UniformCostSearch;
import marko.models.Graph;
import marko.utils.io.InputDevice;
import marko.utils.io.OutputDevice;

public class GraphUtils {
    public static byte inputStartNodeId() throws IOException {
        OutputDevice.print("Enter the start node: ");
        byte startNodeId = InputDevice.getNodeId();
        OutputDevice.endl();
        return startNodeId;
    }

    public static byte inputFinalNodeId() throws IOException {
        OutputDevice.print("Enter the final node: ");
        byte finalNodeId = InputDevice.getNodeId();
        OutputDevice.endl();
        return finalNodeId;
    }

    public static void runUCS(Graph graph, byte startNodeId, byte finalNodeId) {
        OutputDevice.print("UCS cost ::: ");
        OutputDevice.print(UniformCostSearch.computeCostFromAtoB(graph, startNodeId, finalNodeId));
        OutputDevice.endl();
    }
}
