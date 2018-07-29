package Key;


import Manager.Manager;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class KeyNode {

    private Manager manager;

    private String info;
    private String username;
    private String password;
    private boolean toDelete;
    private boolean unsaved;

    private Key.Controller controller;
    private HBox form;

    public KeyNode()
    {
        /*
        info = "Empty";
        username = "Empty";
        password = "Empty";
        */
        buildKey();

    }

    public KeyNode(String info, String username, String password)
    {
        this.info = info;
        this.username = username;
        this.password = password;
        buildKey();

    }

    public KeyNode(String info, String username, String password, boolean toDelete)
    {
        this.info = info;
        this.username = username;
        this.password = password;
        this.toDelete = toDelete;
        buildKey();

    }

    public void sendToDelete()
    {
        this.toDelete = true;
        this.manager.addKeyToDelete(this);
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

    public void setManager(Manager manager)
    {
        this.manager = manager;
    }

    public void setInfo(String info)
    {
        if(info != null)
        {
            if(this.info != info)
            {
                this.info = info;
                this.unsaved = true;
            }
        }
    }

    public void setUsername(String username)
    {
        if(username != null)
        {
            if(this.username != username)
            {
                this.username = username;
                this.unsaved = true;
            }
        }
    }

    public void setPassword(String password)
    {
        if(password != null)
        {
            if(this.password != password)
            {
                this.password = password;
                this.unsaved = true;
            }
        }
    }

    public void setToDelete(boolean toDelete)
    {
        this.toDelete = toDelete;
    }

    public boolean getToDelete()
    {
        return this.toDelete;
    }

    public HBox getForm()
    {
        return this.form;
    }

    public void sendDataToForm()
    {
        this.controller.setDataToForm(this.info, this.username, this.password);
    }

    private void buildKey()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("keyTemplate.fxml"));
            this.form = loader.load();
            this.controller = loader.getController();
            this.sendDataToForm();
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

        this.controller.showAction();
    }

    public boolean isUnsaved()
    {
        return this.unsaved;
    }


    public void setStateForm(String value)
    {
        this.controller.state(value);
    }

    public void testInput()
    {
        System.out.println("[System][Key] Info: " + this.info);
        System.out.println("[System][Key] Username: " + this.username);
        System.out.println("[System][Key] Password: " + this.password);
    }
}
