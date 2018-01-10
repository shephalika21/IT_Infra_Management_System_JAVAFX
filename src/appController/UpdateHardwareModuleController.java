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
 * this class is used for updating the hardware details
 * 
 */
public class UpdateHardwareModuleController  extends pageController
{

    @FXML
    private Tab tabCPU;

    @FXML
    private TextField txtcpuhardwarername;

    @FXML
    private TextField txthardwarecpu;

    @FXML
    private Button btnupdatecpu;

    @FXML
    private Tab tabRAM;

    @FXML
    private TextField txtramhardwarename;

    @FXML
    private TextField txtupdateRAM;

    @FXML
    private Button btnupdateRAM;

    @FXML
    private Tab tabhardwarelocation;

    @FXML
    private AnchorPane tablocation;

    @FXML
    private TextField txthname;

    @FXML
    private TextField txtupdatelocation;

    @FXML
    private Button btnupdatelocation;
    
   
    
/*
 * Below updating the CPU details based on hardware Id
 */
    @FXML
    void updatehardwareCPU(ActionEvent event) {
    	DatabaseConnection db = new DatabaseConnection();
    	String hid=txtcpuhardwarername.getText().toString();
    	int cpuid = Integer.parseInt(txthardwarecpu.getText().toString());
    	
    	
    	String Regqry="UPDATE hardwares_details SET totalcpu=(?) WHERE hardwareid=(?)";
    	try {
			PreparedStatement pst=db.dbConnect().prepareStatement(Regqry);
			pst.setInt(1, cpuid);
			pst.setString(2, hid);
			
			int i =pst.executeUpdate();
			if(i>0){
				
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Hardware Update");
				alert.setHeaderText("CPU Updated to Hardware details sucessfully!!");
				alert.showAndWait();
				
				}
				
			
			else{
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Hardware Update failed");
				alert.setHeaderText("CPU not Updated to Hardware details!!");
				alert.showAndWait();
				
			}
								
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}    	

    }

   /*
    * Updating RAM details 
    */
    
    @FXML
    void updatehardwareRAM(ActionEvent event) {
    	
    	DatabaseConnection db = new DatabaseConnection();
    	String hid=txtramhardwarename.getText().toString();
    	int ramid = Integer.parseInt(txtupdateRAM.getText().toString());
    	
    	
    	String Regqry="UPDATE hardwares_details SET totalram=(?) WHERE hardwareid=(?)";
    	try {
			PreparedStatement pst=db.dbConnect().prepareStatement(Regqry);
			pst.setInt(1, ramid);
			pst.setString(2, hid);
			
			int i =pst.executeUpdate();
			if(i>0){
				
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Hardware Update");
				alert.setHeaderText("RAM Updated to Hardware details sucessfully!!");
				alert.showAndWait();
				
				
				}
				
			
			else{
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Hardware Update Failed");
				alert.setHeaderText("RAM not updated to database!!");
				alert.showAndWait();
				
				
			}
								
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}    	

    }

    /*
     * updating hardware location
     */
    @FXML
    void updatehardwareLocation(ActionEvent event) {
    	DatabaseConnection db = new DatabaseConnection();
    	String hid=txthname.getText().toString();
    	String locationid = txtupdatelocation.getText().toString();
    	
    	
    	String Regqry="UPDATE hardwares_details SET location=(?) WHERE hardwareid=(?)";
    	try {
			PreparedStatement pst=db.dbConnect().prepareStatement(Regqry);
			pst.setString(1, locationid);
			pst.setString(2, hid);
			
			int i =pst.executeUpdate();
			if(i>0){
				
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Hardware Update");
				alert.setHeaderText("Location Updated in Database sucessfully!!");
				alert.showAndWait();
				
				
				}
				
			
			else{
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Hardware Update Failed");
				alert.setHeaderText("Location Updated in Database sucessfully!!");
				alert.showAndWait();
				
				
			}
								
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}    	

    }

    }

