package marko.ui;

import marko.utils.io.OutputDevice;

public class Console {
    static public void header() {
        OutputDevice.endl();
        String title = "Uniform Cost Search for Graphs";
        byte titleLength = (byte) title.length();
        printHeaderPattern(titleLength);
        printPattern("=-", titleLength, title);
        printHeaderPattern(titleLength, (byte) 3);
        printPattern("=-", titleLength, "© Marko Bele, AI Assignment 01");
        printHeaderPattern(titleLength);
        OutputDevice.endl();
    }

    static public void options() {
        printOptionsPattern();
        indent(); OutputDevice.print("1. Romania Cities Problem *");
        indent((byte) 2); OutputDevice.println("- main function of this app");
        indent(); OutputDevice.print("2. Enter a custom graph");
        indent((byte) 2); OutputDevice.println("- for formatting, please check out \"example_input.txt\" and \"input_grammar.txt\"!");
        indent(); OutputDevice.print("3. Enter a saved graph");
        indent((byte) 2); OutputDevice.println("- to be implemented!");
        indent(); OutputDevice.println("4. Quit");
        printOptionsPattern();
    }

    static private void printOptionsPattern() { printPattern("=", (byte) 23); }
    
    static private void printHeaderPattern(byte length) { printPattern("=-", length); }
    
    static private void printHeaderPattern(byte length, byte n) { for(byte i = 0; i < n; ++i) { printPattern("=-", length); } }

    static private void indent() { printPattern(" ", (byte) 4); }

    static private void indent(byte n) { for(byte i = 0; i < n; ++i) { indent(); } }

    static private void printPattern(String p, byte size) { for(byte i = 0; i < size; ++i) { OutputDevice.print(p); } OutputDevice.endl(); }

    static private void printPattern(String p, byte size, String s) { 
        byte patternSize = (byte) (size / 4);
        // if(p.length() > 1) patternSize /= (byte) p.length();
        for(byte i = 0; i < patternSize; ++i) { OutputDevice.print(p); }
        OutputDevice.print(s);
        for(byte i = 0; i < patternSize; ++i) { OutputDevice.print(p); }
        OutputDevice.endl();
    }
}
