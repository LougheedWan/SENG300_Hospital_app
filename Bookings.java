import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Bookings {

	public static ObservableList<String> doc_times = FXCollections.observableArrayList();
	public static ArrayList<String> temp_store = new ArrayList<String>();
	public static ObservableList<String> temp_names = FXCollections.observableArrayList();
	public static ArrayList<String> temp_info = new ArrayList<String>();
	public static String current_id;
	public static void verify_appo(String file) {
		try {
			Scanner search = new Scanner(new File(file));
			String doc = Main.current.getName();
			int current_appo = 0;
			while (search.hasNext()) {
				if (search.nextLine().equals("---")) {
					
					current_appo++;
					if (search.nextLine().equals(doc)) {
						temp_store.add(Integer.toString(current_appo));
					}
				}
			}
			search.close();
			System.out.println(temp_store);
		}
		catch (Exception e) {
			
		}
		
		
		
	}
	
	public static void get_verify_info() {
		try {
			Scanner search  = new Scanner(new File("pending.txt"));
			int count = 0;
			while (search.hasNext()) {
				if (search.nextLine().equals("---")) {
					
					count++;
					
				if (temp_store.contains(Integer.toString(count))) {
					search.nextLine();
					search.nextLine();
					temp_names.add(search.nextLine());
				}
					
				}
			}
		}
		catch (Exception e) {
			
		}
		
		
	}
	
	public static void confirm_verify_info() {
		int current_appo = Main.ver_list.getSelectionModel().getSelectedIndex();
		current_id = temp_store.get(current_appo);
		System.out.println(current_appo);
		int count =0;
		try {
			Scanner search = new Scanner(new File("pending.txt"));
			while (search.hasNext()) {
				if (search.nextLine().equals("---")) {
					count++;
				}
				if (count == Integer.parseInt(current_id)) {
					temp_info.add(search.nextLine());
					temp_info.add(search.nextLine());
					temp_info.add(search.nextLine());
					temp_info.add(search.nextLine());
					temp_info.add(search.nextLine());
					temp_info.add(search.nextLine());
				}
			}
			System.out.println(temp_info);
		}
		catch (Exception e) {
			
		}
		
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
	
	public static void move_appo_data(String d)  {
		String file = "userdata/" + d;
		try {
			FileWriter n = new FileWriter(file, true);
			n.write("\n");
			n.write("---");
			n.write("\n");
			n.write(temp_info.get(0));
			n.write("\n");
			n.write(temp_info.get(2));
			n.write("\n");
			n.write(temp_info.get(3));
			n.write("\n");
			n.write(temp_info.get(4));
			n.write("\n");
			n.write(temp_info.get(5));
			n.write("\n");
			n.close();
		}
		catch(Exception e) {
			
		}
	
		
	}
}
