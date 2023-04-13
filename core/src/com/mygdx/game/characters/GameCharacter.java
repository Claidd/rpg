package com.mygdx.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

    public boolean isAlive(){
        return hp > 0;
    }

    public Vector2 getPosition() {
        return position;
    }

    public abstract void update(float dt);

    public  void render(SpriteBatch batch, BitmapFont font24){
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

        //Добавляем текст. Берем наш фон и рисуем в батче значение hp переводя в инт, а потом в строку. Устанавливаем позицию
        //устанавливаем длину в 80пкс, центруем (выравниваем по горизонтали), выключаем перенос по словам.
        font24.draw(batch, String.valueOf((int) hp), position.x -40, position.y + 80 - 22, 80, 1, false);
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
