package Key;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class KeyNode {
    private String info;
    private String username;
    private String password;

    private Key.Controller controller;
    private HBox form;

    public KeyNode()
    {
        info = "Empty";
        username = "Empty";
        password = "Empty";
        buildKey();
    }

    public KeyNode(String info, String username, String password)
    {
        this.info = info;
        this.username = username;
        this.password = password;
        buildKey();
    }

    public String getInfo()
    {
        return info;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setInfo(String info)
    {
        if(info != null)
            this.info = info;
    }

    public void setUsername(String username)
    {
        if(username != null)
            this.username = username;
    }

    public void setPassword(String password)
    {
        if(password != null)
            this.password = password;
    }

    public void sendDataToForm()
    {
        this.controller.setDataToKeyForm(this.info, this.username, this.password);
    }

    public void getDataFromForm()
    {
        this.controller.getDataFromForm();
    }

    private void buildKey()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("keyTemplate.fxml"));
            this.form = loader.load();
            this.controller = loader.getController();
        }
        catch (Exception ex){
            System.out.println("[System][Key] Failed to load Key Template!");
            ex.printStackTrace();
        }

        if(this.controller != null)
        {
            this.controller.setNode(this);
        }
        else
            System.out.println("[Warning][Key] Controller is null!");
    }
}
