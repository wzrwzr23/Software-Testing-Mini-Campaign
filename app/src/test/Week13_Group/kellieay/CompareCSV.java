package compareCSV;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class CompareCSV {
	
	public static boolean compareCols(String file1, String file2) throws IOException {
		BufferedReader br1 = new BufferedReader(new FileReader(file1));
		BufferedReader br2 = new BufferedReader(new FileReader(file2));
		String header1 = br1.readLine();
		String header2 = br2.readLine();
		List<String> head1 = new ArrayList<String>(Arrays.asList(header1.split(",")));
		List<String> head2 = new ArrayList<String>(Arrays.asList(header2.split(",")));
		if (head1.size() == (head2.size())) {
			return true;
		} else {
			return false;
		}
	}
	
	public static List<String> getHeaders(String file) throws FileNotFoundException {
		String headers;
		List<String> headersLs = new ArrayList<String>();
		Dictionary headerDict = new Hashtable();
		List<String> newLs = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
			headers = br.readLine();
			if (headers != null) {
				headersLs = Arrays.asList(headers.split(","));
				for (int i = 0; i < headersLs.size(); i++) {
					String ele = headersLs.get(i);
					if (ele.charAt(0) == '\"' && ele.charAt(ele.length() - 1)  == '\"') {
						String newele = ele.substring(1, ele.length() - 1);
						newLs.add(newele);
					} else {
						newLs.add(ele);
					}
//					ele.replaceAll("\"", "");
//					String item = String.valueOf(ele.charAt(0).startsWith("\"") ? headersLs.get(i).substring(1, headersLs.get(i).length() - 1) : headersLs.get(i));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newLs;
	}
	
	public static String getUserInput() {
		List<String> userList = new ArrayList<String>();
		String message = "Enter a header. (Press 'Enter' twice to stop entering headers): ";
		Scanner sc = new Scanner(System.in);
		// Scanner sc = new Scanner("/Users/kellieayy/ESC-MiniTesting/ESC-SoftwareMiniTest/fuzzing/random_string.txt");
		// Scanner sc = new Scanner("/Users/kellieayy/ESC-MiniTesting/ESC-SoftwareMiniTest/fuzzing/mutate.txt");
		System.out.println(message);
		String str = sc.nextLine();
		if (str != "") {
			return str;
		}
		else {return null;}
	}
	
	public static List<String> userInpLs(List<String>userLs, String userInp, List<String> headerLs) {
		if (headerLs.contains(userInp)) {
			userLs.add(userInp);
			return userLs;
		} else {
			System.out.println("ERROR! User has given an invalid input.");
			return null;
		}
		
	}
	
	public static List<Integer> getIndex(List<String> userInp, List<String> headerLs) {
		List<Integer>  indexLs = new ArrayList<Integer>();
		if (headerLs.size() > userInp.size()) {
			for (int i = 0; i < userInp.size(); i++) {
				Integer idx = headerLs.indexOf(userInp.get(i));
				indexLs.add(idx);
			}
		} else {
			for (int i = 0; i < headerLs.size(); i++) {
				Integer idx = headerLs.indexOf(userInp.get(i));
				indexLs.add(idx);
			}
		}
		return indexLs;
	}
	
	public static List<String> getContentCsv(String file) throws IOException {
		List<String> contentCsv = new ArrayList<String>();
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();
			while ((line = br.readLine()) != null) {
				contentCsv.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contentCsv;
	}
	
	public static List<String> compareCsv(List<String> file1, List<String> file2, List<Integer> indexLs) {
		List<String> differenceLs = new ArrayList<String>();
		if (file1.size() > file2.size()) {
			for (int i = 0; i < file2.size(); i++) {
				String line1 = file1.get(i);
				String line2 = file2.get(i);
				List<String> ls1 = new ArrayList<String>(Arrays.asList(line1.split(",")));
				List<String> ls2 = new ArrayList<String>(Arrays.asList(line2.split(",")));
				for (int j = 0; j < ls1.size(); j++) {
					if (indexLs.contains(j) || ls1.get(j) == ls2.get(j)) {
						continue;
					} else {
						differenceLs.add(line2);
						differenceLs.add(line1);
						break;
					}
				}
			}
		}
		else {
			for (int i = 0; i < file1.size(); i++) {
				String line1 = file1.get(i);
				String line2 = file2.get(i);
				List<String> ls1 = new ArrayList<String>(Arrays.asList(line1.split(",")));
				List<String> ls2 = new ArrayList<String>(Arrays.asList(line2.split(",")));
				for (int j = 0; j < ls1.size(); j++) {
					if (indexLs.contains(j) || ls1.get(j).equals(ls2.get(j))) {
						continue;
					} else {
						differenceLs.add(line2);
						differenceLs.add(line1);
						break;
					}
				}
			}
		}
		return differenceLs;
	}
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<String> headerLs1 = new ArrayList<String>();
		List<String> headerLs2 = new ArrayList<String>();
		
		String userInp;
		List<String> userLs = new ArrayList<String>();
		List<Integer> indexLs = new ArrayList<Integer>();
		
		List<String> file1Content = new ArrayList<String>();
		List<String> file2Content = new ArrayList<String>();
		List<String> differenceLs = new ArrayList<String>();
		boolean res = compareCols("/samples/fuzz1.csv", "/samples/fuzz2.csv");
		if (res == true) {
			List<String> headerLs = getHeaders("/samples/fuzz1.csv");
			System.out.println(headerLs);
			userInp = getUserInput();
			while (userInp != null) {
				userLs = userInpLs(userLs, userInp, headerLs);
				if (userLs != null) {
					userInp = getUserInput();
				} else {break;}
			}
			if (userLs != null) {
				indexLs = getIndex(userLs, headerLs);
				file1Content = getContentCsv("/samples/fuzz1.csv");
				file2Content = getContentCsv( "/samples/fuzz2.csv");
				differenceLs = compareCsv(file1Content, file2Content, indexLs);
	//			System.out.println(differenceLs);
				try {
					FileWriter fw = new FileWriter("output.csv");
					BufferedWriter bw = new BufferedWriter(fw);
					for (int i = 0; i < differenceLs.size(); i++) {
						bw.write(differenceLs.get(i));
						bw.newLine();
					}
					bw.close();
					fw.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		} else {System.out.println("ERROR. Please ensure that both files have the same number of columns and are in the same order.");}

	}

}
