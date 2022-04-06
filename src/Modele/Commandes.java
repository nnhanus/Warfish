package Modele;

public class Commandes extends Thread{
    private static int MAX_COMMANDE = 3;
    //TIMER
    //static le type du bouquet
    public enum type_bouquet {VVV, JJJ, RRR, VJR, VJJ, VRR, JRR, VVR, JJR, VVJ}

    public Commandes(){
        int type = 1 + (int) (Math.random()*2);

    }

    @Override
    public void run(){

    }
}
