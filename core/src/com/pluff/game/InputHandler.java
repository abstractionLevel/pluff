package com.pluff.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by sky on 10/02/2015.
 */
public class InputHandler extends Game implements InputProcessor {

    public GameRenderer gameRenderer;



    public InputHandler(GameWorld gameWorld,float scaleFactorX,float scaleFactorY) {

    }



    @Override
    public boolean keyDown(int keycode) {




        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {




        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {





        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void create() {

    }


}
