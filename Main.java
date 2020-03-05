import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

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
	private static boolean logged_in = false;
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// 		LOGIN 		//
		// scenes
		Group login = new Group();
		Scene login_scene = new Scene(login, 1280, 720);
		
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
		
		// labels
		
		// set position
		login.getChildren().addAll(user_txt, pass_txt, login_butt);
		
		//		MAIN		//
		// scenes
		Group main = new Group();
		Scene main_scene = new Scene(main, 1280, 720);
		
		
		
		// Canvas
		primaryStage.setTitle("Hospital Management System");
		
		if (logged_in) {primaryStage.setScene(main_scene); }
		else {primaryStage.setScene(login_scene);}
		
		primaryStage.setResizable(true);
		primaryStage.show();
	}
}