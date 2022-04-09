package Modele;

import java.util.Arrays;

/**
 * Bâtiment Principal
 * Gère les ressources générales, et notamment l'argent/score
 */
public class BatPrincipal extends Building {
    public static final int PRINCIPAL_RANGE = 5000; //rayon d'action
    private static int tirelire = 1000; //argent
    public static final int PRIX_PRODUCTION = 100; //prix bâtiment de production
    public static final int PRIX_DEFENSE = 100; //prix bâtiment de défense
    public static final int PRIX_GRAINE = 10; //prix graine
    public static final int PRIX_BOUQUET = 45; //prix bouquet
    public static final int PRIX_JARDINIER = 175; //prix d'un jardinier
    public static final int PRIX_LAQUAIS = 130; //prix d'un laquais

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
     * Achete une graine contre de l'argent.
     * Instantanée.
     */
    public static void acheterGraine(int id) {
        tirelire -= PRIX_GRAINE;
    }

    /**
     * Vend les bouquets composés par un jardinier
     * Automatique, instanté
     */
    public void vendRessource() {
        tirelire += PRIX_BOUQUET;
    }

    /**
     * recruterJardinier
     * Recrute un jardinier et le place sur le terrain
     */
    public void recruterJardinier(){
        tirelire -= PRIX_JARDINIER;
        GrilleMod.addJardinier(new Jardinier(x, y));
    }

    /**
     * recruterLaquais
     * Recrute un laquais et le place sur le terrain
     */
    public static void recruterLaquais(){
        tirelire -= PRIX_LAQUAIS;
        GrilleMod.addLaquais(new Laquais(GrilleMod.getSelectedUnite()));
    }
}

