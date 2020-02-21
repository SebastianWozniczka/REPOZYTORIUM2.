package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

import screen.MenuMisji;
import screen.Misje;
import screen.Play;
import screen.Play2;
import screen.Ulepszenia;
import tools.WorldContactListener;

public class Player extends Sprite  {



    public static enum State {FALLING, JUMPING, STANDING, RUNNING, GROWING, DEAD,KICK}

    ;
    public static State currentState;
    public State previousState;

    public World world;
    public static Body b2body;
    public static BodyDef bdef;
    private Play screen;
    private Play2 screen2;
    public Sprite sprite;
    private MyGdxGame game;


    public void setRunningRight(boolean runningRight) {
        this.runningRight = runningRight;
    }

    public boolean isRunningRight() {
        return runningRight;
    }

    private TextureRegion graczStand,graczStandP;
    private TextureRegion Kick,upadek,upadek2,render;
    private TextureAtlas run2,jump2,fall2;

    private Animation graczRun;
    private Animation graczJump;
    private Animation graczRun2;
    private Animation graczJump2;
    private Animation graczKick;
    private Animation graczKoniec;


    private float stateTimer;
    public boolean runningRight;


    public Player(World world, Play screen) {
        this.world = world;
        this.screen = screen;

        run2=new TextureAtlas("sprites/gracz_pistolet_chod.pack");
        jump2=new TextureAtlas("sprites/gracz_pistolet_skok.pack");
        fall2=new TextureAtlas("sprites/gracz_pistolet_upadajacy.pack");

        Kick=new TextureRegion(screen.getAtlas2().findRegion("gracz_kop"),0,0,128,128);
        graczStand=new TextureRegion(screen.getAtlas().findRegion("gracz_stojacy"),0,0,128,128);
        graczStandP=new TextureRegion(run2.findRegion("gracz_pistolet_chod"),0,0,128,128);
        upadek=new TextureRegion(screen.getAtlas3().findRegion("gracz_upadajacy"),0,0,128,128);
        upadek2=new TextureRegion(fall2.findRegion("gracz_pistolet_upadajacy"),0,0,128,128);
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        //get run animation frames and add them to marioRun Animation
        for (int i = 1; i < 3; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("gracz_skok"), i * 128, 0, 128, 128));
        graczJump = new Animation(0.1f, frames);

        frames.clear();

        for (int i = 1; i < 5; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("gracz_chod"), i * 128, 0, 128, 128));
        graczRun = new Animation(0.1f, frames);

        frames.clear();

        for (int i = 1; i < 5; i++)
            frames.add(new TextureRegion(screen.getAtlas2().findRegion("gracz_kop"), i * 128, 0, 128, 128));
       graczKick = new Animation(0.3f, frames);

       frames.clear();

        for (int i = 1; i < 5; i++)
            frames.add(new TextureRegion(screen.getAtlas4().findRegion("gracz_koniec"), i * 128, 0, 128, 128));
        graczKoniec = new Animation(0.1f, frames);



        frames.clear();

        for (int i = 1; i < 3; i++)
            frames.add(new TextureRegion(jump2.findRegion("gracz_pistolet_skok"), i * 128, 0, 128, 128));
        graczJump2 = new Animation(0.1f, frames);
        frames.clear();



        for (int i = 1; i < 5; i++)
            frames.add(new TextureRegion(run2.findRegion("gracz_pistolet_chod"), i * 128, 0, 128, 128));
        graczRun2 = new Animation(0.1f, frames);
        frames.clear();


        sprite=new Sprite(graczStand);
        sprite.rotate(30);
        setBounds(0, 0, 128 / 100f, 128 / 100f);
      if(Ulepszenia.misje==Misje.m1)
          render=graczStand;
        if(Ulepszenia.misje==Misje.m2)
            render=graczStandP;
            setRegion(render);

        definePlayer();

    }



    public void definePlayer() {
        bdef = new BodyDef();
        bdef.position.set(32 / 100f, 32 / 100f);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.3f, 0.45f);

        fdef.shape = shape;

        b2body.createFixture(fdef).setUserData("gracz");

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / 10f, 6 / 10f), new Vector2(2 / 10f, 6 / 10f));

        fdef.shape = head;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("glowa");
    }

    public void jump() {
        if (currentState != State.JUMPING) {
            b2body.applyLinearImpulse(new Vector2(0, 7f), b2body.getWorldCenter(), true);

            currentState = State.JUMPING;

        }

    }


    public void update(float dt) {
    setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        if(Ulepszenia.misje==Misje.m1)
    setRegion(getFrame(dt));
        if(Ulepszenia.misje==Misje.m2)
            setRegion(getFrame2(dt));
    sprite.setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);

}





    public TextureRegion getFrame(Float dt){
        currentState = getState();

        TextureRegion region;


        switch(currentState){

            case DEAD:
                b2body.setActive(false);
                region= (TextureRegion) graczKoniec.getKeyFrame(stateTimer,false);
                break;

            case KICK:
               region = (TextureRegion) graczKick.getKeyFrame(stateTimer,false);
                break;
            case JUMPING:
                region = (TextureRegion) graczJump.getKeyFrame(stateTimer,false);
                break;
            case RUNNING:
                region = (TextureRegion) graczRun.getKeyFrame(stateTimer,true);
                break;
            case FALLING:
                region=upadek;
                break;


            case STANDING:
            default:
                region = graczStand;

                break;
        }

        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }

        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;

    }

    public TextureRegion getFrame2(Float dt){
        currentState = getState();

        TextureRegion region;


        switch(currentState){

            case DEAD:
                b2body.setActive(false);
                region= (TextureRegion) graczKoniec.getKeyFrame(stateTimer,false);
                break;

            case KICK:
                region = (TextureRegion) graczKick.getKeyFrame(stateTimer,false);
                break;
            case JUMPING:
                region = (TextureRegion) graczJump2.getKeyFrame(stateTimer,false);
                break;
            case RUNNING:
                region = (TextureRegion) graczRun2.getKeyFrame(stateTimer,true);
                break;
            case FALLING:
                region=upadek2;
                break;


            case STANDING:
            default:
                region = graczStand;

                break;
        }

        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }

        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;

    }

    private State getState(){
        if(currentState==State.STANDING&& Gdx.input.isKeyJustPressed(Input.Keys.A))
            return State.KICK;

        if(WorldContactListener.zycie==8)
            return State.DEAD;


        if((b2body.getLinearVelocity().y > 0 && currentState == State.JUMPING) || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;

        else if(b2body.getLinearVelocity().y < 0)
            return State.FALLING;

        else if(b2body.getLinearVelocity().x != 0)
            return State.RUNNING;




        else
            return State.STANDING;
    }




}




