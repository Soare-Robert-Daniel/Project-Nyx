package User;


import java.util.List;

public class UserNode {

    private String username;





    public void registerUser(String user, String password)
    {
        user = Api.Crypto.on(user);
        password = Api.Crypto.on(password);

        System.out.println("[User] -> Send data to datebase!");
        System.out.println("[User][Data] Username:" +  user);
        System.out.println("[User][Data] Username:" +  password);
    }



    public void getUserFromDatabase()
    {



    }



    public void setUserSetting(String setting, boolean value)
    {



    }

    public void deleteUser()
    {



    }


    public String getUsername()

    {

        return username;

    }


    public void setUsername(String username)

    {

        if(username != null)

            this.username = username;

    }

    public void deleteKeysFromDatabase(List keysToDelete)
    {
        System.out.println(String.format("[User][Database] Delete keys | Total keys: %d",keysToDelete.size()));
    }

    public void setKeyToDelete()
    {

    }
}