package Api;

public class Crypt {

    private String encryptionKey;

    public Crypt()
    {
        this.encryptionKey = new String("default");
    }

    public Crypt(String encryptionKey)
    {
        this.encryptionKey = encryptionKey;
    }

    public String encrypt(String value)
    {
        /*
        TO-DO: implement
         */
        return value;
    }

    public String decrypt(String value)
    {
        /*
        TO-DO: implement
         */
        return value;
    }

    public void setEncryptionKey(String encryptionKey) {
        if(encryptionKey != null)
            this.encryptionKey = encryptionKey;
    }

    public String getEncryptionKey()
    {
        return this.encryptionKey;
    }
}
