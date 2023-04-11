package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.model.Hero;
import com.mygdx.game.model.Monster;

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
        hero = new Hero();
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
        float dst = (float) Math.sqrt((hero.getX() - monster.getX()) * (hero.getX() - monster.getX()));
        if (dst < 40.0f) hero.takeDamage(dt * 10.0f);
    }
}
