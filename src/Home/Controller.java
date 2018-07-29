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
import java.security.Key;
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

    public List<KeyNode> keys;

    public void deleteKeys(List keysToDelete)
    {
        for (Object key: keysToDelete) {
            try {
                if (key instanceof KeyNode && key != null)
                {
                    this.pane.getChildren().remove(((KeyNode) key).getForm());
                    this.keys.remove(((KeyNode) key));
                }
            }
            catch (Exception ex)
            {
                System.out.println(String.format("[Error][Home][Delete Key]: %s", ex.getMessage()));
            }
        }
    }

    public void saveAction()
    {
        this.manager.save();
        for(KeyNode key : keys)
        {
            if(key.isUnsaved())
            {
                String color = "89c4f4";
                key.setStateForm("normal");
            }
        }
    }

    public void deleteAction()
    {
        this.manager.deleteKeys();
    }

    public void addKey()
    {
        try {
            KeyNode newKey = new KeyNode();
            newKey.setManager(this.manager);

            this.pane.getChildren().add(newKey.getForm());
            this.keys.add(newKey);
        }
        catch (Exception ex){
            System.out.println("[System][Home] Failed to load Key Template!");
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        this.keys = new ArrayList<KeyNode>();

        System.out.println("[System][Home] Checking form...");

        if(checkInit())
        {
            System.out.println("[Load-> Home Controller] -> All elements are initialized!");
        }
        else
        {
            System.out.println("[Warning][Load-> Home Controller] -> Some elements are not initialized!");
        }
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

    public boolean checkInit()
    {
        if(addButton == null)
        {
            System.out.println("[System][Home][Controller] Add Button is null");
            return false;
        }

        if(pane == null)
        {
            System.out.println("[System][Home][Controller] Pane is null");
            return false;
        }

        return true;
    }

    public void fetchDataFromDatabase()
    {
        System.out.println("[System][Home][Controller] Loading data from database!");

        if(this.manager == null)
        {
            System.out.println("[Warning][Home] Manager is null");
            return;
        }

        for(KeyNode obj : this.manager.getKeys())
        {
            if (obj != null)
            {
                obj.setManager(this.manager);
               
                this.pane.getChildren().add(obj.getForm());
                this.keys.add(obj);
            }
        }
    }
}
