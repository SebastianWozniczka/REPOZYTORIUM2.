package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

import scenes.Hud;
import tools.WorldContactListener;

class PunktyScreen implements Screen {
    private MyGdxGame game;
    private float timer,timer2;
    private int i;
    private Texture[] punkty;
    private Texture render;

            public PunktyScreen(MyGdxGame game){
                this.game=game;
                timer=0;
                timer2=0;
                init();
            }

            private void init(){
                punkty=new Texture[15];
                for( i=0;i<15;i++) {
                    punkty[0] = new Texture("menu/punkty1.png");
                    punkty[1] = new Texture("menu/punkty2.png");
                    punkty[2] = new Texture("menu/punkty3.png");
                    punkty[3] = new Texture("menu/punkty4.png");
                    punkty[4] = new Texture("menu/punkty5.png");
                    punkty[5] = new Texture("menu/punkty6.png");
                    punkty[6] = new Texture("menu/punkty7.png");
                    punkty[7] = new Texture("menu/punkty8.png");
                    punkty[8] = new Texture("menu/punkty9.png");
                    punkty[9] = new Texture("menu/punkty10.png");
                    punkty[10] = new Texture("menu/punkty11.png");
                    punkty[11] = new Texture("menu/punkty12.png");
                    punkty[12] = new Texture("menu/punkty13.png");
                    punkty[13] = new Texture("menu/punkty14.png");
                    punkty[14] = new Texture("menu/punkty15.png");
                }


            }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Hud.addScore(300);
        timer+=delta;
        timer2+=delta;

        if(timer2>5){
            game.setScreen(new Ulepszenia((MyGdxGame) game));
        }

        if(Hud.worldTimer>270){
            if(timer>0.2f){
                render=punkty[0];
            }
            if(timer>0.4f){
                render=punkty[1];
            }
            if(timer>0.6f){
                render=punkty[2];
            }
            if(timer>0.8f){
                render=punkty[3];
            }
            if(timer>1){
                render=punkty[4];
            }
            if(timer>1.2f){
                render=punkty[5];
            }
            if(timer>1.4f){
                render=punkty[6];
            }
            if(timer>1.6f){
                render=punkty[7];
            }
            if(timer>1.8f){
                render=punkty[8];
            }
            if(timer>2){
                render=punkty[9];
            }
            if(timer>2.2f){
                render=punkty[10];
            }
            if(timer>2.4f){
                render=punkty[11];
            }
            if(timer>2.6f){
                render=punkty[12];
            }
            if(timer>2.8f){
                render=punkty[13];
            }
            if(timer>3){
                render=punkty[14];
            }

        }
        if(Hud.worldTimer<270&&Hud.worldTimer>250){
            if(timer>0.2f){
                render=punkty[0];
            }
            if(timer>0.4f){
                render=punkty[1];
            }
            if(timer>0.6f){
                render=punkty[2];
            }
            if(timer>0.8f){
                render=punkty[3];
            }
            if(timer>1){
                render=punkty[4];
            }
            if(timer>1.2f){
                render=punkty[5];
            }
            if(timer>1.4f){
                render=punkty[6];
            }
            if(timer>1.6f){
                render=punkty[7];
            }
            if(timer>1.8f){
                render=punkty[8];
            }
            if(timer>2){
                render=punkty[9];
            }
        }

        if(Hud.worldTimer<250|| WorldContactListener.zycie==8){
            if(timer>0.2f){
                render=punkty[0];
            }
            if(timer>0.4f){
                render=punkty[1];
            }
            if(timer>0.6f){
                render=punkty[2];
            }
            if(timer>0.8f){
                render=punkty[3];
            }
            if(timer>1){
                render=punkty[4];
            }
        }

        game.batch.begin();
        game.batch.draw(render,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch.end();

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
        game.dispose();
    }
}
