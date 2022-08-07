import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class FuzzingCompareCSV {

    private final ArrayList<String> wholeRow1 = new ArrayList<>();
    private final ArrayList<String> wholeRow2 = new ArrayList<>();
    private ArrayList<String> temp;
    private final HashMap unique_comb_to_choose = new HashMap();
    private int balance;
    private final ArrayList<Integer> uniqComb = new ArrayList<>();

    public FuzzingCompareCSV(String file_name1, String file_name2) throws IOException {
        readCSV("samples/"+file_name1+".csv", "samples/"+file_name2+".csv"); //sample_file_1
    }

    public ArrayList<String> getWholeRow1() {
        return this.wholeRow1;
    }

    public void setWholeRow1(String dataRow1) {
        this.wholeRow1.add(dataRow1);
    }

    public ArrayList<String> getWholeRow2() {
        return this.wholeRow2;
    }

    public void setWholeRow2(String dataRow2) {
        this.wholeRow2.add(dataRow2);
    }

    public String readCSV(String file1, String file2) throws IOException {
        String filePath = new File(file1).getAbsolutePath();
        BufferedReader CSVFile1 = new BufferedReader(new FileReader(filePath));
        String dataRow1 = CSVFile1.readLine();
        String[] cols = dataRow1.split(",");
        boolean withoutBalance = true;
        for (int i = 0; i < cols.length; i++){
            if (!Objects.equals(cols[i], "Balance")){
                unique_comb_to_choose.put(i, cols[i]);
            }
            else {
                balance = i;
                withoutBalance = false;
            }
        }
        if (withoutBalance){
            return "Error: Files without Balance column cannot be compared.";
        }
        System.out.println(dataRow1);
        while (dataRow1 != null)
        {   setWholeRow1(dataRow1);
            dataRow1 = CSVFile1.readLine(); // Read next line of data.
        }
        CSVFile1.close();
        temp = new ArrayList<>(wholeRow1);

        String filePath2 = new File(file2).getAbsolutePath();
        BufferedReader CSVFile2 = new BufferedReader(new FileReader(filePath2));
        String dataRow2 = CSVFile2.readLine();
        while (dataRow2 != null)
        {
            setWholeRow2(dataRow2);
            dataRow2 = CSVFile2.readLine(); // Read next line of data.
        }
        CSVFile2.close();
        //System.out.println("Read CSV 1 completed!");
        return "Read CSV completed!";
    }


    public String compareCSV() throws IOException {
        if (!Objects.equals(getWholeRow1().get(0), getWholeRow2().get(0))){
            System.out.println("Different Columns detected!");
            return "Different Columns detected!";
        }

        uniqComb.add(0);
        for (int i: uniqComb){
            if (!unique_comb_to_choose.containsKey(i)){
                System.out.println(
                        "Error: Please input your column index in the given range!");
                return "Error: Please input your column index in the given range!";
            }
        }


        for (String dataRow1 : temp) {
            String[] splitRow1 = dataRow1.split(",");
            boolean deleteLine = false;
            Iterator<String> iterRow2 = wholeRow2.iterator();
            while (iterRow2.hasNext()) {
                String dataRow2 = iterRow2.next();
                String[] splitRow2 = dataRow2.split(",");
                boolean sameContent = true;
                try {
                    for (int i : uniqComb) {
                        if (!splitRow1[i].equals(splitRow2[i])) {
                            sameContent = false;
                            break;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: Files have missing values!");
                    return "Error: Files have missing values!";
                }

                try {
                    if (sameContent) {
                        if (splitRow1[balance].equals(splitRow2[balance])) {
                            iterRow2.remove();
                            deleteLine = true;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: Files have missing values!");
                    return "Error: Files have missing values!";
                }
            }

            if (deleteLine) {
                Iterator<String> iterRow1 = wholeRow1.iterator();
                while (iterRow1.hasNext()) {
                    String line = iterRow1.next();
                    if (line.equals(dataRow1)) {
                        iterRow1.remove();
                    }
                }
            }
        }
        writeOutput("output");
        System.out.println("Compare CSV completed.");
        return "Compare CSV completed.";
    }

    public String writeOutput(String output_name){
        try{
            FileWriter writer = new FileWriter("samples/"+output_name+".csv");
            for(int i = 0; i < getWholeRow1().size(); i++)
            {
                writer.append("").append(getWholeRow2().get(i));
                writer.append('\n');
                writer.append("").append(getWholeRow1().get(i));
                writer.append('\n');
            }
            writer.flush();
            writer.close();
            System.out.println("Output has been created.");
            return "Output has been created.";
        } catch (IOException e){
            return "Write Output Fail!";
        }
    }


    public static void main(String[] args) throws IOException {
        FuzzingCompareCSV compareCSV = new FuzzingCompareCSV("fuzz7", "fuzz8");
        compareCSV.compareCSV();
    }
}

