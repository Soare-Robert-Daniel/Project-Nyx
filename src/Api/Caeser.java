package Api;

public class Caeser {
    public static String encrypt(String pass, int key) {
        char[] nwpass = pass.toCharArray();
        int character;
        for(int i=0; i < pass.length(); i++) {
            character = (int)pass.charAt(i) + key;
            while (character > 126)
                character -= 94;
            nwpass[i] = (char)character;
        }
        return String.valueOf(nwpass);
    }
    public static String decrypt(String pass, int key) {
        char[] nwpass = pass.toCharArray();
        int character;
        for(int i=0; i < pass.length(); i++) {
            character = (int)pass.charAt(i) - key;
            while (character < 33)
                character += 94;
            nwpass[i] = (char)character;
        }
        return String.valueOf(nwpass);
    }
}
