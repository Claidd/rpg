package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Map {
    public static final int CELLS_X = 16;
    public static final int CELLS_Y = 9;
    public static final int CELLS_SIZE = 80;
    private final Texture textureGrass;
    private final Texture textureWall;
    private final Texture textureTree;
    byte[][] data;

    public Map() {
        data = new byte[CELLS_X][CELLS_Y];
        for (int i = 0; i < 10; i++) {
            data[MathUtils.random(0, CELLS_X-1)][MathUtils.random(0, CELLS_Y-1)] = 1;
        }
        for (int i = 0; i < 20; i++) {
            data[MathUtils.random(0, CELLS_X-1)][MathUtils.random(0, CELLS_Y-1)] = 2;
        }
        textureGrass = new Texture("grass.png");
        textureWall = new Texture("stone7.png");
        textureTree = new Texture("tree.png");


    }
// Устанавливаем проходимость
    public boolean isCellPassable(Vector2 position){
        if (position.x < 40.0f || position.x > 1240.0f || position.y < 40.0f || position.y > 680.0f){
            return false;
        }
        return data[(int) (position.x / CELLS_SIZE)] [(int) (position.y / CELLS_SIZE)] == 0;
    }

    public void render(SpriteBatch batch){
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                batch.draw(textureGrass, i * 80, j * 80);
                if (data[i][j] == 1){
                    batch.draw(textureWall,i * 80, j * 80);
                }
                if (data[i][j] == 2){
                    batch.draw(textureTree,i * 80, j * 80);
                }
            }
        }


    }
}
