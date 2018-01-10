package appController;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import applicationModel.DatabaseConnection;
import applicationModel.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

/*
 * this class is used for handling user password. with the help of this class
 * user can change password
 * 
 * */
public class ChangePasswordController extends pageController {

	@FXML
	private AnchorPane changepassword;

	@FXML
	private TextField txtuserid;

	@FXML
	private PasswordField txtcurrentpass;

	@FXML
	private PasswordField txtnewpass;

	@FXML
	private PasswordField txtconfirmpass;

	@FXML
	private Button btnsubmit;

	@FXML
	private Button btnchangetologin;

	// this method will redirect to login page
	@FXML
	void back(ActionEvent event) {
		loginPage();

	}

	/*
	 * 
	 * //this method will get the username  and new password. It will search for the user and 
	 *  reset the password
	 */
	@FXML
	void changepasswordAction(ActionEvent event) throws IOException {

		PreparedStatement pst = null;
		String uname = txtuserid.getText().toString();
		String currentpass = DigestUtils.shaHex(txtcurrentpass.getText().toString());
		String newpass = DigestUtils.shaHex(txtnewpass.getText().toString());
		String newpass1 = DigestUtils.shaHex(txtconfirmpass.getText().toString());
		try {
			pst = DatabaseConnection.dbConnect()
					.prepareStatement("select * from SNL_USERS where userid=? and password=?");

			pst.setString(1, uname);
			pst.setString(2, currentpass);
			ResultSet rs = pst.executeQuery();

			//checking both the password
			if (rs.next()) {
				if (newpass.equals(newpass1)) {
					String Regqry = "UPDATE snl_users SET password=? WHERE userid=(?)";
					pst = DatabaseConnection.dbConnect().prepareStatement(Regqry);
					pst.setString(1, newpass);
					pst.setString(2, uname);
					int i = pst.executeUpdate();
					if (i > 0) {

						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Password Updated");
						alert.setHeaderText("Password has been updated!!");
						alert.showAndWait();
						loginPage();
					}

					else {

						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Password Not Updated");
						alert.setHeaderText("Invalid Entry");
						alert.showAndWait();
					}
				} else {

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Password Do Not Match");
					alert.setHeaderText("Please enter same passwords in the fields!!");
					alert.showAndWait();
				}

			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Wrong Input");
				alert.setHeaderText("Invalid User Name or Password");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
