package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Map {
    public static final int CELLS_X = 16;
    public static final int CELLS_Y = 9;
    public static final int CELLS_SIZE = 80;
    private Texture textureGrass;
    private Texture textureWall;
    byte[][] data;

    public Map() {
        data = new byte[CELLS_X][CELLS_Y];
        for (int i = 0; i < CELLS_X; i+=2) {
            for (int j = 0; j < CELLS_Y; j+=2) {
                data[i][j] = 1;
            }

        }
        textureGrass = new Texture("grass.png");
        textureWall = new Texture("wall.png");


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
                    batch.draw(textureWall,i * 80, j * 80 );
                }
            }
        }
    }
}
