package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.characters.GameCharacter;
import com.mygdx.game.characters.Hero;
import com.mygdx.game.characters.Monster;
import javafx.print.Collation;

import java.util.*;

public class GameScreen {

    private SpriteBatch batch;
    private BitmapFont font24;
    private Hero hero;
    private Monster monster;
    private Texture grass;
    private List<GameCharacter> allCharacters;
    private Comparator<GameCharacter> drawOrderComporator;


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
        allCharacters = new ArrayList<>();
        hero = new Hero(this);
        monster = new Monster(this);
        allCharacters.addAll(Arrays.asList(hero,monster));
        font24 = new BitmapFont(Gdx.files.internal("font2.fnt"));
        grass = new Texture("grass.png");
        drawOrderComporator = new Comparator<GameCharacter>() {
            @Override
            public int compare(GameCharacter o1, GameCharacter o2) {
                return (int) (o2.getPosition().y - o1.getPosition().y);
            }
        };
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

        //Отрисовка героев
//        hero.render(batch, font24);
//        monster.render(batch, font24);
        Collections.sort(allCharacters, drawOrderComporator);
        for (int i = 0; i < allCharacters.size(); i++) {
            allCharacters.get(i).render(batch,font24);
        }
        batch.end();
    }

    public void update(float dt){
        hero.update(dt);
        monster.update(dt);
    }
}
