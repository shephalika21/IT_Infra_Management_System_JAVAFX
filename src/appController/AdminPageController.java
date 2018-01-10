package appController;

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

import org.apache.commons.codec.digest.DigestUtils;

import applicationModel.Apps;
import applicationModel.DatabaseConnection;
import applicationModel.DatabaseDetails;
import applicationModel.HardwareDetails;
import applicationModel.IncidentDetails;
import applicationModel.Main;
import applicationModel.ServerDetails;
import applicationModel.StorageDetails;
import applicationModel.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
 * this class control all admin page and it's function. 
 * There are multiple module under admin page
 * 
 */
public class AdminPageController extends pageController implements Initializable {

	/*
	 * Below are fields, tableveiw, button and text boxes used in admin GUI
	 * 
	 */
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
	private Button btncountapp;

	@FXML
	private Label lblcountapp;

	
	@FXML
	private Button btnlogout;

//redirect user to login page when logout is clicked.
	
	@FXML
	void logout(ActionEvent event) {
		
		loginPage();
			
	}

	
/*
 * below method is calculating total number of application present in database	
 */
	@FXML
	void countAppAction(ActionEvent event) {

		DatabaseConnection db = new DatabaseConnection();
		String sql = "select count(*) from application_details";
		try {
			PreparedStatement pst = DatabaseConnection.dbConnect().prepareStatement(sql);
			ResultSet res = pst.executeQuery(sql);
			int count = 0;
			while (res.next()) {

				count = res.getInt(1);

			}
			pst = DatabaseConnection.dbConnect()
					.prepareStatement("select count(*) from application_details where applicationtype=%SOA");
			lblcountapp.setText("No. of Applications :" + count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@FXML
	TableView<UserData> table;

	// table view of server details table
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

/*
 * below method runs automatically when class is called and sets parameters for different table
 * view 
 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		LoadUserBarChart();
		LoadServerStatusBarChart();
		dBPieChartData();
		LoadINCbarchart();
		hdwarelocationPieChartData();
		storageTypeBarChart();
		/*
		* userTableLoad(); serverTableLoad(); hardwareTableload();
		 * incidentTableload(); applicationTableLoad(); storageTableload();
		 * dbTableLoad();
		 */
		
		// tableview
		
		/*
		 * Setting tableview of user table
		 */

		tbempid.setCellValueFactory(new PropertyValueFactory<>("userid"));
		tbfname.setCellValueFactory(new PropertyValueFactory<>("Firstname"));
		tblname.setCellValueFactory(new PropertyValueFactory<>("Lastname"));
		tbemaild.setCellValueFactory(new PropertyValueFactory<>("Email"));
		tbphone.setCellValueFactory(new PropertyValueFactory<>("phno"));
		tbrole.setCellValueFactory(new PropertyValueFactory<>("Role"));

		// servertable view

		tblservername.setCellValueFactory(new PropertyValueFactory<>("ServerName"));
		tblserverip.setCellValueFactory(new PropertyValueFactory<>("ServerIP"));
		tblservertype.setCellValueFactory(new PropertyValueFactory<>("ServerType"));
		tblservercpu.setCellValueFactory(new PropertyValueFactory<>("CPU"));
		tblservermemory.setCellValueFactory(new PropertyValueFactory<>("Memory"));
		tblservercriticality.setCellValueFactory(new PropertyValueFactory<>("Criticality"));
		tblserverstatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

		// application view

		tbAppName.setCellValueFactory(new PropertyValueFactory<>("appName"));
		tbAppCrtically.setCellValueFactory(new PropertyValueFactory<>("appCritcality"));
		tbAppVer.setCellValueFactory(new PropertyValueFactory<>("appVersion"));
		tbDBHostedOn.setCellValueFactory(new PropertyValueFactory<>("appDBhosted"));
		tbDbRelatedSer.setCellValueFactory(new PropertyValueFactory<>("appRelatedServer"));
		tbAppType.setCellValueFactory(new PropertyValueFactory<>("apptype"));

		// database view

		tbDBName.setCellValueFactory(new PropertyValueFactory<>("dbName"));
		tbDBType.setCellValueFactory(new PropertyValueFactory<>("dbType"));
		tbDBServerName.setCellValueFactory(new PropertyValueFactory<>("dbservername"));
		tbDBApps.setCellValueFactory(new PropertyValueFactory<>("dbapplication"));
		tbDBVer.setCellValueFactory(new PropertyValueFactory<>("dbVersion"));
		tbdbcriticality.setCellValueFactory(new PropertyValueFactory<>("dbCriticality"));

		// Storage view

		tbStorageID.setCellValueFactory(new PropertyValueFactory<>("StorageID"));
		tbStorageType.setCellValueFactory(new PropertyValueFactory<>("StorageType"));
		tbStorageCompany.setCellValueFactory(new PropertyValueFactory<>("Company"));
		tbTotalStorage.setCellValueFactory(new PropertyValueFactory<>("TotalStorage"));
		tbUsedStorage.setCellValueFactory(new PropertyValueFactory<>("UsedStorage"));
		tbLeftStorage.setCellValueFactory(new PropertyValueFactory<>("LeftStorage"));

		// hardware table view

		tbHWID.setCellValueFactory(new PropertyValueFactory<>("hardwareid"));
		tbHWCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
		tbTotalCPU.setCellValueFactory(new PropertyValueFactory<>("totalCPU"));
		tbCPULeft.setCellValueFactory(new PropertyValueFactory<>("leftCPU"));
		tbRAM.setCellValueFactory(new PropertyValueFactory<>("totalRAM"));
		tbLeftRAM.setCellValueFactory(new PropertyValueFactory<>("leftRAM"));

		// Incident View
		tbINCID.setCellValueFactory(new PropertyValueFactory<>("IncidentID"));
		tbINCSev.setCellValueFactory(new PropertyValueFactory<>("Severity"));
		tbINCimpactedCI.setCellValueFactory(new PropertyValueFactory<>("ImpactedCI"));
		tbINCResolvedtime.setCellValueFactory(new PropertyValueFactory<>("ResolvedDays"));
		tbINCTeam.setCellValueFactory(new PropertyValueFactory<>("Team"));
		tbINCRootCause.setCellValueFactory(new PropertyValueFactory<>("RootCause"));
	}

	/*
	 * Module will add user to database
	 */
	@FXML
	void addAction(ActionEvent event) {

		DatabaseConnection db = new DatabaseConnection();
		String userid = txtEmpID.getText().toString();
		String firstname = txtfname.getText().toString();
		String lastname = txtlname.getText().toString();
		String useremailid = txtemailid.getText().toString();
		String pswd = DigestUtils.shaHex("test");
		String userrole = txtrole.getText().toString();
		String phnno = txtphone.getText().toString();

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
			if (i > 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Database Updated");
				alert.setHeaderText("Record added to database");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Database not updated");
				alert.setHeaderText("Record not added to database. Please check details!!");
				alert.showAndWait();

			}
			DatabaseConnection.dbDisconnect();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
/**************add module ends  and delete module starts
 * this module delete user from table based on username
 * 
 * ******************************************/
	
	@FXML
	void deleteAction(ActionEvent event) {

		DatabaseConnection db = new DatabaseConnection();
		String userid = txtEmpID.getText().toString();

		String Regqry = "Delete from  snl_users  where userid=(?)";
		try {
			PreparedStatement pst = db.dbConnect().prepareStatement(Regqry);
			pst.setString(1, userid);

			int i = pst.executeUpdate();
			if (i > 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information From Database");
				alert.setHeaderText("User deleted from database");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Info From Database");
				alert.setHeaderText("User not deleted from database. Please enter valid userid!!");
				alert.showAndWait();

			}

			DatabaseConnection.dbDisconnect();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
/*
 * Serach module starts. Admin can search user from user table
 * 
 */
	@FXML
	void searchAction(ActionEvent event) {

		UserSearch();
	}

	void UserSearch() {
		ObservableList<UserData> data = FXCollections.observableArrayList();
		PreparedStatement prdstmt = null;
		ResultSet rsset = null;

		String searchqry = txtsearch.getText().toString();
		boolean flag = true;

		try {

			prdstmt = DatabaseConnection.dbConnect().prepareStatement("select * from SNL_USERS where userid like ?");
			prdstmt.setString(1, searchqry + "%");
			rsset = prdstmt.executeQuery();

			while (rsset.next()) {
				data.add(new UserData(rsset.getString(1), rsset.getString(2), rsset.getString(3), rsset.getString(4),
						rsset.getString(7), rsset.getString(6)));
				table.setItems(data);
				flag = false;
			}
			prdstmt.close();
			rsset.close();

			if (flag) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Search Request!!!");
				alert.setHeaderText("Record not found in database");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Record found");
				alert.setHeaderText("User found.. Please check!!!");
				alert.showAndWait();

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			e.printStackTrace();
		}

	}
/*
 * 
 * refresh table
 * 
 */
	@FXML
	void refreshTableAction(ActionEvent event) {
		userTableLoad();
	}

// *********method of loading table view

	void userTableLoad() {

		ObservableList<UserData> data = FXCollections.observableArrayList();
		PreparedStatement prdstmt = null;
		ResultSet rsset = null;

		{

			String qry = "Select * from SNL_USERS";

			try {
				prdstmt = DatabaseConnection.dbConnect().prepareStatement(qry);
				rsset = prdstmt.executeQuery();

				while (rsset.next()) {
					data.add(new UserData(rsset.getString(1), rsset.getString(2), rsset.getString(3),
							rsset.getString(4), rsset.getString(7), rsset.getString(6)));
					table.setItems(data);
				}
				prdstmt.close();
				rsset.close();
				DatabaseConnection.dbDisconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(e);
				e.printStackTrace();
			}

		}

	}

// ******************************************************************************************************************
// Dashboard module starts

	@FXML
	private Tab AdmDashboard;

	
	//barchart data
	@FXML
	private BarChart<String, Double> UserbarChart;

	@FXML
	private BarChart<String, Double> ServerStatusBarChart;

	@FXML
	private Button btnUserBar;

	// Pie chart Data
	private ObservableList<PieChart.Data> data;

	@FXML
	private PieChart dbPiechart;

	@FXML
	private BarChart<String, Integer> Incbarchart;

	@FXML
	private PieChart hdWareLocationPieChart;

	@FXML
	private BarChart<String, Double> storageTypeBarChart;

	/*
	 * this method will refresh all table and charts 
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
		LoadUserBarChart();
		LoadServerStatusBarChart();
		dBPieChartData();
		LoadINCbarchart();
		hdwarelocationPieChartData();
		storageTypeBarChart();

	}

	//created bar char
	void LoadUserBarChart() {

		String barquery = "select Role,count(*) from SNL_USERS group by Role";
		XYChart.Series<String, Double> series = new XYChart.Series();
		try {
			ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(barquery);

			while (rs.next()) {
				series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));

			}

			UserbarChart.getData().add(series);
			UserbarChart.setTitle("User By Role");
			UserbarChart.getBarGap();
			DatabaseConnection.dbDisconnect();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

// function for server status bar chart

	void LoadServerStatusBarChart() {

		String barquery = "select status,count(*) from servers_details group by status";
		Series<String, Double> series = new XYChart.Series();
		try {
			ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(barquery);

			while (rs.next()) {
				series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));

			}
			ServerStatusBarChart.getData().add(series);
			ServerStatusBarChart.setTitle("Server Status");
			ServerStatusBarChart.getBarGap();
			DatabaseConnection.dbDisconnect();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/******************pie chart for database details*********/
	void dBPieChartData() 
	{
		try {

			data = FXCollections.observableArrayList();
			String getData = "select count(*),DATABASETYPE from databases_details group by DATABASETYPE";
			ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(getData);
			while (rs.next()) {
				String dbType = String.valueOf(rs.getString(2));
				data.add(new PieChart.Data(dbType, rs.getDouble(1)));
			}
			dbPiechart.setTitle("Database Type Overview");
			dbPiechart.setData(data);
			dbPiechart.getLabelsVisible();
			dbPiechart.setLegendVisible(true);
			DatabaseConnection.dbDisconnect();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
/*****************bar chart for incidents detail******************************************/
	void LoadINCbarchart() {

		String barquery = "select Team,count(*) from incidents_details where CREATEDDATE like '%17' group by Team";
		XYChart.Series<String, Integer> series = new XYChart.Series();
		try {
			ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(barquery);

			while (rs.next()) {
				series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));

			}
			Incbarchart.getData().add(series);
			Incbarchart.setTitle("Incident Resolved by Teams");
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

/*************** hardware location pie chart*****************/
	
	void hdwarelocationPieChartData() {
		try {

			data = FXCollections.observableArrayList();
			String getData = "select location,count(*) from hardwares_details group by location";
			ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(getData);
			while (rs.next()) {
				String dbType = String.valueOf(rs.getString(1));
				data.add(new PieChart.Data(dbType, rs.getDouble(2)));
			}
			hdWareLocationPieChart.setTitle("Database Overview");
			hdWareLocationPieChart.setData(data);
			hdWareLocationPieChart.setTitle("Hadware by Location");
			DatabaseConnection.dbDisconnect();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

/************* Dashboard for storage type***********************************/

	void storageTypeBarChart() {

		String barquery = "select storagetype,count(*) from STORAGE_DETAILS group by storagetype";
		Series<String, Double> series = new XYChart.Series();
		try {
			ResultSet rs = DatabaseConnection.dbConnect().createStatement().executeQuery(barquery);

			while (rs.next()) {
				series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));

			}
			storageTypeBarChart.getData().add(series);
			storageTypeBarChart.setTitle("Storage Type");
			storageTypeBarChart.getBarGap();
			DatabaseConnection.dbDisconnect();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

// ***********************************************************************************************// Server Module starts
/*
 * Server Module for Admin Dashboard
 */
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

	/*
	 * add server module to add server details in server table in database
	 */
	@FXML
	void addServerAction(ActionEvent event) {

		DatabaseConnection db = new DatabaseConnection();
		String servername = txtservername.getText().toString();
		String serip = txtserverip.getText().toString();
		String sertype = txtservertype.getText().toString();
		String osver = txtserverversion.getText().toString();
		String serCritcality = txtservercriticality.getText().toString();
		String status = txtserverstatus.getText().toString();
		int cpu = Integer.parseInt((txtservercpu.getText().toString()));
		int memory = Integer.parseInt((txtservermemory.getText().toString()));

		String Regqry = "Insert into Servers_Details values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = db.dbConnect().prepareStatement(Regqry);
			pst.setString(1, servername);
			pst.setString(2, sertype);
			pst.setInt(3, cpu);
			pst.setInt(4, memory);
			pst.setString(5, osver);
			pst.setString(6, serCritcality);
			pst.setString(7, status);
			pst.setString(8, serip);

			int i = pst.executeUpdate();
			if (i > 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Database Updated");
				alert.setHeaderText("Server added to database");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Database not updated");
				alert.setHeaderText("Server not added to database. Please enter correct details!!");
				alert.showAndWait();

			}

			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	/*
	 * Delete server from table method
	 */
	@FXML
	void deleteServerAction(ActionEvent event) {

		DatabaseConnection db = new DatabaseConnection();
		String serverid = txtservername.getText().toString();

		String Regqry = "Delete from  Servers_details  where servername=(?)";
		try {
			PreparedStatement pst = db.dbConnect().prepareStatement(Regqry);
			pst.setString(1, serverid);

			int i = pst.executeUpdate();
			if (i > 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information From Database");
				alert.setHeaderText("Server deleted from database");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Info From Database");
				alert.setHeaderText("Server not deleted from database. Please enter valid server name!!");
				alert.showAndWait();

			}

			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	/*
	 * load the server table in dashboard
	 */
	@FXML
	void refreshServerTableAction(ActionEvent event) {
		serverTableLoad();

	}

	void serverTableLoad() {
		ObservableList<ServerDetails> serverdata = FXCollections.observableArrayList();
		PreparedStatement prdstmt = null;
		ResultSet rsset = null;

		{

			String qry = "Select * from Servers_Details";

			try {
				prdstmt = DatabaseConnection.dbConnect().prepareStatement(qry);
				rsset = prdstmt.executeQuery();

				while (rsset.next()) {

					serverdata.add(new ServerDetails(rsset.getString("servername"), rsset.getString("serverip"),
							rsset.getString("servertype"), rsset.getInt("CPU"), rsset.getInt("Memory"),
							rsset.getString("criticality"), rsset.getString("status")));
										ServerTable.setItems(serverdata);
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

//Search module
	@FXML
	void searchServerAction(ActionEvent event) {

		serverSearch();
	}

	void serverSearch() {
		ObservableList<ServerDetails> serverdata = FXCollections.observableArrayList();
		PreparedStatement prdstmt = null;
		ResultSet rsset = null;

		String searchqry = txtsearchServer.getText().toString();
		boolean flag = true;

		try {

			prdstmt = DatabaseConnection.dbConnect()
					.prepareStatement("select * from Servers_details where servername like ?");
			prdstmt.setString(1, searchqry + "%");
			rsset = prdstmt.executeQuery();

			while (rsset.next()) {

				serverdata.add(new ServerDetails(rsset.getString("servername"), rsset.getString("serverip"),
						rsset.getString("servertype"), rsset.getInt("CPU"), rsset.getInt("Memory"),
						rsset.getString("criticality"), rsset.getString("status")));
								ServerTable.setItems(serverdata);
				flag = false;
			}
			prdstmt.close();
			rsset.close();

			if (flag) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Search Request!!!");
				alert.setHeaderText("Server not found in database");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Search Request!!");
				alert.setHeaderText("Server found.. Please check!!!");
				alert.showAndWait();

			}

			DatabaseConnection.dbConnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			e.printStackTrace();
		}
	}

	
//Update Server details Module
	@FXML
	private Button updateserver;

	@FXML
	public void serverupdate(ActionEvent event) throws Exception {

			serverUpdatePage();
	}

	@FXML
	private Button btnupdatehardware;

	@FXML
	public void hardwareupdate(ActionEvent event) throws Exception {

				hardwareUpdatePage();
	}

	@FXML
	private Button updateuserphno;

	@FXML
	public void userphoneupdate(ActionEvent event) throws Exception {

		UserUpdatePage();
		
			
	}

	@FXML
	private Button	btnupdateUserPassword;
	
	@FXML
	public void updateUserPassword(ActionEvent event) throws Exception {

		passwordUpdatePage();
		
			
	}
	
/********************************Admin Database Module starts*********************************/
	
	@FXML
	private Tab AdmDatabase;

	// @FXML
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

	//adding database information in database table
	
	@FXML
	void addDatabaseAction(ActionEvent event) {

		DatabaseConnection db = new DatabaseConnection();
		String dbname = txtDatabaseName.getText().toString();
		String dbtype = txtDBtype.getText().toString();
		String dbserver = txtDBhostedOn.getText().toString();
		String dbapp = txtDBApp.getText().toString();
		String dbver = txtDBVer.getText().toString();
		String dbcriticality = txtDBCritcality.getText().toString();

		String Regqry = "Insert into Databases_details values(?,?,?,?,?,?)";
		try {
			PreparedStatement pst = db.dbConnect().prepareStatement(Regqry);
			pst.setString(1, dbname);
			pst.setString(2, dbtype);
			pst.setString(3, dbserver);
			pst.setString(4, dbapp);
			pst.setString(5, dbver);
			pst.setString(6, dbcriticality);

			int i = pst.executeUpdate();
			if (i > 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Database Updated");
				alert.setHeaderText(dbname + " added !!!");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Database not updated");
				alert.setHeaderText(dbname + " not added. Please enter correct details!!");
				alert.showAndWait();

			}

			DatabaseConnection.dbDisconnect();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	//deleting datbase information from table
	
	@FXML
	void deleteDatabaseAction(ActionEvent event) {

		DatabaseConnection db = new DatabaseConnection();
		String dbid = txtDatabaseName.getText().toString();

		String Regqry = "Delete from  databases_details  where databasename=(?)";
		try {
			PreparedStatement pst = db.dbConnect().prepareStatement(Regqry);
			pst.setString(1, dbid);

			int i = pst.executeUpdate();
			if (i > 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information From Database");
				alert.setHeaderText(dbid + " deleted");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Info From Database");
				alert.setHeaderText(dbid + " not deleted. Please enter valid database name!!");
				alert.showAndWait();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	//loading database to table 
	@FXML
	void refreshDBTableAction(ActionEvent event) {

		dbTableLoad();
	}

	void dbTableLoad() {
		ObservableList<DatabaseDetails> dbdata = FXCollections.observableArrayList();
		PreparedStatement prdstmt = null;
		ResultSet rsset = null;

		{

			String qry = "Select * from Databases_details";

			try {
				prdstmt = DatabaseConnection.dbConnect().prepareStatement(qry);
				rsset = prdstmt.executeQuery();

				while (rsset.next()) {
					dbdata.add(new DatabaseDetails(rsset.getString(1), rsset.getString(2), rsset.getString(3),
							rsset.getString(4), rsset.getString(5), rsset.getString(6)));
					DatabaseTable.setItems(dbdata);
				}
				prdstmt.close();
				rsset.close();
				DatabaseConnection.dbDisconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(e);
				e.printStackTrace();
			}

		}

	}

//Database information  search function
	@FXML
	void searchDBAction(ActionEvent event) {

		databaseSearch();
	}

	void databaseSearch() {
		ObservableList<DatabaseDetails> dbdata = FXCollections.observableArrayList();
		PreparedStatement prdstmt = null;
		ResultSet rsset = null;

		String searchqry = txtDBSearch.getText().toString();
		boolean flag = true;

		try {

			prdstmt = DatabaseConnection.dbConnect()
					.prepareStatement("select * from Databases_details where Databasename like ?");
			prdstmt.setString(1, searchqry + "%");
			rsset = prdstmt.executeQuery();

			while (rsset.next()) {
				dbdata.add(new DatabaseDetails(rsset.getString(1), rsset.getString(2), rsset.getString(3),
						rsset.getString(4), rsset.getString(5), rsset.getString(6)));
				DatabaseTable.setItems(dbdata);

				flag = false;
			}
			prdstmt.close();
			rsset.close();

			if (flag) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Search Request!!!");
				alert.setHeaderText(searchqry + " not found!!!!");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Search Request!!");
				alert.setHeaderText(searchqry + " found.. Please check!!!");
				alert.showAndWait();

			}
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			e.printStackTrace();
		}
	}
/******************************Application Module starts******************/
	
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


	//Refresh Application module	
	
	@FXML
	void refreshApplicationAction(ActionEvent event) {
		applicationTableLoad();
	}

	void applicationTableLoad() {
		ObservableList<Apps> appdata = FXCollections.observableArrayList();
		PreparedStatement prdstmt = null;
		ResultSet rsset = null;

		{

			String qry = "Select * from application_details";

			try {
				prdstmt = DatabaseConnection.dbConnect().prepareStatement(qry);
				rsset = prdstmt.executeQuery();

				while (rsset.next()) {
					appdata.add(new Apps(rsset.getString(1), rsset.getString(6), rsset.getString(5), rsset.getString(2),
							rsset.getString(3), rsset.getString(4)));
					AppTable.setItems(appdata);
				}
				prdstmt.close();
				rsset.close();
				DatabaseConnection.dbDisconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(e);
				e.printStackTrace();
			}

		}

	}

	
	//Search module for application tab
	@FXML
	void searchApplicationAction(ActionEvent event) {

		applicationSearch();
	}

	void applicationSearch() {
		ObservableList<Apps> appdata = FXCollections.observableArrayList();
		PreparedStatement prdstmt = null;
		ResultSet rsset = null;

		String searchqry = txtAppsearch.getText().toString();
		boolean flag = true;

		try {

			prdstmt = DatabaseConnection.dbConnect()
					.prepareStatement("select * from application_details where applicationname like ?");
			prdstmt.setString(1, searchqry + "%");
			rsset = prdstmt.executeQuery();

			while (rsset.next()) {

				appdata.add(new Apps(rsset.getString(1), rsset.getString(6), rsset.getString(5), rsset.getString(2),
						rsset.getString(3), rsset.getString(4)));
				AppTable.setItems(appdata);

				flag = false;
			}
			prdstmt.close();
			rsset.close();

			if (flag) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Search Request!!!");
				alert.setHeaderText(searchqry + " not found!!!!");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Search Request!!");
				alert.setHeaderText(searchqry + " found.. Please check!!!");
				alert.showAndWait();

			}

			DatabaseConnection.dbConnect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			e.printStackTrace();
		}
	}

//add module to add application in database	
	@FXML
	void addAppAction(ActionEvent event) {

		DatabaseConnection db = new DatabaseConnection();
		String appname = txtAppName.getText().toString();
		String cric = txtAppCriticality.getText().toString();
		String ver = txtAppVer.getText().toString();
		String dbh = txtAppDBhosted.getText().toString();
		String appser = txtAppReltServer.getText().toString();
		String appt = txtAppType.getText().toString();

		String Regqry = "Insert into Application_details values(?,?,?,?,?,?)";
		try {
			PreparedStatement pst = db.dbConnect().prepareStatement(Regqry);
			pst.setString(1, appname);
			pst.setString(2, dbh);
			pst.setString(3, appser);
			pst.setString(4, appt);
			pst.setString(5, ver);
			pst.setString(6, cric);

			int i = pst.executeUpdate();
			if (i > 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Database Updated");
				alert.setHeaderText(appname + " added !!!");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Database not updated");
				alert.setHeaderText(appname + " not added. Please enter correct details!!");
				alert.showAndWait();

			}
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

//delete application module 	
	@FXML
	void deleteAppAction(ActionEvent event) {

		DatabaseConnection db = new DatabaseConnection();
		String appid = txtAppName.getText().toString();

		String Regqry = "Delete from  application_details  where applicationname=(?)";
		try {
			PreparedStatement pst = db.dbConnect().prepareStatement(Regqry);
			pst.setString(1, appid);

			int i = pst.executeUpdate();
			if (i > 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information From Database");
				alert.setHeaderText(appid + " deleted");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Info From Database");
				alert.setHeaderText(appid + " not deleted. Please enter valid application name!!");
				alert.showAndWait();
			}

			db.dbDisconnect();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

/*******************************************************************************/
	
	// Hardware Module

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

	
	//add Hardware to hardware moduel in admin GUI
	@FXML
	void addHardwareAction(ActionEvent event) {

		DatabaseConnection db = new DatabaseConnection();
		String hwid = txtHWID.getText().toString();
		String hwname = txtHWName.getText().toString();
		int hwtotalCPU = Integer.parseInt(txtTotalCPU.getText().toString());
		int hwleftCPU = Integer.parseInt(txtCPULeft.getText().toString());
		int hwtotalRAM = Integer.parseInt(txtTotalRam.getText().toString());
		int hwleftRAM = Integer.parseInt(txtRAMLeft.getText().toString());
		String hwlocation = txtHWLocation.getText().toString();
		String hwcompany = txtHWCmpny.getText().toString();

		String Regqry = "Insert into hardwares_details values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = db.dbConnect().prepareStatement(Regqry);
			pst.setString(1, hwname);
			pst.setInt(2, hwtotalCPU);
			pst.setInt(3, hwleftCPU);
			pst.setInt(4, hwtotalRAM);
			pst.setInt(5, hwleftRAM);
			pst.setString(6, hwlocation);
			pst.setString(7, hwcompany);
			pst.setString(8, hwid);

			int i = pst.executeUpdate();
			if (i > 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Database Updated");
				alert.setHeaderText(hwid + " added !!!");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Database not updated");
				alert.setHeaderText(hwid + " not added. Please enter correct details!!");
				alert.showAndWait();

			}

			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	//delete hardware details from table
	@FXML
	void deleteHardwareAction(ActionEvent event) {

		DatabaseConnection db = new DatabaseConnection();
		String hwid = txtHWID.getText().toString();

		String Regqry = "Delete from  hardwares_details  where hardwareid=(?)";
		try {
			PreparedStatement pst = db.dbConnect().prepareStatement(Regqry);
			pst.setString(1, hwid);

			int i = pst.executeUpdate();
			if (i > 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information From Database");
				alert.setHeaderText(hwid + " deleted");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Info From Database");
				alert.setHeaderText(hwid + " not deleted. Please enter valid hardware name!!");
				alert.showAndWait();
			}
			DatabaseConnection.dbDisconnect();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	//refresh hardware details 
	@FXML
	void refreshHardwareAction(ActionEvent event) {

		hardwareTableload();
	}

	void hardwareTableload() {
		ObservableList<HardwareDetails> data = FXCollections.observableArrayList();
		PreparedStatement prdstmt = null;
		ResultSet rsset = null;
		String qry = "Select * from hardwares_details";

		try {
			prdstmt = DatabaseConnection.dbConnect().prepareStatement(qry);
			rsset = prdstmt.executeQuery();

			while (rsset.next()) {
				data.add(new HardwareDetails(rsset.getString(8), rsset.getString(7), rsset.getInt(2), rsset.getInt(3),
						rsset.getInt(4), rsset.getInt(5)));
				HWtable.setItems(data);
			}
			prdstmt.close();
			rsset.close();
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			e.printStackTrace();
		}

	}

	@FXML
	void searchHardwareAction(ActionEvent event) {

		hardwareSearch();
	}

	void hardwareSearch() {
		ObservableList<HardwareDetails> data12 = FXCollections.observableArrayList();
		PreparedStatement prdstmt = null;
		ResultSet rsset = null;

		String searchqry = txtHWsearch.getText().toString();

		boolean flag = true;

		try {

			prdstmt = DatabaseConnection.dbConnect()
					.prepareStatement("select * from hardwares_details where hardwareid=?");
			prdstmt.setString(1, searchqry);
			rsset = prdstmt.executeQuery();

			while (rsset.next()) {

				data12.add(new HardwareDetails(rsset.getString(8), rsset.getString(7), rsset.getInt(2), rsset.getInt(3),
						rsset.getInt(4), rsset.getInt(5)));
				// System.out.println("heekkk"+rsset.getString(1));
				HWtable.setItems(data12);
				flag = false;
			}
			prdstmt.close();
			rsset.close();

			if (flag) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Search Request!!!");
				alert.setHeaderText(searchqry + " not found!!!!");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Search Request!!");
				alert.setHeaderText(searchqry + " found.. Please check!!!");
				alert.showAndWait();

			}
			DatabaseConnection.dbDisconnect();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			e.printStackTrace();
		}
	}
//****************************************************************************//
	
	// Storage module
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

	//add storage details in database
	@FXML
	void addStorageAction(ActionEvent event) {

		String storageid = txtStorageID.getText().toString();
		String type = txtStorageType.getText().toString();
		String compy = txtStorageCompany.getText().toString();
		String totstrge = txtTotalStorage.getText().toString();
		String leftstorage = txtLeftStorage.getText().toString();
		String usedstorage = txtUsedStorage.getText().toString();

		String Regqry = "Insert into Storage_details values(?,?,?,?,?,?)";
		try {
			PreparedStatement pst = DatabaseConnection.dbConnect().prepareStatement(Regqry);
			pst.setString(1, storageid);
			pst.setString(2, type);
			pst.setString(3, compy);
			pst.setString(4, totstrge);
			pst.setString(5, leftstorage);
			pst.setString(6, usedstorage);

			int i = pst.executeUpdate();
			if (i > 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Database Updated");
				alert.setHeaderText(storageid + " added !!!");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Database not updated");
				alert.setHeaderText(storageid + " not added. Please enter correct details!!");
				alert.showAndWait();

			}
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	//delete storage details from table
	@FXML
	void deleteStorageAction(ActionEvent event) {

		String Storageid = txtStorageID.getText().toString();

		String Regqry = "Delete from  Storage_details  where StorageID=(?)";
		try {
			PreparedStatement pst = DatabaseConnection.dbConnect().prepareStatement(Regqry);
			pst.setString(1, Storageid);

			int i = pst.executeUpdate();
			if (i > 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information From Database");
				alert.setHeaderText(Storageid + " deleted");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Info From Database");
				alert.setHeaderText(Storageid + " not deleted. Please enter valid hardware name!!");
				alert.showAndWait();
			}

			DatabaseConnection.dbDisconnect();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	//refresh storage
	@FXML
	void refreshStorageAction(ActionEvent event) {

		storageTableload();

	}

	void storageTableload() {
		ObservableList<StorageDetails> storagedata = FXCollections.observableArrayList();
		PreparedStatement prdstmt = null;
		ResultSet rsset = null;

		{

			String qry = "Select * from Storage_details";

			try {
				prdstmt = DatabaseConnection.dbConnect().prepareStatement(qry);
				rsset = prdstmt.executeQuery();

				while (rsset.next()) {
					storagedata.add(new StorageDetails(rsset.getString(1), rsset.getString(2), rsset.getString(3),
							rsset.getInt(4), rsset.getInt(5), rsset.getInt(6)));
					StorageTable.setItems(storagedata);
				}
				prdstmt.close();
				rsset.close();
				DatabaseConnection.dbDisconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(e);
				e.printStackTrace();
			}

		}

	}

	@FXML
	void searchStorageAction(ActionEvent event) {

		storageSearch();
	}

	/**
	 * 
	 */
	void storageSearch() {
		ObservableList<StorageDetails> storagedata = FXCollections.observableArrayList();
		PreparedStatement prdstmt = null;
		ResultSet rsset = null;

		String searchqry = txtStoragesearch.getText().toString();
		boolean flag = true;

		try {

			prdstmt = DatabaseConnection.dbConnect()
					.prepareStatement("select * from storage_details where storageid like ?");
			prdstmt.setString(1, searchqry + "%");
			rsset = prdstmt.executeQuery();

			while (rsset.next()) {
				storagedata.add(new StorageDetails(rsset.getString(1), rsset.getString(2), rsset.getString(3),
						rsset.getInt(4), rsset.getInt(6), rsset.getInt(5)));
				// System.out.println("heekkk"+rsset.getString(1));
				StorageTable.setItems(storagedata);

				flag = false;
			}
			prdstmt.close();
			rsset.close();

			if (flag) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Search Request!!!");
				alert.setHeaderText(searchqry + " not found!!!!");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Search Request!!");
				alert.setHeaderText(searchqry + " found.. Please check!!!");
				alert.showAndWait();

			}
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			e.printStackTrace();
		}
	}
	// Storage module end
/******************************************************************/
	
	// Incidents Module
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

	//adding new incident details in table
	@FXML
	void addIncidentsAction(ActionEvent event) throws Exception {

		DatabaseConnection db = new DatabaseConnection();
		int incid = Integer.parseInt(txtINCID.getText().toString());
		String inctitle = txtINCtitle.getText().toString();
		String inuserid = txtINCUserID.getText().toString();
		int insev = Integer.parseInt(txtINCSev.getText().toString());
		String incimpacted = txtINCimpactedCI.getText().toString();
		String incroot = txtINCRootCause.getText().toString();
		// LocalDate createddate=INCcreateddt.getValue();
		// LocalDate resolveddate =INCResolvedDT.getValue();
		String incteam = txtINCTeam.getText().toString();

		String Regqry = "Insert into incidents_details values(?,?,?,?,?,?,?,?,?,?)";
		try {

			PreparedStatement pst = DatabaseConnection.dbConnect().prepareStatement(Regqry);
			pst.setInt(1, incid);
			pst.setInt(2, insev);
			pst.setString(3, incroot);
			pst.setString(4, inuserid);
			pst.setString(5, incimpacted);
			pst.setString(6, incteam);
			pst.setString(7, inctitle);
			pst.setDate(8, java.sql.Date.valueOf(INCcreateddt.getValue()));
			pst.setDate(9, java.sql.Date.valueOf(INCResolvedDT.getValue()));
			pst.setInt(10, (int) ChronoUnit.DAYS.between(LocalDate.parse(INCcreateddt.getValue().toString()),
					LocalDate.parse(INCResolvedDT.getValue().toString())));

			int i = pst.executeUpdate();
			if (i > 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Database Updated");
				alert.setHeaderText(incid + " added !!!");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Database not updated");
				alert.setHeaderText(incid + " not added. Please enter correct details!!");
				alert.showAndWait();

			}

			DatabaseConnection.dbDisconnect();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	//deleting incidents from incidents table
	@FXML
	void deleteIncidentsAction(ActionEvent event) {

		DatabaseConnection db = new DatabaseConnection();
		String incid = txtINCID.getText().toString();

		String Regqry = "Delete from  incidents_details  where incidentid=(?)";
		try {
			PreparedStatement pst = db.dbConnect().prepareStatement(Regqry);
			pst.setString(1, incid);

			int i = pst.executeUpdate();
			if (i > 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information From Database");
				alert.setHeaderText(incid + " deleted");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Info From Database");
				alert.setHeaderText(incid + " not deleted. Please enter valid incident ID!!");
				alert.showAndWait();
			}

			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	
//loading incidents details	
	@FXML
	void refreshIncidentsAction(ActionEvent event) {

		incidentTableload();
	}

	void incidentTableload() {
		ObservableList<IncidentDetails> data = FXCollections.observableArrayList();
		PreparedStatement prdstmt = null;
		ResultSet rsset = null;

		{

			String qry = "Select * from Incidents_details";

			try {
				prdstmt = DatabaseConnection.dbConnect().prepareStatement(qry);
				rsset = prdstmt.executeQuery();

				while (rsset.next()) {

					data.add(new IncidentDetails(rsset.getInt(1), rsset.getInt(2), rsset.getString(5), rsset.getInt(10),
							rsset.getString(6), rsset.getString(3)));

					INCtable.setItems(data);
				}
				prdstmt.close();
				rsset.close();
				DatabaseConnection.dbConnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(e);
				e.printStackTrace();
			}

		}

	}

	@FXML
	void searchIncidentsAction(ActionEvent event) {

		incidentSearch();
	}
//INCIdent search module
	void incidentSearch() {
		ObservableList<IncidentDetails> data = FXCollections.observableArrayList();
		PreparedStatement prdstmt = null;
		ResultSet rsset = null;

		String searchqry = txtINCsearch.getText().toString();
		boolean flag = true;

		try {

			prdstmt = DatabaseConnection.dbConnect()
					.prepareStatement("select * from Incidents_Details where incidentid like ?");
			prdstmt.setString(1, searchqry + "%");
			rsset = prdstmt.executeQuery();

			while (rsset.next()) {

				data.add(new IncidentDetails(rsset.getInt(1), rsset.getInt(2), rsset.getString(5), rsset.getInt(10),
						rsset.getString(6), rsset.getString(3)));
				INCtable.setItems(data);
				flag = false;
			}
			prdstmt.close();
			rsset.close();

			if (flag) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Search Request!!!");
				alert.setHeaderText(searchqry + " not found!!!!");
				alert.showAndWait();

			}

			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Search Request!!");
				alert.setHeaderText(searchqry + " found.. Please check!!!");
				alert.showAndWait();

			}
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			e.printStackTrace();
		}
	}

}
