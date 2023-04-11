package com.mygdx.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameScreen;

public abstract class GameCharacter {
    Texture texture;
    Texture textureHp;
    Vector2 position;
    float speed;
    float hp, hpMax;
    GameScreen gameScreen;
    float damageEffectTimer;
    float attackTimer;
    Weapon weapon;

    public Vector2 getPosition() {
        return position;
    }

    public abstract void update(float dt);

    public  void render(SpriteBatch batch){
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

    //Проверка на выход персонажа за границы карты
    public  void checkScreenBounds(){
        //Не фиксированные границы карты
        if (position.x > 1240.0f) position.x = 1240.0f;
        if (position.x < 40.0f) position.x = 40.0f;
        if (position.y > 680.0f) position.y = 680.0f;
        if (position.y < 40.0f) position.y = 40.0f;
        //Не фиксированные границы карты
//            if (position.x > 1280) position.x = 0.0f;
    }

    public void takeDamage(float amount){
        hp -= amount;
        damageEffectTimer += 0.5f;
        if (damageEffectTimer > 1.0f) damageEffectTimer = 1.0f;
    }
}
