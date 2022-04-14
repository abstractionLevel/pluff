package com.pluff.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pluff.game.PluffGame;

public class DesktopLauncher  {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Pluff";
        config.width = 277;
        config.height = 408;
		new LwjglApplication(new PluffGame(null), config);
	}


}


