package edu.wc.cs152.anniefarm;

import android.util.Log;

/**
 * Created by water on 12/15/2016.
 */

public class CDCow extends MonsterSprite {
    private BitmapSequence goRight = new BitmapSequence();
    private BitmapSequence goLeft = new BitmapSequence();
    private Collision lastPlatform;

    public CDCow(Vec2d v) {
        super(v);
        goRight.addImage(BitmapRepo.getInstance().getImage(R.drawable.cdcow), 1);
        goLeft.addImage(BitmapRepo.getInstance().getImage(R.drawable.cdcowl), 1);
        setVelocity(new Vec2d(200, 0));
    }

    @Override
    public boolean isAware(Sprite s) {
        return false;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    public void tick(double dt) {
        if (getVelocity().getX() > 0) {
            setBitmaps(goRight);
        } else if (getVelocity().getX() < 0) {
            setBitmaps(goLeft);
        }
        act();
        super.tick(dt);
    }

    public void act() {
        if (isDead()) {
            return;
        }
    }

    public void resolve(Collision collision, Sprite other) {
        if (other.getClass() == PlatformSprite.class) {
            if (getVelocity().getY() > 0) {
                lastPlatform = collision;
                if (getVelocity().getX() >= 0) {
                    setBitmaps(goRight);
                } else if (getVelocity().getX() < 0) {
                    setBitmaps(goLeft);
                }
                if (other.getBoundingBox().bottom < getBoundingBox().bottom) {
                    setVelocity(new Vec2d(-getVelocity().getX(), -getVelocity().getY()));
                } else {
                    setVelocity(new Vec2d(getVelocity().getX(), 0));
                    setPosition(new Vec2d(getPosition().getX(), other.getPosition().getY() - getBoundingBox().height() - 1));
                }
            } else if (other.getBoundingBox().bottom < getBoundingBox().bottom) {
                setVelocity(new Vec2d(-getVelocity().getX(), -getVelocity().getY()));
            } else {
                setVelocity(new Vec2d(getVelocity().getX(), 0));
            }
        } else if (other.getClass().getSuperclass() == MonsterSprite.class) {
            setVelocity(new Vec2d(-getVelocity().getX(), getVelocity().getY()));
        } else if (other.getClass() == PlayerSprite.class) {
            if (other.getBoundingBox().bottom < 1.1f * getBoundingBox().top) {
                takeDamage(1);
                ((PlayerSprite) other).addJump();
            } else {
                ((PlayerSprite) other).loseHealth(25);
            }
        }
    }
}
