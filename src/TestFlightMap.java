import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TestFlightMap{
    FlightMap fm;
    Map<String, LinkedList<String>> route;
    Map<String, Integer> cost;
    Set<String> cities;
    @Before
    public void initialize(){
        // create the fixture for testing
        route = new TreeMap<String,LinkedList<String>>();
        cost = new TreeMap<String,Integer>();
        cities = new TreeSet<String>();
    }
    @Test
    public void constructorTest1(){
        // test if the constructor could create data structures and store the input and output file
        fm = new FlightMap("inputFile1.txt","outputfile1.txt");
        assertEquals("inputFile1.txt",fm.inputFileName);
        assertEquals("outputFile1.txt",fm.outputFileName);
        assertEquals(route, fm.routeMap);
        assertEquals(cost, fm.costMap);
        assertEquals(cities, fm.citiesSet);
    }

    @Test
    public void constructorTest2(){
        // test if the constructor could create data structures and store the input and output file
        fm = new FlightMap("inputFile2.txt","outputfile2.txt");
        assertEquals("inputFile2.txt",fm.inputFileName);
        assertEquals("outputFile2.txt",fm.outputFileName);
        assertEquals(route, fm.routeMap);
        assertEquals(cost, fm.costMap);
        assertEquals(cities, fm.citiesSet);
    }
    @Test
    public void readInputFileTest1(){
        // test if maps are stored in the FlightMap for inputfile1.txt
        LinkedList<String> plist = new LinkedList<String>();
        plist.add("W");
        plist.add("R");
        route.put("P", plist);
        cost.put("PW", 200);
        cost.put("PR", 300);
        cities.add("P");

        LinkedList<String> qlist = new LinkedList<String>();
        qlist.add("X");
        route.put("Q", qlist);
        cost.put("QX", 375);
        cities.add("Q");

        LinkedList<String> rlist = new LinkedList<String>();
        rlist.add("X");
        route.put("R", rlist);
        cost.put("RX", 200);
        cities.add("R");

        LinkedList<String> slist = new LinkedList<String>();
        slist.add("T");
        route.put("S", slist);
        cost.put("ST", 300);
        cities.add("S");

        LinkedList<String> tlist = new LinkedList<String>();
        tlist.add("W");
        route.put("T", tlist);
        cost.put("TW", 350);
        cities.add("T");

        LinkedList<String> wlist = new LinkedList<String>();
        wlist.add("S");
        wlist.add("Y");
        route.put("W", wlist);
        cost.put("WS", 250);
        cost.put("WY", 500);
        cities.add("W");

        LinkedList<String> xlist = new LinkedList<String>();
        route.put("X", xlist);
        cities.add("X");

        LinkedList<String> ylist = new LinkedList<String>();
        ylist.add("Z");
        ylist.add("R");
        route.put("Y", ylist);
        cost.put("YZ", 450);
        cost.put("YR", 600);
        cities.add("Y");

        LinkedList<String> zlist = new LinkedList<String>();
        route.put("Z", zlist);
        cities.add("Z");

        fm = new FlightMap("inputFile1.txt","outputfile1.txt");
        fm.readInputFile();
        assertEquals(route, fm.routeMap);
        assertEquals(cost, fm.costMap);
        assertEquals(cities, fm.citiesSet);
        assertEquals("P", fm.originCity);
    }
    @Test
    public void readInputFileTest2(){
        // test if maps are stored in the FlightMap for inputfile2.txt
        route.clear();
        cost.clear();
        cities.clear();
        LinkedList<String> plist = new LinkedList<String>();
        plist.add("W");
        route.put("P", plist);
        cost.put("PW", 200);
        cities.add("P");

        LinkedList<String> qlist = new LinkedList<String>();
        qlist.add("X");
        route.put("Q", qlist);
        cost.put("QX", 375);
        cities.add("Q");

        LinkedList<String> rlist = new LinkedList<String>();
        rlist.add("X");
        route.put("R", rlist);
        cost.put("RX", 200);
        cities.add("R");

        LinkedList<String> slist = new LinkedList<String>();
        slist.add("T");
        route.put("S", slist);
        cost.put("ST", 300);
        cities.add("S");

        LinkedList<String> tlist = new LinkedList<String>();
        tlist.add("W");
        route.put("T", tlist);
        cost.put("TW", 350);
        cities.add("T");

        LinkedList<String> wlist = new LinkedList<String>();
        wlist.add("S");
        wlist.add("Y");
        route.put("W", wlist);
        cost.put("WS", 250);
        cost.put("WY", 500);
        cities.add("W");

        LinkedList<String> xlist = new LinkedList<String>();
        route.put("X", xlist);
        cities.add("X");

        LinkedList<String> ylist = new LinkedList<String>();
        ylist.add("Z");
        ylist.add("R");
        route.put("Y", ylist);
        cost.put("YZ", 450);
        cost.put("YR", 600);
        cities.add("Y");

        LinkedList<String> zlist = new LinkedList<String>();
        route.put("Z", zlist);
        cities.add("Z");

        fm = new FlightMap("inputFile2.txt","outputfile2.txt");
        fm.readInputFile();
        assertEquals(route, fm.routeMap);
        assertEquals(cost, fm.costMap);
        assertEquals(cities, fm.citiesSet);
        assertEquals("P", fm.originCity);
    }
    // search function are tested in TestSearchMap
}