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
	private static boolean loggedIn = false;
	//private User currentUser;
	
	// Methods
	
	public boolean checkLogin(String username, String password){
		// check file IO for file titled given username
		// check password if it matches
		// If matches return true, else return false
                boolean login = false; // boolean used to check if user exists
                String userid = "";
                String pass = "";
                Scanner x;
                try{
                    x = new Scanner(new File("users.txt"));
                    x.useDelimiter("[,\n");
                    while(x.hasNext() && login == false){
                        userid = x.next();
                        pass = x.next();
                        if(userid.trim().equals(username.trim()) && pass.trim().equals(password.trim())){
                            login = true;
                        }
                    }
                    x.close();
                }
                catch(Exception e){
                    System.out.println("Login error");
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
		login.getChildren().addAll(user_txt, pass_txt, login_butt);
		
		
		login_butt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (checkLogin(user_txt.getText(), pass_txt.getText())) {
					//setUser(user_txt.getText(), pass_txt.getText());
					loggedIn = true;
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
		
		if (loggedIn) {primaryStage.setScene(main_scene); }
		else {primaryStage.setScene(login_scene);}
		
		primaryStage.setResizable(true);
		primaryStage.show();
	}
}