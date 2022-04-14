package com.pluff.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by sky on 13/02/2015.
 */
public class ScrollHander { //classe per lo scroolingUp


    private Mattone mattone1,mattone2,mattone3,mattone4,mattone5,mattone6,mattone7,mattone8,mattone9,mattone10,palla;


    public static final int SCROLL_SPEED = 200; //imposto la velocita dello scrolling
    public static final int DISTANZA_MATTONI  = 45;



    public ScrollHander() {


        float screenWidth = Gdx.graphics.getWidth();//prendo la larghezza del display del terminale
        float screenHeight = Gdx.graphics.getHeight();//prendo l altezza del display del terminale
        float gameWidth = 136;//setto la larchezza del mondo
        float gameHeight = screenHeight / (screenWidth / gameWidth);//setto l altezza dello schermo
        int midPointY = (int)gameHeight;//posiozione y gallina sul fondo dello schermo



        mattone1 = new Mattone(5,midPointY-84,10,2,SCROLL_SPEED);//inizializzo l oggetto mattone con velocita etc
        mattone2 = new Mattone(115,mattone1.getY()-DISTANZA_MATTONI,10,2,SCROLL_SPEED);//inizializzo l oggetto mattone con velocita etc
        mattone3 = new Mattone(5,mattone2.getY()-DISTANZA_MATTONI,10,2,SCROLL_SPEED);//inizializzo l oggetto mattone con velocita etc
        mattone4 = new Mattone(115,mattone3.getY()-DISTANZA_MATTONI,10,2,SCROLL_SPEED);
        mattone5 = new Mattone(5,mattone4.getY()-DISTANZA_MATTONI,10,2,SCROLL_SPEED);
        mattone6 = new Mattone(115,mattone5.getY()-DISTANZA_MATTONI,10,2,SCROLL_SPEED);
        mattone7 = new Mattone(5,mattone6.getY()-DISTANZA_MATTONI,10,2,SCROLL_SPEED);
        mattone8 = new Mattone(115,mattone7.getY()-DISTANZA_MATTONI,10,2,SCROLL_SPEED);
        mattone9 = new Mattone(5,mattone8.getY()-DISTANZA_MATTONI,10,2,SCROLL_SPEED);
        mattone10 = new Mattone(115,mattone9.getY()-DISTANZA_MATTONI,10,2,SCROLL_SPEED);
        palla = new Mattone(5,mattone10.getY()-DISTANZA_MATTONI-20,14,18,SCROLL_SPEED);



    }


    public void update(float delta) {//mi serve per chiamarlo nell metodo di aggionmaneto della classe GameHandler
        mattone1.update(delta);//chiamo il metodo aggiornamento per la velocita e in caso vada fuori coordinate lo resetto
        mattone2.update(delta);
        mattone3.update(delta);
        mattone4.update(delta);
        mattone5.update(delta);
        mattone6.update(delta);
        mattone7.update(delta);
        mattone8.update(delta);
        mattone9.update(delta);
        mattone10.update(delta);
        palla.update(delta);





        if(mattone1.getTouched()==true) {//se tocco l estremita
            mattone1.scroll();//sposto l asse x

        }

       else if(mattone2.getTouched()==true) {
            mattone2.scroll();

        }

        else if(mattone3.getTouched()==true) {
            mattone3.scroll();
        }
        else if(mattone4.getTouched()==true) {
            mattone4.scroll();
        }
        else if(mattone5.getTouched()==true) {
            mattone5.scroll();
        }
    }




    public Mattone getMattone1() { return mattone1; }
    public Mattone getMattone2() {
        return mattone2;
    }
    public Mattone getMattone3() {
        return mattone3;
    }
    public Mattone getMattone4() {
        return mattone4;
    }
    public Mattone getMattone5() {
        return mattone5;
    }
    public Mattone getMattone6() {
        return mattone6;
    }
    public Mattone getMattone7() {
        return mattone7;
    }
    public Mattone getMattone8() {
        return mattone8;
    }
    public Mattone getMattone9() {
        return mattone9;
    }
    public Mattone getMattone10() {
        return mattone10;
    }
    public Mattone getPalla() {return  palla;}


}
