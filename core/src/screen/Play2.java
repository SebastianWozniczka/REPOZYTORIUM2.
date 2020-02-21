package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

import scenes.Hud;
import sprites.Brick;
import sprites.Player;

public class Play2 implements Screen {
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    public SpriteBatch batch;
    private Hud hud;
    private TmxMapLoader loader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    Play screen;
    private TextureAtlas run,jump,fall;


     Brick brick;
    private Player player;



    public static MyGdxGame game;

    public TiledMap getMap() {
        return map;
    }

    public TextureAtlas getRun() {
        return run;
    }

    public TextureAtlas getJump() {
        return jump;
    }

    public TextureAtlas getFall() {
        return fall;
    }

    public Play2(MyGdxGame game) {
        this.game=game;
        run=new TextureAtlas("sprites/gracz_pistolet_chod.pack");
        jump=new TextureAtlas("sprites/gracz_pistolet_skok.pack");
        fall=new TextureAtlas("sprites/gracz_pistolet_upadajacy.pack");
        screen=new Play(game);
        init();
        Ulepszenia.misje=Misje.m2;
        batch=new SpriteBatch();
        gamecam = new OrthographicCamera();
        gamePort = new StretchViewport(800 / 100f, 600 / 100f, gamecam);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        hud=new Hud(game.batch);
        loader=new TmxMapLoader();
        map=loader.load("map/mapa1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / 100f);
        world = new World(new Vector2(0, -9.87f), true);
        b2dr = new Box2DDebugRenderer();
        player = new Player(world,  screen);

        Rectangle rect2 =new Rectangle();
       brick=new Brick(this, rect2);


        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
          new Brick(this,rect);



        }


    }


    private void init() {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        game.batch.setProjectionMatrix(gamecam.combined);
        renderer.render();
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();
        hud.stage.draw();
        b2dr.render(world, gamecam.combined);
    }

    public void update(float dt) {
        screen.handleInput(dt);
        gamecam.update();
        hud.update(dt);
        renderer.setView(gamecam);
        world.step(1 / 30f, 6, 2);
        gamecam.position.x = player.b2body.getPosition().x;
        gamecam.position.y = player.b2body.getPosition().y;
        player.update(dt);

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    public World getWorld() {
        return world;
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
        game.dispose();
    }
}
