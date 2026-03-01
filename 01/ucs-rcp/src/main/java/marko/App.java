package marko;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class App 
{
    private ArrayList<Edge>[] graph;

    private static void readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Graph: ");

        String input = reader.readLine();
        input = raw.replace(" ", "");
        System.out.println("Normalization: " + input);
    }

    public static void main( String[] args ) throws IOException {
        readInput();
    }
}
