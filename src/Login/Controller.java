package Login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import Manager.Manager;

public class Controller implements Initializable {

    Manager manager;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;


    private boolean checkInit()
    {
        if(usernameField == null || passwordField == null || loginButton == null)
            return false;
        return true;
    }

    public void ShowFields(){

        if(usernameField.getText() != null)
            System.out.println(usernameField.getText());
        if(passwordField.getText() != null)
            System.out.println(passwordField.getText());
    }

    public void loginUser(MouseEvent event){
        System.out.println("Go to Home!");
        ShowFields();
        this.manager.changeScene("home");
    }

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        System.out.println("[System] Checking form...");
        if(checkInit())
        {
            System.out.println("[Load-> Login Controller] -> All elements are initialized!");
        }
        else
        {
            System.out.println("[Warning][Load-> Login Controller] -> Some elements are not initialized!");
        }
        System.out.println("[System] Loading data...");
    }

    public void test()
    {
        System.out.println("Test passed...");
    }

    public void setManager(Manager manager)
    {
        this.manager = manager;
        if(this.manager == null)
        {
            System.out.println("[Warning] Login Manager is null");
            return;
        }
        this.manager.testManager();
        System.out.println("[System] Login Controller has initialized it's manager...");
    }

}

