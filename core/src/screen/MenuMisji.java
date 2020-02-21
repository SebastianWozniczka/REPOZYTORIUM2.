package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class MenuMisji implements Screen {
    private MyGdxGame game;
    private Texture tlo;
    private SpriteBatch batch;
    private Viewport gamePort;
    private Stage stage;
    private com.badlogic.gdx.scenes.scene2d.ui.Button jeden;
    private com.badlogic.gdx.scenes.scene2d.ui.Button dwa;
    private Button trzy;

    public MenuMisji(final MyGdxGame game) {
        jeden=new com.badlogic.gdx.scenes.scene2d.ui.Button(new com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle(prepareJeden()));
        dwa=new com.badlogic.gdx.scenes.scene2d.ui.Button(new com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle(prepareDwa()));
        trzy=new com.badlogic.gdx.scenes.scene2d.ui.Button(new com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle(prepareTrzy()));
        batch=new SpriteBatch();
        gamePort=new StretchViewport(800,600);
        stage=new Stage(gamePort, game.batch);

        this.game=game;
        tlo=new Texture("menu/menu2.png");


        jeden.setWidth(40);
        jeden.setHeight(40);
        jeden.setX(350);
        jeden.setY(350);
        jeden.debug();


        dwa.setWidth(40);
        dwa.setHeight(40);
        dwa.setX(400);
        dwa.setY(300);
        dwa.debug();


        trzy.setWidth(40);
        trzy.setHeight(40);
        trzy.setX(340);
        trzy.setY(0);
        trzy.debug();

        stage.addActor(jeden);
        stage.addActor(dwa);
        stage.addActor(trzy);

        jeden.addCaptureListener(new InputListener() {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

            game.setScreen(new Play((MyGdxGame) game));


            jeden.remove();
            dwa.remove();
            trzy.remove();
            return true;
        }
    });


        dwa.addCaptureListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                game.setScreen(new Play2((MyGdxGame) game));


                jeden.remove();
                dwa.remove();
                trzy.remove();
                return true;
            }
        });


        trzy.addCaptureListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                game.setScreen(new Play((MyGdxGame) game));


                jeden.remove();
                dwa.remove();
                trzy.remove();
                return true;
            }
        });
    }









    private com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle prepareTrzy() {
        TextureAtlas atlasNg = new TextureAtlas("menu/trzy.pack");
        Skin skinNg = new Skin(atlasNg);
        com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle buttonStyleNg=new com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle();
        buttonStyleNg.up=skinNg.getDrawable("trzy");
        skinNg.getDrawable("trzy");
        return buttonStyleNg;
    }

    private com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle prepareDwa() {
        TextureAtlas atlasNg = new TextureAtlas("menu/dwa.pack");
        Skin skinNg = new Skin(atlasNg);
        com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle buttonStyleNg=new com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle();
        buttonStyleNg.up=skinNg.getDrawable("dwa");
        skinNg.getDrawable("dwa");
        return buttonStyleNg;
    }

    private static com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle prepareJeden() {
        TextureAtlas atlasNg = new TextureAtlas("menu/jeden.pack");
        Skin skinNg = new Skin(atlasNg);
        com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle buttonStyleNg=new com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle();
        buttonStyleNg.up=skinNg.getDrawable("jeden");
        skinNg.getDrawable("jeden");
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
        game.batch.draw(tlo,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch.end();
        Gdx.input.setInputProcessor(stage);
        batch.begin();
        stage.draw();
        batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
