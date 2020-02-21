package sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import screen.Play;
import screen.Play2;

public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;
    Play2 play2;
    Rectangle rect2;

    public InteractiveTileObject(Play screen, Rectangle bounds){
        this.world=screen.getWorld();

        this.map=screen.getMap();
        this.bounds=bounds;


        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / 100, (bounds.getY() + bounds.getHeight() / 2) /100);
        body = world.createBody(bdef);
        shape.setAsBox(bounds.getWidth() / 2 /100, bounds.getHeight() / 2 / 100);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }

    public InteractiveTileObject(Play2 play2, Rectangle bounds) {
        this.world=play2.getWorld();
        this.map=play2.getMap();
        this.bounds=bounds;


        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / 100, (bounds.getY() + bounds.getHeight() / 2) /100);
        body = world.createBody(bdef);
        shape.setAsBox(bounds.getWidth() / 2 /100, bounds.getHeight() / 2 / 100);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }


    public abstract void onHeadHit();




    public void setCategoryFilter(short filterBit){
        Filter filter=new Filter();
        filter.categoryBits=filterBit;
        fixture.setFilterData(filter);

    }

    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer=(TiledMapTileLayer) map.getLayers().get(0);
        return layer.getCell((int)(body.getPosition().x*100/16),(int)(body.getPosition().y*100/16));
    }

}