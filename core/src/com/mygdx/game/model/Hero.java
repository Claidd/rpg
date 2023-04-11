package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hero {
    private final Texture texture;
    private float x;
    private float y;
    private final float speed;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Hero() {
        this.texture = new Texture("knight.png");
        this.x = 200.0f;
        this.y = 200.0f;
        this.speed = 100.0f;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture,x,y);
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
