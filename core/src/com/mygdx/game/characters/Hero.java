package com.mygdx.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Item;

public class Hero extends GameCharacter{
    private int coins;
    private int level;
    private int exp;
    private int[] expTo = {0, 0, 100, 200, 300, 500, 1000, 5000};



    public Hero(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.level = 1;
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

    public void renderHUD(SpriteBatch batch, BitmapFont font){
        font.draw(batch, "Knight Jhon \n EXP: " + exp + "/" + expTo[level+1] + " LEVEL: " + level + "\nCOINS: " + coins, 20, 700);
    }

    public void killMonster(Monster monster){
        exp += monster.hpMax * 5;
        if (exp >= expTo[level+1]){
            level++;
            hpMax += 10;
            hp = hpMax;
            exp -= expTo[level];
        }
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

        moveForward(dt);
//        temp.set(position).mulAdd(direction,speed * dt);
//        if (gameScreen.getMap().isCellPassable(temp)){
//            position.set(temp);
//        }
        //Проверка на выход за границы карты
        checkScreenBounds();
    }

    public void useItem(Item it) {
        switch (it.getType()){
            case COINS:
                coins += MathUtils.random(3,5);
                break;
        }
        it.deactivate();
    }
}
