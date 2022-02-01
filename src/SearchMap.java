import java.util.*;
import java.io.*;

public class SearchMap{

    public static void main(String args[]) {
        FlightMap fm = new FlightMap(args[0],args[1]);
        fm.readInputFile();
        try {
            PrintWriter output = new PrintWriter(args[1]);
            output.println(String.format("%-15s %-25s %-10s", "Destination", "Flight Route from " + fm.originCity, "Total Cost"));
            fm.search(fm.originCity, fm.originCity, 0, output);
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}