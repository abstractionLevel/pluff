package com.pluff.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


/**
 * Created by sky on 04/02/2015.
 */
public class WinScreen implements Screen{

    SpriteBatch batch;
    OrthographicCamera guiCam;
    Game game;
    Rectangle resetRect;

    Vector3 touchPoint;


    public WinScreen(Game game) {


        this.game = game;
        batch = new SpriteBatch();

        guiCam = new OrthographicCamera();//per la camera fissa
        guiCam.setToOrtho(true, 136, 180);
        resetRect = new Rectangle(35,80, 70, 50);
        touchPoint = new Vector3();

    }

    public void update(float deltaTime) {
        if(Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if(OverlapTest.pointInRectangle(resetRect,touchPoint.x,touchPoint.y)) {
                game.setScreen(new GameScreen(game));
            }
        }
    }


    public void draw(float deltaTime) {
        GL20 gl = Gdx.gl;
        gl.glClearColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        batch.setProjectionMatrix(guiCam.combined);

            batch.begin();
            batch.draw(Assets.win,(136/2)-67,60, 136, 40);
            batch.end();



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        draw(delta);
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
