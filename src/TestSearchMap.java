import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class TestSearchMap{
    @Test
    public void mainTest1(){
        // test if we get the expected output file using inputfile1.txt
        SearchMap.main(new String[]{"inputfile1.txt","outputfile1.txt"});
        try {
            byte[] fileExpectedBytes = Files.readAllBytes(Paths.get("outputfileExpected1.txt"));
            byte[] fileActualBytes = Files.readAllBytes(Paths.get("outputfile1.txt"));
            String fileExpected = new String(fileExpectedBytes, StandardCharsets.UTF_8);
            String fileActual = new String(fileActualBytes,StandardCharsets.UTF_8);
            assertEquals(fileExpected,fileActual);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void mainTest2(){
        // test if we get the expected output file using inputfile2.txt
        SearchMap.main(new String[]{"inputfile2.txt","outputfile2.txt"});
        try {
            byte[] fileExpectedBytes = Files.readAllBytes(Paths.get("outputfileExpected2.txt"));
            byte[] fileActualBytes = Files.readAllBytes(Paths.get("outputfile2.txt"));
            String fileExpected = new String(fileExpectedBytes, StandardCharsets.UTF_8);
            String fileActual = new String(fileActualBytes,StandardCharsets.UTF_8);
            assertEquals(fileExpected,fileActual);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}