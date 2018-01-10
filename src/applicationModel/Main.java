package applicationModel;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


public class Main extends Application {
	
	//this is stage variable will used in all class
	public static Stage primaryStage;
	
	

/*this method will set the stage to run JavaFx applications and this is default method and must be override.
	
when will run the program this is loaded the loginpage	*/
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage1) {
		
			
			try {
				this.primaryStage=primaryStage1;
				Parent loaderlogin;
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

	public static void main(String[] args) {
		
		launch(args);
		
		}
}