import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class CompareCSVunitTest {
    @Test
    public void testfilesCheckPass() throws IOException {
        CompareCSV compareCSV = new CompareCSV();
        Assert.assertEquals("Read CSV completed!", compareCSV.readCSV("samples/sample_file_1.csv", "samples/sample_file_2.csv"));
        System.out.println("Read CSV completed!");
    }

    @Test
    public void testcompareNumberOfColsPass() throws IOException {
        CompareCSV compareCSV = new CompareCSV("sample_file_1", "sample_file_2");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        try{
            Assert.assertEquals("Compare CSV completed.", compareCSV.compareCSV());
        }catch (IOException e){
            System.out.println("compareCSV failed.");
        }
    }

    @Test
    public void testcompareType() throws IOException {
        CompareCSV compareCSV = new CompareCSV("sample_file_1", "sample_file_2");
        ByteArrayInputStream in = new ByteArrayInputStream("a".getBytes());
        System.setIn(in);
        try{
            Assert.assertEquals("Error: Please input the column index in terms of integer.", compareCSV.compareCSV());
        }catch (Exception e){
            System.out.println("Input successfully.");
        }
    }

