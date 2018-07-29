package User;


import java.util.List;
import Api.Crypt;

public class UserNode {

    private String username;
    private String password;

    public UserNode()
    {
        this.username = new String("Default");
        this.password = new String("Default");
    }

    public void registerUser(String user, String password)
    {
        this.setUsername(user);
        this.setPassword(password);
    }

    public boolean auth(String user, String password, String userDatabase, String passwordDatabase)
    {

       System.out.println(String.format( "[User][Data][Auth] Data: %s - %s : %s - %s" , user, password, userDatabase, passwordDatabase));


       if(
               (user.equals(userDatabase)) &&
                       (password.equals(passwordDatabase))
               ) {

           this.setUsername(user);
           this.setPassword(password);
           return true;
       }
       else
            return false;
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

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        if(password != null)
            this.password = password;
    }

}