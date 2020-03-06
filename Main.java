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
	
	public boolean checkLogin(String username, String password){
		// check file IO for file titled given username
		// check password if it matches
		// If matches return true, else return false
                boolean login = false; // boolean used to check if user exists
                String userid = "";
                String pass = "";
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
		TextField pass_txt = new TextField("password");
		pass_txt.setPrefWidth(200);
		pass_txt.setLayoutX(540);
		pass_txt.setLayoutY(350);
		
		// label
		Label error_lbl = new Label("username or password incorrect");
		error_lbl.setTextFill(Color.web("#0076a3"));
		error_lbl.setLayoutX(555);
		error_lbl.setLayoutY(375);
		
		
		
		// Add to group
		//System.out.println("adding children");
		login.getChildren().addAll(user_txt, pass_txt, login_butt);
		
		
		login_butt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				login.getChildren().remove(error_lbl);
				if (checkLogin(user_txt.getText(), pass_txt.getText())) {
					//setUser(user_txt.getText(), pass_txt.getText());
					primaryStage.setScene(main_scene);
				}
				else {
					login.getChildren().add(error_lbl);
				}
			}
		});
		
		
		
		
		
		
		//		MAIN		//
		
		
		
		// Canvas
		primaryStage.setTitle("Hospital Management System");
		
		primaryStage.setScene(login_scene);
		primaryStage.setResizable(true);
		primaryStage.show();
	}
}