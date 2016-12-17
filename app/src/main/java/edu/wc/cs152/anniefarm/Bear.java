package edu.wc.cs152.anniefarm;

/**
 * Created by water on 12/16/2016.
 */

public class Bear extends MonsterSprite {

    private Collision lastPlatform;
    private PlayerSprite player;

    public Bear(Vec2d v, PlayerSprite p) {
        super(v);
        BitmapSequence bs = new BitmapSequence();
        bs.addImage(BitmapRepo.getInstance().getImage(R.drawable.bear), 1);
        setBitmaps(bs);
        setVelocity(new Vec2d(150, 0));
        player = p;
    }

    @Override
    public boolean isAware(Sprite s) {
        if (getDistance(s) < 500)
            return true;
        else
            return false;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    public void tick(double dt) {
        act();
        super.tick(dt);
    }

    public void act() {
        if (isAware(player)) {
            Vec2d v = drawVectorTo(player);
            float x,y;
            if (v.getX() > 0) {
                x=-100.0f;
            } else if (v.getX()< 0) {
                x=100.0f;
            } else {
                x=0.0f;
            }

            if (v.getY() < 0) {
                y=getVelocity().getY();
            } else {
                if (getDistance(lastPlatform.getOtherSprite())< 40) {
                    y = -250.0f;
                } else {
                    y=0;
                }
            }
            setVelocity(new Vec2d(x,y));
        }

        if (isDead()) {
            return;
        }
    }

    public void resolve(Collision collision, Sprite other) {
        if (other.getClass() == PlatformSprite.class) {
            if (getVelocity().getY() > 0) {
                lastPlatform = collision;
                if (!isAware(player)) {
                    if (getBoundingBox().left <= other.getBoundingBox().left && getVelocity().getX()<0 || getBoundingBox().right >= other.getBoundingBox().right && getVelocity().getX()>0) {
                        setVelocity(new Vec2d(-getVelocity().getX(), getVelocity().getY()));
                    }
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
