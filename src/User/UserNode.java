package User;


public class UserNode {

    private String username;





    public void registerUser(String username, String password)
    {
        username = Api.Crypto.on(username);
        password = Api.Crypto.on(password);

        System.out.println("[User] -> Send data to datebase!");
        System.out.println("[User][Data] Username:" +  username);
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

}