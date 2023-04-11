package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.model.Hero;
import com.mygdx.game.model.Monster;

public class MyGdxGame extends ApplicationAdapter {

	/*
    === Идеи ===
    1. + Движение по пикселям
    2. Преграды
    3. Анимация
    4. Снаряды, выстрелы
    5. Монстр двигается хаотично
    6. Преследование монстром героя
    7. Аптечки, Монеты, Зелья
    8. Параметры героя/монстра (хп, крит, опыт, скорость)
    9. Опыт героя
    10. Оружие
    11. Урони игры
    12. Финальный босс
    13. Драка с монстрами
    14. Полоска здоровья
    15. Привязать логику к хп героя
     */
	SpriteBatch batch;
	Hero hero;
	Monster monster;

	public Hero getHero() {
		return hero;
	}

	public Monster getMonster() {
		return monster;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		hero = new Hero();
		monster = new Monster(this);
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		ScreenUtils.clear(0, 0.4f, 0, 1);
		batch.begin();
		hero.render(batch);
		monster.render(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	public void update(float dt){
		hero.update(dt);
		monster.update(dt);
		float dst = (float) Math.sqrt((hero.getX() - monster.getX()) * (hero.getX() - monster.getX()));
		if (dst < 40.0f) hero.takeDamage(dt * 10.0f);
	}
}
