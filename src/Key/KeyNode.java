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

    public void delete()
    {
        //this.form.getParent().getchi
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
        this.controller.setDataToKeyForm(this.info, this.username, this.password);
    }

    public void sendDataToFormPrototype()
    {
        this.controller.setDataToKeyForm(this.info, this.username, this.password);
    }

    public void getDataFromForm()
    {
        this.controller.getDataFromForm();
        System.out.println("[Data Flow][Key][Sent] -------- Begin Journey ---------");
        this.manager.sendDataToDatabase(this);
    }

    public void getDataFromFormPrototype()
    {
        this.controller.getDataFromFormPrototype();
        System.out.println("[Data Flow][Key][Sent] -------- Begin Journey ---------");
        this.manager.sendDataToDatabase(this);
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

    public void testInput()
    {
        System.out.println("[System][Key] Info: " + this.info);
        System.out.println("[System][Key] Username: " + this.username);
        System.out.println("[System][Key] Password: " + this.password);
    }
}
