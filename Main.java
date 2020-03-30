import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.*;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.BufferedReader;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
	public static User current;
	private Stage secondStage;
	private Stage newStage;
	private static TextField name_input;
	private static TextField email_input;
	private static TextField name1_input;
	private static TextField address_input;
	private static TextField number_input;
	private static TextField email_1_input;
	private static TextField dob_input;
	private static ComboBox gender_combobox;
	private static TextArea additional_info;
	private static TextArea reason_txt;
	private static ChoiceBox doc_choose;
	private static ListView<String> list;

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
				current = new Doctor(current_user, current_type, username);

				//from here on, anything that is in the doctor class the current user can do.
				//we repeat the same thing for all classes below
			}
			else if (current_type.equals("Patient")) {
				current = new Patient(current_user, current_type, username);
				//patient class methods can be used if object is patient.
			}
			else if (current_type.equals("Nurse")) {
				current = new Nurse(current_user, current_type, username);
			}
			else if (current_type.equals("Admin")) {
				current = new Admin(current_user, current_type, username);
			}

			scan.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void store_appo_info(String file) {
		try {
			FileWriter writer = new FileWriter(file, true);
			writer.write(current.getUsername());
			writer.write(name_input.getText());
			writer.write("\n");
			writer.write(email_input.getText());
			writer.write("\n");
			writer.write((String)doc_choose.getValue());
			writer.write("\n");
			writer.write(reason_txt.getText());
			writer.write("\n");
			String temp = list.getSelectionModel().getSelectedItems().stream().map(Object::toString).collect(Collectors.joining());
			writer.write(temp);
			writer.write("\n");
			writer.close();


		}
		catch (Exception e) {

		}
	}

	public static String get_doc_txt() {
		String temp = (String)doc_choose.getValue();
		System.out.println(temp);
		if(temp.equals("Bob Ross")) {
			return "bobross.txt";
		}
		else if(temp.equals("Jacky Chan")) {
			return "jackychan.txt";
		}
		else {
			return " ";
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

		// Rectangle - patients list
		Rectangle app1_rct_pa = new Rectangle(1000.00d, 200.00d, 400.00d, 600.00d);
		app1_rct_pa.setFill(Color.GAINSBORO);
		Rectangle app2_rct_pa = new Rectangle(1000.00d, 200.00d, 400.00d, 30.00d);
		app2_rct_pa.setFill(Color.rgb(160, 0, 0, 0.7));
		// Labels - patients list
		Label app1_lbl_pa = new Label("My Patients");
		app1_lbl_pa.setLayoutX(1170);
		app1_lbl_pa.setLayoutY(200);
		app1_lbl_pa.setFont(new Font("Arial", 24));

		group.getChildren().addAll(app1_rct, app2_rct, app1_lbl, app1_rct_pa, app2_rct_pa, app1_lbl_pa);
	}

	public static void goto_nurse(Group group) {
		System.out.println("NURSE LOADED");
		//group.getChildren().addAll();
		}

	public static void goto_patient(Group group) {
		System.out.println("PATIENT LOADED");
		//group.getChildren().addAll(info);
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

	//Launch program
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

		Group times = new Group();
		Scene times_scene = new Scene(times, 1000, 500);

		Group pinfo = new Group();
		Scene pinfo_scene = new Scene(pinfo, 1000, 620);

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

		Button var_apo = new Button("verify appointment");
		var_apo.setPrefWidth(100);
		var_apo.setLayoutX(1700);
		var_apo.setLayoutY(300);
		var_apo.setStyle("-fx-background-color: Cornsilk");

		// personal information button
		Button info = new Button("personal information");
		info.setPrefWidth(200);
		info.setLayoutX(1000);
		info.setLayoutY(110);
		info.setStyle("-fx-background-color: Cornsilk");

// add personal information //
	// name
	Label name_1 = new Label("Name");
	name_1.setLayoutX(20);
	name_1.setLayoutY(30);
	Font name_1_font = Font.font(16);
	name_1.setFont(name_1_font);

	name1_input = new TextField("Name");
	name1_input.setPrefWidth(250);
	name1_input.setLayoutX(20);
	name1_input.setLayoutY(50);

	// address
	Label address = new Label("Address");
	address.setLayoutX(20);
	address.setLayoutY(80);
	Font address_font = Font.font(16);
	address.setFont(address_font);

	address_input = new TextField("Address");
	address_input.setPrefWidth(250);
	address_input.setLayoutX(20);
	address_input.setLayoutY(100);

	// contact no.
	Label number = new Label("Contact No.");
	number.setLayoutX(20);
	number.setLayoutY(130);
	Font number_font = Font.font(16);
	number.setFont(number_font);

	number_input = new TextField("Contact No.");
	number_input.setPrefWidth(250);
	number_input.setLayoutX(20);
	number_input.setLayoutY(150);

	// email
	Label email_1 = new Label("Email");
	email_1.setLayoutX(20);
	email_1.setLayoutY(180);
	Font email_1_font = Font.font(16);
	email_1.setFont(email_1_font);

	email_1_input = new TextField("Email");
	email_1_input.setPrefWidth(250);
	email_1_input.setLayoutX(20);
	email_1_input.setLayoutY(200);

	// DOB
	Label dob = new Label("Date of Birth");
	dob.setLayoutX(20);
	dob.setLayoutY(230);
	Font dob_font = Font.font(16);
	dob.setFont(dob_font);

	dob_input = new TextField("MM/DD/YYYY");
	dob_input.setPrefWidth(250);
	dob_input.setLayoutX(20);
	dob_input.setLayoutY(250);

	// gender
	Label gender = new Label("Gender");
	gender.setLayoutX(20);
	gender.setLayoutY(280);
	Font gender_font = Font.font(16);
	gender.setFont(gender_font);

	String gender_c[] = {"Male", "Female", "Other", "Prefer not to say"};
	ComboBox gender_combobox = new ComboBox(FXCollections.observableArrayList(gender_c));
	gender_combobox.setLayoutX(20);
	gender_combobox.setLayoutY(300);

	// additional information
	Label ainfo = new Label("Additional information");
	ainfo.setLayoutX(20);
	ainfo.setLayoutY(330);
	Font ainfo_font = Font.font(16);
	ainfo.setFont(ainfo_font);

	additional_info = new TextArea();
	additional_info.setLayoutX(20);
	additional_info.setLayoutY(350);
	additional_info.setMinSize(200, 200);


	pinfo.getChildren().addAll(name_1, name1_input, address, address_input, number, number_input, email_1, email_1_input, dob, dob_input, gender, gender_combobox, ainfo, additional_info);


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

		name_input = new TextField("input name");
		name_input.setLayoutX(0);
		name_input.setLayoutY(50);

		Label email = new Label("Email");
		email.setLayoutX(0);
		email.setLayoutY(75);
		Font email_font = Font.font(16);
		email.setFont(email_font);

		email_input = new TextField("input email");
		email_input.setLayoutX(0);
		email_input.setLayoutY(95);

		Label doctor = new Label("Select Doctor");
		doctor.setLayoutX(0);
		doctor.setLayoutY(120);
		doctor.setFont(email_font);

		//hard code the names, it should be fine for now
		String doc[] = {"Bob Ross", "Jacky Chan"};

		doc_choose = new ChoiceBox(FXCollections.observableArrayList(doc));
		doc_choose.setLayoutX(0);
		doc_choose.setLayoutY(140);

		Label reason = new Label("Reason:");
		reason.setLayoutX(0);
		reason.setLayoutY(160);
		reason.setFont(email_font);

		reason_txt = new TextArea();
		reason_txt.setLayoutX(0);
		reason_txt.setLayoutY(180);
		reason.setMaxSize(100, 50);

		Button view_times = new Button("view available times");
		view_times.setLayoutX(400);
		view_times.setLayoutY(500);

		new_appo.getChildren().addAll(appo_title, name, name_input, email, email_input, doctor, doc_choose, reason, reason_txt, view_times);

		//view times

		Label title = new Label("Your doctor has these times avaliable:");
		title.setLayoutX(0);
		title.setLayoutY(0);
		title.setFont(fonts);

		list = new ListView<String>(Bookings.doc_times);
		list.setLayoutX(0);
		list.setLayoutY(40);
		list.setPrefSize(400, 300);

		Button confirm_appo = new Button("Confirm");
		confirm_appo.setLayoutX(500);
		confirm_appo.setLayoutY(350);

		times.getChildren().addAll(title,list, confirm_appo);

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
					home.getChildren().addAll(new_apo,name_user,var_apo, info);

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
				secondStage = new Stage();
				secondStage.setScene(new_appo_scene);
				secondStage.setTitle("new appoitment");
				secondStage.show();
			}

		});

		view_times.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				Bookings.get_doc_times(get_doc_txt());
				secondStage.setScene(times_scene);
				secondStage.setTitle("view times");
				secondStage.show();
			}
		});

		confirm_appo.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				store_appo_info("pending.txt");
				secondStage.close();

				Alert alert = new Alert(AlertType.INFORMATION, "Thank you, your appointment is now pending.", ButtonType.OK);
				alert.showAndWait();
			}
		});

		info.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				newStage = new Stage();
				newStage.setScene(pinfo_scene);
				newStage.setTitle("Personal Information");
				newStage.show();
			}
		});

		// Canvas
		primaryStage.setTitle("Hospital Management System");

		primaryStage.setScene(login_scene);
		primaryStage.setResizable(true);
		primaryStage.show();
	}
}
