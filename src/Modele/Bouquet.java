package Modele;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Classe réservée au bouquet en train d'être généré à l'aide de l'interface
 */
public abstract class Bouquet {
    private static int[] bouquet = {-1, -1, -1};
    private static final HashMap<Integer, Integer> typeMap =new HashMap<>() {{
        put(222, 0);//JJJ
        put(221, 1);//JJR (mais à l'envers)
        put(211, 2);//JRR
        put(111, 3);//RRR
        put(322, 4);//VJJ
        put(321, 5);//VJR
        put(311, 6);//VRR
        put(332, 7);//VVJ
        put(331, 8);//VVR
        put(333, 9);//VVV
    }};

    public static int encodeBouquet(int[] b){
        Arrays.sort(b);
        return (b[0] + 1) + 10 * (b[1] + 1) + 100 * (b[2] + 1);
    }
    public static int getType(int[] b){
        return typeMap.get(encodeBouquet(b));
    }

    public static void clearBouquet(){
        Arrays.fill(bouquet, -1);
    }

    public static int[] getBouquet(){return bouquet;}

    public static void finishBouquet(){
        GrilleMod.addBouquet(Bouquet.getType(bouquet));
        clearBouquet();
    }

    public static void cancelBouquet(){
        for(int n : bouquet){
            if(n > -1){
                ((Jardinier) GrilleMod.getSelectedUnite()).getInventaire()[n]++;
            }
        }
        clearBouquet();
    }

    public static boolean isReady(){
        for(int i = 0; i < 3; i++){
            if(bouquet[i] == -1){
                return false;
            }
        }
        return true;
    }

    public static void addFlower(int f){
        for(int i = 0; i < 3; i++){
            if(bouquet[i] == -1){
                bouquet[i] = f;
                break;
            }
        }
    }
}
