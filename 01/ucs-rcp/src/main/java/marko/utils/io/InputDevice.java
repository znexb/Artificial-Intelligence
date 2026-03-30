package marko.utils.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import marko.utils.CityMapper;

public class InputDevice {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static String readLine() throws IOException { return reader.readLine(); }
    public static String readString() throws IOException { return readLine(); }
    public static byte readByte() throws IOException { return Byte.parseByte(readLine()); }
    public static int readInt() throws IOException { return Integer.parseInt(readString()); }

    public static int readIntSafe() throws IOException {
        while (true) {
            try {
                return Integer.parseInt(readString());
            } catch (NumberFormatException e) {
                OutputDevice.print("Invalid number. Try again: ");
            }
        }
    }

    public static boolean readYesNo() throws IOException {
        while (true) {
            String input = readString().trim().toLowerCase();

            if (input.equals("y") || input.equals("yes")) return true;
            if (input.equals("n") || input.equals("no")) return false;

            OutputDevice.print("Enter y/n: ");
        }
    }

    public static byte getNodeId() throws IOException {
        String input = readString();
        return Byte.parseByte(input);
    }

    public static byte dualInput(String input) {
        byte reg;
        try {
            reg = Byte.parseByte(input);
        } catch (NumberFormatException e) {
            reg = CityMapper.convert(input);
        }
        return reg;
    }
}
