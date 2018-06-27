package Api;

import java.util.Random;

public class Crypto {
    public static String on(String pass){
        return Caeser.encrypt(RailFance.encrypt(pass, 4), 25);
    }
    public static String on(String pass, int level){
        Random rand = new Random();
        String temp = pass;
        int n;
        for(int i=0; i < level; i++){
            n = rand.nextInt(6) + 2;
            temp = RailFance.encrypt(temp, n);
            System.out.println(temp);
            temp = ((char)n) + temp;
            System.out.println(n);
            n = rand.nextInt(50)+ 10;
            temp = Caeser.encrypt(temp, n);System.out.println(temp);
            temp = ((char)n) + temp;
            System.out.println(n);

        }
        temp = (char)level + temp;
        return temp;
    }

    public static String off(String pass){
        return Caeser.decrypt(RailFance.decrypt(pass, 4), 25);
    }
    public static String off(String pass, int level){
        String temp = pass;
        level = (int)pass.charAt(0);
        temp = temp.substring(1);
        int n;
        for(int i=0; i < level; i++) {
            n = (int)(temp.charAt(0));
            System.out.println(n);
            temp = Caeser.decrypt(temp.substring(1), n);
            System.out.println(temp);
            n = (int)(temp.charAt(0));
            System.out.println(n);
            temp = RailFance.decrypt(temp.substring(1), n);
            System.out.println(temp);
        }
        return temp;
    }
}
