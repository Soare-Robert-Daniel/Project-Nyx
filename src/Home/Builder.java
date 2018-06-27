package Home;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Builder {
    protected int width;
    protected int height;
    protected boolean active;

    FXMLLoader loader;

    private void init() throws Exception
    {
        this.loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        this.loader.load();
    }
    public Builder() throws Exception
    {
        height = 450;
        width = 880;
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

    public Home.Controller getController() throws Exception
    {
        // this.loader.load(getClass().getResource("Login.fxml"));
        return (Home.Controller) this.loader.getController();
    }
}
