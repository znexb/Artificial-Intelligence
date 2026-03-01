package marko;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class App 
{
    private static ArrayList<Edge>[] graph;

    private static String readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Graph: ");

        String input = reader.readLine();
        return input;
    }

    public static void main( String[] args ) throws IOException {
        String input = readInput();
        Parser p = new Parser(input);
        graph = p.parse();
    }
}
