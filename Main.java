import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;

import javafx.application.Application;
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
	//private User currentUser;
	
	// Methods
	private String current_user;
	private String current_type;
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
			BufferedReader scan = new BufferedReader(new FileReader(username + ".txt"));
			current_user = scan.readLine();
			System.out.println(current_user);
			current_type = scan.readLine();
			System.out.println(current_type);
			
			//make if statement to determine what object to make
			
			if (current_type.equals("Doctor")) {
				Doctor current = new Doctor(current_user);
				//from here on, anything that is in the doctor class the current user can do.
				//we repeat the same thing for all classes below
			}
			else if (current_type.equals("Patient")) {
				Patient current = new Patient(current_user);
				//patient class methods can be used if object is patient.
			}
			
			scan.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// scenes
		Group login = new Group();
		Scene login_scene = new Scene(login, 1280, 720);
		
		Group main = new Group();
		Scene main_scene = new Scene(main, 1280, 720);
		
		Group new_appo = new Group();
		Scene new_appo_scene = new Scene(new_appo, 1000, 620);
		
		// 		LOGIN 		//
		// buttons
		Button login_butt = new Button("Login");
		login_butt.setPrefWidth(60);
		login_butt.setLayoutX(610);
		login_butt.setLayoutY(400);
		login_butt.setStyle("-fx-background-color: Cornsilk");
		
		// textfields
		TextField user_txt = new TextField("username");
		user_txt.setPrefWidth(200);
		user_txt.setLayoutX(540);
		user_txt.setLayoutY(320);
		PasswordField pass_txt = new PasswordField();
		pass_txt.setPrefWidth(200);
		pass_txt.setLayoutX(540);
		pass_txt.setLayoutY(350);
		
		// label
		Label error_lbl = new Label("username or password incorrect");
		error_lbl.setTextFill(Color.web("#0076a3"));
		error_lbl.setLayoutX(555);
		error_lbl.setLayoutY(375);
		
		Label welcome_lbl = new Label("Welcome!");
		welcome_lbl.setTextFill(Color.web("#0076a3"));
		welcome_lbl.setLayoutX(555);
		welcome_lbl.setLayoutY(275);
		
		
		// Add to group
		//System.out.println("adding children");
		login.getChildren().addAll(user_txt, pass_txt, login_butt,welcome_lbl);
		
		
		//		MAIN		//
		
		Button new_apo = new Button("book appointment");
		new_apo.setPrefWidth(100);
		new_apo.setLayoutX(610);
		new_apo.setLayoutY(400);
		new_apo.setStyle("-fx-background-color: Cornsilk");
		
		Label name_user = new Label();
		name_user.setLayoutX(0);
		name_user.setLayoutY(10);
		
		
		main.getChildren().addAll(new_apo,name_user);
		
//		new appointment		//
		
		Label appo_title = new Label("BOOK APPOINTMENT");
		appo_title.setTextFill(Color.web("#0076a3"));
		appo_title.setLayoutX(0);
		appo_title.setLayoutY(0);
		Font font = Font.font(25);
		appo_title.setFont(font);
		
		Label name = new Label("name");
		name.setLayoutX(0);
		name.setLayoutY(30);
		Font name_font = Font.font(16);
		name.setFont(name_font);
		
		TextField name_input = new TextField("input name");
		name_input.setLayoutX(0);
		name_input.setLayoutY(50);
		
		new_appo.getChildren().addAll(appo_title, name, name_input);
		
		
		login_butt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				login.getChildren().remove(error_lbl);
				if (checkLogin(user_txt.getText(), pass_txt.getText())) {
					setUser(user_txt.getText(), pass_txt.getText());
					name_user.setText("Welcome " + current_user);
					primaryStage.setScene(main_scene);
				}
				else {
					login.getChildren().add(error_lbl);
				}
			}
		});
		
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