package Manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    Login.Builder login;

    @Override
    public void start(Stage primaryStage) throws Exception{

        login = new Login.Builder();

        // Parent root = FXMLLoader.load(getClass().getResource("Login\\Login.fxml"));
        primaryStage.setTitle("Project Nyx");
        primaryStage.setScene(login.buildScene());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
