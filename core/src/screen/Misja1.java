package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

public class Misja1 implements Screen {
    private MyGdxGame game;
    private Texture obraz1;
    private float timer;
    public Misja1(MyGdxGame game) {
        this.game=game;
        timer=0;
        obraz1=new Texture("menu/misja1.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(obraz1,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch.end();

        timer+=delta;
        MyGdxGame.manager.get("sounds/menu.mp3", Music.class).play();
        if(Gdx.input.isTouched()&&timer>0.1f){
            game.setScreen(new Play((MyGdxGame) game));
        }
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
