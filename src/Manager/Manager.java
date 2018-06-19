package Manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Manager {

    public Login.Controller loginControl;

    public Manager()
    {
        loginControl = null;
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

    public void addLoginController(Login.Controller controller)
    {
        this.loginControl = controller;
        if(this.loginControl == null)
            System.out.println("Login Controller is null!");
    }
}
