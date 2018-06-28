package Home;

import Key.KeyNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List;

import Manager.Manager;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;

public class Controller implements Initializable {

    Manager manager;

    @FXML
    private Button addButton;

    @FXML
    private VBox pane;

    public void deleteKeys(List keysToDelete)
    {
        for (Object key: keysToDelete) {
            try {
                if (key instanceof Key.KeyNode && key != null) {
                    this.pane.getChildren().remove(((KeyNode) key).getForm());
                }
            }
            catch (Exception ex)
            {
                System.out.println(String.format("[Error][Home][Delete Key]: %s", ex.getMessage()));
            }
        }
    }

    public void deleteAction()
    {
        this.manager.deleteKeys();
    }

    public void addKey()
    {
        //HBox newKey = (HBox) pane.getChildren().get(0);
        //pane.getChildren().add();

        /*
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("keyTemplate.fxml"));
            HBox newKey = loader.load();
        }
        catch (Exception ex){
            System.out.println("[System][Home] Failed to load Key Template!");
            ex.printStackTrace();
        }

        try {

        }
        catch (Exception ex){
            System.out.println("[System][Home] Failed to add key!");
            ex.printStackTrace();
        }
*/
        try {
            Key.KeyNode newKey = new Key.KeyNode();
            newKey.setManager(this.manager);
            this.pane.getChildren().add(newKey.getForm());
        }
        catch (Exception ex){
            System.out.println("[System][Home] Failed to load Key Template!");
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        System.out.println("[System][Home] Checking form...");
        if(true)
        {
            System.out.println("[Load-> Home Controller] -> All elements are initialized!");
        }
        else
        {
            System.out.println("[Warning][Load-> Home Controller] -> Some elements are not initialized!");
        }
        System.out.println("[System] Loading data...");
        checkInit();
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

    public void checkInit()
    {
        if(addButton == null)
        {
            System.out.println("[System][Home][Controller] Add Button is null");
        }

        if(pane == null)
        {
            System.out.println("[System][Home][Controller] Pane is null");
        }
    }
}
