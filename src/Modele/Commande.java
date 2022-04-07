package Modele;

import View.VueCommandes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Commande{
    public final static int MAX_COMMANDE = 4; //nombre max de commande
    private final static int LIFESPAN = 15; //Durée de vie
    private final static int SPAWN_DELAY = 10; //délai d'apparition
    private final int[] value = {(int) (Math.random()*2),(int) (Math.random()*2),(int) (Math.random()*2)}; //commande générée aléatoirement

    /**
     * Constructeur
     * Démarre le timer d'expiration
     */
    public Commande(){
        new Timer(1000*LIFESPAN, expiration).start();
    }

    public Commande(int i){
    }

    /**
     * Met fin à une commande
     */
    ActionListener expiration = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            expired();
        }
    };

    /**
     * genCommande
     * Génère une commande
     */
    static ActionListener genCommande = new ActionListener(){
        public void actionPerformed(ActionEvent evt) {
            GrilleMod.addCommande();
            VueCommandes.updateCommandes();
        }
    };

    /**
     * Déclenche l'apparition d'une commande à intervalle régulier
     */
    static{
        new Timer(1000*SPAWN_DELAY, genCommande).start();
    }

    /**
     * expired
     * Met fin à la commande
     */
    public void expired(){
        GrilleMod.removeCommande(this);
        VueCommandes.updateCommandes();
    }

    /**
     * getValue
     * @return le contenu de la commande
     */
    public int[] getValue(){
        return this.value;
    }
    /**
     * hasValidBouquet
     * Vérifie que le jardinier possède bien le bouquet demandé
     * @return true si le jardinier possède le bouquet, false sinon
     */
    public boolean hasValidBouquet(){
        return GrilleMod.getBouquets()[Bouquet.getType(value)] > 0;
    }
}
