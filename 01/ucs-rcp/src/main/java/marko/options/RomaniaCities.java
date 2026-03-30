package marko.options;

import java.io.IOException;

import marko.UniformCostSearch;
import marko.models.Graph;
import marko.utils.CityMapper;
import marko.utils.io.InputDevice;
import marko.utils.io.OutputDevice;

public class RomaniaCities {
    private static Graph romania;
    private byte startCityId;
    private byte finalCityId;

    static { // Map from "Artificial Intelligence: A Modern Approach, Global Edition, 4ed"
        romania = new Graph((byte) 20);

        romania.addNode(1, 71, 7, 151);                     // Oradea           0
        romania.addNode(0, 71, 2, 75);                      // Zerind           1
        romania.addNode(1, 75, 3, 118, 7, 140);             // Arad             2
        romania.addNode(2, 118, 4, 111);                    // Timisoara        3
        romania.addNode(3, 111, 5, 70);                     // Lugoj            4
        romania.addNode(4, 70, 6, 75);                      // Mehadia          5
        romania.addNode(5, 75, 9, 120);                     // Drobeta          6
        romania.addNode(0, 151, 2, 140, 8, 80, 10, 99);     // Sibiu            7
        romania.addNode(7, 80, 9, 146, 11, 97);             // Rimnicu Vilcea   8
        romania.addNode(6, 120, 8, 146, 11, 138);           // Craiova          9
        romania.addNode(7, 99, 13, 211);                    // Fagaras          10
        romania.addNode(8, 97, 9, 138, 13, 101);            // Pitesti          11
        romania.addNode(13, 90);                            // Giurgiu          12
        romania.addNode(10, 211, 11, 101, 12, 90, 14, 85);  // Bucharest        13
        romania.addNode(13, 85, 15, 142, 18, 98);           // Urziceni         14
        romania.addNode(14, 142, 16, 92);                   // Vaslui           15
        romania.addNode(15, 92, 17, 87);                    // Iasi             16
        romania.addNode(16, 87);                            // Neamt            17
        romania.addNode(14, 98, 19, 86);                    // Hirsova          18
        romania.addNode(18, 86);                            // Eforie           19
    }

    public void run() throws IOException {
        printMap();
        inputStartCity();
        inputFinalCity();
        // OutputDevice.print(romania);
        runUCS();
    }

    private void printMap() {
        OutputDevice.endl();
        CityMapper.printMap();
        OutputDevice.endl();
    }

    private void inputStartCity() throws IOException {
        OutputDevice.print("Enter the starting city: ");
        String input = InputDevice.readString().trim();

        try {
            startCityId = Byte.parseByte(input);
        } catch (NumberFormatException e) {
            startCityId = CityMapper.convert(input);
        }
        
        OutputDevice.endl();
    }

    private void inputFinalCity() throws IOException {
        OutputDevice.print("Enter the destination city: ");
        String input = InputDevice.readString();
        finalCityId = CityMapper.convert(input);
        OutputDevice.endl();
    }

    private void runUCS() {
        OutputDevice.print("UCS cost ::: ");
        OutputDevice.print(UniformCostSearch.computeCostFromAtoB(romania, startCityId, finalCityId));
        OutputDevice.endl();
    }
}
