package com.pluff.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 * Created by sky on 09/02/2015.
 */
public class GameScreen implements Screen {

    private GameRenderer gameRenderer;
    private GameWorld gameWorld;
    private float runTime;
    GameRenderer game;
    Game gameS;



    public GameScreen(Game game) {
        this.gameS = game;


        float screenWidth = Gdx.graphics.getWidth();//prendo la larghezza del display del terminale
        float screenHeight = Gdx.graphics.getHeight();//prendo l altezza del display del terminale
        float gameWidth = 136;//setto la larchezza del mondo
        float gameHeight = screenHeight / (screenWidth / gameWidth);//setto l altezza dello schermo
        int midPointY = (int)gameHeight;//posiozione y gallina sul fondo dello schermo

        gameWorld = new GameWorld(midPointY);//gli passo i dati al costruttore in modo da passare la coordinata y nel costruttore di bird che si trova nella GameWorld

        Gdx.input.setInputProcessor(new InputHandler(gameWorld, screenWidth / gameWidth, screenHeight / gameHeight));

        /*creo gameRenderer e gli passo i dati un oggetto gameWorld altezza del display piu la coordinata y ,*/
        gameRenderer = new GameRenderer(gameS,gameWorld,(int)gameHeight,midPointY);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        runTime+=delta;
        gameWorld.update(delta);
        gameRenderer.render(runTime);


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
