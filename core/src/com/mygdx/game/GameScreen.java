package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.characters.Hero;
import com.mygdx.game.characters.Monster;

public class GameScreen {

    SpriteBatch batch;
    private BitmapFont font24;
    Hero hero;
    Monster monster;
    private Texture grass;

    public GameScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    public Hero getHero() {
        return hero;
    }

    public Monster getMonster() {
        return monster;
    }



    //Метод для преднастройки игры.
    public void create(){
        hero = new Hero(this);
        monster = new Monster(this);
        font24 = new BitmapFont(Gdx.files.internal("font2.fnt"));
        grass = new Texture("grass.png");
    }

    public void render(){
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        ScreenUtils.clear(0, 0.4f, 0, 1);
        batch.begin();
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                batch.draw(grass, i * 80, j * 80);
            }
        }
        hero.render(batch, font24);
        monster.render(batch, font24);
        batch.end();
    }

    public void update(float dt){
        hero.update(dt);
        monster.update(dt);
    }
}
