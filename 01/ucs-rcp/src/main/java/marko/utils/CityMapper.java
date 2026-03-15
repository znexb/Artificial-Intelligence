package marko.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import marko.utils.io.OutputDevice;

public class CityMapper {
    private static Map<String, Byte> table = new LinkedHashMap<>();
    private static String[] cities = {"Oradea", "Zerind", "Arad", "Timisoara", "Lugoj", "Mehadia", "Drobeta", "Sibiu", "Rimnicu Valcea", "Craiova", "Fagaras", "Pitesti", "Giurgiu", "Bucharest", "Urziceni", "Vaslui", "Iasi", "Neamt", "Hirsova", "Eforie"};

    static {
        Byte id = 0;
        for(String city : cities) {
            table.put(city, id++);
        }
    }

    public static byte convert(String s) { return table.getOrDefault(s, Integer.valueOf(-1).byteValue()); }

    public static void printMap() { 
        for(Map.Entry<String, Byte> entry : table.entrySet()) {
            OutputDevice.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
