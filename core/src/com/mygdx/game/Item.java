package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Item {
    public enum Type{
        COINS(0);
        int index;

        Type(int index){
            this.index = index;
        }
    }

    private Vector2 position;
    private Vector2 velosity;
    private Type type;
    private float time;
    private float timeMax;
    private boolean active;

    public Vector2 getPosition() {
        return position;
    }

    public Type getType() {
        return type;
    }

    public boolean isActive(){
        return active;
    }
    public void deactivate(){
        this.active = false;
    }

    public Item() {
        this.position = new Vector2(0,0);
        this.velosity = new Vector2(0,0);
        this.type = Type.COINS;
        this.timeMax = 5.0f;
        this.time = 0.0f;
        this.active = false;
    }

    public void setup(float x, float y, Type type){
        this.position.set(x,y);
        this.velosity.set(MathUtils.random(-50,50),MathUtils.random(-50,50) );
        this.type = type;
        this.time = 0.0f;
        this.active = true;
    }

    public void update(float dt){
        time +=dt;
        position.mulAdd(velosity,dt);
        if (time > timeMax){
            deactivate();
        }
    }


}
