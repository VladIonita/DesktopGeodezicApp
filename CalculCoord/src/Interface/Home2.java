package Interface;


//import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Home2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

    	try {
    		
			Parent root = FXMLLoader.load(getClass().getResource("Home2.fxml"));
			primaryStage.setTitle("Hello World");
			primaryStage.setScene(new Scene(root, 1080,720));
			primaryStage.show();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
}