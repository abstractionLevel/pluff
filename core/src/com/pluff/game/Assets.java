package com.pluff.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by sky on 08/02/2015.
 */
public class Assets {

    public static Texture logoTexture,player1Win,texturebird,reset,uovo,retry,terra;
    public static TextureRegion teraText,bird,mattone,logo, retryText,win,resetText;
    public static Sound flap,dead,victory;
    public static BitmapFont font , shadow, whiteFont;
    private static Preferences prefs;

    public static Texture loadTexture(String file) {
        return  new Texture(Gdx.files.internal(file));
    }

    public  static void load() {

        reset = loadTexture("gameover4.png");
        resetText = new TextureRegion(reset,0,0,204,38);
        resetText.flip(false, true);

        retry = loadTexture("retry.png");
        retryText = new TextureRegion(retry, 0, 0, 159, 48);
        retryText.flip(false, true);

        terra = loadTexture("terra2.png");
        teraText = new TextureRegion(terra,0,0,136,8);
        teraText.flip(false,true);
        logoTexture = new Texture(Gdx.files.internal("pluff 3.png"));
        logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);




        texturebird = new Texture(Gdx.files.internal("gallina.png"));
        texturebird.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);



        player1Win = new Texture(Gdx.files.internal("victory3.png"));
        player1Win.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        win = new TextureRegion(player1Win, 0, 0, 169, 41);
        win.flip(false, true);






        bird = new TextureRegion(texturebird,0,0,17,12);
        bird.flip(false,true);



        flap= Gdx.audio.newSound(Gdx.files.internal("jump2.wav"));
        dead= Gdx.audio.newSound(Gdx.files.internal("dead.wav"));
        victory = Gdx.audio.newSound(Gdx.files.internal("victory.wav"));


        font = new BitmapFont(Gdx.files.internal("text.fnt"));
        font.setScale(.13f,-.13f);
        whiteFont = new BitmapFont(Gdx.files.internal("whitetext.fnt"));
        whiteFont.setScale(.1f, -.1f);
        shadow= new BitmapFont(Gdx.files.internal("shadow.fnt"));
        shadow.setScale(.13f,-.13f);

        prefs = Gdx.app.getPreferences("ZombieBird");

        if(!prefs.contains("highScore"))  {
            prefs.putInteger("highScore",0);
        }

    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {

        flap.dispose();
        dead.dispose();

        victory.dispose();
        font.dispose();
        shadow.dispose();
    }
}
