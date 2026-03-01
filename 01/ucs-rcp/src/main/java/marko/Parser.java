package marko;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final String s;
    private int i = 0;

    public Parser(String raw) { this.s = raw.replace(" ", ""); }


    public ArrayList<Edge>[] parse() {
        List<ArrayList<Edge>> groups = parseRGB();

        if(i != s.length()) { // This checks whether everything in the string was consumed.
            throw error("Trailing characters");
        }
    }
}
