package Manager;

import Api.Crypt;
import Key.KeyNode;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Manager {

    private int testManager;
    private Stage stage;
    private User.UserNode user;

    private Login.Builder loginBuilder;
    private Login.Controller loginControl;

    private Home.Builder homeBuilder;
    private Home.Controller homeControl;


    private Database.XMLStore db;

    private List keysToDelete;
    public List<KeyNode> keys;

    private Crypt crypt;

    public Manager()
    {
        this.keysToDelete = new ArrayList();
    }

    public Manager(int x) throws Exception
    {
        testManager = x;
        this.crypt = new Crypt();

        this.db = new Database.XMLStore(this);

        // LOGIN
        loginBuilder = new Login.Builder();
        loginControl = loginBuilder.getController();

        // HOME
        homeBuilder = new Home.Builder();
        homeControl = homeBuilder.getController();

        // Init Controllers's settings
        initializeControllers();

        // User
        this.user = new User.UserNode();
        this.keys = new ArrayList();
        this.keysToDelete = new ArrayList();

    }

    public void changeScene(String sceneName)
    {
        switch (sceneName)
        {
            case "login":
                try {
                    this.stage.setScene(this.loginBuilder.buildScene());
                } catch (Exception ex)
                {
                    System.out.println("[Error][Manager][Change Scene] Login Scene is not working !");
                    ex.printStackTrace();
                }

                break;
            case "home":
                try {
                    // Set the key
                    this.crypt.setEncryptionKey(this.user.getPassword());

                    // Get data from database
                    this.keys = this.db.getKeysFromDoc();

                    // Fetch the data to the form
                    this.homeControl.fetchDataFromDatabase();

                    this.stage.setScene(this.homeBuilder.buildScene());
                } catch (Exception ex)
                {
                    System.out.println("[Error][Manager][Change Scene] Home Scene is not working !");
                    ex.printStackTrace();
                }
                break;
            case "home_New_User":
                try {
                    this.stage.setScene(this.homeBuilder.buildScene());
                } catch (Exception ex)
                {
                    System.out.println("[Error][Manager][Change Scene] Home New User Scene is not working !");
                    ex.printStackTrace();
                }
                break;
            case "settings":
                break;
            case "edit":
                break;
            default:
                System.out.println("[Warning][Manager][Change Scene] Value is not valid");
        }
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
        if(this.stage == null)
        {
            System.out.println("[Warning] Manager Stage is null!");
        }
    }

    public void testManager()
    {
        System.out.println(String.format("[Test][Manager] Manager number: %d" , testManager));
    }

    private void initializeControllers()
    {
        if(this.loginControl != null)
        {
            // Add the reference of the manager to the login controller
            this.loginControl.setManager(this);
        }
        else {
            System.out.println("[Warning][Manager][Init] Login Controller is null!");
        }
        if(this.homeControl != null)
        {
            // Add the reference of the manager to the home controller
            this.homeControl.setManager(this);
        }
        else {
            System.out.println("[Warning][Manager][Init] Home Controller is null!");
        }
    }


    // +---------------------------------- Login & Register System -----------------------------------------+

    public boolean login(String username, String password)
    {
        try {
            String path = String.format("resources\\%s.xml", username);
            Path test = Paths.get(path);

            if(Files.exists(test))
            {
                this.db.setPathToCurrentDatabase(path);
            }
            else
            {
                System.out.println("[Warning][Manager][Login]: The database for this user do not exist!");
            }

            String nameProfile = this.db.getProfileSetting("nameProfile");
            String passwordProfile = this.db.getProfileSetting("passwordProfile");

            if(this.user.auth( username, password, nameProfile, passwordProfile)) {

                return true;
            }
        }
        catch (Exception ex)
        {
            System.out.println("[Error][Manager][Login]: ");
            System.out.println(ex.getMessage());
        }

        return false;
    }

    public boolean register(String username, String password)
    {
        // Create a new database
        String path = String.format("resources\\%s.xml", username);
        Path test = Paths.get(path);

        if(Files.exists(test))
        {
            return false;
        }

        this.db.setPathToCurrentDatabase(path);

        this.user.registerUser(username, password);

        return true;
    }


    // +-------------------------------------------- Save System -------------------------------------------------+

    public void save()
    {
        this.backup();
        this.keys = this.homeControl.keys;
        this.createNewDoc();
    }

    private void backup()
    {

        try {
            // Open the current database
            File input = new File(this.db.getPathToCurrentDatabase());

            if(!input.exists())
            {
                return;
            }

            // Get the time
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd__HH_mm_ss");
            String formatDateTime = time.format(formatter);

            // Format the name of the backup file
            String nameFileOutput = String.format( "%s_%s", user.getUsername(), formatDateTime);
            System.out.println("[Backup][Manager][File Open & Move] New file name: " + nameFileOutput);

            //File output = new File(String.format("resources\\Backup\\%s.xml", nameFileOutput));
            //System.out.println("[Backup][Manager][File Open & Move] New path to file: " + output.getAbsolutePath());

            // Get the path
            String outputPath = String.format("resources\\Backup\\%s.xml", nameFileOutput);

            // Set Up the move functions
            FileSystem fileSys = FileSystems.getDefault();
            Path srcPath = fileSys.getPath(input.getAbsolutePath());
            Path destPath = fileSys.getPath(outputPath);


            // Move file to Backup folder
            Files.move(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception ex)
        {
            System.out.println("[Error][Backup][Manager][File Open & Move] " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<KeyNode> getKeys()
    {
        if(this.keys.isEmpty())
        {
            System.out.println("[Warning][Manager][Get Keys]: The list with the keys is empty");
        }
        return this.keys;
    }

    public void createNewDoc()
    {
        /*

            This function push the data to a new database for the current user

         */
        try {

            // Set the path
            String path = String.format("resources//%s.xml", this.user.getUsername());
            this.db.setPathToCurrentDatabase(path);

            // Create the database
            this.db.CreateNewDoc();

            // Set the key
            this.crypt.setEncryptionKey(this.user.getPassword());

            this.db.addProfileSetting("nameProfile", this.encrypt(this.user.getUsername()));
            this.db.addProfileSetting("passwordProfile", this.encrypt(this.user.getPassword()));

            for (KeyNode key : this.keys) {
                try {
                    if (key != null) {
                        this.db.addKey(this.encrypt(key.getUsername()), this.encrypt(key.getPassword()), this.encrypt(key.getInfo()));
                    }
                } catch (Exception ex) {
                    System.out.println(String.format("[Error][Manager][Add Key]: %s", ex.getMessage()));
                }
            }

            // Build the file
            this.db.buildDoc();

            // Write on disk & console (for testing)
            this.db.output();
        }
        catch (Exception ex)
        {
            System.out.println(String.format("[Error][Manager][Create Doc]: %s", ex.getMessage()));
        }
    }

    // +----------------------------------------- Delete System ------------------------------------------------+
    public void addKeyToDelete(Key.KeyNode key)
    {
        try {
            this.keysToDelete.add(key);
        }
        catch (Exception ex)
        {
           System.out.println(String.format("[Error][Manager][Add Key To Delete]: %s", ex.getMessage()));
           if(key == null)
                {
                    System.out.println("[Error][Manager][Add Key To Delete][NUll] Key is null");
                }
           if(this.keysToDelete == null)
                {
                    System.out.println("[Error][Manager][Add Key To Delete][NUll] KeysToDelete is null");
                }

        }
    }

    public void deleteKeys()
    {
        try {
            if(this.keysToDelete.isEmpty())
                return;
            // this.createNewDoc();
            this.homeControl.deleteKeys(this.keysToDelete);
            this.keysToDelete.clear();
            this.save();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    // +---------------------------------------- Encryption -------------------------------------+

    public String encrypt(String value)
    {
        /*
        TO-DO: implement
         */
        try {
            value = this.crypt.encrypt(value);
        }
        catch (Exception ex)
        {

        }

        return value;
    }

    public String decrypt(String value)
    {
        /*
        TO-DO: implement
         */
        try {
            value = this.crypt.decrypt(value);
        }
        catch (Exception ex)
        {

        }

        return value;
    }

}
