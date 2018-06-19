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



public class Controller implements Initializable {

    //@FXML
    public TextField usernameField;

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
        System.out.println("You clicked me!");
        ShowFields();
    }

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        System.out.println("Checking form...");
        if(checkInit())
        {
            System.out.println("-> All elements are initialized!");
        }
        System.out.println("Loading data...");
    }

}
