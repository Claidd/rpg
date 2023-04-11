package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hero {
    private final Texture texture;
    private final Texture textureHp;
    private float x;
    private float y;
    private final float speed;
    private float hp, hpMax;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Hero() {
        this.texture = new Texture("knight.png");
        this.textureHp = new Texture("hp.png");
        this.x = 200.0f;
        this.y = 200.0f;
        this.speed = 100.0f;
        this.hpMax = 100.0f;
        this.hp = hpMax;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture,x,y);
        batch.setColor(1,0,0,1);
        // обновление урона.
        batch.draw(textureHp, x, y + 90, 0,0, hp, 5,1,1,0,0,0,80,5,false,false);
        batch.setColor(1,1,1,1);
    }

    public void takeDamage(float amount){
        hp -= amount;
    }

    public void update(float dt){
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            y += speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            y -= speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            x += speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            x -= speed * dt;
        }
    }

}
