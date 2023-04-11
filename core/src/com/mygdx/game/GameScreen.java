package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.characters.Hero;
import com.mygdx.game.characters.Monster;

public class GameScreen {

    SpriteBatch batch;

    Hero hero;
    Monster monster;

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
    }

    public void render(){
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        ScreenUtils.clear(0, 0.4f, 0, 1);
        batch.begin();
        hero.render(batch);
        monster.render(batch);
        batch.end();
    }

    public void update(float dt){
        hero.update(dt);
        monster.update(dt);
    }
}
