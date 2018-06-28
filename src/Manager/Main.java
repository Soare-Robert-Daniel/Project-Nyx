package Manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    private Stage stage;
    private Scene scene;


    Manager manager;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            stage = primaryStage;

            manager = new Manager(10);
            manager.setStage(stage);
        }
        catch (Exception ex) {
            System.out.println("Error at initializing the start of the app!");
            ex.printStackTrace();
        }



        // Parent root = FXMLLoader.load(getClass().getResource("Login\\Login.fxml"));
        //primaryStage.setTitle("Project Nyx");
        //primaryStage.setScene(login.buildScene());
        //scene = primaryStage.getScene();

        //primaryStage.show();

        stage.setTitle("Project Nyx");
        stage.setResizable(false);
        manager.changeScene("login");
        scene = stage.getScene();

        stage.show();
    }

    void Login() throws Exception
    {
        if(true)
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
