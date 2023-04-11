package com.mygdx.game.characters;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameScreen;

public class Monster extends GameCharacter {

    private Vector2 direction;
    private Vector2 temp;
    private float moveTimer;
    private float attackTimer;
    private float attackPeriod;
    private float activityRadius;
    private float attackRadius;


    public Monster(GameScreen gameScreen) {
        this.texture = new Texture("skelet.png");
        this.position = new Vector2(400,200);
        this.direction = new Vector2(0,0);
        this.temp = new Vector2(0,0);
        this.hpMax = 40;
        this.hp = hpMax;
        this.speed = 40.0f;
        this.gameScreen = gameScreen;
        this.activityRadius = 200.0f;
        this.attackPeriod = 0.4f;
        this.attackRadius = 50.0f;
    }


@Override
    public void render(SpriteBatch batch){
        batch.draw(texture,position.x - 40.0f,position.y - 40.0f);
    }

    @Override
    public void update(float dt){
        float dst = gameScreen.getHero().getPosition().dst(this.position);

        //=======ДВИЖЕНИЕ==========
        //Если расстояние до героя меньше 40, то монстр двигается к герою.
        if (dst < activityRadius) {
            temp.set(gameScreen.getHero().getPosition());
            //чтобы получить направление от монстра к герою нужно вычесть из координат героя координаты монстра.
            temp.sub(this.position);
            temp.nor();
            position.mulAdd(temp, speed * dt);
        }
        else {
            //Устанавливаем новое направление движения. Не обнуляем, а добавляем к текущей позиции новое значение direction
            //умноженной на скорость. Получается при каждом кадре мы должны позицию изменить прибавив вектор направления
            //умноженный на скорость и от масштабированный на delta time, которое прошло с предыдущего кадра.
            position.mulAdd(direction, speed * dt);
            moveTimer -= dt;
            if (moveTimer < 0.0f) {
                //По истечению таймера переустанавливаем таймер на рандомное время от 3 до 4-х секунд
                moveTimer = MathUtils.random(2.0f, 4.0f);
                //Далее рандомно меняем направление монстра.
                direction.set(MathUtils.random(-1.0f, 1.0f), MathUtils.random(-1.0f, 1.0f));
                //Нормируем вектор, что бы его квадратный корень был равен единицы, а не единицы с дробной частью
                direction.nor();
            }
        }//=======ДВИЖЕНИЕ==========

        //========АТАКА============
        if (dst < attackRadius){
            attackTimer += dt;
            if (attackTimer >= attackPeriod){
                attackTimer = 0.0f;
                gameScreen.getHero().takeDamage(10.0f);
            }
        } //========АТАКА============

        //========ПРОВЕРКА ГРАНИЦ============
        checkScreenBounds();
        //========ПРОВЕРКА ГРАНИЦ============

    }
}
