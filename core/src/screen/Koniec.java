package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;


class Koniec implements Screen {
    private MyGdxGame game;
    private Texture koniec;
    private float timer;
    public Koniec(MyGdxGame game) {
        this.game=game;
        koniec=new Texture("menu/zakonczenie.png");
        timer=0;

        MyGdxGame.manager.get("sounds/15_for_a_higher_cause.mp3", Music.class).play();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {




        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        timer+=delta;

        if(timer>25){
            game.setScreen(new Loading((MyGdxGame) game));
            game.getScreen().dispose();
        }
        game.batch.begin();
        game.batch.draw(koniec,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        game.setPaused(true);
    }

    @Override
    public void resume() {
        game.setPaused(false);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.dispose();
    }
}
