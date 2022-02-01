import java.util.*;
import java.io.*;

/**
 * Search Map uses FlightMap to get user input and output file name,
 * and print out all reachable cities from the origin city
 */
public class SearchMap{

    /**
     * main method to get input and output file
     * print out all reachable cities from the origin city in output file
     *
     * @param args command line arguments, input file name and output file name
     */
    public static void main(String args[]) {
        // get user's input for input file and output file name from command
        FlightMap fm = new FlightMap(args[0],args[1]);
        // load input file and store the map
        fm.readInputFile();
        try {
            // print the table to the output file with all reachable cities
            PrintWriter output = new PrintWriter(args[1]);
            output.println(String.format("%-15s %-25s %-10s", "Destination", "Flight Route from " + fm.originCity, "Total Cost"));
            // search for reachable cities from the origin city
            fm.search(fm.originCity, fm.originCity, 0, output);
            // close the printwriter
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}