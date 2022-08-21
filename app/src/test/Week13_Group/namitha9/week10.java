package week10;
import java.io.*;  
import java.util.*;

public class week10 {

    public static void main(String[] args){
        String filepath1 = "/samples/fuzz1.csv";
        String filepath2 = "/samples/fuzz2.csv";
        filesCheck(filepath1, filepath2);
    }

    public static String filesCheck(String filepath1, String filepath2){
        try{
            String row1 = "";
            String[][] arr1 = new String[1002][];
            int i = 0;

            BufferedReader csvReader = new BufferedReader(new FileReader(filepath1));
            while ((row1 = csvReader.readLine()) != null) {
                String[] data = row1.split(",");
                arr1[i] = new String[]{data[0], data[1], data[2], data[3], data[4]};
                i+=1;
            }
            csvReader.close();
            // System.out.println(arr1);

            String row2 = "";
            String[][] arr2 = new String[1002][];
            int j = 0;

            BufferedReader csvReader2 = new BufferedReader(new FileReader(filepath2));
            while ((row2 = csvReader2.readLine()) != null) {
                String[] data = row2.split(",");
                //System.out.println(data[0]);
                arr2[j] = new String[]{data[0], data[1], data[2], data[3], data[4]};
                j+=1;
                // do something with the data
            }
            csvReader2.close();

            FileWriter csvW3 = new FileWriter(new File("/samples/fuzz_output.csv"));
            boolean empty_arr = true;
            for(int k=0; k<arr1.length-1; k++){
                if(arr1[k] != null){
                    empty_arr = false;
                }
            }
            if(empty_arr == true){
                return "empty";
            }
            boolean empty_arr2 = true;
            for(int k=0; k<arr2.length-1; k++){
                if(arr2[k] != null){
                    empty_arr2 = false;
                }
            }
            if(empty_arr2 == true){
                return "empty";
            }

            if(week10.compareType(filepath1, filepath2) == false){
                return "different file types";
            }

            String[][] arr3 = new String[1002][];
            int h = 0;

            System.out.println("Enter a unique combination of column index: 0, 1, 2, 3");

            Scanner user_input = new Scanner(System.in); 
            String col_idx = user_input.nextLine(); 
            ArrayList<Integer> final_idx = new ArrayList<>();
            String[] col_idxs = col_idx.split(",");
            try {
                for (String col: col_idxs) {
                    final_idx.add(Integer.parseInt(col));
                }
                final_idx.add(4);
            }catch (Exception e){
                System.out.println("Error: Please input the column index in terms of integer.");
                return "Error: Please input the column index in terms of integer.";
            }

            // System.out.println("checkpoint1");

            for(int k=0; k<arr1.length-1; k++){
                Integer check = 0;
                if(arr1[k][1].equals(arr2[k][1]) && arr1[k][2].equals(arr2[k][2]) && arr1[k][3].equals(arr2[k][3]) && arr1[k][4].equals(arr2[k][4])){
                    check = 0;
                    continue;
                }
                // System.out.println("heree");
                for(int p=0; p<5; p++){
                    // System.out.println(arr1[k][p]);
                    // System.out.println(p);
                    // System.out.println(final_idx);
                    if(arr1[k][p].equals(arr2[k][p]) && final_idx.contains(p)){
                        check+=1;
                        // System.out.println(check);
                    }
                }
                // System.out.println(check);
                // System.out.println(final_idx);
                if(check < final_idx.size()){
                    //System.out.println(arr1[k][0]);
                    arr3[h] = new String[]{arr2[k][0], arr2[k][1], arr2[k][2], arr2[k][3], arr2[k][4]};
                    arr3[h+1] = new String[]{arr1[k][0], arr1[k][1], arr1[k][2], arr1[k][3], arr1[k][4]};
                    check = 0;
                    // System.out.println(arr3[h][0]);
                    // System.out.println("checkpoint2");
                    h+=2;
                }
            }
            // System.out.println("checkpoint3");
            // System.out.println(arr3[5][0]);
            // System.out.println(h);

            
            BufferedWriter csvWriter = new BufferedWriter(csvW3);
            for(int d=0;d<h;d++)
            {
                // System.out.println("checkpoint4");
                csvWriter.write(arr3[d][0]+","+arr3[d][1]+","+arr3[d][2]+","+arr3[d][3]+","+arr3[d][4]);
                csvWriter.newLine();
            }
            csvWriter.close();
            csvW3.close();
            // System.out.println("checkpoint5");

        }catch (IOException e)   
        {  
            e.printStackTrace();  
            return "file doesnt exist";
        }  

        return "fuzz_output.csv";
    }
    
    public static boolean compareNumberOfCols(String filepath1, String filepath2){
        try{
            BufferedReader csvReader1 = new BufferedReader(new FileReader(filepath1));
            BufferedReader csvReader2 = new BufferedReader(new FileReader(filepath2));
            String header1 = csvReader1.readLine();
		    String header2 = csvReader2.readLine();
            if (header1 == null && header2 == null){
                csvReader1.close();
                csvReader2.close();
                return true;
            }
            if (header1 == null || header2 == null){
                csvReader1.close();
                csvReader2.close();
                return false;
            }
            String[] data1 = header1.split(",");
            String[] data2 = header2.split(",");
            System.out.println(data1);
            csvReader1.close();
            csvReader2.close();
            if (data1.length == data2.length) {
                return true;
            }
            else {
                return false;
            }
        }catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    public static boolean compareType(String filepath1, String filepath2){
        try{
            String extension1 = "";
            int i1 = filepath1.lastIndexOf(".");
            if(i1>0){
                extension1 = filepath1.substring(i1+1);
            }
            // System.out.println(extension1);
            String extension2 = "";
            int i2 = filepath2.lastIndexOf(".");
            if(i2>0){
                extension2 = filepath2.substring(i2+1);
            }
            // System.out.println(extension2);
            if(extension1.equals(extension2)){
                return true;
            }
            return false;
        }catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
    }
}
