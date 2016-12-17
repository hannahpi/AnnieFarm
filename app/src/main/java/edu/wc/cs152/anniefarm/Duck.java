package edu.wc.cs152.anniefarm;

/**
 * Created by water on 12/16/2016.
 */

public class Duck extends MonsterSprite {
    private BitmapSequence goRight = new BitmapSequence();
    private BitmapSequence goLeft = new BitmapSequence();
    private Collision lastPlatform;

    public Duck(Vec2d v) {
        super(v);
        goRight.addImage(BitmapRepo.getInstance().getImage(R.drawable.duck), 1);
        goLeft.addImage(BitmapRepo.getInstance().getImage(R.drawable.duckl), 1);
        setBitmaps(goRight);
        setVelocity(new Vec2d(200, 0));
        setHealth(2);
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
        if (getVelocity().getX() >= 0) {
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
                if (getBoundingBox().left <= other.getBoundingBox().left && getVelocity().getX()<0 || getBoundingBox().right >= other.getBoundingBox().right && getVelocity().getX()>0) {
                    setVelocity(new Vec2d(-getVelocity().getX(), getVelocity().getY()));
                }
                if (getVelocity().getX() > 0) {
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
