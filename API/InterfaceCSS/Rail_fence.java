public class Rail_fence {
    public static String encrypt(String pass, int key) {
        String nwpass;
        int nr_train = 2*key - 2;
        String[] trains = new String[nr_train];

        for(int i=0; i < nr_train; i++) {
            trains[i] = String.valueOf(pass.charAt(i));
            try {
                for (int j = i + nr_train; j < pass.length(); j += nr_train) {
                    trains[i] += pass.charAt(j);
                }
            } catch (NullPointerException e) {
                System.out.println("Pointer " + e.getMessage());
            }
        }

        nwpass = trains[0];
        for(int i=1; i < key - 1; i++){
            int j;
            for(j=0; j < trains[nr_train - i].length(); j++)
                nwpass = nwpass + trains[i].charAt(j) +  trains[nr_train - i].charAt(j);
            if(trains[i].length() !=  trains[nr_train - i].length())
                nwpass = nwpass + trains[i].charAt(j);
        }
        nwpass = nwpass + trains[key - 1];
        return nwpass;
    }
    public static String decrypt(String pass, int key) {
        String nwpass = new String();
        int nr_train = 2*key - 2, nr_character = pass.length() / nr_train;
        int rest = pass.length() % nr_train, ok = 0;
        String[] trains = new String[key];
        String[] final_trains = new String[nr_train];

        trains[0] = pass.substring(0,nr_character);
        if(rest != 0) {
            trains[0] += pass.charAt(nr_character);
            ok++;
            rest--;
        }
        for(int i=1; i < key - 1; i++){
            trains[i] = pass.substring(nr_character + 2 * (i-1) * nr_character + ok, nr_character + 2 * i * nr_character + ok);
            if(rest != 0){
                trains[i] += pass.charAt(nr_character + 2 * i * nr_character + ok);
                ok++;
                rest--;
            }
            for(int j=0; j < trains[i].length(); j++){
                if(j == 0)
                    final_trains[i] = String.valueOf(trains[i].charAt(j));
                else if(j == 1)
                    final_trains[nr_train - i] = String.valueOf(trains[i].charAt(j));
                else if(j % 2 == 0)
                    final_trains[i] += trains[i].charAt(j);
                else
                    final_trains[nr_train - i] += trains[i].charAt(j);
            }
        }
        trains[key - 1] = pass.substring(nr_character + 2 * (key-2) * nr_character + ok);
        if(rest != 0)
            ok++;
        final_trains[0] = trains[0];
        final_trains[key - 1] = trains[key - 1];

        for(int i=0; i< nr_character; i++){
            for(int j=0; j < nr_train; j++){
                if(i == 0 && j==0)
                    nwpass = String.valueOf(trains[0].charAt(0));
                else
                    nwpass = nwpass + final_trains[j].charAt(i);
            }
        }
        for(int i=0; i < ok; i++)
            nwpass = nwpass + final_trains[i].charAt(final_trains[i].length() - 1);

        return nwpass;
    }
}
