import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Bookings {

	public static ObservableList<String> doc_times = FXCollections.observableArrayList();
	public static ObservableList<String> appo_times = FXCollections.observableArrayList();
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
			search.close();
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
			search.close();
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
			input.close();
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
	public static void remove_pending() {
		File temp = new File("pendingtemp.txt");
		File inputfile = new File("pending.txt");
		String currentLine;
		int count =0;
		try {
			BufferedReader input = new BufferedReader (new FileReader(inputfile));
			BufferedWriter output = new BufferedWriter(new FileWriter(temp));
			
			while ((currentLine = input.readLine()) != null){
				if (currentLine.equals("---")) {
					count++;
				}
				if (count == Integer.parseInt(current_id)) {
					
				}
				else {
					output.write(currentLine);
					output.write("\n");
				}
			}
			input.close();
			output.close();
			System.gc();
			Path path = Paths.get(inputfile.getAbsolutePath());
			Files.delete(path);
			boolean sucess = temp.renameTo(inputfile);
			System.out.println(sucess);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void get_delete_appo() {
		try {
			String input = Main.current.getUsername();
			Scanner c = new Scanner(new File("userdata/" + input + ".txt"));
			while (c.hasNext()) {
				if (c.nextLine().equals("---")) {
					c.nextLine();
					c.nextLine();
					c.nextLine();
					c.nextLine();
					appo_times.add(c.nextLine());
					System.out.println(appo_times);
				}
			}
			c.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void delete_appo() {
		String input2 = Main.current.getUsername();
		File temp = new File("pendingtemp.txt");
		File inputfile = new File("userdata/" + input2 + ".txt");
		String currentLine;
		int current_appo = Main.del_list.getSelectionModel().getSelectedIndex() +1;
		int count = 0;
		try {
			BufferedReader input = new BufferedReader (new FileReader(inputfile));
			BufferedWriter output = new BufferedWriter (new FileWriter(temp));
			
			while ((currentLine = input.readLine()) != null) {
				if (currentLine.equals("---")) {
					count++;
				}
				if (current_appo == count) {
					
				}
				else {
					output.write(currentLine);
					output.write("\n");
				}
				
			}
			input.close();
			output.close();
			System.gc();
			Path path = Paths.get(inputfile.getAbsolutePath());
			Files.delete(path);
			boolean sucess = temp.renameTo(inputfile);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
