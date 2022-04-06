package Control;

import Modele.*;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static Modele.GrilleMod.*;

/**
 * Controller
 * Le controleur, lie les boutons de la vue aux actions du modèle
 */
public class Controller implements ActionListener, MouseListener {

    public View view;

    public Controller(View view) {
        this.view = view;
        //Ajout des listener aux boutons
        view.b1.addActionListener(this);
        view.b2.addActionListener(this);
        view.b3.addActionListener(this);
        view.b4.addActionListener(this);
        view.b5.addActionListener(this);
        view.b6.addActionListener(this);
        View.b7.addActionListener(this);
        View.b8.addActionListener(this);
        view.terrain.addMouseListener(this);
        view.bfr.addActionListener(this);
        view.bfv.addActionListener(this);
        view.bfj.addActionListener(this);
        view.bpr.addActionListener(this);
        view.bpv.addActionListener(this);
        view.bpj.addActionListener(this);
        view.prod.addActionListener(this);
        view.def.addActionListener(this);
    }

    /**
     * Achat d'une graine
     * @param i l'indice de la graine à acheter, cf GrilleMod
     * @param j le jardinier qui achète la graine
     */
    protected void acheteGrain(int i, Jardinier j){
        /**Conditions: avoir assez d'argent et être au bâtiment principal*/
        if(BatPrincipal.getTirelire() >= BatPrincipal.PRIX_GRAINE
                && GrilleMod.getSQDist(j.getX(), j.getY(), GrilleMod.getBatX(), GrilleMod.getBatY()) <= GrilleMod.getBatPrincipal().getRange()) {
            //Achat de la graine et ajout à l'inventaire
            j.acheterGraine(i);
            BatPrincipal.acheterGraine(i);
            //mise a jour de l'affichage
            View.updateSolde();
            View.updateInv();
        }
    }

    /**
     * Plantation d'une fleur
     * @param i le type de fleur à planter
     * @param j le jardinier qui plante une fleur
     */
    protected void planterGraine(int i, Jardinier j){
        /**Conditions : il n'y a pas d'éléments à cet endroit sur le terrain
         * et on a une graine de la bonne couleur*/
        if (!(GrilleMod.isNotValidPosition(j.getX(), j.getY())) && j.getInventaire()[i] > 0){
            //plantation de la fleur
            j.planteFleur(i);
            //mise à jour de l'affichage
            View.updateInv();
            VueFleur.updateFleur();
        }
    }

    /**
     * Liaison des actions aux boutons
     * @param e l'action event performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //le jardinier sélectionné
        Jardinier j = (Jardinier) GrilleMod.getSelectedJard();

        /**Récolter une fleur*/
        if (e.getSource() == view.b1) {
            //récupération de la fleur la plus proche
            Fleur f = j.plusProcheFleur();
            //récuparation de sa disttance au jardinier
            int dist = j.getSQDistFrom(f.getX(), f.getY());
            /**Conditions: la fleur peut être ramassée et la distance est suffisamment petite*/
            if (f.isPickable() && dist < 3000) {
                j.recolterRessource(f);//récolte de la fleur
                View.updateInv(); //mise à jour de la vue
            }
        }

        /**Effrayer un nuisible*/
        if (e.getSource() == view.b2) {
            j.effrayer();
        }

        /**Désherber une fleur morte*/
        if (e.getSource() == view.b3) {
            //récupération de la fleur la plus proche
            Fleur f = j.plusProcheFleur();
            //récuparation de sa distance au jardinier
            int dist = j.getSQDistFrom(f.getX(), f.getY());
            /**Conditions: la fleur est suffisamment proche et elle est morte*/
            if (dist < 30000 && (f).getIsDead()) {
                //Désherbage
                j.desherber(f);
            }
        }

        /**Affiche le menu de plantation*/
        if(e.getSource() == view.b4){
            //Ferme les deux autres menus qui peuvent se trouver au même endroit
            view.graines.setVisible(false);
            view.buildings.setVisible(false);
            //Ouvre le menu demandé
            view.planter.setVisible(!(view.planter.isVisible()));
        }

        /**Confectionne un bouquet*/
        if(e.getSource() == view.b5){
            if(j.getInventaire()[GrilleMod.indiceFleurR] >= 3){
                j.confectionneBouquet();
                View.updateInv();
            }
        }

        /**Vend un bouquet*/
        if(e.getSource() == view.b6){ //Bouquet
            if(j.getInventaire()[GrilleMod.indiceBouquet] > 0 && GrilleMod.getSQDist(j.getX(), j.getY(), GrilleMod.getBatX(), GrilleMod.getBatY()) <= GrilleMod.getBatPrincipal().getRange()){
                j.vendBouquet();
                View.updateSolde();
                View.updateInv();
            }
        }

        /**Ouvre la boutique de graines*/
        if(e.getSource() == View.b7){
            //Ferme les deux autres menus qui peuvent se trouver au même endroit
            view.planter.setVisible(false);
            view.buildings.setVisible(false);
            //Ouvre le menu demandé
            view.graines.setVisible(!(view.graines.isVisible()));
        }

        /**Ouvre la boutique de bâtiments*/
        if(e.getSource() == View.b8){
            //Ferme les deux autres menus qui peuvent se trouver au même endroit
            view.planter.setVisible(false);
            view.graines.setVisible(false);
            //Ouvre le menu demandé
            view.buildings.setVisible(!(view.buildings.isVisible()));
        }

        /**boutons de la boutique de graines**/
        if (e.getSource() == view.bfr) {//Achat d'une graine rouge
            acheteGrain(indiceGraineR, j);
        }
        if (e.getSource() == view.bfj) { //Achat d'une graine jaune
            acheteGrain(indiceGraineJ, j);
        }
        if (e.getSource() == view.bfv) { //Achat d'une graine verte
            acheteGrain(indiceGraineV, j);
        }

        /**boutons de plantations**/
        if (e.getSource() == view.bpr){ //Plantation d'une fleur rouge
            planterGraine(indiceGraineR, j);
        }
        if (e.getSource() == view.bpj){ //Plantation d'une fleur jaune
            planterGraine(indiceGraineJ, j);
        }
        if (e.getSource() == view.bpv){ //Plantation d'une fleur verte
            planterGraine(indiceGraineV, j);
        }

        /**boutons de la boutique de batiments**/
        if (e.getSource() == view.prod){ //Achat d'un bat de production
            /**Conditions: avoir assez d'argent et la position doit être libre*/
            if(BatPrincipal.getTirelire() >= BatPrincipal.PRIX_PRODUCTION && !GrilleMod.isNotValidPosition(j.getX(), j.getY())){
                //Construction et achat
                j.construitBatProduction();
                //mise à jour de la vue
                View.updateSolde();
            }
        }
        if (e.getSource() == view.def){ //Achat d'un bat de defense
            /**Conditions: avoir assez d'argent et la position doit être libre*/
            if(BatPrincipal.getTirelire() >= BatPrincipal.PRIX_DEFENSE && !GrilleMod.isNotValidPosition(j.getX(), j.getY())){
                //Construction et achat
                j.construitBatDefense();
                //mise à jour de la vue
                View.updateSolde();
            }
        }
    }

    /**Gestion des clics
     * Clic gauche = sélection d'un jardinier
     * Clic droit = déplecement du jardinier sélectionné
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        //Coordonnées du clic
        double mouseX = e.getPoint().getX();
        double mouseY = e.getPoint().getY();
        //Vérification clique gauche
        if (SwingUtilities.isLeftMouseButton(e)) {
            //on parcourt les jardiniers pour trouver le plus proche
            for (Jardinier u : GrilleMod.getJardiniers()) {
                //s'il est assez proche on le sélectionne
                if (GrilleMod.getSQDist((int) mouseX, (int) mouseY, u.getX(), u.getY()) < 20) {
                    GrilleMod.setSelectedJard(u);
                }
            }
        } else {
            //Déplacement du jardinier sélectionné vers la position cliquée
            GrilleMod.getSelectedJard().setMoving((int) mouseX, (int) mouseY);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}