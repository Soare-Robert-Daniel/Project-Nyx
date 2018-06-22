package Key;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import Manager.Manager;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;

public class Controller implements Initializable {

    private KeyNode node;

    @FXML
    private TextField info;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private CheckBox show;

    @FXML
    private CheckBox edit;

    @Override
    public void initialize(URL location, ResourceBundle resource) {

    }

    public void showAction()
    {
        if(this.show.isSelected())
        {
            this.node.sendDataToForm();
            this.edit.setDisable(true);
        }
        else
        {
            this.username.setText("Hidden");
            this.password.setText("Hidden");
            this.edit.setDisable(false);
        }
    }

    public void editAction()
    {
        if(this.edit.isSelected())
        {
            this.info.setEditable(true);
            this.username.setEditable(true);
            this.password.setEditable(true);
        }
        else
        {
            this.info.setEditable(false);
            this.username.setEditable(false);
            this.password.setEditable(false);
            this.node.getDataFromForm();
        }
    }

    public void setDataToKeyForm(String infoS, String usernameS, String passwordS)
    {
        this.info.setText(infoS);
        this.username.setText(usernameS);
        this.password.setText(passwordS);
    }

    public void getDataFromForm()
    {
        this.node.setInfo(this.info.getText());
        this.node.setPassword(this.password.getText());
        this.node.setUsername(this.username.getText());
    }

    public void setNode(KeyNode node)
    {
        this.node = node;
    }
}

