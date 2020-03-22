import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Bookings {

	public static ObservableList<String> doc_times = FXCollections.observableArrayList();
	public static void verify_appo(String file) {
		
		
	}
	
	public static void get_doc_times(String d) {
		
		String file = "userdata/" + d;
		System.out.println(file);
		boolean check = false;
		
		try {
			Scanner input = new Scanner(new File(file));
			while (input.hasNextLine()) {
				if (check == true) {
					String temp = input.next();
					System.out.println(temp);
					String temp2[] = temp.split(",");
					for (int i = 0; i<=temp2.length; i++) {
						doc_times.add(temp2[i]);
						System.out.println(doc_times);
						
					}
					check = false;
					
				}
				if(input.next().equals("times")) {
					System.out.println("FOUND TIMES");
					check = true;
				}
			}
			
		}
		catch (Exception e){
			
		}
	}
}
