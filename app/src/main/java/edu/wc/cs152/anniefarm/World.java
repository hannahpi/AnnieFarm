package edu.wc.cs152.anniefarm;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by shaffer on 4/27/16.
 */
public class World {
    private static World defaultInstance;
    public final static int X_OFF = 200;
    public final static float TOP_Y = .25f;
    public final static float BOTTOM_Y = .10f;
    private GameSurfaceView view;
    private RectF viewRect;
    private Bitmap background;
    private ArrayList<Sprite> sprites;
    private int lives;
    private int lvl;
    private float cameraHeight;

    public World(GameSurfaceView view) {
        this.view = view;
        viewRect = new RectF(0,0,1000,1000);
        lvl=1;
        lives = 2;
        sprites = new ArrayList<Sprite>();
        loadLevel(lvl,0);
        cameraHeight = 0;

        defaultInstance = this;
    }

    public void loadLevel(int lvl, long score) {
        switch (lvl) {
            case 0:
                background = BitmapRepo.getInstance().getImage(R.drawable.backgameover);
                if (!sprites.isEmpty())
                    sprites.clear();
                sprites.add(new PlayerSprite(new Vec2d(0,400),0));
                sprites.add(new PlatformSprite(new Vec2d(0, 700)));
                sprites.add(new FinishSprite(new Vec2d(500,500)));
                break;
            case 1:
                if (!sprites.isEmpty())
                    sprites.clear();
                background = BitmapRepo.getInstance().getImage(R.drawable.backfarms);
                sprites.add(new PlayerSprite(new Vec2d(0,400),score));
                sprites.add(new PlatformSprite(new Vec2d(0, 700)));
                sprites.add(new PlatformSprite(new Vec2d(0, 100)));
                sprites.add(new PlatformSprite(new Vec2d(700, 400)));
                sprites.add(new PlatformSprite(new Vec2d(300, 700)));
                sprites.add(new PlatformSprite(new Vec2d(1000,700)));
                sprites.add(new PlatformSprite(new Vec2d(1350,600)));
                sprites.add(new PlatformSprite(new Vec2d(1000,300)));
                sprites.add(new PlatformSprite(new Vec2d(1350,250)));
                sprites.add(new PlatformSprite(new Vec2d(1700,500)));
                sprites.add(new PlatformSprite(new Vec2d(2100,650)));
                sprites.add(new PlatformSprite(new Vec2d(2400,650)));
                sprites.add(new PlatformSprite(new Vec2d(2800,750)));
                sprites.add(new PlatformSprite(new Vec2d(3200,850)));
                sprites.add(new PlatformSprite(new Vec2d(3600,750)));
                sprites.add(new CDCow(new Vec2d(350,0)));
                sprites.add(new CDCow(new Vec2d(250,-300)));
                sprites.add(new CDCow(new Vec2d(1200,0)));
                sprites.add(new CDCow(new Vec2d(1800,250)));
                sprites.add(new CDCow(new Vec2d(2600,450)));
                sprites.add(new CDCow(new Vec2d(3000,550)));
                sprites.add(new CDCow(new Vec2d(3800,550)));
                sprites.add(new FinishSprite(new Vec2d(3800,550)));
                break;
            case 2:
                if (!sprites.isEmpty())
                    sprites.clear();
                background = BitmapRepo.getInstance().getImage(R.drawable.backbakerys);
                sprites.add(new PlayerSprite(new Vec2d(0,300),score));
                sprites.add(new PlatformSprite(new Vec2d(0, 700)));
                sprites.add(new PlatformSprite(new Vec2d(400, 700)));
                sprites.add(new PlatformSprite(new Vec2d(800, 700)));
                sprites.add(new PlatformSprite(new Vec2d(1200, 700)));
                sprites.add(new PlatformSprite(new Vec2d(1600, 700)));
                sprites.add(new PlatformSprite(new Vec2d(2000, 700)));
                sprites.add(new PlatformSprite(new Vec2d(2400, 700)));
                sprites.add(new PlatformSprite(new Vec2d(2800, 700)));
                sprites.add(new PlatformSprite(new Vec2d(3200, 700)));
                sprites.add(new PlatformSprite(new Vec2d(3600, 700)));
                sprites.add(new PlatformSprite(new Vec2d(4000, 700)));
                sprites.add(new PlatformSprite(new Vec2d(4400, 700)));
                sprites.add(new PlatformSprite(new Vec2d(4800, 700)));
                sprites.add(new PlatformSprite(new Vec2d(5200, 700)));
                sprites.add(new PlatformSprite(new Vec2d(5600, 700)));
                sprites.add(new PlatformSprite(new Vec2d(6000, 700)));
                sprites.add(new PlatformSprite(new Vec2d(6400, 700)));
                sprites.add(new PlatformSprite(new Vec2d(6800, 700)));
                sprites.add(new PlatformSprite(new Vec2d(7200, 700)));
                sprites.add(new Duck(new Vec2d(200, 400)));
                sprites.add(new Duck(new Vec2d(600, 400)));
                sprites.add(new Duck(new Vec2d(1000, 400)));
                sprites.add(new Duck(new Vec2d(1400, 400)));
                sprites.add(new Duck(new Vec2d(1800, 400)));
                sprites.add(new Duck(new Vec2d(2200, 400)));
                sprites.add(new Duck(new Vec2d(2600, 400)));
                sprites.add(new Duck(new Vec2d(3000, 400)));
                sprites.add(new Duck(new Vec2d(3400, 400)));
                sprites.add(new Duck(new Vec2d(3800, 400)));
                sprites.add(new Duck(new Vec2d(4200, 400)));
                sprites.add(new Duck(new Vec2d(4600, 400)));
                sprites.add(new Duck(new Vec2d(5000, 400)));
                sprites.add(new Duck(new Vec2d(5400, 400)));
                sprites.add(new Duck(new Vec2d(5800, 400)));
                sprites.add(new Duck(new Vec2d(6200, 400)));
                sprites.add(new Duck(new Vec2d(6600, 400)));
                sprites.add(new Duck(new Vec2d(7000, 400)));
                sprites.add(new FinishSprite(new Vec2d(7300,500)));
                break;
            case 3:
                if (!sprites.isEmpty())
                    sprites.clear();
                background = BitmapRepo.getInstance().getImage(R.drawable.backgrizzlys);
                sprites.add(new PlayerSprite(new Vec2d(0,0),score));
                PlatformSprite.setSprite(R.drawable.platform2);
                sprites.add(new PlatformSprite(new Vec2d(0, 700)));
                sprites.add(new PlatformSprite(new Vec2d(400, 700)));
                sprites.add(new PlatformSprite(new Vec2d(800, 700)));
                sprites.add(new PlatformSprite(new Vec2d(1200, 700)));
                sprites.add(new PlatformSprite(new Vec2d(1600, 700)));
                sprites.add(new PlatformSprite(new Vec2d(2000, 700)));
                sprites.add(new PlatformSprite(new Vec2d(2400, 700)));
                sprites.add(new PlatformSprite(new Vec2d(2800, 700)));
                sprites.add(new PlatformSprite(new Vec2d(3200, 700)));
                sprites.add(new PlatformSprite(new Vec2d(3600, 600)));
                sprites.add(new PlatformSprite(new Vec2d(4000, 500)));
                sprites.add(new PlatformSprite(new Vec2d(4400, 500)));
                sprites.add(new PlatformSprite(new Vec2d(4800, 400)));
                sprites.add(new PlatformSprite(new Vec2d(5200, 300)));
                sprites.add(new PlatformSprite(new Vec2d(5600, 300)));
                sprites.add(new PlatformSprite(new Vec2d(6000, 200)));
                sprites.add(new PlatformSprite(new Vec2d(6400, 200)));
                sprites.add(new PlatformSprite(new Vec2d(6800, 0)));
                sprites.add(new PlatformSprite(new Vec2d(7200, -200)));
                sprites.add(new PlatformSprite(new Vec2d(7200, 300)));
                sprites.add(new PlatformSprite(new Vec2d(7200, 800)));
                sprites.add(new Bear(new Vec2d(200, 200),(PlayerSprite) sprites.get(0)));
                sprites.add(new CDCow(new Vec2d(500,500)));
                sprites.add(new CDCow(new Vec2d(1700,500)));
                sprites.add(new CDCow(new Vec2d(2100,500)));
                sprites.add(new CDCow(new Vec2d(2500,500)));
                sprites.add(new Duck(new Vec2d(3000,500)));
                sprites.add(new CDCow(new Vec2d(3300,500)));
                sprites.add(new CDCow(new Vec2d(2000,500)));
                sprites.add(new Bear(new Vec2d(7200, -400),(PlayerSprite) sprites.get(0)));
                sprites.add(new FinishSprite(new Vec2d(7200,600)));
                break;
            case 4:
                if (!sprites.isEmpty())
                    sprites.clear();
                lives = 99;
                background = BitmapRepo.getInstance().getImage(R.drawable.backbakerys);
                sprites.add(new PlayerSprite(new Vec2d(0,300),score));
                sprites.add(new PlatformSprite(new Vec2d(0, 700)));
                sprites.add(new PlatformSprite(new Vec2d(400, 700)));
                sprites.add(new PlatformSprite(new Vec2d(800, 700)));
                sprites.add(new PlatformSprite(new Vec2d(1200, 700)));
                sprites.add(new PlatformSprite(new Vec2d(1600, 700)));
                sprites.add(new PlatformSprite(new Vec2d(2000, 700)));
                sprites.add(new PlatformSprite(new Vec2d(2400, 700)));
                sprites.add(new PlatformSprite(new Vec2d(2800, 700)));
                sprites.add(new PlatformSprite(new Vec2d(3200, 700)));
                sprites.add(new PlatformSprite(new Vec2d(3600, 700)));
                sprites.add(new PlatformSprite(new Vec2d(4000, 700)));
                sprites.add(new PlatformSprite(new Vec2d(4400, 700)));
                sprites.add(new PlatformSprite(new Vec2d(4800, 700)));
                sprites.add(new PlatformSprite(new Vec2d(5200, 700)));
                sprites.add(new PlatformSprite(new Vec2d(5600, 700)));
                sprites.add(new PlatformSprite(new Vec2d(6000, 700)));
                sprites.add(new PlatformSprite(new Vec2d(6400, 700)));
                sprites.add(new PlatformSprite(new Vec2d(6800, 700)));
                sprites.add(new PlatformSprite(new Vec2d(7200, 700)));
                break;
        }
    }

    public static World getInstance() {
        return defaultInstance;
    }

    public RectF getViewRect() {
        return viewRect;
    }

    public void draw(Canvas c) {
        // transform coordinates
        if (view.isPaused())
            return;
        if (sprites.get(0).getBoundingBox().top - cameraHeight < TOP_Y * (c.getHeight() + cameraHeight))
            cameraHeight = cameraHeight - 10;
        else if (sprites.get(0).getBoundingBox().bottom > cameraHeight + c.getHeight() - BOTTOM_Y * c.getHeight()  && cameraHeight <= 600)
            cameraHeight = cameraHeight + 10;
        transform(c);

        float x = sprites.get(0).getBoundingBox().left;
        int n = (int) x/background.getWidth();
        // draw background
        viewRect.set(sprites.get(0).getBoundingBox().left - X_OFF,cameraHeight,sprites.get(0).getBoundingBox().left - X_OFF + c.getWidth(),cameraHeight+c.getHeight());

        Paint p = new Paint();
        p.setARGB(255,255,255,255);
        c.drawRect(viewRect,p);
        c.drawBitmap(background, (n-1) * background.getWidth(), 0, null);
        c.drawBitmap(background, n * background.getWidth(), 0, null);
        c.drawBitmap(background, (n+1) * background.getWidth(), 0, null);
        Paint p2 = new Paint();
        p2.setARGB(255,0,0,0);
        p.setTextSize(40.0f);


        //show score and lives
        c.drawRect(viewRect.left + 50.0f, viewRect.top+50.0f, viewRect.left+325.0f, viewRect.top+120.0f,p2 );
        c.drawText(lives + " LIVES", viewRect.left+55.0f, viewRect.top+105.0f, p);
        c.drawRect(viewRect.right - 250.0f, viewRect.top+50.0f, viewRect.right, viewRect.top+120.0f,p2);
        c.drawText(((PlayerSprite) sprites.get(0)).getScore() + "", viewRect.right - 245.0f, viewRect.top+105.0f,p);

        // draw sprites
        for(Sprite s: sprites) {
            if (s != null && s.getBoundingBox().intersect(viewRect))  //slightly off-screen Sprites should still be drawn
                s.draw(c);
        }
    }


    private void transform(Canvas c) {
        Matrix m = new Matrix();
        m.setTranslate(-sprites.get(0).getPosition().getX()+X_OFF, -cameraHeight);
        c.setMatrix(m);
    }

    public void tick(double t) {
        for(Sprite s: sprites)
            s.tick(t);

        resolve_collisions();

        //check player (if they fell no collision would've been generated)
        PlayerSprite ps = (PlayerSprite) sprites.get(0);
        if (ps.getClass() == PlayerSprite.class) { //if player falls
            if (ps.isDead()) {
                dead(ps);
            }
        }
    }

    private void resolve_collisions() {
        ArrayList<Collision> collisions = new ArrayList<>();
        for(int i=0; i < sprites.size()-1; i++)
            for(int j=i+1; j < sprites.size(); j++) {
                Sprite s1 = sprites.get(i);
                Sprite s2 = sprites.get(j);

                if (s1.collidesWith(s2))
                    collisions.add(new Collision(s1, s2));
            }

        for(Collision c: collisions) {
            c.resolve();
            Sprite s1= c.getSprite();
            Sprite s2= c.getOtherSprite();
            if (s1.getClass() == PlayerSprite.class && s2.getClass() == FinishSprite.class) {
                if (lvl==0) {
                    lives = 1;
                    ((PlayerSprite) s1).setScore(-1000);
                }
                lvl++;
                lives++;
                view.pause();
                ((PlayerSprite) s1).addScore(1000);
                loadLevel(lvl, ((PlayerSprite) s1).getScore());
                view.pause();
            }
            if (s1.getClass() == PlayerSprite.class && ((PlayerSprite) s1).isDead()) {
                dead((PlayerSprite) s1);
            } else if (s1.getClass().getSuperclass() == MonsterSprite.class && ((MonsterSprite) s1).isDead()) {
                sprites.add(new DeathSprite(s1.getPosition()));
                sprites.remove(s1);
            } else if (s1.getClass() == DeathSprite.class && ((DeathSprite) s1).isDone()) {
                sprites.remove(s1);
            } else if (s2.getClass() == PlayerSprite.class && ((PlayerSprite) s2).isDead()) {
                sprites.add(new DeathSprite(s2.getPosition()));
                sprites.remove(s2);
            } else if (s2.getClass().getSuperclass() == MonsterSprite.class && ((MonsterSprite) s2).isDead()) {
                sprites.add(new DeathSprite(s2.getPosition()));
                sprites.remove(s2);
            } else if (s2.getClass() == DeathSprite.class && ((DeathSprite) s2).isDone()) {
                sprites.remove(s2);
            }


        }
    }

    private void dead(PlayerSprite ps) {
        sprites.add(new DeathSprite(ps.getPosition()));
        lives--;
        view.pause();
        if (lives==0)
            lvl=0;
        ps.setScore(ps.getScore()/2);
        loadLevel(lvl,ps.getScore());
        view.pause();
    }
}
