package appController;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

import applicationModel.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

/*
 * this class is used by admin to reset passwords
 */
public class UpdatePasswordController extends pageController
{
	@FXML
	private AnchorPane changepassword;

	@FXML
	private TextField txtuserid;

	
	@FXML
	private PasswordField txtnewpass;

	@FXML
	private PasswordField txtconfirmpass;

	@FXML
	private Button btnsubmit;

	@FXML
	private Button btnchangetologin;
/*
 * clear field method
 * 
 */
	
	void clearfiled()
	{
		txtuserid.clear();
		txtnewpass.clear();
		txtconfirmpass.clear();
	}
	
		/*
	 * 
	 * //this method will get the username  and new password. It will 
	 * reset the update
	 */
	@FXML
	void changepasswordAction(ActionEvent event) throws IOException {

		PreparedStatement pst = null;
		String uname = txtuserid.getText().toString();
		String newpass = DigestUtils.shaHex(txtnewpass.getText().toString());
		String newpass1 = DigestUtils.shaHex(txtconfirmpass.getText().toString());
		try {
				if (newpass.equals(newpass1))
				{
					String Regqry = "UPDATE snl_users SET password=? WHERE userid=(?)";
					pst = DatabaseConnection.dbConnect().prepareStatement(Regqry);
					pst.setString(1, newpass);
						pst.setString(2, uname);
					int i = pst.executeUpdate();
					if (i > 0) 
					{

						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Password Updated");
						alert.setHeaderText("Password has been updated!!");
						alert.showAndWait();
						clearfiled();
						
						}
					
					else 
					{

						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Password Updated Failed");
						alert.setHeaderText("User does not exist");
						alert.showAndWait();
						clearfiled();
					
					}

					}
				
				else 
				{

					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Password Do Not Match");
					alert.setHeaderText("Please enter same passwords in the fields!!");
					alert.showAndWait();
					clearfiled();
				}

			}	 
		
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}