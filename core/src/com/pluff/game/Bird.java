package com.pluff.game;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by sky on 09/02/2015.
 */
public class Bird {


    private Vector2  position;
    Body body;
    int width, height;

    public Bird() {}


    public Bird(int x, int y, int width, int height) {
        position = new Vector2(x, y);//variabile posizione di aggiornamento

        this.width = width;
        this.height = height;



    }


    public void update(float delta) {


        if (position.x > 60 - 12/100) {
            position.x = 60 - 12;
        }


    }



    public float getX() {//ritorna la cooridanata x  della gallina che gli ho passato nel costruttore di Bird nella classe GameWorld
        return position.x;
    }

    public float getY() { return position.y;
    }

    public void setX(float x) {
        position.x = x;
    }

    public void setY(float y) {
        position.y = y;
    }



    public float getWidth() {return width;
    }

    public float getHeight() { //ritorna la lunghezza della gallina
        return height;
    }

}
