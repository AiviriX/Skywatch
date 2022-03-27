package com.aiv.skywatch;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		SkyGame game = new SkyGame();
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Skywatch");
		config.setWindowedMode(1366, 768);
		config.setForegroundFPS(60);
		new Lwjgl3Application(new SkyGame(), config);
	}
}
