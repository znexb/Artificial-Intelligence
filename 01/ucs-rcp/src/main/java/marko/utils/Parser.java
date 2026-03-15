package marko.utils;

import java.util.ArrayList;
import java.util.List;

import marko.models.Edge;

public class Parser {
    private final String s;
    private int i = 0;

    public Parser(String raw) { this.s = raw.replace(" ", ""); }


    public ArrayList<Edge>[] parse() { // Main function of the parser
        List<ArrayList<Edge>> groups = parsePGB();

        if(i != s.length()) { // This checks whether everything in the string was consumed.
            throw error("Trailing characters");
        }

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] result = groups.toArray(new ArrayList[0]);
        return result;
    }


    private List<ArrayList<Edge>> parsePGB() { // PGB := '[' (SGB (',' SGB)*)? ']'
        expect('[');

        List<ArrayList<Edge>> groups = new ArrayList<>();

        if(peekIs(']')) { // Allows empty first-grade [ ] input
            expect(']');
            return groups;
        }

        groups.add(parseSGB()); // One or more SGBs separated by commas
        while(peekIs(',')) {
            expect(',');
            groups.add(parseSGB());
        }

        expect(']');
        return groups;
    }

    private ArrayList<Edge> parseSGB() { // SGB := '[' (RB (',' RB)*)? ']'
        expect('[');

        ArrayList<Edge> edges = new ArrayList<>();

        if(peekIs(']')) { // Allows empty group
            expect(']');
            return edges;
        }

        edges.add(parseRB());
        while(peekIs(',')) {
            expect(',');
            edges.add(parseRB());
        }

        expect(']');
        return edges;
    }

    private Edge parseRB() { // RB := '(' PAIR ')'
        expect('(');

        byte destination = parseNonNegativeByte();
        expect(',');
        short weight = parseNonNegativeShort();

        expect(')');
        return new Edge(destination, weight);
    }


    // Simplify these later -- repetition of the first part of the code
    private byte parseNonNegativeByte() {
        if(!has() || !Character.isDigit(peek())) {
            throw error("Expected a digit");
        }
        int start = i;
        while(has() && Character.isDigit(s.charAt(i))) {
            i++;
        } // the substring (start, i) is guranteed digits only
        
        try {
            return Byte.parseByte(s.substring(start, i));
        } catch (NumberFormatException ex) {
            throw error("Invalid byte value");
        }
    }

    private short parseNonNegativeShort() {
        if(!has() || !Character.isDigit(peek())) {
            throw error("Expected a digit");
        }
        int start = i;
        while(has() && Character.isDigit(s.charAt(i))) {
            i++;
        }

        try {
            return Short.parseShort(s.substring(start, i));
        } catch (NumberFormatException ex) {
            throw error("Invalid short value");
        }
    }


    private boolean has() { return i < s.length(); }

    private char peek() { 
        if(!has()) throw error("Unexpected end of input");
        return s.charAt(i);
    }

    private boolean peekIs(char c) {
        return has() && s.charAt(i) == c;
    }

    private void expect(char c) {
        if(!peekIs(c)) throw error("Expected '" + c + "'");
        i++;
    }

    private RuntimeException error(String msg) { return new RuntimeException(msg + " at index " + i + " near: " + preview()); }

    private String preview() {
        int end = Math.min(s.length(), i + 20);
        return s.substring(i, end);
    }
}
