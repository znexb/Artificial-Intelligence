package marko.utils.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputDevice {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static String readLine() throws IOException { return reader.readLine(); }

    public static String readString() throws IOException { return readLine(); }

    public static byte readByte() throws IOException { return Byte.parseByte(readLine()); }
}
