package com.pluff.game;



/**
 * Created by sky on 09/02/2015.
 */

/*classe per creare gli oggeti nel gioco*/
public class GameWorld {

    Bird bird;//creo l oggetto bird
    Uovo uovo;
    ScrollHander scrollHander;

    private int midPointY;


    public GameWorld() {}

    public GameWorld(int midPointY) {


        this.midPointY = midPointY;

        bird = new Bird(136/2-9,midPointY-50,17,12);//instanzion l oggetto bird con posizione altezza e larghezza, e lo passo alla classe GameRenderer per disegnarlo
        uovo = new Uovo(136/2-9,midPointY-50,14,18);
        scrollHander = new ScrollHander();//inializzo l oggeetto scrollHander in modo da instanziare i mattoni sullo schermo

    }

    public void update(float delta) {

        updateReady(delta);

    }

    public void updateReady(float delta) {
        bird.update(delta);//aggiorno la posizione della gallina
        scrollHander.update(delta);//chiamata al metodo di aggiornamento della classe Scrollhander
        uovo.update(delta);

    }


    public Bird getBird() {//ritono dell oggetto gallina

        return bird;
    }

    public Uovo getUovo() {//ritono dell oggetto gallina

        return uovo;
    }
    public int getMidPointY() {
        return midPointY;
    }

    public ScrollHander getScrollHander() {
        return scrollHander;
    }



}
