import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class CompareCSVsystemTest {
    @Test
    public void SystemTestPass() throws IOException {
        CompareCSV compareCSV = new CompareCSV("sample_file_1", "sample_file_2");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        Assert.assertEquals("Compare CSV completed.", compareCSV.compareCSV());
    }

    @Test
    public void SystemTestFail() throws IOException {
        CompareCSV compareCSV = new CompareCSV("sample_file_1", "sample_file_2");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        Assert.assertEquals("Compare CSV completed.", compareCSV.compareCSV());
    }
}
