import java.io.File;
import java.util.*;
import java.io.*;

/**
 * store input file map and find all reachable cities from origin city
 */
public class FlightMap{
    /** map with city name as key and linkedlist of cities name as value to realize the adjacency list*/
    public Map<String, LinkedList<String>> routeMap;
    /** map with the flight route (PR means from P to R) as value with its cost as value*/
    public Map<String, Integer> costMap;
    /** set of all cities, used later to tell if a city has been explored or not*/
    public Set<String> citiesSet;
    /** origin city we are looking at */
    public String originCity;
    /** the name for input file*/
    public String inputFileName;
    /** the name for output file */
    public String outputFileName;

    /**
     * constructor method
     * @param input, name of the input file
     * @param output, name of the output file
     */
    public FlightMap(String input, String output){
        // generate two maps to store the flight schedules and its cost
        routeMap = new TreeMap<String,LinkedList<String>>();
        // map to keep track of the cost
        costMap = new TreeMap<String,Integer>();
        // set of of cities
        citiesSet = new TreeSet<String>();
        // store the input and output file name
        inputFileName = input;
        outputFileName = output;
    }

    /**
     *  store the flight schedule into two maps(routeMap, costMap) and a set(citiesSet)
     */
    public void readInputFile(){
        try {
            File inputFile = new File(inputFileName);
            Scanner in = new Scanner(inputFile);
            originCity = in.nextLine();
            while (in.hasNextLine()){
                String line = in.nextLine();
                String[] splitted = line.split(" ");
                // if citiesSet does not contain the departing city, add to the citiesSet
                // create adjacency list for that city with arriving city
                if (!citiesSet.contains(splitted[0])){
                    citiesSet.add(splitted[0]);
                    LinkedList<String> newll = new LinkedList<String>();
                    newll.add(splitted[1]);
                    routeMap.put(splitted[0],newll);
                }
                // if the departing city is in the routeMap, add arriving city into its adjacency list in routeMap
                else{
                    routeMap.get(splitted[0]).add(splitted[1]);
                }
                // if the arriving city is not in the citiesSet, add it to the routeMap
                if (!citiesSet.contains(splitted[1])){
                    citiesSet.add(splitted[1]);
                    LinkedList<String> newll = new LinkedList<String>();
                    routeMap.put(splitted[1],newll);
                }
                String routeName = splitted[0] + splitted[1];
                // record the cost for each flight in the costMap
                costMap.put(routeName, Integer.parseInt(splitted[2]));
            }
            // close the scanner
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * use recursion to get the route for reachable cities and print it in the output file
     *
     * @param route, the path before reaching to the exploring city
     * @param city, the departing city we are exploring
     * @param cost, the cost of flight before reaching to the exploring city
     * @param newPW, the printwriter for the output file
     */

    public void search(String route, String city, Integer cost, PrintWriter newPW){
        // Iterate through all possible cities city could reach to
        ListIterator listIt = routeMap.get(city).listIterator();
        while (listIt.hasNext()){
            String tempRoute = route;
            Integer tempCost = cost;
            String temp = (String) listIt.next();
            // citiesSet are all cities that we haven't explored that if city could reach to
            if (citiesSet.contains(temp)){
                tempRoute += ", " + temp;
                tempCost += costMap.get(city+temp);
                // if reached by city, remove it from citiesSet
                citiesSet.remove(temp);
                newPW.println(String.format("%-15s %-25s %-10s", temp, tempRoute, tempCost));
            }
            // if this city is not in the citiesSet, we return because we already find one route could reach to city before
            else{
                return;
            }
            // recursion to search for next levels of cities
            search(tempRoute, temp, tempCost,newPW);
        }
    }
}