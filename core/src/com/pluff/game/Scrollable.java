package com.pluff.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by sky on 13/02/2015.
 */
public class Scrollable {//classe per lo scrolling

    public Vector2 position;
    public Vector2 velocity;
    public int width,height;
    public boolean isScrolledUp;//per resettare i mattoni sullo schermo
    public boolean touched; //mi serve per quando tocca le pareti converto la velocita


    public Scrollable(float x, float y, int width, int height, float scrolledSpeed) {
        position = new Vector2(x,y);//la posizione dello sprite
        velocity = new Vector2(scrolledSpeed,0);//setto la velocita di x=scrollSpeed in modo da farlo muovere solo sull asse x
        this.width = width;
        this.height = height;
        isScrolledUp = false;
        touched = false;



    }

    public void update(float delta) {

        position.add(velocity.cpy().scl(delta));


        if(position.x + width > 136) {//se il mattone supera x=136 unita si attiva la variabile touchedd=true
            touched=true;//attivo il true in modo che nella classe ScrollHander chiamo lo scroll e converto la velocita
        }
        if(position.x  <= 0 ){
            touched=true;
        }
    }

    public void scroll() {//mi serve per quando il mattone tocca le estremita destra sull asse x inverte la posizione
        if(position.x + width > 136) {
            velocity.x = -100;
            touched= false;
        }

        if(position.x < 0) {//se tocca l estremita sinistra converte la velocita
            velocity.x = + 100;
            touched=false;
        }
    }
    public void reset(float newY) {//quando l oggetto non e piu visibile sullo schermo lo resetto in modo da farlo apparire di nuovo alla posizione y = n
        position.y = newY;//gli metto la posizine per farlo apparire


    }



    public float getY() {
        return position.y;
    }

    public float getX() {

        return position.x;
    }

    public int getWidth() {

        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public float getTailY() {

        return position.y + height;
    }


    public boolean getTouched() {
        return touched;
    }
}
