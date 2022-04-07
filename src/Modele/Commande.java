package Modele;

import View.VueCommandes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Commande{
    public final static int MAX_COMMANDE = 4; //nombre max de commande
    private final static int LIFESPAN = 20;
    private final static int SPAWN_DELAY = 5;
    private final int[] value = {(int) (Math.random()*2),(int) (Math.random()*2),(int) (Math.random()*2)};

    public Commande(){
        new Timer(1000*LIFESPAN, expiration).start();
    }

    public Commande(int i){
        System.out.println("Init Commandes");
    }

    ActionListener expiration = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            expired();
        }
    };

    static ActionListener genCommande = new ActionListener(){
        public void actionPerformed(ActionEvent evt) {
            GrilleMod.addCommande();
            VueCommandes.updateCommandes();
            System.out.println("Added commande");
        }
    };

    static{
        new Timer(1000*SPAWN_DELAY, genCommande).start();
    }

    public void expired(){
        GrilleMod.removeCommande(this);
        VueCommandes.updateCommandes();
        System.out.println("Expired");
    }

    public int[] getValue(){
        return this.value;
    }
    /**
     * Vérifie que le jardinier possède bien le bouquet demandé
     * @return
     */
    public boolean hasValidBouquet(){
        return GrilleMod.getBouquets()[Bouquet.getType(value)] > 0;
    }
}
