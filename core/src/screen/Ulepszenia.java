package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class Ulepszenia implements Screen {
    private MyGdxGame game;
    private Texture ulepszenia;
    private Stage stage;
    private Viewport viewport;
    private OrthographicCamera cam;
    private Button dol,gora,pistolet;
    private SpriteBatch batch;
    boolean upPressed, downPressed;
    public static Misje misje;

public Ulepszenia(final MyGdxGame game) {

    this.game = game;
    ulepszenia = new Texture("menu/ulepsz.png");

    batch = new SpriteBatch();
    cam = new OrthographicCamera();
    viewport = new FitViewport(800, 600, cam);
    stage = new Stage(viewport, ((MyGdxGame) game).batch);
    dol = new Button(new Button.ButtonStyle());
    gora = new Button(new Button.ButtonStyle());
    if(misje==Misje.m1);
    pistolet=new Button(new Button.ButtonStyle(preparePistolet()));
    cam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 200, 0);

    dol.setWidth(Gdx.graphics.getWidth());
    dol.setHeight(100);
    dol.setX(0);
    dol.setY(0);

    gora.setWidth(Gdx.graphics.getWidth());
    gora.setHeight(100);
    gora.setX(0);
    gora.setY(Gdx.graphics.getHeight() - 100);

    pistolet.setWidth(100);
    pistolet.setHeight(100);
    pistolet.setX(0);
    pistolet.setY(Gdx.graphics.getHeight() - 100);

    stage.addActor(dol);
    stage.addActor(gora);
    stage.addActor(pistolet);

    stage.addListener(new InputListener() {

        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            switch (keycode) {
                case Input.Keys.UP:
                    upPressed = true;
                    break;
                case Input.Keys.DOWN:
                    downPressed = true;
                    break;

            }
            return true;
        }

        @Override
        public boolean keyUp(InputEvent event, int keycode) {
            switch (keycode) {
                case Input.Keys.UP:
                    upPressed = false;
                    break;
                case Input.Keys.DOWN:
                    downPressed = false;
                    break;

            }
            return true;
        }
    });



    Image upImg = new Image(new Texture("menu/strzalka_dol.png"));
    upImg.setSize(600, 150);
    upImg.addListener(new InputListener() {




        @Override
        public boolean mouseMoved(InputEvent event, float x, float y) {
            upPressed = true;
            cam.position.y +=1;


            return true;
        }
    });


    Image downImg = new Image(new Texture("menu/strzalka_gora.png"));
    downImg.setSize(600, 150);
    downImg.addListener(new InputListener() {


        @Override
        public boolean mouseMoved(InputEvent event, float x, float y) {
            cam.position.y -=1;
            return true;
        }
    });

   Table table=new Table();
    table.add();

    table.add();
    table.row().pad(Gdx.graphics.getHeight(), 0, 0, 0);

    table.add();

    stage.addActor(table);

    pistolet.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if(misje==Misje.m1)
                game.setScreen(new MenuMisji((MyGdxGame) game));
            return true;
        }
    });
}


    private static Button.ButtonStyle preparePistolet() {
        TextureAtlas atlasNg = new TextureAtlas("menu/pistolet.pack");
        Skin skinNg = new Skin(atlasNg);
        Button.ButtonStyle buttonStyleNg=new Button.ButtonStyle();
        buttonStyleNg.up=skinNg.getDrawable("pistolet1");
        skinNg.getDrawable("pistolet2");
        return buttonStyleNg;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.input.setInputProcessor(stage);
        game.batch.setProjectionMatrix(cam.combined);





       game.batch.begin();
       game.batch.draw(ulepszenia,0,0,800,1800);
       game.batch.end();

        batch.begin();


        stage.draw();
        batch.end();


        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            cam.position.y+=5;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            cam.position.y-=5;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MenuScreen(game));
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
        stage.dispose();
        game.dispose();
    }
}
