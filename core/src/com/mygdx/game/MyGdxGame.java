package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.model.Hero;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Hero hero;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		hero = new Hero();


	}

	@Override
	public void render () {
		update();
		ScreenUtils.clear(0, 0.4f, 0, 1);
		batch.begin();
		hero.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void update(){
		hero.update();
	}
}
