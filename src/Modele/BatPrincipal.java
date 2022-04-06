package Modele;

/**
 * Bâtiment Principal
 * Gère les ressources générales, et notamment l'argent/score
 */
public class BatPrincipal extends Building {
    public static final int PRINCIPAL_RANGE = 5000; //rayon d'action
    private static int tirelire = 200; //l'argent du joueur
    public static final int PRIX_PRODUCTION = 100; //prix d'un bâtiment de production
    public static final int PRIX_DEFENSE = 100; //prix d'un bâtiment de défense
    public static final int PRIX_JARD = 100; //prix d'un jardinier
    public static final int PRIX_GRAINE = 10; //prix d'une graine
    public static final int PRIX_BOUQUET = 45; //prix d'un bouquet

    public BatPrincipal(int x, int y) {
        super(x, y, PRINCIPAL_RANGE);
    }

    /**
     * @return la tirelire
     */
    public static int getTirelire() {
        return tirelire;
    }

    /**
     * Modifie tirelire
     *
     * @param x la nouvelle balance
     */
    public static void setTirelire(int x) {
        tirelire = x;
    }

    /**
     * Créer un nouveau jardinier sur la case du bâtiment.
     * Prend du temps.
     */
    public void creeJard() {
        tirelire -= PRIX_JARD;
        GrilleMod.addUnite(new Jardinier(GrilleMod.getBatPrincipal().getX(), GrilleMod.getBatPrincipal().getY()));
        //TODO un timer gérer par GrilleMod pour temporiser
        //cree un jardinnier
        //et attendre un temps
    }

    /**
     * Achete une graine contre de l'argent.
     * Instantanée.
     */
    public static void acheterGraine(int id) {
        tirelire -= PRIX_GRAINE;
    }

    /**
     * Vide l'inventaire d'un paysan et le transfert dans l'inventaire du bâtiment
     * Automatique, instantané
     *
     * @param p un paysan
     */
    /**public void collecteRessource(Jardinier p) {
        //copie toutes les ressources du paysan dans le bâtiment
        for (int i = 0; i < 5; i++) {
            ressources[i] += p.getInventaire()[i];
        }
        //vide l'inventaire du paysan
        p.videInventaire();
    }**/

    /**
     * Vend les bouquets composés par un jardinier
     * Automatique, instanté
     */
    public void vendRessource() {
        tirelire += PRIX_BOUQUET;
    }
}

