package Manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.ExecutionException;


public class Manager {

    private int testManager;
    private Stage stage;

    private Login.Builder loginBuilder;
    private Login.Controller loginControl;

    private Home.Builder homeBuilder;
    private Home.Controller homeControl;

    public Manager()
    {
        loginControl = null;
    }

    public Manager(int x) throws Exception
    {
        testManager = x;

        // LOGIN
        loginBuilder = new Login.Builder();
        loginControl = loginBuilder.getController();

        // HOME
        homeBuilder = new Home.Builder();
        homeControl = homeBuilder.getController();

        // Init Controllers's settings
        initializeControllers();
    }

    public boolean login() throws Exception
    {
        return true;
    }

    public boolean settings() throws Exception
    {
        return true;
    }

    public boolean baseInterface() throws Exception
    {
        return true;
    }

    public void changeScene(String sceneName)
    {
        switch (sceneName)
        {
            case "login":
                try {
                    this.stage.setScene(this.loginBuilder.buildScene());
                } catch (Exception ex)
                {
                    System.out.println("[Error][Manager][Change Scene] Login Scene is not working !");
                    ex.printStackTrace();
                }

                break;
            case "home":
                try {
                    this.stage.setScene(this.homeBuilder.buildScene());
                } catch (Exception ex)
                {
                    System.out.println("[Error][Manager][Change Scene] Home Scene is not working !");
                    ex.printStackTrace();
                }
                break;
            case "settings":
                break;
            case "edit":
                break;
            default:
                System.out.println("[Warning][Manager][Change Scene] Value is not valid");
        }
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
        if(this.stage == null)
        {
            System.out.println("[Warning] Manager Stage is null!");
        }
    }

    public void testManager()
    {
        System.out.println(String.format("[Test][Manager] Manager number: %d" , testManager));
    }

    private void initializeControllers()
    {
        if(this.loginControl != null)
        {
            // Add the reference of the manager to the login controller
            this.loginControl.setManager(this);
        }
        else {
            System.out.println("[Warning][Manager][Init] Login Controller is null!");
        }
        if(this.homeControl != null)
        {
            // Add the reference of the manager to the home controller
            this.homeControl.setManager(this);
        }
        else {
            System.out.println("[Warning][Manager][Init] Home Controller is null!");
        }
    }
}
