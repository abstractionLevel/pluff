package com.pluff.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by sky on 13/02/2015.
 */
public class Mattone extends Scrollable {//classe oggetto mattone che eredita tutti gli oggetti della classe Scrollable


    private Rectangle rectangle;

    public Mattone(float x, float y, int width, int height, float scrolledSpeed) {
        super(x, y, width, height, scrolledSpeed);//eredito dalla classe Scrollable
        //velocita x
        //velocita y
        //larghezza width
        //lunghezza height
        //velocita scrolledSpeed
        rectangle = new Rectangle();


    }

    @Override
    public void update(float delta) {
        super.update(delta);

        rectangle.set(position.x,position.y,width,height);


    }

    @Override
    public void reset(float newY) {
        super.reset(newY);//eredito il metodo reset dalla classe Scrollable in modo da ripristinare la posizione dell oggetto quando e fuori dallo schermo

    }

    public void onRestart( float x,float y,int width,int height, float scrollSpeed) {
        velocity.x = scrollSpeed;
        position.y = y;
        position.x = x;
        this.width =width;
        this.height = height;

    }

    public Rectangle getRectangle() {
        return rectangle;
    }



    public void setX(float x) {
        position.x = x;
    }

    public void setY(float y) {
        position.y = y;
    }

}





