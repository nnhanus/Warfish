package Modele;

import static java.lang.Math.*;

/**
 * Nuisible
 * Classe gérant les actions, affectations, et le comportement des nuisibles
 */
public class Nuisible extends Thread{
    private int x, y;
    private int dir; //la direction du lapin en degrés (?)
    private boolean enfuite = false;
    private Case target = null;

    public Nuisible(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.getTarget();
        this.start(); //??
    }

    /*
    Getters
     */
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public boolean getStatus(){
        return this.enfuite;
    }

    /**
     * avanceNuisible
     * Fait avancer le lapin de distance dans la direction dir
     * @param distance la distance parcourue par le lapin
     * Je propose qu'on utilise l'axe y habituel, pas celui des info-graphistes (il va quand même falloir le prendre en compte au moment de l'affichage)
     */
    public void avanceNuisible(int distance){
        this.x += distance*cos(dir);
        this.y += distance*sin(dir);
    }

    /**
     * tourneNuisible
     * Fait se tourner le lapin de dir degrés
     * @param dir la valeur en degré de combien le lapin se tourne
     */
    public void tourneNuisible(int dir){
        this.dir = (this.dir + dir)%360;
    }

    /**
     * getRelevantCase
     * @return la case sur laquelle le lapin se trouve
     */
    public Case getRelevantCase(){
        return GrilleMod.plateau.get(this.x%GrilleMod.TAILLE_CASE).get(this.y%GrilleMod.TAILLE_CASE);
    }

    /**
     * mangeFleur
     * fait manger une fleur au lapin
     * Précondition : On doit avoir vérifier que la case sur laquelle se trouve le lapin contient bien une fleur
     */
    public void mangeFleur(){
        this.getRelevantCase().removeFleur();
    }

    /**
     * setenFuite
     * Fait passer le lapin en état de fuite
     */
    public void setenFuite(){
        this.enfuite = true;
    }

    /**
     * getTarget
     * donne une cible au lapin (la fleur la plus proche)
     * très brute force, on pourrait faire plus intelligemment
     */
    public void getTarget(){
        Case currentCase = this.getRelevantCase();
        double minDistance = GrilleMod.LARGEUR_GRILLE*GrilleMod.LARGEUR_GRILLE + GrilleMod.HAUTEUR_GRILLE*GrilleMod.HAUTEUR_GRILLE;
        Case closestFlower = null;
        for(int i = 0; i < GrilleMod.LARGEUR_GRILLE; i++){
            for(int j = 0; j < GrilleMod.HAUTEUR_GRILLE; j++){
                if(GrilleMod.getCase(i,j).contientFleur()){
                    double currentDistance = (currentCase.x - i)*(currentCase.x - i)+(currentCase.y - j)*(currentCase.y - j);
                    if(currentDistance < minDistance){
                        minDistance = currentDistance;
                        closestFlower = GrilleMod.plateau.get(i).get(j);
                    }
                }
            }
        }
        this.target = closestFlower;
    }

    /**
     *run
     * thread décrivant le comportement du lapin : il tourne jusqu'à être aligné avec une fleur, après quoi il court en ligne droite
     */
    @Override
    public void run(){
       while(!enfuite){
           if(this.getRelevantCase().contientFleur()){ //si le lapin est sur une fleur il la mange
               mangeFleur();
           }
           int tanga =(this.target.y-this.y)/(this.target.x - this.x);
            if(tanga == tan(dir)){ //si le lapin est en face de la fleur la plus proche il avance en ligne droite
                this.avanceNuisible(100);
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                if(tan(dir) < tanga){ //cas où le lapin doit se tourner
                    this.tourneNuisible(1);
                }else{
                    this.tourneNuisible(-1);
                }
                try {
                    sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        currentThread().interrupt();
    }
}
