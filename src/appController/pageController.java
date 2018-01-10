package appController;

import java.io.IOException;

import applicationModel.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * 
 * @author Sunil
 *
 *this class contains all methods to load different pages like login,
 *registration, admin ,manager,serverupdate and hardware update.
 */

public class pageController {

//*******************method load to registration page***********/
	public void registrationPage()
	{
		
		try {
			AnchorPane loaderlogin;
			loaderlogin = FXMLLoader.load(getClass().getResource("/viewModel/Registration.fxml"));
			Scene scenes = new Scene(loaderlogin);
			Main.primaryStage.setTitle("Registration Page");
			Main.primaryStage.setScene(scenes);
			Main.primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	

//*******************method load to admin page***********/
	
	public void adminPage()
	{
		
		try {
			AnchorPane loaderlogin;
			loaderlogin = FXMLLoader.load(getClass().getResource("/viewModel/AdminWelcome.fxml"));
			Scene scenes = new Scene(loaderlogin);
			Main.primaryStage.setTitle("Admin Page");
			Main.primaryStage.setScene(scenes);
			Main.primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

//*******************method load to manager page***********/

	public void managerPage()
	{
		
		try {
			AnchorPane loaderlogin;
			loaderlogin = FXMLLoader.load(getClass().getResource("/viewModel/ManagerWelcome.fxml"));
			Scene scenes = new Scene(loaderlogin);
			Main.primaryStage.setTitle("Welcome Manager Page");
			Main.primaryStage.setScene(scenes);
			Main.primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

//*******************method load to changepassword page***********/
	
	public void changePasswordPage()
	{
		
		try {
			AnchorPane loaderlogin;
			loaderlogin = FXMLLoader.load(getClass().getResource("/viewModel/changepassword.fxml"));
			Scene scenes = new Scene(loaderlogin);
			Main.primaryStage.setTitle("Password Management");
			Main.primaryStage.setScene(scenes);
			Main.primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

//*******************method  load to hardwareupdate page***********/
	Stage primaryStage = new Stage();
	public void hardwareUpdatePage()
	{
		
		try {
			
			AnchorPane loaderlogin;
			loaderlogin = FXMLLoader.load(getClass().getResource("/viewModel/HardwareUpdateModule.fxml"));
			Scene scenes = new Scene(loaderlogin);
			primaryStage.setTitle("Hardware Management");
			primaryStage.setScene(scenes);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

//*******************method to load serverupdate page***********/

	public void serverUpdatePage()
	{
		
		try {
			
			
			AnchorPane loaderlogin;
			loaderlogin = FXMLLoader.load(getClass().getResource("/viewModel/ServerUpdateModule.fxml"));
			Scene scenes = new Scene(loaderlogin);
			primaryStage.setTitle("Server Management");
			primaryStage.setScene(scenes);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

//*******************method to load login page***********/

	public void loginPage()
	{		
		try {
			
			AnchorPane loaderlogin;
			loaderlogin = FXMLLoader.load(getClass().getResource("/viewModel/LoginPage.fxml"));
			Scene scenes = new Scene(loaderlogin);
			Main.primaryStage.setTitle("Welcome to IT Infra System");
			Main.primaryStage.setScene(scenes);
			Main.primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	

	//*******************method to load UserUpdatePage ***********/
	public void UserUpdatePage()
	{		
		try {
			
			
			
			AnchorPane loaderlogin;
			loaderlogin = FXMLLoader.load(getClass().getResource("/viewModel/UserUpdateModule.fxml"));
			Scene scenes = new Scene(loaderlogin);
			primaryStage.setTitle("User Update");
			primaryStage.setScene(scenes);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	//*******************method to load password update page ***********/
	public void passwordUpdatePage()
	{
		
		try {
			
			
			AnchorPane loaderlogin;
			loaderlogin = FXMLLoader.load(getClass().getResource("/viewModel/UpdatePassword.fxml"));
			Scene scenes = new Scene(loaderlogin);
			primaryStage.setTitle("User Password Management");
			primaryStage.setScene(scenes);
			primaryStage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}
//class ends