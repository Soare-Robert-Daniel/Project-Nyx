package Home;

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
    private Button addButton;


    public void addKey()
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        System.out.println("[System] Checking form...");
        if(true)
        {
            System.out.println("[Load-> Home Controller] -> All elements are initialized!");
        }
        else
        {
            System.out.println("[Warning][Load-> Home Controller] -> Some elements are not initialized!");
        }
        System.out.println("[System] Loading data...");
        chechInit();
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
            System.out.println("[Warning] Home Manager is null");
            return;
        }
        this.manager.testManager();
        System.out.println("[System] Home Controller has initialized it's manager...");
    }

    public void chechInit()
    {
        if(addButton == null)
        {
            System.out.println("[System][Home][Controller] Add Button is null");
        }
    }
}
