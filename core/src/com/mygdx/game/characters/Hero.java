package com.mygdx.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hero extends GameCharacter{

    public Hero() {
        this.texture = new Texture("knight.png");
        this.textureHp = new Texture("hp.png");
        this.position = new Vector2(200,200);
        this.speed = 100.0f;
        this.hpMax = 100.0f;
        this.hp = hpMax;
    }

    public void takeDamage(float amount){
        hp -= amount;
        damageEffectTimer += 0.5f;
        if (damageEffectTimer > 1.0f) damageEffectTimer = 1.0f;
    }

    @Override
    public void render(SpriteBatch batch){
        //Покраска при уроне
        if (damageEffectTimer > 0){
            batch.setColor(1,1-damageEffectTimer,1-damageEffectTimer,1);
        }
        batch.draw(texture,position.x - 40.0f, position.y - 40.0f);
        batch.setColor(1,1,1,1);
        //Покраска при уроне

        batch.setColor(0,0,0,1);
        // обновление уровня hp.
        batch.draw(textureHp, position.x - 42, position.y + 90 - 42, 83, 10);
        batch.setColor(1,0,0,1);
        // обновление уровня hp.
        batch.draw(textureHp, position.x - 40, position.y + 90 - 40, 0,0, hp / hpMax * 80, 5,1,1,0,0,0,80,5,false,false);
        batch.setColor(1,1,1,1);
    }

    @Override
    public void update(float dt){
        //шок таймS
        damageEffectTimer -= dt;
        if (damageEffectTimer < 0.0f) damageEffectTimer = 0.0f;

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            position.y += speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            position.y -= speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            this.texture = new Texture("knight.png");
            position.x += speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            this.texture = new Texture("knightBack.png");
            position.x -= speed * dt;
        }
        //Проверка на выход за границы карты
        checkScreenBounds();
    }

}
