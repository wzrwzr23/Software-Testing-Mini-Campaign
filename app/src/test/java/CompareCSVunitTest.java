import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class CompareCSVunitTest {
    @Test
    public void testReadCSVPass() throws IOException {
        CompareCSV compareCSV = new CompareCSV();
        Assert.assertEquals("Read CSV completed!", compareCSV.readCSV("samples/sample_file_1.csv", "samples/sample_file_2.csv"));
        System.out.println("Read CSV completed!");
    }
    @Test
    public void testReadCSVFailWithoutBalance() throws IOException {
        CompareCSV compareCSV = new CompareCSV("WithoutBalance1", "WithoutBalance2");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        try{
            Assert.assertEquals("Error: Files without Balance column cannot be compared.", compareCSV.readCSV("samples/WithoutBalance1.csv", "samples/WithoutBalance2.csv"));
            System.out.println("Error: Files without Balance column cannot be compared.");
        }catch (Exception e){
            System.out.println("File has balance column.");
        }
    }

    @Test
    public void testCompareCSVPass() throws IOException {
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
    public void testCompareCSVFailDifferentCols() throws IOException {
        CompareCSV compareCSV = new CompareCSV("DifferentColumn1", "DifferentColumn2");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        try{
            Assert.assertEquals("Different Columns detected!", compareCSV.compareCSV());
        }catch (Exception e){
            System.out.println("Same Column");
        }
    }

    @Test
    public void testCompareCSVFailInputFormatIncorrect() throws IOException {
        CompareCSV compareCSV = new CompareCSV("sample_file_1", "sample_file_2");
        ByteArrayInputStream in = new ByteArrayInputStream("a".getBytes());
        System.setIn(in);
        try{
            Assert.assertEquals("Error: Please input the column index in terms of integer.", compareCSV.compareCSV());
        }catch (Exception e){
            System.out.println("Input successfully.");
        }
    }

    @Test
    public void testCompareCSVFailColIndexOutOfRange() throws IOException {
        CompareCSV compareCSV = new CompareCSV("sample_file_1", "sample_file_2");
        ByteArrayInputStream in = new ByteArrayInputStream("3,4".getBytes());
        System.setIn(in);
        try{
            Assert.assertEquals("Error: Please input your column index in the given range!", compareCSV.compareCSV());
        }catch (Exception e){
            System.out.println("Input successfully.");
        }
    }

    @Test
    public void testCompareCSVFailFileWithMissingValue() throws IOException {
        CompareCSV compareCSV = new CompareCSV("FileWithMissingValue1", "FileWithMissingValue2");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        try{
            Assert.assertEquals("Error: Files have missing values!", compareCSV.compareCSV());
        }catch (Exception e){
            System.out.println("File length correct.");
        }
    }
    @Test
    public void testCompareCSVFailWithoutBalance() throws IOException {
        CompareCSV compareCSV = new CompareCSV("WithoutBalance1", "WithoutBalance2");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        try{
            Assert.assertEquals("Error: Files without Balance column cannot be compared.", compareCSV.readCSV("samples/WithoutBalance1.csv", "samples/WithoutBalance2.csv"));
            System.out.println("Error: Files without Balance column cannot be compared.");
        }catch (Exception e){
            System.out.println("File has balance column.");
        }
    }

    @Test
    public void testWriteOutputPass() throws IOException {
        CompareCSV compareCSV = new CompareCSV("sample_file_1", "sample_file_2");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        Assert.assertEquals("Output has been created.", compareCSV.writeOutput("UnitTest_output"));
    }
}
