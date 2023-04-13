package com.mygdx.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameScreen;

public class Hero extends GameCharacter{



    public Hero(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.texture = new Texture("knight.png");
        this.textureHp = new Texture("hp.png");
        this.direction = new Vector2(0,0);
        this.position = new Vector2(MathUtils.random(0, 1240), MathUtils.random(0, 680));
        if (!gameScreen.getMap().isCellPassable(position)){
            this.position.set(MathUtils.random(0, 1240), MathUtils.random(0, 680));
        }
        this.speed = 100.0f;
        this.hpMax = 100.0f;
        this.hp = hpMax;
        this.temp = new Vector2(0,0);
        this.weapon = new Weapon("Меч истины", 70, 1.0f, 20.0f);
    }


    @Override
    public void update(float dt){

        Monster nearestMonster = null;
        float minDist = Float.MAX_VALUE;
        for (int i = 0; i < gameScreen.getAllMonsters().size(); i++) {
            //Перебираем расстояние до каждого монстра
            float dst = gameScreen.getAllMonsters().get(i).getPosition().dst(this.position);
            if (dst < minDist){
                minDist = dst;
                nearestMonster = gameScreen.getAllMonsters().get(i);
            }
        }


        //Атака
        if ( nearestMonster != null && minDist  < weapon.getAttackRadius()){
            attackTimer += dt;
            if (attackTimer > weapon.getAttackPeriod()){
                attackTimer = 0.0f;
                nearestMonster.takeDamage(weapon.getDamage());
            }
        }
        //Атака

        //шок таймS
        damageEffectTimer -= dt;
        if (damageEffectTimer < 0.0f) damageEffectTimer = 0.0f;

        //Движение
            direction.set(0,0);
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            direction.y = 1.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            direction.y = -1.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            direction.x = 1.0f;
            this.texture = new Texture("knight.png");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            direction.x = -1.0f;
            this.texture = new Texture("knightBack.png");
        }


        temp.set(position).mulAdd(direction,speed * dt);
        if (gameScreen.getMap().isCellPassable(temp)){
            position.set(temp);
        }
        //Проверка на выход за границы карты
        checkScreenBounds();
    }

}
