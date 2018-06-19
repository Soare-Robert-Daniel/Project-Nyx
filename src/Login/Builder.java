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
    protected boolean active;

    FXMLLoader loader;

    private void init() throws Exception
    {
        this.loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        this.loader.load();
    }
    public Builder() throws Exception
    {
        height = 600;
        width = 800;
        active = true;
        init();
    }

    public Builder(int width, int height) throws Exception
    {
        this.height = height;
        this.width = width;
        this.active = true;
        init();
    }

    public Builder(int width, int height, boolean active) throws Exception
    {
        this.height = height;
        this.width = width;
        this.active = active;
        init();
    }

    public Scene buildScene() throws Exception
    {
        Parent root = this.loader.getRoot();

        return new Scene(root, width, height);
    }

    public Controller getController() throws Exception
    {
        // this.loader.load(getClass().getResource("Login.fxml"));
        return (Controller) this.loader.getController();
    }

}
