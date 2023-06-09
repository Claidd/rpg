package com.mygdx.game.characters;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameScreen;

public class Monster extends GameCharacter {



    private float moveTimer;
    private float activityRadius;



    public Monster(GameScreen gameScreen) {
        this.texture = new Texture("skelet.png");
        this.textureHp = new Texture("hp.png");
        this.position = new Vector2(MathUtils.random(0, 1240), MathUtils.random(0, 680));
        if (!gameScreen.getMap().isCellPassable(position)){
            this.position.set(MathUtils.random(0, 1240), MathUtils.random(0, 680));
        }
        this.direction = new Vector2(0,0);
        this.hpMax = 40;
        this.hp = hpMax;
        this.speed = 40.0f;
        this.gameScreen = gameScreen;
        this.activityRadius = 200.0f;
        this.weapon = new Weapon("sword", 50, 1.0f, 30.0f);
    }

    @Override
    public void update(float dt){

        //шок таймS
        damageEffectTimer -= dt;
        if (damageEffectTimer < 0.0f) damageEffectTimer = 0.0f;

        float dst = gameScreen.getHero().getPosition().dst(this.position);
        //=======ДВИЖЕНИЕ==========
        //Если расстояние до героя меньше 40, то монстр двигается к герою.
        if (dst < activityRadius) {
            direction.set(gameScreen.getHero().getPosition()).sub(this.position).nor();
        }
        else {
            //Устанавливаем новое направление движения. Не обнуляем, а добавляем к текущей позиции новое значение direction
            //умноженной на скорость. Получается при каждом кадре мы должны позицию изменить прибавив вектор направления
            //умноженный на скорость и от масштабированный на delta time, которое прошло с предыдущего кадра.
            moveTimer -= dt;
            if (moveTimer < 0.0f) {
                //По истечению таймера переустанавливаем таймер на рандомное время от 3 до 4-х секунд
                moveTimer = MathUtils.random(2.0f, 4.0f);
                //Далее рандомно меняем направление монстра.
                direction.set(MathUtils.random(-1.0f, 1.0f), MathUtils.random(-1.0f, 1.0f));
                //Нормируем вектор, что бы его квадратный корень был равен единицы, а не единицы с дробной частью
                direction.nor();
            }
        }
//        temp.set(position).mulAdd(direction, speed * dt);
//        //чтобы получить направление от монстра к герою нужно вычесть из координат героя координаты монстра.
//        if (gameScreen.getMap().isCellPassable(temp)){
//            position.set(temp);
//        }
        moveForward(dt);
        //=======ДВИЖЕНИЕ==========

        //========АТАКА============
        if (dst < weapon.getAttackRadius()){
            attackTimer += dt;
            if (attackTimer >= weapon.getAttackPeriod()){
                attackTimer = 0.0f;
                gameScreen.getHero().takeDamage(weapon.getDamage());
            }
        } //========АТАКА============

        //========ПРОВЕРКА ГРАНИЦ============
        checkScreenBounds();
        //========ПРОВЕРКА ГРАНИЦ============

    }
}
