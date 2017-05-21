package main;

/**
 * Created by hagoterio on 21/05/17.
 */
public class Util {

    public static String getTime(long milliseconds){
        int seconds = (int) (milliseconds / 1000) % 60 ;
        int minutes = (int) ((milliseconds / (1000*60)) % 60);
        int hours   = (int) ((milliseconds / (1000*60*60)));
        return hours+" heures, "+minutes+" minutes, "+seconds+" secondes, "+milliseconds%60+" millisecondes";
    }
}
