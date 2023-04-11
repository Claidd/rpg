package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
    16. Перенос на вектор +
     */
	SpriteBatch batch;
	GameScreen gameScreen;



	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.gameScreen = new GameScreen(batch);
		this.gameScreen.create();
	}

	@Override
	public void render () {
		gameScreen.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	public void update(float dt){

	}
}
