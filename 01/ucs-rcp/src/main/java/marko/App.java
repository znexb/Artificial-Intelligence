package marko;

import java.io.IOException;

import marko.models.Graph;
import marko.ui.Console;
import marko.utils.io.InputDevice;
import marko.utils.io.OutputDevice;

public class App 
{
    private static Graph graph;
    private static byte initialStateId;
    private static byte finalStateId;

    private static void inputGraph() throws IOException {
        OutputDevice.print("Enter the base graph: ");
        String input = InputDevice.readString();
        graph = new Graph(input);
        
        OutputDevice.printGraphInformation(graph);

        OutputDevice.print("Enter the initial node (the city one finds themselves in): ");
        input = InputDevice.readString();
        initialStateId = Byte.parseByte(input);
        OutputDevice.endl();

        OutputDevice.print("Enter the goal node (the city to get to): ");
        input = InputDevice.readString();
        finalStateId = Byte.parseByte(input);
        OutputDevice.endl();

        OutputDevice.print("UCS cost ::: ");
        OutputDevice.print(UniformCostSearch.computeCostFromAtoB(graph, initialStateId, finalStateId));
        OutputDevice.endl();
    }

    private static void quit() { System.exit(0); }

    public static void main(String[] args) throws IOException {
        Console.header();
        Console.options();
        while (true) {
            byte option = InputDevice.readByte();
            switch (option) {
                case 2:
                    inputGraph();
                    break;
                default: 
                    quit();
                    break;
            }
        }
    }
}
