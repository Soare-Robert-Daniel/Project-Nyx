package Login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.lang.Character;
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

    @FXML
    private Button registerButton;


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

    public void loginUser(){
        System.out.println("Go to Home!");
        ShowFields();
        if(this.manager.login(usernameField.getText(), passwordField.getText()))
            this.manager.changeScene("home");
        else
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText("Login failed!");
            alert.setContentText("Invalid name or password");
            alert.showAndWait();
        }
    }

    public void registerUser() throws Exception
    {
        if(checkForWrongInput())
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Register");
            alert.setHeaderText("Registration failed!");
            alert.setContentText("Invalid input! \n - all fields must be completed \n - only letters and digits \n - minimum 8 characters");
            alert.showAndWait();
            return;
        }

        if(this.manager.register(usernameField.getText(), passwordField.getText()))
        {
            System.out.println("Register!");
            ShowFields();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Register");
            alert.setHeaderText("Thank you for your registration!");
            alert.setContentText(String.format("User %s has been registered!", this.usernameField.getText()));
            alert.showAndWait();

            this.manager.changeScene("home_New_User");
        }
        else
        {
            System.out.println("Register failed!");
            ShowFields();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Register failed");
            alert.setHeaderText("User already exists!");
            alert.setContentText("Choose another user name");
            alert.showAndWait();
        }

    }

    private boolean checkForWrongInput()
    {
        // Check if the fields are empty
        if(this.usernameField.getText().isEmpty() || this.passwordField.getText().isEmpty())
        {
            return true;
        }

        // Check for length
        if( //this.usernameField.getText().length() < 8 || this.passwordField.getText().length() < 8 ||
                this.usernameField.getText().length() > 16 || this.passwordField.getText().length() > 16)
        {
            return true;
        }

        // Check for invalid characters
        for(char c : this.usernameField.getText().toCharArray())
        {
            if(!Character.isLetterOrDigit(c))
                return true;
        }

        for(char c : this.passwordField.getText().toCharArray())
        {
            if(!Character.isLetterOrDigit(c))
                return true;
        }
        return false;
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

