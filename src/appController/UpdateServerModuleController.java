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
 * this class deals with updating the server details like CPU,RAM and OS Version
 */
	public class UpdateServerModuleController extends pageController
	
	{

	    @FXML
	    private Tab tabCPU;

	    @FXML
	    private TextField txtcpuservername;

	    @FXML
	    
	    private TextField txtservercpu;

	    @FXML
	    private Button btnupdatecpu;

	    @FXML
	    private Tab tabRAM;

	    @FXML
	    private TextField txtramservername;

	    @FXML
	    private TextField txtupdateRAM;

	    @FXML
	    private Button btnupdateRAM;

	    @FXML
	    private Tab tabOSversion;

	    @FXML
	    private AnchorPane tabOS;

	    @FXML
	    private TextField txtosservername;

	    @FXML
	    private TextField txtupdateos;

	    @FXML
	    private Button btnupdateOS;
	    
	  
	  
/*
 * updating CPU details based on serverID
 */
	    @FXML
	    void updateCPU(ActionEvent event) {
	    	
	    	DatabaseConnection db = new DatabaseConnection();
	    	String serverid=txtcpuservername.getText().toString();
	    	int cpuid = Integer.parseInt(txtservercpu.getText().toString());
	    	
	    	
	    	String Regqry="UPDATE servers_details SET CPU=(?) WHERE servername=(?)";
	    	try {
				PreparedStatement pst=db.dbConnect().prepareStatement(Regqry);
				pst.setInt(1, cpuid);
				pst.setString(2, serverid);
				
				int i =pst.executeUpdate();
				if(i>0){
					
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Server Update");
					alert.setHeaderText("CPU Updated to Server details sucessfully!!");
					alert.showAndWait();
					}
					
				
				else{
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Server Update Failed");
					alert.setHeaderText("CPU Not Updated to Server details!!");
					alert.showAndWait();
				}
									
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}    	


	    }

	    /*
	     * updating RAM details based on server id
	     */
	    @FXML
	    void updateRAM(ActionEvent event) {
	    	DatabaseConnection db = new DatabaseConnection();
	    	String serverid=txtramservername.getText().toString();
	    	int ramid = Integer.parseInt(txtupdateRAM.getText().toString());
	    	
	    	
	    	String Regqry="UPDATE servers_details SET memory=(?) WHERE servername=(?)";
	    	try {
				PreparedStatement pst=db.dbConnect().prepareStatement(Regqry);
				pst.setInt(1, ramid);
				pst.setString(2, serverid);
				
				int i =pst.executeUpdate();
				if(i>0){
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Server Update");
					alert.setHeaderText("Memory Updated to Server details sucessfully!!");
					alert.showAndWait();
					}
					
				
				else{
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Server Update Failed");
					alert.setHeaderText("Memory Not Updated to Server details!!");
					alert.showAndWait();
				}
									
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}    	

	    }
	
	   /*
	    * updating os version based on server id 
	    */
	    @FXML
	    void updateOSVersion(ActionEvent event) {
	    	DatabaseConnection db = new DatabaseConnection();
	    	String serverid=txtosservername.getText().toString();
	    	String osid = txtupdateos.getText().toString();
	    	
	    	
	    	String Regqry="UPDATE servers_details SET osversion=(?) WHERE servername=(?)";
	    	try {
				PreparedStatement pst=db.dbConnect().prepareStatement(Regqry);
				pst.setString(1, osid);
				pst.setString(2, serverid);
				
				int i =pst.executeUpdate();
				if(i>0){
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Server Update");
					alert.setHeaderText("OS Version Updated to Server details sucessfully!!");
					alert.showAndWait();
					}
					
				
				else{
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Server Update Failed");
					alert.setHeaderText("OS Version Not Updated to Server details!!");
					alert.showAndWait();
					
				}
									
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}    	

	    }


}
