package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screen.Loading;
import screen.MenuMisji;

public class MyGdxGame extends Game {


	private boolean paused;
	public static AssetManager manager;
	public SpriteBatch batch;
	public static Preferences prefs;
	public static short HEAD_BIT = 2;
	public static short BRICK_BIT = 4;
	public static final short DESTROYED_BIT = 8;
	public static final short COIN_BIT = 16;

	@Override
	public void create() {
		prefs = Gdx.app.getPreferences("com.mygdx.game.prefs");
		batch = new SpriteBatch();

		manager = new AssetManager();

		manager.load("sounds/intro.mp3", Music.class);
		manager.load("sounds/15_for_a_higher_cause.mp3", Music.class);
		manager.load("sounds/menu.mp3", Music.class);
		manager.finishLoading();
			this.setScreen(new Loading(this));

	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public boolean isPaused() {
		return paused;
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {

	}
}