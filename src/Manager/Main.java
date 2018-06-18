package Manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    private Stage stage;


    Login.Builder login;
    Manager manager;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            stage = primaryStage;
            login = new Login.Builder();
            manager = new Manager();
        }
        catch (Exception ex) {
            System.out.println("Error at initializing the start of the app!");
            ex.printStackTrace();
        }

        try
        {
            manager.addLoginController(login.getController());
        }
        catch (Exception ex) {
            System.out.println("Error at initializing the controllers!");
            ex.printStackTrace();
        }
        // Parent root = FXMLLoader.load(getClass().getResource("Login\\Login.fxml"));
        primaryStage.setTitle("Project Nyx");
        primaryStage.setScene(login.buildScene());
        primaryStage.show();
    }

    void Login() throws Exception
    {
        if(manager.login())
        {
            //stage.setScene(scene);
        }
        else
        {

        }
    }

    void Settings()
    {

    }

    void Register()
    {

    }

    void BaseInterface()
    {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
