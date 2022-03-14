package Modele;
public abstract class Ressource extends Thread{ //à terme plusieurs types de ressources
    protected int amount;
    protected int x;
    protected int y;


    public Ressource(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract boolean isPickable(); //c'est vraiment utile de le déclarer ici ??

    public abstract int getid();

    public abstract int getAmount();

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}




