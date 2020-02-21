package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

public class Loading implements Screen {
    private Texture[] logo;
    private MyGdxGame game;
    private Texture render;
    private float timer;


    public Loading(MyGdxGame game) {
        this.game=game;
        logo=new Texture[4];
        logo[0]=new Texture("menu/logo1.png");
        logo[1]=new Texture("menu/logo2.png");
        logo[2]=new Texture("menu/logo3.png");
        logo[3]=new Texture("menu/logo4.png");

        MyGdxGame.manager.get("sounds/intro.mp3", Music.class).play();

    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        timer+=delta;


        render=logo[0];
        if(timer>0.2f) {
            render=logo[1];
        }

        if(timer>0.4f) {
            render=logo[2];
        }

        if(timer>0.6f) {
            render=logo[3];
        }

        if(timer>1) {
            if(Gdx.input.isTouched())
                game.setScreen(new MenuScreen((MyGdxGame) game));
        }

        game.batch.begin();
        game.batch.draw(render, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
