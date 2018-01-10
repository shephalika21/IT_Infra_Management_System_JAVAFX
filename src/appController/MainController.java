package appController;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.codec.digest.DigestUtils;

import applicationModel.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController extends pageController {

//*****Below are the button ,field and variable name used in login fxml file.
	@FXML
	private Button login;

	@FXML
	private Button signup;

	@FXML
	private Button btnchangepass;

	@FXML
	private TextField Username;

	@FXML
	private PasswordField password;

//this is login function and runs when login button clicked
	public void login(ActionEvent event) throws Exception

	{	

/*************Reading the username and password from the user and checking in database and also 
 checking for input for all given field, if not then alert message is being displayed with  message****************************************/		
		PreparedStatement pst = null;
		String uname = Username.getText().toString();
		String pass = DigestUtils.shaHex(password.getText().toString());
		String test = DigestUtils.shaHex("test");
		String sql = "select password from snl_users where userid=?";
		pst = DatabaseConnection.dbConnect().prepareStatement(sql);
		pst.setString(1, uname);
		ResultSet res = pst.executeQuery();
		String passwordtble = "";
		while (res.next()) {
			passwordtble = res.getString(1);

		}
		if (uname.trim().length() > 0 && pass.trim().length() > 0) {

		}
		if (uname == "" || pass == "") {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Input");
			alert.setHeaderText("Please input all the fields");
			alert.showAndWait();
		}
	
/*if user is logining with default password then it must changed before logging. Test password is default password for new 
user*/	
		else if (pass.equals(test) && pass.equals(passwordtble)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Change Password");
			alert.setHeaderText("You are logging in for the first time. Please change your password!!");
			alert.showAndWait();
			changePasswordPage();
						
		} 
		
//checking user role in the table
		else {
			sql = "select role from snl_users where userid=?";
			pst = DatabaseConnection.dbConnect().prepareStatement(sql);
			pst.setString(1, uname);
			res = pst.executeQuery();
			String role = "";
			while (res.next()) {
				role = res.getString(1);

			}

//checking password
			pst = DatabaseConnection.dbConnect()
					.prepareStatement("select * from SNL_USERS where userid=? and password=?");

			pst.setString(1, uname);
			pst.setString(2, pass);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				if (role.equals("admin") || role.equals("Admin")) {
					//loading adminPage for admin
					adminPage();
				
				} else if (role.equals("manager") || role.equals("Manager")) {
					
				//loading Manager Module for manager users.
				managerPage();
				}

			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Wrong Input");
				alert.setHeaderText("Invalid User Name or Password");
				alert.showAndWait();
				Username.clear();
				password.clear();
								
			}

		}
		DatabaseConnection.dbConnect().close();

	}

	//calling UserRegistration Page
	@FXML
	public void UserRegistration(ActionEvent event) throws Exception {

		
		registrationPage();
	}

	//Calling Password change Module
	@FXML
	public void changepassword(ActionEvent event) {
		
		changePasswordPage();
	}
}
