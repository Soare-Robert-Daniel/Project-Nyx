package Login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    public void loginUser(MouseEvent event){
        System.out.println("You clicked me!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resource) {

    }

}
