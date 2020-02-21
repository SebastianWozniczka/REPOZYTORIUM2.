package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class MenuScreen implements Screen {

    private Texture tex1;
    private Stage stage;
    private Viewport viewport;
    private OrthographicCamera cam;
    public static Button play, ulepszenia,wyjscie;
    private SpriteBatch batch;


    MyGdxGame game;
    public MenuScreen(final MyGdxGame game) {
        this.game=game;
        MyGdxGame.manager.get("sounds/intro.mp3", Music.class).pause();

        tex1=new Texture("menu/tlo.png");
        batch=new SpriteBatch();
        cam=new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);
        stage = new Stage(viewport, ((MyGdxGame) game).batch);
        play=new Button(new Button.ButtonStyle(preparePlay()));
        ulepszenia =new Button(new Button.ButtonStyle(preparejakGrac()));
        wyjscie=new Button(new Button.ButtonStyle(prepareWyjscie()));
        play.setWidth(500);
        play.setHeight(100);
        play.setX(70);
        play.setY(500);
        play.debug();

        ulepszenia.setWidth(500);
        ulepszenia.setHeight(100);
        ulepszenia.setX(-30);
        ulepszenia.setY(300);
        ulepszenia.debug();

        wyjscie.setWidth(500);
        wyjscie.setHeight(100);
        wyjscie.setX(-130);
        wyjscie.setY(100);
        wyjscie.debug();



        stage.addActor(play);
        stage.addActor(ulepszenia);
        stage.addActor(wyjscie);
        reactOnClick();
        play.addCaptureListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

               game.setScreen(new Misja1((MyGdxGame) game));


                play.remove();
               ulepszenia.remove();
                wyjscie.remove();
                return true;
            }
        });

        wyjscie.addCaptureListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
              Gdx.app.exit();

                play.remove();
                wyjscie.remove();
                ulepszenia.remove();
                return true;
            }




        });

        ulepszenia.addCaptureListener(new InputListener() {

            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
               // Gdx.app.exit();


                return super.mouseMoved(event, x, y);
        }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Ulepszenia((MyGdxGame) game));
                ulepszenia.remove();
                wyjscie.remove();
                play.remove();
                return true;
            }
        });



    }


    private static Button.ButtonStyle preparePlay() {
        TextureAtlas atlasNg = new TextureAtlas("menu/start.pack");
        Skin skinNg = new Skin(atlasNg);
        Button.ButtonStyle buttonStyleNg=new Button.ButtonStyle();
        buttonStyleNg.up=skinNg.getDrawable("start");
        skinNg.getDrawable("start");
        return buttonStyleNg;
    }

    private static Button.ButtonStyle preparejakGrac() {
        TextureAtlas atlasNg = new TextureAtlas("menu/ulepszenia.pack");
        Skin skinNg = new Skin(atlasNg);
        Button.ButtonStyle buttonStyleNg=new Button.ButtonStyle();
        buttonStyleNg.up=skinNg.getDrawable("ulepszenia");
        skinNg.getDrawable("ulepszenia");
        return buttonStyleNg;
    }

    private static Button.ButtonStyle prepareWyjscie() {
        TextureAtlas atlasNg = new TextureAtlas("menu/koniec.pack");
        Skin skinNg = new Skin(atlasNg);
        Button.ButtonStyle buttonStyleNg=new Button.ButtonStyle();
        buttonStyleNg.up=skinNg.getDrawable("koniec");
        skinNg.getDrawable("koniec");
        return buttonStyleNg;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(tex1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();
        Gdx.input.setInputProcessor(stage);
        game.batch.begin();
        game.batch.end();
        batch.begin();
        stage.draw();
        batch.end();
        update();



    }

    public void reactOnClick(){

        Action testAction= Actions.moveBy(100,0,1);
        play.addAction(testAction);

        Action testAction2= Actions.moveBy(200,0,0.5f);
        ulepszenia.addAction(testAction2);

        Action testAction3= Actions.moveBy(300,0,0.3f);
        wyjscie.addAction(testAction3);
    }

    private void update(){
        stage.act();
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
        stage.dispose();
        game.dispose();

    }

}
