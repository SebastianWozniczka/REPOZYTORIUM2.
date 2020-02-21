package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;

import screen.Play;
import tools.WorldContactListener;

public class Przeciwnik1 extends Enemy {

    private float stateTimer;
    private boolean runningRight;
    public static float timer2;
    private Animation walkAnimation,kick,jump;
    private TextureRegion stand,upadek;
    private Texture render,hp1,hp2,hp3,hp4,hp5,hp6,hp7,hp8;



    private Array<TextureRegion> frames;
    public static BodyDef bdef;
    private Array<Przeciwnik1> p1;
    public static boolean koniec;

    private State currentState;
    private State previousState;
    private enum State {FALLING, JUMPING, STANDING, RUNNING, KICK}


    ;

    public Przeciwnik1(Play screen, float x, float y) {
        super(screen, x, y);




        hp1=new Texture("menu/hp1.png");
            render=hp1;










        p1=new Array<Przeciwnik1>();
        frames=new Array<TextureRegion>();
        koniec=false;
        stand=new TextureRegion(screen.getAtlas().findRegion("gracz_stojacy"),0,0,128,128);
        upadek=new TextureRegion(screen.getAtlas3().findRegion("gracz_upadajacy"),0,0,128,128);
        for(int i=1;i<3;i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("gracz_chod"), i*128, 0, 128, 128));
            walkAnimation = new Animation(0.4f, frames);
        }
        frames.clear();

        for(int i=1;i<5;i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("gracz_chod"), i*128, 0, 128, 128));
            kick = new Animation(0.8f, frames);
        }
        frames.clear();
        for(int i=1;i<3;i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("gracz_chod"), i*128, 0, 128, 128));
            jump = new Animation(0.4f, frames);
        }

        frames.clear();


        stateTimer=0;
        setBounds(getX(),getY(),128/100f,128/100f);
        setRegion(stand);

        currentState = State.STANDING;
        previousState =State.STANDING;
        stateTimer = 0;
        runningRight = true;


    }

    public Array<Przeciwnik1> getP1() {
        return p1;
    }

    public Array<Enemy> getEnemies() {
        Array<Enemy> enemies = new Array<Enemy>();
        enemies.addAll(p1);
        return enemies;
    }

    @Override
    public void update(float dt){
        stateTimer+=dt;
        timer2+=dt;
        setPosition(b2body.getPosition().x-getWidth()/2f,b2body.getPosition().y-getHeight()/2f);


           setRegion(getFrame(dt));

        velocity = new Vector2(-1, 0);
        b2body.setLinearVelocity(velocity);
        if(b2body.getPosition().x<Player.b2body.getPosition().x)
            b2body.setLinearVelocity(1, 0);





        }





    public TextureRegion getFrame(float dt) {

        currentState = getState();
        TextureRegion region;
        switch(currentState){

            case KICK:
                region = (TextureRegion) kick.getKeyFrame(stateTimer);
                break;
            case JUMPING:
                region = (TextureRegion) jump.getKeyFrame(stateTimer,false);
                break;
            case RUNNING:
                region = (TextureRegion) walkAnimation.getKeyFrame(stateTimer,true);
                break;
            case FALLING:
                region=upadek;
                break;
            case STANDING:
            default:
                region = stand;
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

    private State getState() {


        if((b2body.getLinearVelocity().y > 0 && currentState == State.JUMPING) || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;

        else if(b2body.getLinearVelocity().y < 0)
            return State.FALLING;

        else if(b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
 else if(WorldContactListener.kop==1) {
     if(Player.currentState!= Player.State.KICK)
     Player.b2body.applyLinearImpulse(new Vector2(-.2f,.3f),Player.b2body.getWorldCenter(),true);
            return State.KICK;
        }

        else
            return State.STANDING;
    }



    protected void defineEnemy() {
        bdef=new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type=BodyDef.BodyType.DynamicBody;
        b2body=world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(0.3f, 0.45f);
        fdef.shape=shape;
        b2body.createFixture(fdef).setUserData("p1");
        b2body.setActive(false);
        fdef.restitution=0.9f;

    }

    @Override
    public void draw(Batch batch) {

           batch.draw(render,b2body.getPosition().x-0.25f,b2body.getPosition().y+0.7f,0.5f,0.1f);

        super.draw(batch);
    }

    @Override
    public void hitOnHead(Player mario) {


    }

    @Override
    public void hitByEnemy(Enemy enemy) {


    }



}



