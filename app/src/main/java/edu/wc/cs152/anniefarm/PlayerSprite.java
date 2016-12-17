package edu.wc.cs152.anniefarm;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by shaffer on 4/27/16.
 */
public class PlayerSprite extends PhysicalSprite {
    private Collision lastPlatform;
    private int health;
    private long lastHit;
    private final int FULL_HEALTH = 100;
    private boolean removedFrame = true;
    ArrayList<BitmapSequence> addBlink = new ArrayList<BitmapSequence>();
    BitmapSequence goRight = new BitmapSequence();
    BitmapSequence goLeft = new BitmapSequence();
    BitmapSequence goJumpRight = new BitmapSequence();
    BitmapSequence goJumpLeft = new BitmapSequence();
    private long score;
    private int numJump;
    private final int JUMP = 400;
    private final int MAX_X = 200;
    private final int MIN_X = -200;
    private final int MIN_Y = -600;

    public PlayerSprite(Vec2d v, long score) {
        super(v);
        goRight.addImage(BitmapRepo.getInstance().getImage(R.drawable.anniestand), 0.1);
        goRight.addImage(BitmapRepo.getInstance().getImage(R.drawable.annierun), 0.02);
        goLeft.addImage(BitmapRepo.getInstance().getImage(R.drawable.anniestandl), 0.1);
        goLeft.addImage(BitmapRepo.getInstance().getImage(R.drawable.annierunl), 0.02);
        goJumpRight.addImage(BitmapRepo.getInstance().getImage(R.drawable.anniejump), .1);
        goJumpRight.addImage(BitmapRepo.getInstance().getImage(R.drawable.anniejump), .1);
        goJumpLeft.addImage(BitmapRepo.getInstance().getImage(R.drawable.anniejumpl), .1);
        goJumpLeft.addImage(BitmapRepo.getInstance().getImage(R.drawable.anniejumpl), .1);
        setBitmaps(goRight);
        lastHit = System.currentTimeMillis() - 5000;
        health = FULL_HEALTH;
        numJump=0;
        this.score = score;
        setAcceleration(new Vec2d(0,600));
    }

    @Override
    public boolean isActive() {
        return true;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public void loseHealth(int dmg) {
        health -= dmg;
        numJump=0;
        Log.d("game" , "Player lost health.  " + health + " remaining.");
        lastHit = System.currentTimeMillis();
    }

    public void addScore(long add) {
        score+=add;
    }

    public void setScore(long score) {
        this.score= score;
    }

    public int getHealth() {
        return health;
    }

    public void cure(int heal) {
        health += heal;
    }

    @Override
    public void tick(double dt) {
        if (isDead()) { return; }
        if (getBoundingBox().top > 1000) {
            health = 0;
        }
        float difX = 0;
        float difY = 0;
        MotionEvent e = TouchEventQueue.getInstance().dequeue();
        if (e != null && e.getAction() == MotionEvent.ACTION_DOWN) {
            difX = e.getX() - World.X_OFF;
            difY = e.getY() - position.getY() + World.getInstance().getViewRect().top ;

            if (difY < 0) {
                jump();
            }

            if (difX > 0) {  //right
                setVelocity(new Vec2d(MAX_X, getVelocity().getY()));
                if (getVelocity().getY() < 0) {
                    setBitmaps(goJumpRight);
                } else {
                    setBitmaps(goRight);
                }
            } else if (difX < 0) {  //left
                setVelocity(new Vec2d(MIN_X,getVelocity().getY()));
                if (getVelocity().getY() < 0) {
                    setBitmaps(goJumpLeft);
                } else {
                    setBitmaps(goLeft);
                }
            }
        }
        if (lastHit+3000 > System.currentTimeMillis()) {
            getBitmaps().addBlink();
            if (addBlink.indexOf(getBitmaps()) < 0) {
                addBlink.add(getBitmaps());
                removedFrame = false;
            }
        } else if (!removedFrame) {
            for (BitmapSequence bms:addBlink) {
                bms.removeBlink();
            }
            removedFrame = true;
        }
        super.tick(dt);
    }

    @Override
    public void draw(Canvas c) {
        super.draw(c);
        if (health<FULL_HEALTH) {
            Paint p = new Paint();
            p.setARGB(255,0,0,0);
            Paint p2 = new Paint();
            p2.setARGB(255,150,150,255);
            c.drawRect(getBoundingBox().left, getBoundingBox().top - 20, getBoundingBox().right, getBoundingBox().top, p);
            c.drawRect(getBoundingBox().left, getBoundingBox().top - 20, getBoundingBox().left - (1.0f*health/FULL_HEALTH)*(getBoundingBox().left-getBoundingBox().right), getBoundingBox().top, p2);
        }
    }

    public void addJump() {
        numJump++;
    }

    public long getScore() {
        return score;
    }

    public void jump() {
        if (getDistance(lastPlatform.getOtherSprite())< 40) {
            setVelocity(getVelocity().add(new Vec2d(0,MIN_Y)));
            setBitmaps(goJumpRight);
        }
    }

    public void resolve(Collision collision, Sprite other) {
        float perX;
        float perY;
        RectF intersection;

        if (other.getClass()==PlatformSprite.class) {
            if (getVelocity().getY() > 0) {
                lastPlatform = collision;
                if (getVelocity().getX() > 0 ) {
                    setBitmaps(goRight);
                } else if (getVelocity().getX() < 0) {
                    setBitmaps(goLeft);
                }
                intersection = intersectionWith(other);
                perX = intersection.right / getBoundingBox().right -.5f;
                perY = intersection.bottom / getBoundingBox().bottom -.5f;
                if (other.getBoundingBox().bottom < getBoundingBox().bottom) {
                    if (getBoundingBox().top < other.getBoundingBox().bottom)
                    { setPosition(new Vec2d(getPosition().getX()-perX*50,getPosition().getY()-perY*50)); }
                    setVelocity(new Vec2d(0,0));
                    numJump = 0;
                } else {
                    setVelocity(new Vec2d(getVelocity().getX(), 0));
                    setPosition(new Vec2d(getPosition().getX(), other.getPosition().getY() - getBoundingBox().height() - 1));
                    numJump = 0;
                }
            } else {
                setVelocity(new Vec2d(getVelocity().getX(),0));
            }
        } else if (other.getClass().getSuperclass()==MonsterSprite.class && lastHit+3000 < System.currentTimeMillis()) {
            if (getBoundingBox().bottom < 1.1f * other.getBoundingBox().top) {
                ((MonsterSprite) other).takeDamage(1);
                numJump++;
                score+=JUMP*numJump;
                setVelocity(new Vec2d(getVelocity().getX(), -Math.abs(getVelocity().getY())-100));
            } else {
                loseHealth(25);
            }
        }
    }

}
