package View;

import java.beans.Expression;

public class ThreadAffichage extends Thread{

    private View view;

    public ThreadAffichage(View view){
        this.view = view;
    }

    public void run(){
        while(true){
            view.revalidate();
            view.repaint(0,0,800,800);
            try {
                Thread.sleep(50);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
