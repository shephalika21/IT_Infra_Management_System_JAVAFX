package appController;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import applicationModel.Apps;
import applicationModel.DatabaseConnection;
import applicationModel.DatabaseDetails;
import applicationModel.HardwareDetails;
import applicationModel.IncidentDetails;
import applicationModel.Main;
import applicationModel.ServerDetails;
import applicationModel.StorageDetails;
import applicationModel.UserData;
import javafx.scene.control.Label;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Sunil
 *
 *
 *This class controls all manager modules and it is similar to admin module.
 *There is very small change in functionally , managers have only view access for most of module
 *except incidents
 */
public class ManagerPageController extends AdminPageController implements Initializable {

	    @FXML
	    private Tab AdmUserMgmt;

	
	    @FXML
	    private TableColumn<?, ?> tbempid;

	    @FXML
	    private TableColumn<?, ?> tbfname;

	    @FXML
	    private TableColumn<?, ?> tblname;

	    @FXML
	    private TableColumn<?, ?> tbemaild;

	    @FXML
	    private TableColumn<?, ?> tbphone;

	    @FXML
	    private TableColumn<?, ?> tbrole;

	    @FXML
	    private TextField txtemailid;

	    @FXML
	    private TextField txtphone;

	    @FXML
	    private TextField txtrole;

	    @FXML
	    private TextField txtlname;

	    @FXML
	    private TextField txtfname;

	    @FXML
	    private TextField txtEmpID;

	    @FXML
	    private Button btnAdd;

	    @FXML
	    private Button btnDelete;

	    @FXML
	    private TextField txtsearch;

	    @FXML
	    private Button btnsearch;

	    @FXML
	    private Button btnRefresh;
	    
	    @FXML
	    private DatePicker INCcreateddt;

	    @FXML
	    private DatePicker INCResolvedDT;


	    @FXML
	    private Button btncountstorage;

	    @FXML
	    private Label lblcountstorage;
	    

	    @FXML
	    private Button btncounthardwares;

	    @FXML
	    private Label lblcounthardwares;
	    
	    @FXML
	    private Button btncountapp;

	    @FXML
	    private Label lblcountapp;
	    
	    @FXML
	    private Button btncountdb;

	    @FXML
	    private Label lblcountdb;
	    

	    @FXML
	    private Button btncountservers;

	    @FXML
	    private Label lblcountservers;
	    
	    @FXML
	    private Button btncountusers;

	    @FXML
	    private Label lblcountusers;

	    @FXML
	    private Button btnlogout;
	
	    /*
	     * logout function
	     * (non-Javadoc)
	     * @see appController.AdminPageController#logout(javafx.event.ActionEvent)
	     */
	    @FXML
	    void  logout(ActionEvent event)
	    {
	    	AnchorPane loaderlogin;
			try {
				loaderlogin = FXMLLoader.load(getClass().getResource("/viewModel/LoginPage.fxml"));
				Scene scenes = new Scene(loaderlogin);
				Main.primaryStage.setScene(scenes);
				Main.primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    
	    
	    /*
	     * calculate total number of database application
	     */
	    @FXML
	    void countDBAction(ActionEvent event) {
	    	
	    	String sql="select count(*) from databases_details";
	    	try{
	    	PreparedStatement pst=DatabaseConnection.dbConnect().prepareStatement(sql);
	    	ResultSet res = pst.executeQuery();
	    	int count=0;
            while (res.next()){
                
					count = res.getInt(1);
				
				
            }
            Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Database Count!!!");
			alert.setHeaderText("No. of Databases : "+count);
			alert.showAndWait();
			lblcountdb.setText("No. of Databases : "+count);
	    	DatabaseConnection.dbConnect().close();
			pst.close();
			res.close();
	    }
	    	catch(SQLException e){
	    		e.printStackTrace();
	    	}
	    	
	    }
/*
 * calculate total count of hardware
 */
	    @FXML
	    void countHardwareAction(ActionEvent event) {
	    	String sql="select count(*) from hardwares_details";
	    	try{
	    	PreparedStatement pst=DatabaseConnection.dbConnect().prepareStatement(sql);
	    	ResultSet res = pst.executeQuery();
	    	int count=0;
            while (res.next()){
                
					count = res.getInt(1);
				
				
            }
            Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Hardwares Count!!!");
			alert.setHeaderText("No. of Hardwares : "+count);
			alert.showAndWait();
			lblcounthardwares.setText("No. of Hardwares : "+count);
			DatabaseConnection.dbConnect().close();
			pst.close();
			res.close();
	    }
	    	catch(SQLException e){
	    		e.printStackTrace();
	    	}
	    }

/*
 * calculate total count of server
 */
	    @FXML
	    void countServerAction(ActionEvent event) {
	    	DatabaseConnection db = new DatabaseConnection();
	    	String sql="select count(*) from servers_details";
	    	try{
	    	PreparedStatement pst=DatabaseConnection.dbConnect().prepareStatement(sql);
	    	ResultSet res = pst.executeQuery();
	    	int count=0;
            while (res.next()){
                
					count = res.getInt(1);
				
				
            }
            Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Servers Count!!!");
			alert.setHeaderText("No. of Servers : "+count);
			alert.showAndWait();
            
	    	lblcountservers.setText("No. of Servers : "+count);
	    	DatabaseConnection.dbConnect().close();
			pst.close();
			res.close();
	    }
	    	catch(SQLException e){
	    		e.printStackTrace();
	    	}
	    }


/*
 * calculate total count of storage
 */

	    @FXML
	    void countStorageAction(ActionEvent event) {
	    	DatabaseConnection db = new DatabaseConnection();
	    	String sql="select count(*) from Storage_details";
	    	try{
	    	PreparedStatement pst=DatabaseConnection.dbConnect().prepareStatement(sql);
	    	ResultSet res = pst.executeQuery();
	    	int count=0;
            while (res.next()){
                
					count = res.getInt(1);
				
				
            }
            Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Storage Devices Count!!!");
			alert.setHeaderText("No. of Storage Devices : "+count);
			alert.showAndWait();
            
	    	lblcountstorage.setText("No. of Storage Devices : "+count);
	    	DatabaseConnection.dbConnect().close();
			pst.close();
			res.close();
	    }
	    	catch(SQLException e){
	    		e.printStackTrace();
	    	}
	    }


/*
 * calculate total count of user
 */

	    @FXML
	    void countUserAction(ActionEvent event) {
	    	DatabaseConnection db = new DatabaseConnection();
	    	String sql="select count(*) from snl_users";
	    	try{
	    	PreparedStatement pst=DatabaseConnection.dbConnect().prepareStatement(sql);
	    	ResultSet res = pst.executeQuery();
	    	int count=0;
            while (res.next()){
                
					count = res.getInt(1);
				
				
            }
            Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Users Count!!!");
			alert.setHeaderText("No. of Employees : "+count);
			alert.showAndWait();
            
	    	lblcountusers.setText("No. of Employees : "+count);
	    	DatabaseConnection.dbConnect().close();
			pst.close();
			res.close();
	    }
	    	catch(SQLException e){
	    		e.printStackTrace();
	    	}
	    }
	    

/*
 * calculate total count of application
 */

	    @FXML
	    void countAppAction(ActionEvent event) {
	    	
	    	DatabaseConnection db = new DatabaseConnection();
	    	String sql="select count(*) from application_details";
	    	try{
	    	PreparedStatement pst=DatabaseConnection.dbConnect().prepareStatement(sql);
	    	ResultSet res = pst.executeQuery();
	    	int count=0;
            while (res.next()){
                
					count = res.getInt(1);
				
				
            }
            Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Applications Count!!!");
			alert.setHeaderText("No. of Applications : "+count);
			alert.showAndWait();
            
	    	lblcountapp.setText("No. of Applications : "+count);
	    	DatabaseConnection.dbConnect().close();
			pst.close();
			res.close();
	    }
	    	catch(SQLException e){
	    		e.printStackTrace();
	    	}
	    }
	    @FXML
	    private Button btnexportexcel, btnexportemployee, btnexportdb, btnexportapp, btnexporthardware, btnexportstorage, btnexportincident;

	    /*
	     * Create excel report for employee table
	     */
	    @FXML
	    void employeeExportexcel(ActionEvent event){
	    	
	    	
	    	PreparedStatement pst;
			try {
				String query="select * from snl_users";
				pst = DatabaseConnection.dbConnect().prepareStatement(query);
				ResultSet res = pst.executeQuery();
				
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet= wb.createSheet("Employee Details");
				XSSFRow header= sheet.createRow(0);
				header.createCell(0).setCellValue("Employee ID");
				header.createCell(1).setCellValue("First Name");
				header.createCell(2).setCellValue("Last Name");
				header.createCell(3).setCellValue("Email");
				header.createCell(4).setCellValue("Role");
				header.createCell(5).setCellValue("Phone Number");
				
			
				int i=1;
				while(res.next()){
					XSSFRow row = sheet.createRow(i);
					row.createCell(0).setCellValue(res.getString(1));
					row.createCell(1).setCellValue(res.getString(2));
					row.createCell(2).setCellValue(res.getString(3));
					row.createCell(3).setCellValue(res.getString(4));
					row.createCell(4).setCellValue(res.getString(5));
					row.createCell(5).setCellValue(res.getString(6));
					
					i++;
				}
				
				
				try {
					
					FileOutputStream fileOut= new FileOutputStream("EmployeeDetails.xlsx");
					wb.write(fileOut);
					fileOut.close();
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Excel Export!!!");
					alert.setHeaderText("Employee Details exported to Excel!!!");
					alert.showAndWait();
					
					DatabaseConnection.dbConnect().close();
					pst.close();
					res.close();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    }
	    

	    /*
	     * Create excel report for server table
	     */
	    @FXML
	    void serverexportexcel(ActionEvent event){
	    	
	    	
	    	PreparedStatement pst;
			try {
				String query="select * from servers_details";
				pst = DatabaseConnection.dbConnect().prepareStatement(query);
				ResultSet res = pst.executeQuery();
				
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet= wb.createSheet("Server Details");
				XSSFRow header= sheet.createRow(0);
				header.createCell(0).setCellValue("Server Name");
				header.createCell(1).setCellValue("Server IP");
				header.createCell(2).setCellValue("Server Type");
				header.createCell(3).setCellValue("OS Version");
				header.createCell(4).setCellValue("CPU");
				header.createCell(5).setCellValue("Memory");
				header.createCell(6).setCellValue("Criticality");
				header.createCell(7).setCellValue("Status");
				
			
				int i=1;
				while(res.next()){
					XSSFRow row = sheet.createRow(i);
					row.createCell(0).setCellValue(res.getString(1));
					row.createCell(1).setCellValue(res.getString(8));
					row.createCell(2).setCellValue(res.getString(2));
					row.createCell(3).setCellValue(res.getString(5));
					row.createCell(4).setCellValue(res.getInt(3));
					row.createCell(5).setCellValue(res.getInt(4));
					row.createCell(6).setCellValue(res.getString(6));
					row.createCell(7).setCellValue(res.getString(7));
					i++;
				}
				
				
				try {
					
					FileOutputStream fileOut= new FileOutputStream("ServerDetails.xlsx");
					wb.write(fileOut);
					fileOut.close();
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Excel Export!!!");
					alert.setHeaderText("Server Details exported to Excel!!!");
					alert.showAndWait();
					
					DatabaseConnection.dbConnect().close();
					pst.close();
					res.close();
					
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    }
	 

	    /*
	     * Create excel report for database table
	     */
	    @FXML
	    void databaseExportexcel(ActionEvent event){
	    	
	    	
	    	PreparedStatement pst;
			try {
				String query="select * from databases_details";
				pst = DatabaseConnection.dbConnect().prepareStatement(query);
				ResultSet res = pst.executeQuery();
				
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet= wb.createSheet("Database Details");
				XSSFRow header= sheet.createRow(0);
				header.createCell(0).setCellValue("Database Name");
				header.createCell(1).setCellValue("Database Type");
				header.createCell(2).setCellValue("Server Name");
				header.createCell(3).setCellValue("Application Name");
				header.createCell(4).setCellValue("Database Version");
				header.createCell(5).setCellValue("Criticality");
				
				
			
				int i=1;
				while(res.next()){
					XSSFRow row = sheet.createRow(i);
					row.createCell(0).setCellValue(res.getString(1));
					row.createCell(1).setCellValue(res.getString(2));
					row.createCell(2).setCellValue(res.getString(3));
					row.createCell(3).setCellValue(res.getString(4));
					row.createCell(4).setCellValue(res.getString(5));
					row.createCell(5).setCellValue(res.getString(6));
					i++;
				}
				
				
				try {
					
					FileOutputStream fileOut= new FileOutputStream("DatabaseDetails.xlsx");
					wb.write(fileOut);
					fileOut.close();
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Excel Export!!!");
					alert.setHeaderText("Database Details exported to Excel!!!");
					alert.showAndWait();
					
					DatabaseConnection.dbConnect().close();
					pst.close();
					res.close();
					
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    }
	    

	    /*
	     * Create excel report for application table
	     */
	    @FXML
	    void appexportexcel(ActionEvent event){
	    	
	    	
	    	PreparedStatement pst;
			try {
				String query="select * from application_details";
				pst = DatabaseConnection.dbConnect().prepareStatement(query);
				ResultSet res = pst.executeQuery();
				
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet= wb.createSheet("Application Details");
				XSSFRow header= sheet.createRow(0);
				header.createCell(0).setCellValue("Application Name");
				header.createCell(1).setCellValue("Database Name");
				header.createCell(2).setCellValue("Server Name");
				header.createCell(3).setCellValue("Application Type");
				header.createCell(4).setCellValue("Version");
				header.createCell(5).setCellValue("Criticality");
				
			
				int i=1;
				while(res.next()){
					XSSFRow row = sheet.createRow(i);
					row.createCell(0).setCellValue(res.getString(1));
					row.createCell(1).setCellValue(res.getString(2));
					row.createCell(2).setCellValue(res.getString(3));
					row.createCell(3).setCellValue(res.getString(4));
					row.createCell(4).setCellValue(res.getString(5));
					row.createCell(5).setCellValue(res.getString(6));
					i++;
				}
				
				
				try {
					
					FileOutputStream fileOut= new FileOutputStream("ApplicationDetails.xlsx");
					wb.write(fileOut);
					fileOut.close();
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Excel Export!!!");
					alert.setHeaderText("Application Details exported to Excel!!!");
					alert.showAndWait();
					
					DatabaseConnection.dbConnect().close();
					pst.close();
					res.close();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    }
	 

	    /*
	     * Create excel report for hardware table
	     */
	    @FXML
	    void hardwareExportexcel(ActionEvent event){
	    	
	    	
	    	PreparedStatement pst;
			try {
				String query="select * from hardwares_details";
				pst = DatabaseConnection.dbConnect().prepareStatement(query);
				ResultSet res = pst.executeQuery();
				
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet= wb.createSheet("Hardware Details");
				XSSFRow header= sheet.createRow(0);
				header.createCell(0).setCellValue("Hardware Name");
				header.createCell(1).setCellValue("Total CPU");
				header.createCell(2).setCellValue("Left CPU");
				header.createCell(3).setCellValue("Total RAM");
				header.createCell(4).setCellValue("Left RAM");
				header.createCell(5).setCellValue("Location");
				header.createCell(6).setCellValue("Company");
				header.createCell(7).setCellValue("Hardware ID");
				
			
				int i=1;
				while(res.next()){
					XSSFRow row = sheet.createRow(i);
					row.createCell(0).setCellValue(res.getString(1));
					row.createCell(1).setCellValue(res.getInt(2));
					row.createCell(2).setCellValue(res.getInt(3));
					row.createCell(3).setCellValue(res.getInt(4));
					row.createCell(4).setCellValue(res.getInt(5));
					row.createCell(5).setCellValue(res.getString(6));
					row.createCell(6).setCellValue(res.getString(7));
					row.createCell(7).setCellValue(res.getString(8));
					i++;
				}
				
				
				try {
					
					FileOutputStream fileOut= new FileOutputStream("HardwareDetails.xlsx");
					wb.write(fileOut);
					fileOut.close();
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Excel Export!!!");
					alert.setHeaderText("Hardware Details exported to Excel!!!");
					alert.showAndWait();
					
					DatabaseConnection.dbConnect().close();
					pst.close();
					res.close();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    }
	 

	    /*
	     * Create excel report for storage table
	     */
	    
	    @FXML
	    void storageExportexcel(ActionEvent event){
	    	
	    	
	    	PreparedStatement pst;
			try {
				String query="select * from storage_details";
				pst = DatabaseConnection.dbConnect().prepareStatement(query);
				ResultSet res = pst.executeQuery();
				
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet= wb.createSheet("Storage Details");
				XSSFRow header= sheet.createRow(0);
				header.createCell(0).setCellValue("Storage ID");
				header.createCell(1).setCellValue("Storage Type");
				header.createCell(2).setCellValue("Company");
				header.createCell(3).setCellValue("Total Storage");
				header.createCell(4).setCellValue("Used Storage");
				header.createCell(5).setCellValue("Left Storage");
				
			
				int i=1;
				while(res.next()){
					XSSFRow row = sheet.createRow(i);
					row.createCell(0).setCellValue(res.getString(1));
					row.createCell(1).setCellValue(res.getString(2));
					row.createCell(2).setCellValue(res.getString(3));
					row.createCell(3).setCellValue(res.getInt(4));
					row.createCell(4).setCellValue(res.getInt(5));
					row.createCell(5).setCellValue(res.getInt(4) - res.getInt(5));
					i++;
				}
				
				
				try {
					
					FileOutputStream fileOut= new FileOutputStream("StorageDetails.xlsx");
					wb.write(fileOut);
					fileOut.close();
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Excel Export!!!");
					alert.setHeaderText("Storage Details exported to Excel!!!");
					alert.showAndWait();
					
					DatabaseConnection.dbConnect().close();
					pst.close();
					res.close();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    }
	 

	    /*
	     * Create excel report for incident table
	     */
	    
	    @FXML
	    void incidentexportexcel(ActionEvent event){
	    	
	    	
	    	PreparedStatement pst;
			try {
				String query="select * from incidents_details";
				pst = DatabaseConnection.dbConnect().prepareStatement(query);
				ResultSet res = pst.executeQuery();
				
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet= wb.createSheet("Incidents Details");
				XSSFRow header= sheet.createRow(0);
				header.createCell(0).setCellValue("Incident ID");
				header.createCell(1).setCellValue("Severity");
				header.createCell(2).setCellValue("Root Cause");
				header.createCell(3).setCellValue("Raised by Employee");
				header.createCell(4).setCellValue("Impacted CI");
				header.createCell(5).setCellValue("Team");
				header.createCell(6).setCellValue("Description");
				header.createCell(7).setCellValue("Created Date");
				header.createCell(8).setCellValue("Resolved Date");
				header.createCell(9).setCellValue("Resolution Time");
			
				int i=1;
				while(res.next()){
					XSSFRow row = sheet.createRow(i);
					row.createCell(0).setCellValue(res.getInt(1));
					row.createCell(1).setCellValue(res.getInt(2));
					row.createCell(2).setCellValue(res.getString(3));
					row.createCell(3).setCellValue(res.getString(4));
					row.createCell(4).setCellValue(res.getString(5));
					row.createCell(5).setCellValue(res.getString(6));
					row.createCell(6).setCellValue(res.getString(7));
					row.createCell(7).setCellValue(res.getDate(8).toString());
					row.createCell(8).setCellValue(res.getDate(9).toString());
					row.createCell(9).setCellValue(res.getInt(10));
					i++;
				}
				
				
				try {
					
					FileOutputStream fileOut= new FileOutputStream("IncidentDetails.xlsx");
					wb.write(fileOut);
					fileOut.close();
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Excel Export!!!");
					alert.setHeaderText("Incidents Details exported to Excel!!!");
					alert.showAndWait();
					
					DatabaseConnection.dbConnect().close();
					pst.close();
					res.close();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    }
	 
	  
	    

	    
	    //table view of user data table
	    @FXML
	     TableView<UserData> table;
		
	    //table view of server details table
	    @FXML
		 TableView<ServerDetails> ServerTable;
		    
	    @FXML
		TableView<Apps> AppTable;
	    
	    @FXML
	    TableView<DatabaseDetails> DatabaseTable;
	    
	    @FXML
	    TableView<HardwareDetails> HWtable;
	    
	    @FXML
	    TableView<IncidentDetails> INCtable;
	    
	    			
		    @Override
			public void initialize(URL arg0, ResourceBundle arg1) {
			
		    	
		    	/*
		    	 * refreshAll(null);
		    	 * userTableLoad();
		    	dbTableLoad();
		    	serverTableLoad();
		    	hardwareTableload();
		    	storageTableload();
		    	applicationTableLoad();
		    	incidentTableload();
		    	*/
		    	
		    	//assert tableview !=null;
		    	// TODO Auto-generated method stub
				tbempid.setCellValueFactory(new PropertyValueFactory<>("userid"));
		    	tbfname.setCellValueFactory(new PropertyValueFactory<>("Firstname"));
				tblname.setCellValueFactory(new PropertyValueFactory<>("Lastname"));
				tbemaild.setCellValueFactory(new PropertyValueFactory<>("Email"));
				tbphone.setCellValueFactory(new PropertyValueFactory<>("phno"));
				tbrole.setCellValueFactory(new PropertyValueFactory<>("Role"));

				
		    //servertable view
				
				tblservername.setCellValueFactory(new PropertyValueFactory<>("ServerName"));
		    	tblserverip.setCellValueFactory(new PropertyValueFactory<>("ServerIP"));
				tblservertype.setCellValueFactory(new PropertyValueFactory<>("ServerType"));
				tblservercpu.setCellValueFactory(new PropertyValueFactory<>("CPU"));
				tblservermemory.setCellValueFactory(new PropertyValueFactory<>("Memory"));
				tblservercriticality.setCellValueFactory(new PropertyValueFactory<>("Criticality"));
				tblserverstatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
				
				//application view
				
				tbAppName.setCellValueFactory(new PropertyValueFactory<>("appName"));
		    	tbAppCrtically.setCellValueFactory(new PropertyValueFactory<>("appCritcality"));
				tbAppVer.setCellValueFactory(new PropertyValueFactory<>("appVersion"));
				tbDBHostedOn.setCellValueFactory(new PropertyValueFactory<>("appDBhosted"));
				tbDbRelatedSer.setCellValueFactory(new PropertyValueFactory<>("appRelatedServer"));
				tbAppType.setCellValueFactory(new PropertyValueFactory<>("apptype"));
				
				
				//database view
				

				tbDBName.setCellValueFactory(new PropertyValueFactory<>("dbName"));
		    	tbDBType.setCellValueFactory(new PropertyValueFactory<>("dbType"));
				tbDBServerName.setCellValueFactory(new PropertyValueFactory<>("dbservername"));
				tbDBApps.setCellValueFactory(new PropertyValueFactory<>("dbapplication"));
				tbDBVer.setCellValueFactory(new PropertyValueFactory<>("dbVersion"));
				tbdbcriticality.setCellValueFactory(new PropertyValueFactory<>("dbCriticality"));
				
				//Storage view
				
				tbStorageID.setCellValueFactory(new PropertyValueFactory<>("StorageID"));
		    	tbStorageType.setCellValueFactory(new PropertyValueFactory<>("StorageType"));
				tbStorageCompany.setCellValueFactory(new PropertyValueFactory<>("Company"));
				tbTotalStorage.setCellValueFactory(new PropertyValueFactory<>("TotalStorage"));
				tbUsedStorage.setCellValueFactory(new PropertyValueFactory<>("UsedStorage"));
				tbLeftStorage.setCellValueFactory(new PropertyValueFactory<>("LeftStorage"));
				
				//hardware table view
				
				tbHWID.setCellValueFactory(new PropertyValueFactory<>("hardwareid"));
				tbHWCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
				tbTotalCPU.setCellValueFactory(new PropertyValueFactory<>("totalCPU"));
				tbCPULeft.setCellValueFactory(new PropertyValueFactory<>("leftCPU"));
				tbRAM.setCellValueFactory(new PropertyValueFactory<>("totalRAM"));
				tbLeftRAM.setCellValueFactory(new PropertyValueFactory<>("leftRAM"));
				
				
				//Incident View
				tbINCID.setCellValueFactory(new PropertyValueFactory<>("IncidentID"));
				tbINCSev.setCellValueFactory(new PropertyValueFactory<>("Severity"));
				tbINCimpactedCI.setCellValueFactory(new PropertyValueFactory<>("ImpactedCI"));
				tbINCResolvedtime.setCellValueFactory(new PropertyValueFactory<>("ResolvedDays"));
				tbINCTeam.setCellValueFactory(new PropertyValueFactory<>("Team"));
				tbINCRootCause.setCellValueFactory(new PropertyValueFactory<>("RootCause"));
		    }
		    
		    /*
		     * search module
		     * (non-Javadoc)
		     * @see appController.AdminPageController#searchAction(javafx.event.ActionEvent)
		     */

		    @FXML
		    void searchAction(ActionEvent event) {
		    	UserSearch();
		    	}

		    @FXML
		    void refreshTableAction(ActionEvent event)
		    {
		    	
		    	ObservableList<UserData> data=FXCollections.observableArrayList();
		    	PreparedStatement prdstmt=null;
		    	ResultSet rsset=null;
		    
			    {
			
			    	String qry="Select * from SNL_USERS";
			    	
			    	try {
						prdstmt=DatabaseConnection.dbConnect().prepareStatement(qry);
						rsset=prdstmt.executeQuery();
						
						while(rsset.next())
						{
							data.add(new UserData(rsset.getString(1),rsset.getString(2),rsset.getString(3),rsset.getString(4),rsset.getString(7),rsset.getString(6)));
										table.setItems(data);
						}
						prdstmt.close();
						rsset.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.err.println(e);
						e.printStackTrace();
					}
			    
			    }
			
		    	
		    }

	    /************************************************************/
	    
	    //Dashboard module starts
	    @FXML
	    private Tab AdmDashboard;


	    @FXML
	    private BarChart<String, Double> UserbarChart;

	    @FXML
	    private BarChart<String, Double> ServerStatusBarChart;
	    
	    @FXML
	    private Button btnUserBar;
	    
	  //Pie chart Data
	    private ObservableList<PieChart.Data> data;
	    
	    @FXML
	    private PieChart dbPiechart;
	    
	    @FXML
	    private BarChart<String,Integer> Incbarchart;
	    
	    
	    @FXML
	    private PieChart hdWareLocationPieChart;
	    
	    @FXML
	    private BarChart<String,Double> storageTypeBarChart;

	    /*
	     * below creating bar and pie chart and loading in refreshAll method
	     * (non-Javadoc)
	     * @see appController.AdminPageController#refreshAll(javafx.event.ActionEvent)
	     */
	    
		   @FXML
		   void refreshAll(ActionEvent event)
		   
		   {
		   UserbarChart.getData().clear();
		   ServerStatusBarChart.getData().clear();
		   dbPiechart.getData().clear();
		   Incbarchart.getData().clear();
		  hdWareLocationPieChart.getData().clear();
		  storageTypeBarChart.getData().clear();  
		  ServerBarChart1.getData().clear();
		  ServerBarChart2.getData().clear();
		  ApplicationBarChart1.getData().clear();
		  ApplicationBarChart2.getData().clear();
		  DatabaseBarChart1.getData().clear();
		  DatabaseBarChart2.getData().clear();
		  HardwareBarChart1.getData().clear();
		  HardwareBarChart2.getData().clear();
		  StorageBarChart1.getData().clear();
		  StorageBarChart2.getData().clear();
		  IncidentBarChart2.getData().clear();
		  IncidentBarChart1.getData().clear();
		  LoadUserBarChart();
		    LoadServerStatusBarChart();
		    dBPieChartData();
		    LoadINCbarchart();
		    hdwarelocationPieChartData();
		     storageTypeBarChart();
		     LoadServerBarChart1();
		     LoadServerBarChart2();
		     LoadApplicationBarChart1();
		     LoadApplicationBarChart2();
		     LoadDatabaseBarChart1();
		     LoadDatabaseBarChart2();
		     LoadHardwareBarChart1();
		     LoadHardwareBarChart2();
		     LoadStorageBarChart1();
		     LoadStorageBarChart2();
		     LoadIncidentBarChart2();
		     LoadIncidentBarChart1();
		    
		   }
		   
		   @FXML
		    private BarChart<String, Number> IncidentBarChart1;

		    @FXML
		    private BarChart<String, Number> IncidentBarChart2;
		    
		    
		    void LoadIncidentBarChart1() {

				String barquery = "select severity,count(*) from incidents_details group by severity";
				XYChart.Series<String, Number> series = new XYChart.Series();
				try {
					ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(barquery);

					while (rs.next()) {
						series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));

					}

					IncidentBarChart1.getData().add(series);
					IncidentBarChart1.setTitle("Incidents By Severity");
					IncidentBarChart1.getBarGap();

					DatabaseConnection.dbDisconnect();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		    
		    void LoadIncidentBarChart2() {

				String barquery = "select userid,count(*) from incidents_details group by userid having count(*)>2";
				XYChart.Series<String, Number> series = new XYChart.Series();
				try {
					ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(barquery);

					while (rs.next()) {
						series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));

					}

					IncidentBarChart2.getData().add(series);
					IncidentBarChart2.setTitle("No. of Incidents Raised By");
					IncidentBarChart2.getBarGap();
					DatabaseConnection.dbDisconnect();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		   
		   
		   
		    @FXML
		    private BarChart<String, Number> StorageBarChart1;

		    @FXML
		    private BarChart<String, Number> StorageBarChart2;
		    
		    
		    void LoadStorageBarChart1() {

				String barquery = "select company,count(*) from storage_details group by company";
				XYChart.Series<String, Number> series = new XYChart.Series();
				try {
					ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(barquery);

					while (rs.next()) {
						series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));

					}

					StorageBarChart1.getData().add(series);
					StorageBarChart1.setTitle("Storage By Company");
					StorageBarChart1.getBarGap();

					DatabaseConnection.dbDisconnect();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		    
		    void LoadStorageBarChart2() {

				String barquery = "select storagetype,count(*) from storage_details group by storagetype";
				XYChart.Series<String, Number> series = new XYChart.Series();
				try {
					ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(barquery);

					while (rs.next()) {
						series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));

					}

					StorageBarChart2.getData().add(series);
					StorageBarChart2.setTitle("Storage Type");
					StorageBarChart2.getBarGap();
					DatabaseConnection.dbDisconnect();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		   
		   
		    @FXML
		    private BarChart<String, Number> HardwareBarChart1;

		    @FXML
		    private BarChart<String, Number> HardwareBarChart2;
		    
		    
		    void LoadHardwareBarChart1() {

				String barquery = "select company,count(*) from hardwares_details group by company";
				XYChart.Series<String, Number> series = new XYChart.Series();
				try {
					ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(barquery);

					while (rs.next()) {
						series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));

					}

					HardwareBarChart1.getData().add(series);
					HardwareBarChart1.setTitle("Hardware By Company");
					HardwareBarChart1.getBarGap();

					DatabaseConnection.dbDisconnect();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		    
		    void LoadHardwareBarChart2() {

				String barquery = "select location,count(*) from hardwares_details group by location";
				XYChart.Series<String, Number> series = new XYChart.Series();
				try {
					ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(barquery);

					while (rs.next()) {
						series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));

					}

					HardwareBarChart2.getData().add(series);
					HardwareBarChart2.setTitle("Hardwares By Location");
					HardwareBarChart2.getBarGap();
					DatabaseConnection.dbDisconnect();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		   
		    @FXML
		    private BarChart<String, Number> DatabaseBarChart1;

		    @FXML
		    private BarChart<String, Number> DatabaseBarChart2;
		    
		    
		    void LoadDatabaseBarChart1() {

				String barquery = "select databasetype,count(*) from databases_details group by databasetype";
				XYChart.Series<String, Number> series = new XYChart.Series();
				try {
					ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(barquery);

					while (rs.next()) {
						series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));

					}

					DatabaseBarChart1.getData().add(series);
					DatabaseBarChart1.setTitle("Database Type");
					DatabaseBarChart1.getBarGap();

					DatabaseConnection.dbDisconnect();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		    
		    void LoadDatabaseBarChart2() {

				String barquery = "select criticality,count(*) from databases_details group by criticality";
				XYChart.Series<String, Number> series = new XYChart.Series();
				try {
					ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(barquery);

					while (rs.next()) {
						series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));

					}

					DatabaseBarChart2.getData().add(series);
					DatabaseBarChart2.setTitle("Database Criticality");
					DatabaseBarChart2.getBarGap();
					DatabaseConnection.dbDisconnect();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		    @FXML
		    private BarChart<String, Number> ApplicationBarChart1;

		    @FXML
		    private BarChart<String, Number> ApplicationBarChart2;
		    
		    void LoadApplicationBarChart1() {

				String barquery = "select applicationtype,count(*) from application_details group by applicationtype";
				XYChart.Series<String, Number> series = new XYChart.Series();
				try {
					ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(barquery);

					while (rs.next()) {
						series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));

					}

					ApplicationBarChart1.getData().add(series);
					ApplicationBarChart1.setTitle("Application Type");
					ApplicationBarChart1.getBarGap();

					DatabaseConnection.dbDisconnect();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		    
		    void LoadApplicationBarChart2() {

				String barquery = "select criticality,count(*) from application_details group by criticality";
				XYChart.Series<String, Number> series = new XYChart.Series();
				try {
					ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(barquery);

					while (rs.next()) {
						series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));

					}

					ApplicationBarChart2.getData().add(series);
					ApplicationBarChart2.setTitle("Application Criticality");
					ApplicationBarChart2.getBarGap();
					DatabaseConnection.dbDisconnect();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		    @FXML
		    private BarChart<String, Number> ServerBarChart1;

		    @FXML
		    private BarChart<String, Number> ServerBarChart2;
		    
		    void LoadServerBarChart1() {

				String barquery = "select servertype,count(*) from servers_details group by servertype";
				XYChart.Series<String, Number> series = new XYChart.Series();
				try {
					ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(barquery);

					while (rs.next()) {
						series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));

					}

					ServerBarChart1.getData().add(series);
					ServerBarChart1.setTitle("Server Type");
					ServerBarChart1.getBarGap();

					DatabaseConnection.dbDisconnect();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		    
		    void LoadServerBarChart2() {

				String barquery = "select criticality,count(*) from servers_details group by criticality";
				XYChart.Series<String, Number> series = new XYChart.Series();
				try {
					ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(barquery);

					while (rs.next()) {
						series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));

					}

					ServerBarChart2.getData().add(series);
					ServerBarChart2.setTitle("Server Criticality");
					ServerBarChart2.getBarGap();
					DatabaseConnection.dbDisconnect();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

	//*****************************************************************************************************************    
	    //Server Module starts
	    @FXML
	    private Tab AdmServers;

	   
	    @FXML
	    private TableColumn<?, ?> tblservername;

	    @FXML
	    private TableColumn<?, ?> tblserverip;

	    @FXML
	    private TableColumn<?, ?> tblservertype;

	    @FXML
	    private TableColumn<?, ?> tblservercpu;

	    @FXML
	    private TableColumn<?, ?> tblservermemory;

	    @FXML
	    private TableColumn<?, ?> tblservercriticality;

	    @FXML
	    private TableColumn<?, ?> tblserverstatus;

	    @FXML
	    private TextField txtserverstatus;

	    @FXML
	    private TextField txtservermemory;

	    @FXML
	    private TextField txtserverversion;

	    @FXML
	    private TextField txtservertype;

	    @FXML
	    private TextField txtserverip;

	    @FXML
	    private TextField txtservername;

	    @FXML
	    private TextField txtservercriticality;

	    @FXML
	    private TextField txtservercpu;

	    @FXML
	    private Button btnaddserver;

	    @FXML
	    private Button btndeleteserver;

	    @FXML
	    private TextField txtsearchServer;

	    @FXML
	    private Button btnsearchserver;

	    @FXML
	    private Button btnRefreshserver;


	    @FXML
	    void refreshServerTableAction(ActionEvent event)
	    {
	    	serverTableLoad();
	    	}
	    
	    @FXML
	    void searchServerAction(ActionEvent event) {
	    
	    	serverSearch();
	    }
	
	    /**************************************************************************************/
	    
	    @FXML
	    private Tab AdmDatabase;

	    //@FXML
	   // private TableView<?> Db;

	    @FXML
	    private TableColumn<?, ?> tbDBName;

	    @FXML
	    private TableColumn<?, ?> tbDBType;

	    @FXML
	    private TableColumn<?, ?> tbDBServerName;

	    @FXML
	    private TableColumn<?, ?> tbDBApps;


	    @FXML
	    private TableColumn<?, ?> tbDBVer;
	    
	    @FXML
	    private TableColumn<?, ?> tbdbcriticality;
	    
	    

	    @FXML
	    private TextField txtDBhostedOn;

	    @FXML
	    private TextField txtDBApp;

	    @FXML
	    private TextField txtDBVer;

	    @FXML
	    private TextField txtDBtype;

	    @FXML
	    private TextField txtDatabaseName;

	    @FXML
	    private TextField txtDBCritcality;

	    @FXML
	    private Button btnDBAdd;

	    @FXML
	    private Button btnDBDelete;

	    @FXML
	    private TextField txtDBSearch;

	    @FXML
	    private Button btnDBsearch;

	    @FXML
	    private Button btnDBRefresh;
	    


		@FXML
		void refreshDBTableAction(ActionEvent event) {
			
					dbTableLoad();
	    							}

		

		@FXML
		void searchDBAction(ActionEvent event) {
		databaseSearch();	
		}
	
/**************************************************************************************************/
				

	    @FXML
	    private Tab AdmApps;

	    
	    @FXML
	    private TableColumn<?, ?> tbAppName;

	    @FXML
	    private TableColumn<?, ?> tbAppCrtically;

	    @FXML
	    private TableColumn<?, ?> tbAppVer;

	    @FXML
	    private TableColumn<?, ?> tbDBHostedOn;

	    @FXML
	    private TableColumn<?, ?> tbDbRelatedSer;

	    @FXML
	    private TableColumn<?, ?> tbAppType;

	    @FXML
	    private TextField txtAppDBhosted;

	    @FXML
	    private TextField txtAppReltServer;

	    @FXML
	    private TextField txtAppType;

	    @FXML
	    private TextField txtAppVer;

	    @FXML
	    private TextField txtAppCriticality;

	    @FXML
	    private TextField txtAppName;

	    @FXML
	    private Button btnAppAdd;

	    @FXML
	    private Button btnAppDelete;

	    @FXML
	    private TextField txtAppsearch;

	    @FXML
	    private Button btnAppsearch;

	    @FXML
	    private Button btnAppRefresh;

	    @FXML
	    void refreshApplicationAction(ActionEvent event)
	    {
	    	applicationTableLoad();	
	    }
	    
	    @FXML
	    void searchApplicationAction(ActionEvent event) {
	    	applicationSearch();
	    	}
	
/**********************************************************************************************************/

	    //Hardware Module
	    
	    @FXML
	    private Tab AdmHardware;

	    
	    @FXML
	    private TableColumn<?, ?> tbHWID;

	    @FXML
	    private TableColumn<?, ?> tbTotalCPU;

	    @FXML
	    private TableColumn<?, ?> tbCPULeft;

	    @FXML
	    private TableColumn<?, ?> tbRAM;

	    @FXML
	    private TableColumn<?, ?> tbLeftRAM;

	    @FXML
	    private TableColumn<?, ?> tbHWCompany;

	    @FXML
	    private TextField txtCPULeft;

	    @FXML
	    private TextField txtTotalRam;

	    @FXML
	    private TextField txtRAMLeft;

	    @FXML
	    private TextField txtTotalCPU;

	    @FXML
	    private TextField txtHWName;

	    @FXML
	    private TextField txtHWID;

	    @FXML
	    private TextField txtHWCmpny;

	    @FXML
	    private TextField txtHWLocation;

	    @FXML
	    private Button btnHWAdd;

	    @FXML
	    private Button btnHWDelete;

	    @FXML
	    private TextField txtHWsearch;

	    @FXML
	    private Button btnHWsearch;

	    @FXML
	    private Button btnHWRefresh;
	    

	@FXML
		void refreshHardwareAction(ActionEvent event) {

	hardwareTableload();
	}

	
	@FXML
		void searchHardwareAction(ActionEvent event) {
hardwareSearch();
	}
	    
	    
	/**************************************************************************************************/
	    
	    
//Storage module
	    @FXML
	    private Tab AdmStorage;

	    @FXML
	    private TableView<StorageDetails> StorageTable;

	    @FXML
	    private TableColumn<?, ?> tbStorageID;

	    @FXML
	    private TableColumn<?, ?> tbStorageType;

	    @FXML
	    private TableColumn<?, ?> tbStorageCompany;

	    @FXML
	    private TableColumn<?, ?> tbTotalStorage;

	    @FXML
	    private TableColumn<?, ?> tbLeftStorage;

	    @FXML
	    private TableColumn<?, ?> tbUsedStorage;

	    @FXML
	    private TextField txtTotalStorage;

	    @FXML
	    private TextField txtLeftStorage;

	    @FXML
	    private TextField txtUsedStorage;

	    @FXML
	    private TextField txtStorageCompany;

	    @FXML
	    private TextField txtStorageType;

	    @FXML
	    private TextField txtStorageID;

	    @FXML
	    private Button btnStorageAdd;

	    @FXML
	    private Button btnStorageDelete;

	    @FXML
	    private TextField txtStoragesearch;

	    @FXML
	    private Button btnStoragesearch;

	    @FXML
	    private Button btnStorageRefresh;

	    
	    @FXML
	    void refreshStorageAction(ActionEvent event)
	    {
	    	
	    	storageTableload();
	    	
	    }
	    
	    @FXML
		void searchStorageAction(ActionEvent event) {
storageSearch();
	    }
	 //Storage module end
	    
/**************************************************************************************************/
	    
	    //Incidents Module
	    @FXML
	    private Tab AdmIncidents;

	   
	    @FXML
	    private TableColumn<?, ?> tbINCID;

	    @FXML
	    private TableColumn<?, ?> tbINCSev;

	    @FXML
	    private TableColumn<?, ?> tbINCimpactedCI;

	    @FXML
	    private TableColumn<?, ?> tbINCResolvedtime;

	    @FXML
	    private TableColumn<?, ?> tbINCTeam;

	    @FXML
	    private TableColumn<?, ?> tbINCRootCause;

	    @FXML
	    private TextField txtINCRootCause;

	    @FXML
	    private TextField txtINCCreatedDt;

	    @FXML
	    private TextField txtINCResolvedDT;

	    @FXML
	    private TextField txtINCimpactedCI;

	    @FXML
	    private TextField txtINCSev;

	    @FXML
	    private TextField txtINCID;

	    @FXML
	    private TextField txtINCTeam;

	    @FXML
	    private TextField txtINCtitle;

	    @FXML
	    private TextField txtINCUserID;

	    @FXML
	    private Button btnINCAdd;

	    @FXML
	    private Button btnINCDelete;

	    @FXML
	    private TextField txtINCsearch;

	    @FXML
	    private Button btnINCsearch;

	    @FXML
	    private Button btnINCRefresh;
	    
	    @FXML
	    void addIncidentsAction(ActionEvent event) throws Exception {
	    	
	    	DatabaseConnection db = new DatabaseConnection();
	    	int incid=Integer.parseInt(txtINCID.getText().toString());
	    	String inctitle=txtINCtitle.getText().toString();
	    	String inuserid=txtINCUserID.getText().toString();
	    	int insev=Integer.parseInt(txtINCSev.getText().toString());
	    	String incimpacted =txtINCimpactedCI.getText().toString();
	    	String incroot =txtINCRootCause.getText().toString();
	    	//LocalDate createddate=INCcreateddt.getValue();
	    	//LocalDate resolveddate =INCResolvedDT.getValue();
	    	String incteam=txtINCTeam.getText().toString();
	    	
	    	String Regqry="Insert into incidents_details values(?,?,?,?,?,?,?,?,?,?)";
	    	try {
	    		
	    		//Date d1 =  new SimpleDateFormat("yyyy-MM-dd").parse(createddate.toString());
		    	//Date d2 =  new SimpleDateFormat("yyyy-MM-dd").parse(resolveddate.toString());
		    	
		  
				PreparedStatement pst=db.dbConnect().prepareStatement(Regqry);
				pst.setInt(1, incid);
				pst.setInt(2, insev);
				pst.setString(3, incroot);
				pst.setString(4, inuserid);
				pst.setString(5, incimpacted);
				pst.setString(6, incteam);
				pst.setString(7, inctitle);
				pst.setDate(8,  java.sql.Date.valueOf(INCcreateddt.getValue()));
				pst.setDate(9, java.sql.Date.valueOf(INCResolvedDT.getValue()));
				pst.setInt(10, (int)ChronoUnit.DAYS.between(LocalDate.parse(INCcreateddt.getValue().toString()),LocalDate.parse(INCResolvedDT.getValue().toString())));
				
				int i =pst.executeUpdate();
				if(i>0){

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Database Updated");
					alert.setHeaderText(incid+" added !!!");
					alert.showAndWait();
					
					}	
				
				else{
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Database not updated");
					alert.setHeaderText(incid+" not added. Please enter correct details!!");
					alert.showAndWait();
					
				}

				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
	
	    }

	    
	    @FXML
	    void deleteIncidentsAction(ActionEvent event) {
	    	
	    	DatabaseConnection db = new DatabaseConnection();
	    	String incid=txtINCID.getText().toString();
	    	
	    	
	    	String Regqry="Delete from  incidents_details  where incidentid=(?)";
	    	try {
				PreparedStatement pst=db.dbConnect().prepareStatement(Regqry);
				pst.setString(1, incid);
								
				int i =pst.executeUpdate();
				if(i>0){
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information From Database");
					alert.setHeaderText(incid+" deleted");
					alert.showAndWait();
					
					}	
				
				else{
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Info From Database");
					alert.setHeaderText(incid+" not deleted. Please enter valid incident ID!!");
					alert.showAndWait();
				}				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}    	

	    }

	    

	    @FXML
	    void refreshIncidentsAction(ActionEvent event)
	    {
	    	incidentTableload();	
	    }
	    
	    @FXML
	    void searchIncidentsAction(ActionEvent event) {
	    	
	    incidentSearch();
	    }
	    }
