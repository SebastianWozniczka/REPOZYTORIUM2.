package sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import screen.Play;
import tools.WorldContactListener;

public abstract class Enemy extends Sprite {
    protected World world;
    protected Play screen;
    public Body b2body;
    public Vector2 velocity;
    public Player player;
    public Enemy(Play screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        //
        defineEnemy();



       b2body.setActive(false);


       if(WorldContactListener.odwrocenie==1){
           reverseVelocity(true,false);
       }




    }

    protected abstract void defineEnemy();
    public abstract void update(float dt);
    public abstract void hitOnHead(Player mario);
    public abstract void hitByEnemy(Enemy enemy);
    protected void reverseVelocity(boolean x, boolean y){
        if(x)

            velocity.x = -velocity.x;
        if(y)
            velocity.y = -velocity.y;
    }


}

