package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

import box2dLight.ConeLight;
import box2dLight.RayHandler;
import scenes.Hud;
import sprites.Brick;
import sprites.Enemy;
import sprites.Player;
import sprites.Przeciwnik1;
import tools.WorldContactListener;


public class Play extends MyGdxGame implements Screen {
    private MyGdxGame game;
    private Hud hud;
    private TextureAtlas atlas,atlas2,atlas3,atlas4;

    private Texture hp1;
    private Texture hp2;
    private Texture hp3;
    private Texture hp4;
    private Texture hp5;
    private Texture hp6;
    private Texture hp7;
    public Texture hp8;


    public static Texture render;
    private Texture portal1,portal2,portal3,portal4,portal5,portal6,portal7,portal8,portal9,portal10,render2;


    private OrthographicCamera gamecam;
    private Viewport gamePort;
    public SpriteBatch batch;


    private TmxMapLoader loader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;

    private Brick brick;



    private RayHandler rayHandler;
    private ConeLight myLight;


    private Player player;

    private float pozycjaX;
    private float promien;
    private float timer;
    private float timer2;
    private float timer3;
    private Array<Przeciwnik1> p1;


    public TextureAtlas getAtlas() {
        return atlas;
    }

    public TextureAtlas getAtlas2() {
        return atlas2;
    }

    public TextureAtlas getAtlas3() {
        return atlas3;
    }

    public TextureAtlas getAtlas4() {
        return atlas4;
    }

    public Play(MyGdxGame game) {
        this.game=game;
        init();
        Ulepszenia.misje=Misje.m1;
        pozycjaX=0.3f;
        promien=20;
        timer2=0;
        timer3=0;
        p1=new Array<Przeciwnik1>();


        MyGdxGame.manager.get("sounds/menu.mp3", Music.class).pause();

        gamecam = new OrthographicCamera();
        gamePort = new StretchViewport(800 / 100f, 600 / 100f, gamecam);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        loader=new TmxMapLoader();
        map=loader.load("map/mapa1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / 100f);


        hud=new Hud(game.batch);
        batch=new SpriteBatch();

        world = new World(new Vector2(0, -9.87f), true);
        b2dr = new Box2DDebugRenderer();

        player = new Player(world, this);


        rayHandler=new RayHandler(world);
        world.setContactListener(new WorldContactListener());



           myLight= new ConeLight(rayHandler,5,Color.BLUE,5,player.b2body.getPosition().x-pozycjaX,player.b2body.getPosition().y-0.3f,promien,30);

        Rectangle rect2 =new Rectangle();
        brick=new Brick(this, rect2);

        p1.add(new Przeciwnik1( this,gamePort.getWorldWidth(),3.32f));
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Brick(this,rect);

        }

            timer=0;

    }



    private void init(){
        hp1=new Texture("menu/hp1.png");
        hp2=new Texture("menu/hp2.png");
        hp3=new Texture("menu/hp3.png");
        hp4=new Texture("menu/hp4.png");
        hp5=new Texture("menu/hp5.png");
        hp6=new Texture("menu/hp6.png");
        hp7=new Texture("menu/hp7.png");
        hp8=new Texture("menu/hp8.png");

        portal1=new Texture("sprites/portal1.png");
        portal2=new Texture("sprites/portal2.png");
        portal3=new Texture("sprites/portal3.png");
        portal4=new Texture("sprites/portal4.png");
        portal5=new Texture("sprites/portal5.png");
        portal6=new Texture("sprites/portal6.png");
        portal7=new Texture("sprites/portal7.png");
        portal8=new Texture("sprites/portal8.png");
        portal9=new Texture("sprites/portal9.png");
        portal10=new Texture("sprites/portal10.png");

        atlas=new TextureAtlas("sprites/gracz.pack");
        atlas2=new TextureAtlas("sprites/gracz_kop.pack");
        atlas3=new TextureAtlas("sprites/gracz_upadajacy.pack");
        atlas4=new TextureAtlas("sprites/gracz_koniec.pack");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);

        render=hp1;
        render2=portal1;

        if(WorldContactListener.zycie==1)
            render=hp2;
        if(WorldContactListener.zycie==2)
            render=hp3;
        if(WorldContactListener.zycie==3)
            render=hp4;
        if(WorldContactListener.zycie==4)
            render=hp5;
        if(WorldContactListener.zycie==5)
            render=hp6;
        if(WorldContactListener.zycie==6)
            render=hp7;
        if(WorldContactListener.zycie==7)
            render=hp8;

        if(timer2>1&&timer2>10&&timer2>20){
            render2=portal2;
        }
        if(timer2>2&&timer2>11&&timer2>20.3f){
            render2=portal3;
        }
        if(timer2>3&&timer2>12&&timer2>20.6f){
            render2=portal4;
        }
        if(timer2>4&&timer2>13&&timer2>20.9f){
            render2=portal5;
        }
        if(timer2>5&&timer2>14&&timer2>21.2f){
            render2=portal6;
        }

        if(timer2>6&&timer2>15&&timer2>21.5f){
            render2=portal7;
        }

        if(timer2>7&&timer2>16&&timer2>21.8f){
            render2=portal8;
        }
        if(timer2>8&&timer2>17&&timer2>22.1f){
            render2=portal9;
        }

        if(timer2>9&&timer2>18&&timer>20&&timer2>22.4f){
            render2=portal10;
        }

        game.batch.setProjectionMatrix(gamecam.combined);
        renderer.render();
        rayHandler.render();
        game.batch.begin();
        game.batch.draw(render2,48,0.5f,1,6);
        for (Enemy enemy :brick.getP1())
            enemy.draw(game.batch);

        if (player.b2body.getPosition().x<48.5f&&player.b2body.getPosition().y < -1) {
            game.setScreen(new Koniec((MyGdxGame) game));
            game.getScreen().dispose();
        }

        player.draw(game.batch);

        game.batch.draw(render,player.b2body.getPosition().x-0.25f,player.b2body.getPosition().y+0.7f,0.5f,0.1f);
        game.batch.end();

        hud.stage.draw();

        b2dr.render(world, gamecam.combined);

    }

    public void update(float dt){
        handleInput(dt);

        timer+=dt;
        timer2+=dt;


        rayHandler.setAmbientLight(1f);

         if(timer>10)  rayHandler.setAmbientLight(.9f);
         if(timer>20)  rayHandler.setAmbientLight(.8f);
         if(timer>30)  rayHandler.setAmbientLight(.7f);
         if(timer>40)  rayHandler.setAmbientLight(.6f);
         if(timer>50)  rayHandler.setAmbientLight(.5f);
         if(timer>60)  rayHandler.setAmbientLight(.4f);
         if(timer>70)  rayHandler.setAmbientLight(.3f);
         if(timer>80)  rayHandler.setAmbientLight(.2f);
         if(timer>90)  rayHandler.setAmbientLight(.1f);
         if(timer>100)  rayHandler.setAmbientLight(.1f);

        if(timer>110)  rayHandler.setAmbientLight(.1f);
        if(timer>120)  rayHandler.setAmbientLight(.2f);
        if(timer>130)  rayHandler.setAmbientLight(.3f);
        if(timer>140)  rayHandler.setAmbientLight(.4f);
        if(timer>150)  rayHandler.setAmbientLight(.5f);
        if(timer>160)  rayHandler.setAmbientLight(.6f);
        if(timer>170)  rayHandler.setAmbientLight(.7f);
        if(timer>180)  rayHandler.setAmbientLight(.8f);
        if(timer>190)  rayHandler.setAmbientLight(.9f);
        if(timer>200)  rayHandler.setAmbientLight(1f);

if(player.b2body.getPosition().x>48.5f){

    timer3+=dt;
    player.b2body.setActive(false);
  Player.currentState=Player.State.DEAD;

    if(timer3>3){
        game.setScreen(new PunktyScreen((MyGdxGame) game));
    }
}


        gamecam.update();
        hud.update(dt);
        renderer.setView(gamecam);
        world.step(1 / 30f, 6, 2);
        rayHandler.update();
        gamecam.position.x = player.b2body.getPosition().x;
        gamecam.position.y = player.b2body.getPosition().y;
        player.update(dt);

        for(Enemy enemy : brick.getP1()) {
            enemy.update(dt);
            if (enemy.getX() < player.getX() + 224 / 100) {
                enemy.b2body.setActive(true);
           }
        }
    }

    public void handleInput(float dt){

        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            gamecam.zoom+=0.01f;

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            gamecam.zoom-=0.01f;

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.sprite.translateX(1f);
            player.sprite.setColor(Color.BLUE);
            player.jump();
    }

         if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
}

    public TiledMap getMap() {
        return map;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {
        game.setPaused(true);
    }

    @Override
    public void resume() {
        game.setPaused(true);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        rayHandler.dispose();
        game.dispose();

    }
}
