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

    public Vector2 getPosition() {
        return position;
    }

    public abstract void update(float dt);

    public abstract void render(SpriteBatch batch);

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
}
