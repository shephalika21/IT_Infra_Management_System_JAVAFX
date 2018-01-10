package appController;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.commons.codec.digest.DigestUtils;

import applicationModel.DatabaseConnection;
import applicationModel.Main;

import javax.jws.soap.SOAPBinding.Style;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * this class deals with user registration module
 * 
 */

public class RegistrationModelController extends pageController {
	@FXML
	private TextField empid;

	@FXML
	private TextField fname;

	@FXML
	private TextField lname;

	@FXML
	private TextField emailid;

	@FXML
	private PasswordField password;

	@FXML
	private PasswordField passwordRepeat;

	@FXML
	private TextField Role;

	@FXML
	private TextField phone;

	@FXML
	private Button Reset;

	@FXML
	private Button RegistrationSubmit;

	@FXML
	private ImageView Regtologin;

	@FXML
	private Button regtohome;

	@FXML
	private Label lblrepassword;

	@FXML
	private Label lblphone;

	@FXML
	private Label lblempid;

	@FXML
	private Label lblrole;

	@FXML
	private Label lbllastname;

	@FXML
	private Label lblpassword;

	@FXML
	private Label lblfirstname;

	@FXML
	private Label lblemailid;

	/*
	 * below method will clear all field on registration page
	 */
	public void Reset(ActionEvent Event) {
		password.clear();
		passwordRepeat.clear();
		empid.clear();
		fname.clear();
		lname.clear();
		emailid.clear();
		phone.clear();
		Role.clear();
	}

	/*
	 * this will get the user input from the from and store in database
	 */
	public void RegistrationSubmit(ActionEvent Event) {
		DatabaseConnection db = new DatabaseConnection();
		String userid = empid.getText().toString();
		String firstname = fname.getText().toString();
		String lastname = lname.getText().toString();
		String useremailid = emailid.getText().toString();
		String pswd = DigestUtils.shaHex(password.getText().toString());
		String userrole = Role.getText().toString();
		String phnno = phone.getText().toString();
		String pass1 = DigestUtils.shaHex(passwordRepeat.getText().toString());
		String regex = "\\d+";
		String regexemail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		/****** Check if user id is left blank or empty *******/
		if (userid == null || userid.trim().equals("")) {
			lblempid.setText("User ID cannot be empty");
			lblfirstname.setText("");
			lbllastname.setText("");
			lblpassword.setText("");
			lblemailid.setText("");
			lblrepassword.setText("");
			lblphone.setText("");
			lblrole.setText("");
			return;
		}

		/****** Check if first name is left blank or empty *******/
		if (firstname == null || firstname.trim().equals("")) {
			lblfirstname.setText("First name cannot be empty");
			lblempid.setText("");
			lbllastname.setText("");
			lblpassword.setText("");
			lblemailid.setText("");
			lblrepassword.setText("");
			lblphone.setText("");
			lblrole.setText("");
			return;
		}

		/****** Check if last name is left blank or empty *******/
		if (lastname == null || lastname.trim().equals("")) {
			lbllastname.setText("Last Name cannot be empty");
			lblempid.setText("");
			lblfirstname.setText("");
			lblpassword.setText("");
			lblemailid.setText("");
			lblrepassword.setText("");
			lblphone.setText("");
			lblrole.setText("");
			return;
		}

		/****** Check if email is left blank or empty *******/
		if (useremailid == null || useremailid.trim().equals("")) {
			lblempid.setText("");
			lblfirstname.setText("");
			lbllastname.setText("");
			lblpassword.setText("");
			lblemailid.setText("Please enter email id");
			lblrepassword.setText("");
			lblphone.setText("");
			lblrole.setText("");
			return;
		}

		if (!useremailid.matches(regexemail)) {
			lblempid.setText("");
			lblfirstname.setText("");
			lbllastname.setText("");
			lblpassword.setText("");
			lblemailid.setText("Please enter valid email");
			lblrepassword.setText("");
			lblphone.setText("");
			lblrole.setText("");
			return;
		}

		/****** Check if confirm password is left blank or empty *******/
		if (pswd == null || pswd.trim().equals("")) {
			lblempid.setText("");
			lblfirstname.setText("");
			lbllastname.setText("");
			lblpassword.setText("Please enter password");
			lblemailid.setText("");
			lblrepassword.setText("");
			lblphone.setText("");
			lblrole.setText("");
			return;
		}

		/******
		 * Check if create password and confirm password are same or not
		 *******/
		if (!pass1.equals(pswd)) {
			lblempid.setText("");
			lblfirstname.setText("");
			lbllastname.setText("");
			lblpassword.setText("");
			lblemailid.setText("");
			lblrepassword.setText("Passwords do not match!");
			lblphone.setText("");
			lblrole.setText("");
			return;
		}

		/****** Check if role is left blank or empty *******/
		if (userrole == null || userrole.trim().equals("")) {
			lblempid.setText("");
			lblfirstname.setText("");
			lbllastname.setText("");
			lblpassword.setText("");
			lblemailid.setText("");
			lblrepassword.setText("");
			lblphone.setText("");
			lblrole.setText("Please enter role");
			return;
		}

		/****** Check if phone number is left blank or empty *******/
		if (phone.getText().toString() == null || phone.getText().toString().trim().equals("")) {
			lblempid.setText("");
			lblfirstname.setText("");
			lbllastname.setText("");
			lblpassword.setText("");
			lblemailid.setText("");
			lblrepassword.setText("");
			lblphone.setText("Please enter phone number");
			lblrole.setText("");
			return;
		}

		/****** Check if phone number is not a number *******/
		if (!phone.getText().toString().matches(regex)) {
			lblempid.setText("");
			lblfirstname.setText("");
			lbllastname.setText("");
			lblpassword.setText("");
			lblemailid.setText("");
			lblrepassword.setText("");
			lblphone.setText("Please enter numbers");
			lblrole.setText("");
			return;
		}

		/****** Check if create pass word is left blank or empty *******/
		if (pass1 == null || pass1.trim().equals("")) {
			lblempid.setText("");
			lblfirstname.setText("");
			lbllastname.setText("");
			lblpassword.setText("");
			lblemailid.setText("");
			lblrepassword.setText("Please re-enter password");
			lblphone.setText("");
			lblrole.setText("");
			return;
		} else {

			/*****
			 * after all validation ,insert the data into user table in database
			 ***/
			String Regqry = "Insert into snl_users values(?,?,?,?,?,?,?)";
			try {
				PreparedStatement pst = db.dbConnect().prepareStatement(Regqry);
				pst.setString(1, userid);
				pst.setString(2, firstname);
				pst.setString(3, lastname);
				pst.setString(4, useremailid);
				pst.setString(5, pswd);
				pst.setString(6, userrole);
				pst.setString(7, phnno);

				int i = pst.executeUpdate();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("New User Created");
				alert.setHeaderText("New User has been created. You can login now!!");
				alert.showAndWait();
				lblempid.setText("");
				lblfirstname.setText("");
				lbllastname.setText("");
				lblpassword.setText("");
				lblemailid.setText("");
				lblrepassword.setText("");
				lblphone.setText("");
				lblrole.setText("");
				pst.close();

				/****
				 * if data is inserted successfully , redirect user to login
				 * page otherwise back to same page
				 ******/
				if (i > 0) {
					loginPage();

				} else {
					registrationPage();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

			try {
				db.dbConnect().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/***********this is redirect user to login page whenever they want to go***/
	public void gotoLoginPage(ActionEvent Event) {
		loginPage();
	}

}
/********Registration Module ends**************************/