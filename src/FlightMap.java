import java.io.File;
import java.util.*;
import java.io.*;

public class FlightMap{
    public Map<String, LinkedList<String>> routeMap;
    public Map<String, Integer> costMap;
    public Set<String> citiesSet;
    public String originCity;
    public String inputFileName;
    public String outputFileName;

    //constructor
    public FlightMap(String input, String output){
        // generate two maps to store the flight schedules and its cost
        routeMap = new TreeMap<String,LinkedList<String>>();
        costMap = new TreeMap<String,Integer>();
        citiesSet = new TreeSet<String>();
        // store the input and output file name
        inputFileName = input;
        outputFileName = output;
    }

    // store the flight schedule into two maps and a set(used to keep track which cities could be reached later)
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
    // use recursion to get the route for reachable cities and print it in the output file
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
    public static void main(String args[]){
    }
}