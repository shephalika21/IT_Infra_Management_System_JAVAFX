package appController;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import applicationModel.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

/*
 * this class update the userphone
 */
public class UpdateUserModuleController extends pageController
{

	   @FXML
	    private TextField txtuserid;

	    @FXML
	    private TextField txtphno;

	    @FXML
	    private Button btnupdateuserphno;
	    
	   
	    /*
	     * updating user phone number 
	     */
	    
	    @FXML
	    void updateuserphone(ActionEvent event) {
	    	
	    	DatabaseConnection db = new DatabaseConnection();
	    	String userid=txtuserid.getText().toString();
	    	int phone= Integer.parseInt(txtphno.getText().toString());
	    	
	    	
	    	String Regqry="UPDATE snl_users SET phno=(?) WHERE userid=(?)";
	    	try {
				PreparedStatement pst=db.dbConnect().prepareStatement(Regqry);
				pst.setInt(1, phone);
				pst.setString(2, userid);
				
				int i =pst.executeUpdate();
				if(i>0){
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Phone Number Update!!");
					alert.setHeaderText("Phone number Updated to User details!!");
					alert.showAndWait();
				
					
					}
					
				
				else{
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Phone Number Update Failed");
					alert.setHeaderText("Phone Number Not Updated to User details!!");
					alert.showAndWait();
				
					
				}
									
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}    	

	    }

    }

