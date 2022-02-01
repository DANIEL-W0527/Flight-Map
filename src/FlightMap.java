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

    public FlightMap(String input, String output){
        routeMap = new TreeMap<String,LinkedList<String>>();
        costMap = new TreeMap<String,Integer>();
        citiesSet = new TreeSet<String>();
        inputFileName = input;
        outputFileName = output;
    }

    public void readInputFile(){
        try {
            File inputFile = new File(inputFileName);
            Scanner in = new Scanner(inputFile);
            originCity = in.nextLine();
            while (in.hasNextLine()){
                String line = in.nextLine();
                String[] splitted = line.split(" ");
                if (!citiesSet.contains(splitted[0])){
                    citiesSet.add(splitted[0]);
                    LinkedList<String> newll = new LinkedList<String>();
                    newll.add(splitted[1]);
                    routeMap.put(splitted[0],newll);
                }
                else{
                    routeMap.get(splitted[0]).add(splitted[1]);
                }
                if (!citiesSet.contains(splitted[1])){
                    citiesSet.add(splitted[1]);
                    LinkedList<String> newll = new LinkedList<String>();
                    routeMap.put(splitted[1],newll);
                }
                String routeName = splitted[0] + splitted[1];
                costMap.put(routeName, Integer.parseInt(splitted[2]));
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void search(String route, String city, Integer cost,PrintWriter newPW){
        ListIterator listIt = routeMap.get(city).listIterator();
        while (listIt.hasNext()){
            String tempRoute = route;
            Integer tempCost = cost;
            String temp = (String) listIt.next();
            if (citiesSet.contains(temp)){
                tempRoute += ", " + temp;
                tempCost += costMap.get(city+temp);
                citiesSet.remove(temp);
                newPW.println(String.format("%-15s %-25s %-10s", temp, tempRoute, tempCost));
            }
            else{
                return;
            }
            search(tempRoute, temp, tempCost,newPW);
        }
    }
    public static void main(String args[]){
        /**
        FlightMap fm = new FlightMap("inputfile2.txt","outputfile.txt");
        fm.readInputFile();
        System.out.println(fm.routeMap);
        System.out.println(fm.costMap);
        System.out.println(fm.citiesSet);
        System.out.println(fm.originCity);
        */
    }
}