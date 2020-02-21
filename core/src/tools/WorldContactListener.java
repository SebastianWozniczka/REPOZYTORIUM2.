package tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;


public class WorldContactListener implements ContactListener {


    public static int zycie=0;
    public static int atak=0;
    public static int kop;
    public static int odwrocenie;
    @Override
    public void beginContact(Contact contact) {
        kop=0;
        odwrocenie=0;

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if ( fixA.getUserData() == "p1"&&fixB.getUserData()=="p1") {
            odwrocenie++;
        }


        if ( fixA.getUserData() == "gracz"&&fixB.getUserData()=="p1") {
        Gdx.app.log("dobrze","jest");
           atak++;
            kop++;
            zycie++;

        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
