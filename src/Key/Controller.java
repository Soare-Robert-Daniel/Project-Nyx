package Key;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.layout.HBox;

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

    @FXML
    private CheckBox delete;

    @FXML
    private HBox frame;

    @Override
    public void initialize(URL location, ResourceBundle resource) {

    }

    public void showAction()
    {
        if(this.show.isSelected())
        {
            this.node.sendDataToForm();

            this.edit.setDisable(false);
            this.edit.setOpacity(1);

        }
        else
        {
            if(this.node.getUsername() != null)
                this.username.setText("Hidden");
            if(this.node.getPassword() != null)
                this.password.setText("Hidden");

            this.edit.setDisable(true);
            this.edit.setSelected(false);
            this.edit.setOpacity(0);

            this.delete.setDisable(true);
            this.delete.setOpacity(0);
        }
    }

    public void editAction()
    {
        if(this.edit.isSelected())
        {
            this.info.setEditable(true);
            this.username.setEditable(true);
            this.password.setEditable(true);

            this.delete.setDisable(false);
            this.delete.setOpacity(1);
        }
        else
        {
            this.info.setEditable(false);
            this.username.setEditable(false);
            this.password.setEditable(false);

            this.getDataFromForm();

            if(this.node.isUnsaved())
            {
                this.state("unsaved");
            }

            this.delete.setDisable(true);
            this.delete.setOpacity(0);
        }
    }

    public void deleteAction()
    {
        if(this.delete.isSelected())
        {
            this.state("delete");
            this.node.sendToDelete();
        }
        else
        {
            state("");
        }
    }

    public void setDataToForm(String infoS, String usernameS, String passwordS)
    {
        this.info.setText(infoS);
        this.username.setText(usernameS);
        this.password.setText(passwordS);
    }

    public void setDataToKeyFormPrototype(String infoS, String usernameS, String passwordS, boolean toDelete)
    {
        this.info.setText(infoS);
        this.username.setText(usernameS);
        this.password.setText(passwordS);
        this.delete.setSelected(toDelete);
        deleteAction();
    }

    public void getDataFromForm()
    {
        this.node.setInfo(this.info.getText());
        this.node.setPassword(this.password.getText());
        this.node.setUsername(this.username.getText());
    }

    public void getDataFromFormPrototype()
    {
        this.node.setInfo(this.info.getText());
        this.node.setPassword(this.password.getText());
        this.node.setUsername(this.username.getText());
        this.node.setToDelete(this.delete.isSelected());
    }

    public void setNode(KeyNode node)
    {
        this.node = node;
    }

    public void state(String value)
    {
        switch (value)
        {
            case "unsaved":
                String color1 = "ffae42";
                this.frame.setStyle( String.format("-fx-background-color: #%s;", color1));
                break;
            case "delete":
                String color2 = "f03434";
                this.frame.setStyle( String.format("-fx-background-color: #%s;", color2));
                break;
            case "normal":
                String color3 = "89c4f4";
                this.frame.setStyle( String.format("-fx-background-color: #%s;", color3));
                break;
            default:
                if(this.node.isUnsaved())
                {
                    state("unsaved");
                }
                else
                {
                    state("normal");
                }
                break;
        }
    }
}

