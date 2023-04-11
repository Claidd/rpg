package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hero {
    private Texture texture;
    private float x;
    private float y;

    public Hero() {
        texture = new Texture("knight.png");
        x = 200.0f;
        y = 200.0f;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture,x,y);
    }

    public void update(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            y += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            y -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            x += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            x -= 1;
        }
    }
}
