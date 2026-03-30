package marko;

import java.io.IOException;

import marko.options.CustomGraph;
import marko.options.RomaniaCities;
import marko.options.SavedGraph;
import marko.ui.Console;
import marko.utils.io.InputDevice;

public class App {
    private static void quit() { System.exit(0); }

    public static void main(String[] args) throws IOException {
        Console.header();
        Console.options();
        while (true) {
            byte option = InputDevice.readByte();
            switch (option) {
                case 1: {
                    RomaniaCities app = new RomaniaCities();
                    app.run();
                    break;
                } case 2: {
                    CustomGraph app = new CustomGraph();
                    app.run();
                    break;
                } case 3: {
                    SavedGraph app = new SavedGraph();
                    app.run();
                    break;
                } default: 
                    quit();
                    break;
            }
            Console.notifyRepeat();
        }
    }
}
