import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class Main extends Application {
	// Instance Variables
	private String current_user;
	private String current_type;
	private User current;
	
	// Methods
	public boolean checkLogin(String username, String password){
		// check file IO for file titled given username
		// check password if it matches
		// If matches return true, else return false
                boolean login = false; // boolean used to check if user exists
                String userid = "";
                String pass = "";
                //assigned all usernames to one arraylist and all passwords to another (valid ones)
                ArrayList<String> un = new ArrayList<String>();
                ArrayList<String> pw = new ArrayList<String>();
                Scanner x;
                try{
                    x = new Scanner(new File("users.txt"));
                   // x.useDelimiter(",");
                    while(x.hasNextLine()){
                    	System.out.println("WHILE LOOP WORKING");
                        String temp = x.next();
                        String temp2[] = temp.split(",");
                        un.add(temp2[0]);
                        pw.add(temp2[1]);
                       
                    }
                    for (int count =0; count<un.size(); count++) {
                    	if(username.equals(un.get(count))) {
                    		if (password.equals(pw.get(count))){
                    			login = true;
                    		}
                    	}
                    }
                    System.out.println(un);
                    System.out.println(pw);
                    x.close();
                }
                catch(Exception e){
                    System.out.println(e);
                }
                if(login == true)
                    return true;
		else
                    return false;
	}
	
	
	public void setUser(String username, String pw) {
		// Pull and place in local variables all text file info
		//currentUser = new User(username, password);
		
		
		try {
			BufferedReader scan = new BufferedReader(new FileReader("userdata/" + username + ".txt"));
			current_user = scan.readLine();
			System.out.println(current_user);
			current_type = scan.readLine();
			System.out.println(current_type);
			
			//make if statement to determine what object to make
			
			if (current_type.equals("Doctor")) {
				Doctor current = new Doctor(current_user, current_type);
				//from here on, anything that is in the doctor class the current user can do.
				//we repeat the same thing for all classes below
			} 
			else if (current_type.equals("Patient")) {
				Patient current = new Patient(current_user, current_type);
				//patient class methods can be used if object is patient.
			}
			else if (current_type.equals("Nurse")) {
				Nurse current = new Nurse(current_user, current_type);
			}
			else if (current_type.equals("Admin")) {
				Admin current = new Admin(current_user, current_type);
			}
			
			scan.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public static void goto_doctor(Group group) {
		// Rectangles
		Rectangle app1_rct = new Rectangle(40.00d, 200.00d, 400.00d, 600.00d);
		app1_rct.setFill(Color.GAINSBORO);
		Rectangle app2_rct = new Rectangle(40.00d, 200.00d, 400.00d, 30.00d);
		app2_rct.setFill(Color.rgb(160, 0, 0, 0.7));
		// Labels
		Label app1_lbl = new Label("Upcoming Appointments");
		app1_lbl.setLayoutX(100);
		app1_lbl.setLayoutY(200);
		app1_lbl.setFont(new Font("Arial", 24));
		
		group.getChildren().addAll(app1_rct, app2_rct, app1_lbl);
	}
	
	public static void goto_nurse(Group group) {
		System.out.println("NURSE LOADED");
		//group.getChildren().addAll();
		}
	
	public static void goto_patient(Group group) {
		System.out.println("PATIENT LOADED");
		//group.getChildren().addAll();
		}
	
	public static void goto_admin(Group group) {
		System.out.println("ADMIN LOADED");
		//group.getChildren().addAll();
		}
	
	
	public static void base_gui(Group group) {
		// Header Red Bar
		Rectangle header_rct = new Rectangle(40.00d,100.00d, 1840.00d, 50.00d);
		header_rct.setFill(Color.rgb(160, 0, 0));
		// User Info
		
		
		// HMS logo
		Label logo_lbl = new Label("HMS");
		logo_lbl.setFont(new Font("Arial", 110));
		logo_lbl.setLayoutX(40);
		logo_lbl.setLayoutY(-10);
		// Logged in user
		Label info1_lbl = new Label("Logged in as");
		info1_lbl.setFont(new Font("Arial", 20));
		info1_lbl.setLayoutX(290);
		info1_lbl.setLayoutY(50);
		Label info2_lbl = new Label("TYPE" + ": " + "First " + "M " + "Last");
		info2_lbl.setFont(new Font("Typewriter", 16));
		info2_lbl.setLayoutX(290);
		info2_lbl.setLayoutY(73);
		
		
		group.getChildren().addAll(header_rct,logo_lbl, info1_lbl, info2_lbl);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// scenes
		Group login = new Group();
		Scene login_scene = new Scene(login, 1920, 1080);
		login_scene.setFill(Color.GHOSTWHITE);
		
		Group home = new Group();
		Scene main_scene = new Scene(home, 1920, 1080);
		
		Group new_appo = new Group();
		Scene new_appo_scene = new Scene(new_appo, 1000, 620);
		
		// 		LOGIN 		//
		// buttons
		Button login_butt = new Button("Login");
		login_butt.setPrefWidth(60);
		login_butt.setLayoutX(930);
		login_butt.setLayoutY(600);
		login_butt.setStyle("-fx-background-color: Cornsilk");
		
		// textfields
		TextField user_txt = new TextField();
		user_txt.setPromptText("username");
		user_txt.setPrefWidth(200);
		user_txt.setLayoutX(860);
		user_txt.setLayoutY(510);
		PasswordField pass_txt = new PasswordField();
		pass_txt.setPromptText("password");
		pass_txt.setPrefWidth(200);
		pass_txt.setLayoutX(860);
		pass_txt.setLayoutY(550);
		
		// label
		Label error_lbl = new Label("username or password incorrect");
		error_lbl.setTextFill(Color.web("#0076a3"));
		error_lbl.setLayoutX(860);
		error_lbl.setLayoutY(575);
		
		Label welcome_lbl = new Label("Welcome!");
		welcome_lbl.setTextFill(Color.BLACK);
		welcome_lbl.setLayoutX(860);
		welcome_lbl.setLayoutY(480);
		Font font = Font.font(16);
		welcome_lbl.setFont(font);
		
		// Add to group
		//System.out.println("adding children");
		login.getChildren().addAll(user_txt, pass_txt, login_butt,welcome_lbl);
		
		
		//		MAIN		//
		
		Label name_user = new Label();
		name_user.setLayoutX(500);
		name_user.setLayoutY(10);
		
		
		
		
		
		
		
		
		// BUTTONS
		Button new_apo = new Button("book appointment");
		new_apo.setPrefWidth(100);
		new_apo.setLayoutX(1700);
		new_apo.setLayoutY(110);
		new_apo.setStyle("-fx-background-color: Cornsilk");

		home.getChildren().addAll(new_apo,name_user);
		
		
		
//		new appointment		//
		
		Label appo_title = new Label("BOOK APPOINTMENT");
		appo_title.setTextFill(Color.web("#0076a3"));
		appo_title.setLayoutX(0);
		appo_title.setLayoutY(0);
		Font fonts = Font.font(25);
		appo_title.setFont(fonts);
		
		Label name = new Label("name");
		name.setLayoutX(0);
		name.setLayoutY(30);
		Font name_font = Font.font(16);
		name.setFont(name_font);
		
		TextField name_input = new TextField("input name");
		name_input.setLayoutX(0);
		name_input.setLayoutY(50);
		
		Label email = new Label("Email");
		email.setLayoutX(0);
		email.setLayoutY(75);
		Font email_font = Font.font(16);
		email.setFont(email_font);
		
		TextField email_input = new TextField("input email");
		email_input.setLayoutX(0);
		email_input.setLayoutY(95);
		
		Label doctor = new Label("Select Doctor");
		doctor.setLayoutX(0);
		doctor.setLayoutY(120);
		doctor.setFont(email_font);
		
		String doc[] = {"Bob Ross", "Chris Lee", "James Bond"};
		
		ChoiceBox doc_choose = new ChoiceBox(FXCollections.observableArrayList(doc));
		doc_choose.setLayoutX(0);
		doc_choose.setLayoutY(140);
		
		
		new_appo.getChildren().addAll(appo_title, name, name_input, email, email_input, doctor, doc_choose);
		
		
		
		
		// BUTTON ACTIONS //
		
		
		// Login
		login_butt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				login.getChildren().remove(error_lbl);
				if (checkLogin(user_txt.getText(), pass_txt.getText())) {
					setUser(user_txt.getText(), pass_txt.getText());
					name_user.setText("Welcome " + current_user);
					Font main_font = Font.font(20);
					name_user.setFont(main_font);
					primaryStage.setScene(main_scene);
					base_gui(home);
					
					
					// trying to figure this out 
					/*
					if (current.getType().equals("Doctor")) {
						goto_doctor(home);
					}
					else if (current.getType().equals("Nurse")) {
						goto_nurse(home);
					}
					else if (current.getType().equals("Patient")) {
						goto_patient(home);
					}
					else if (current.getType().equals("Admin")) {
						goto_admin(home);
					}
					*/
					
				}
				else {
					login.getChildren().add(error_lbl);
				}
			}
		});
		
		// New Appointmnet
		new_apo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Stage secondStage = new Stage();
				secondStage.setScene(new_appo_scene);
				secondStage.setTitle("new appoitment");
				secondStage.show();
			}
			
		});
		
		
		// Canvas
		primaryStage.setTitle("Hospital Management System");
		
		primaryStage.setScene(login_scene);
		primaryStage.setResizable(true);
		primaryStage.show();
	}
}