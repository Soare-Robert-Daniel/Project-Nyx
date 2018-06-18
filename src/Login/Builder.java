package Login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
     This class will build the Login Scene
     Any change in the way that scene is build should be done here
 */
public class Builder {
    protected int width;
    protected int height;


    public Builder()
    {
        height = 600;
        width = 800;
    }

    public Builder(int width, int height)
    {
        this.height = height;
        this.width = width;
    }

    public Scene buildScene() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        return new Scene(root, width, height);
    }

}
