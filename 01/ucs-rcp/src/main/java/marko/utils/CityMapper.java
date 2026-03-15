package marko.utils;

import java.util.HashMap;
import java.util.Map;

public class CityMapper {
    private static Map<String, Byte> table = new HashMap<>();
    private static String[] cities = {"Oradea", "Zerind", "Arad", "Timisoara", "Lugoj", "Mehadia", "Drobeta", "Sibiu", "Rimnicu Valcea", "Fagaras", "Pitesti", "Giurgiu", "Bucharest", "Urziceni", "Vaslui", "Iasi", "Neamt", "Hirsova", "Eforie"};

    static {
        Byte id = 0;
        for(String city : cities) {
            table.put(city, id++);
        }
    }

    public static int convert(String s) { return table.getOrDefault(s, Integer.valueOf(-1).byteValue()); }
}
