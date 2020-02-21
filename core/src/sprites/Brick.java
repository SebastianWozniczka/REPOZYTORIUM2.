package sprites;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import screen.Play;
import screen.Play2;

public class Brick extends InteractiveTileObject {
    private final int BLANK_COIN = 1;
    private static TiledMapTileSet tileSet;
    Array<Przeciwnik1> p1;

    public Brick(Play2 play2, Rectangle rect2) {
        super(play2,rect2);
    }


    public Array<Przeciwnik1> getP1() {
        return p1;
    }

    public Brick(Play screen, Rectangle bounds) {
        super(screen, bounds);

        fixture.setUserData("ziemia");

       p1=new Array<Przeciwnik1>();
        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            p1.add(new Przeciwnik1(screen, rect.getX() /100f, rect.getY() / 100f));
        }

    }

    @Override
    public void onHeadHit() {

    }


}